package allkindsearch;

import java.util.ArrayList;


import java.util.BitSet;
import java.util.Date;
import java.util.List;
import fenxi.Analyze;
import beans.xmlbean;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

import pinyin.SpellSearch;
/**
 * <p>类名：WendangSearch
 * <p>作用：搜索文档
 * <p>@author :born to try
 */
public class WendangSearch {
	public long timeOfSearch;
	ArrayList<xmlbean> returnResult=new ArrayList<xmlbean>();
	public  ArrayList<xmlbean> wendangsearch(String key,final String kinds) throws IOException {
		 if   (key.getBytes().length   ==   key.length())   { 
             SpellSearch  pinyin=new SpellSearch();
             key=pinyin.Search(key);
           //  System.out.print(key);
     }   
	BooleanQuery multiKeys = new BooleanQuery();
	Date begintime=new Date();
	Analyze.doAnalyze(key);
	String[] keyword = Analyze.str;	
	String tomcat=getPath("webapps");       
    String filepath= tomcat+"\\"+"index";
	File   indexDir = new File(filepath);  
	IndexReader reader = IndexReader.open(indexDir);
	IndexSearcher searcher = new IndexSearcher(reader);
	for(int i=0;i<keyword.length;i++){
		TermQuery luceneQuery1 = new TermQuery(new Term("title",keyword[i]));
		TermQuery luceneQuery2 = new TermQuery(new Term("keywords",keyword[i]));	
		TermQuery luceneQuery3 = new TermQuery(new Term("describe",keyword[i]));
		TermQuery luceneQuery4 = new TermQuery(new Term("kind",keyword[i]));
		TermQuery luceneQuery5 = new TermQuery(new Term("date",keyword[i]));
		TermQuery luceneQuery6 = new TermQuery(new Term("url",keyword[i]));
		TermQuery luceneQuery7 = new TermQuery(new Term("author",keyword[i]));
		TermQuery luceneQuery8 = new TermQuery(new Term("publisher",keyword[i]));
		multiKeys.add(luceneQuery1, BooleanClause.Occur.SHOULD);
		multiKeys.add(luceneQuery2, BooleanClause.Occur.SHOULD);
		multiKeys.add(luceneQuery3, BooleanClause.Occur.SHOULD);
		multiKeys.add(luceneQuery4, BooleanClause.Occur.SHOULD);
		multiKeys.add(luceneQuery5, BooleanClause.Occur.SHOULD);
		multiKeys.add(luceneQuery6, BooleanClause.Occur.SHOULD);
		multiKeys.add(luceneQuery7, BooleanClause.Occur.SHOULD);
		multiKeys.add(luceneQuery8, BooleanClause.Occur.SHOULD);
}
	    Hits hits = null;
	    class wendangFilter extends Filter{ 
		private static final long serialVersionUID = 1L;
		public BitSet bits(IndexReader reader) throws IOException {  
			  BitSet bitSet = new BitSet(reader.maxDoc());
			  if(kinds!="all"){
			  bitSet.set(0, bitSet.size()-1,false);//设置位队列的每一位都问false		  
			  Term term = new Term("kind",kinds);
			  TermDocs termDocs = reader.termDocs(term);//从索引中取出含term的文档
			  while(termDocs.next()){
			  bitSet.set(termDocs.doc(), true);		  
			  }	
			  System.out.print("ohyeah");
			  System.out.print(kinds);
			  }
			  if(kinds.equals("all"))
			  {
				  System.out.print("success");
				  bitSet.set(0, bitSet.size()-1,false);
				  Term term = new Term("kind","doc");
				  TermDocs termDocs = reader.termDocs(term);//从索引中取出含term的文档
				  while(termDocs.next()){
				  bitSet.set(termDocs.doc(), true);
				  }	 
				  Term term1=new Term("kind","ppt");
				  TermDocs termDocs1 = reader.termDocs(term1);//从索引中取出含term的文档
				  while(termDocs1.next()){
				  bitSet.set(termDocs1.doc(), true);
				  }	 
				  Term term2 = new Term("kind","xls");
				  TermDocs termDocs2 = reader.termDocs(term2);//从索引中取出含term的文档
				  while(termDocs2.next()){
				  bitSet.set(termDocs2.doc(), true);
				  }	 
				  Term term3=new Term("kind","pdf");
				  TermDocs termDocs3 = reader.termDocs(term3);//从索引中取出含term的文档
				  while(termDocs3.next()){
				  bitSet.set(termDocs3.doc(), true);
				  }	 
			  }
			  return bitSet;
			 }
	}
try {
	wendangFilter filter = new wendangFilter();
	hits = searcher.search(multiKeys,filter);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	for(int j = 0; j< hits.length(); j++)
	{
		Document document = null;
		try {
			document = hits.doc(j);
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        xmlbean result=new xmlbean();
        String text = document.get("title"); 
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color='red'>", "</font>"); 
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter,new QueryScorer(multiKeys)); 
		highlighter.setTextFragmenter(new SimpleFragmenter(30)); 
		if (text != null) { 
		MMAnalyzer analyzer = new MMAnalyzer();
		TokenStream tokenStream = analyzer.tokenStream("title",new StringReader(text)); 
		String highLightText = highlighter.getBestFragment(tokenStream, text); 
		if(highLightText==null)
		{
			result.setTitle(document.get("title"));
		}else{
		     result.setTitle(highLightText);  
		}
		}
		String text1 = document.get("describe"); 
		SimpleHTMLFormatter simpleHTMLFormatter1 = new SimpleHTMLFormatter("<font color='red'>", "</font>"); 
		Highlighter highlighter1 = new Highlighter(simpleHTMLFormatter1,new QueryScorer(multiKeys)); 
		highlighter1.setTextFragmenter(new SimpleFragmenter(30)); 
		if (text1!= null) { 
		MMAnalyzer analyzer1 = new MMAnalyzer();
		TokenStream tokenStream1 = analyzer1.tokenStream("describe",new StringReader(text1)); 
		String highLightText1 = highlighter1.getBestFragment(tokenStream1, text1); 
		if(highLightText1==null)
		{
			result.setDescribe(document.get("describe"));
		}else{
		     result.setDescribe(highLightText1);  
		}
		}  
		result.setKeywords(document.get("keywords"));
        result.setKind(document.get("kind"));
        result.setDate(document.get("date"));
        result.setAuthor(document.get("author"));
        result.setPublisher(document.get("publisher"));
        result.setUrl(document.get("url"));
    	returnResult.add(result);
        Date endtime=new  Date();
    timeOfSearch=endtime.getTime()-begintime.getTime();
}   System.out
.println("The time For indexsearch is " + timeOfSearch + " ms");
	searcher.close();
    searcher=null;
	return returnResult;
}
	 public static String getPath(String Webapps){
			
			String path=System.getProperty("catalina.home");
		   int binindex=path.lastIndexOf("bin");
	       if(binindex!=-1){
	       	path=path.substring(0, binindex-1);
	       }		
			path=path+"\\"+Webapps;
			return path;	
		} 
   
}
