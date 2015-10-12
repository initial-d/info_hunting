package allkindsearch;

import java.util.ArrayList;
import java.util.Date;
import fenxi.Analyze;
import beans.xmlbean;
import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import jeasy.analysis.MMAnalyzer;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

import pinyin.SpellSearch;

/**
 * <p>类名：search
 * <p>作用：搜索类
 * <p>@author :born to try
 */

public class search {
	public long timeOfSearch;
	ArrayList<xmlbean> returnResult=new ArrayList<xmlbean>();
	public  ArrayList<xmlbean> search(String key,String kinds) throws IOException {
		 if   (key.getBytes().length   ==   key.length())   { 
             SpellSearch  pinyin=new SpellSearch();
             key=pinyin.Search(key);
           //  System.out.print(key);
     }   
	if(kinds.equals("all")){
    System.out.print("success");
	BooleanQuery multiKeys = new BooleanQuery();
	Analyze.doAnalyze(key);
	String[] keyword = Analyze.str;	
	Date begintime=new Date();
	String tomcat=getPath("webapps");       
    String filepath= tomcat+"\\"+"index";
	IndexSearcher searcher = new IndexSearcher(filepath);
	for(int i=0;i<keyword.length;i++){
		TermQuery luceneQuery1 = new TermQuery(new Term("title",keyword[i]));
		TermQuery luceneQuery2 = new TermQuery(new Term("keywords",keyword[i]));
		TermQuery luceneQuery3 = new TermQuery(new Term("kind",keyword[i]));
		TermQuery luceneQuery4 = new TermQuery(new Term("date",keyword[i]));
		TermQuery luceneQuery5 = new TermQuery(new Term("url",keyword[i]));
		TermQuery luceneQuery6 = new TermQuery(new Term("author",keyword[i]));
		TermQuery luceneQuery7 = new TermQuery(new Term("publisher",keyword[i]));
		TermQuery luceneQuery8 = new TermQuery(new Term("describe",keyword[i]));
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
try {
	hits = searcher.search(multiKeys);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	for(int j = 0; j< hits.length(); j++)
	{   //System.out.println(hits.score(j));
		Document document = null;
		try {
			document = hits.doc(j);
		} catch (IOException e) {
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
        result.setScores(hits.score(j));
    	returnResult.add(result);
    Date endtime=new  Date();
    timeOfSearch=endtime.getTime()-begintime.getTime();
    
}   System.out
.println("The time For indexsearch is " + timeOfSearch + " ms");
	searcher.close();
    searcher=null;
    }
		else if(kinds.equals("fuz")){
			BooleanQuery fuzzyKeys = new BooleanQuery();
			Analyze.doAnalyze(key);
			String[] keyword = Analyze.str;	
			Date begintime=new Date();
			String tomcat=getPath("webapps");       
		    String filepath= tomcat+"\\"+"index";
			IndexSearcher searcher = new IndexSearcher(filepath);
			for(int i=0;i<keyword.length;i++){
				FuzzyQuery luceneQuery1 = new FuzzyQuery(new Term("title",keyword[i]),0.0f);
				FuzzyQuery luceneQuery2 = new FuzzyQuery(new Term("keywords",keyword[i]),0.0f);
				FuzzyQuery luceneQuery3 = new FuzzyQuery(new Term("kind",keyword[i]),0.0f);
				FuzzyQuery luceneQuery4 = new FuzzyQuery(new Term("date",keyword[i]),0.0f);
				FuzzyQuery luceneQuery5 = new FuzzyQuery(new Term("url",keyword[i]),0.0f);
				FuzzyQuery luceneQuery6 = new FuzzyQuery(new Term("author",keyword[i]),0.0f);
				FuzzyQuery luceneQuery7 = new FuzzyQuery(new Term("publisher",keyword[i]),0.0f);
				FuzzyQuery luceneQuery8 = new FuzzyQuery(new Term("describe",keyword[i]),0.0f);
				fuzzyKeys.add(luceneQuery1, BooleanClause.Occur.SHOULD);
				fuzzyKeys.add(luceneQuery2, BooleanClause.Occur.SHOULD);
				fuzzyKeys.add(luceneQuery3, BooleanClause.Occur.SHOULD);
				fuzzyKeys.add(luceneQuery4, BooleanClause.Occur.SHOULD);
				fuzzyKeys.add(luceneQuery5, BooleanClause.Occur.SHOULD);
				fuzzyKeys.add(luceneQuery6, BooleanClause.Occur.SHOULD);
				fuzzyKeys.add(luceneQuery7, BooleanClause.Occur.SHOULD);
				fuzzyKeys.add(luceneQuery8, BooleanClause.Occur.SHOULD);
		}
			 Hits hits = null;
		try {
			hits = searcher.search(fuzzyKeys);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			for(int j = 0; j< hits.length(); j++)
			{
				Document document = null;
				try {
					document = hits.doc(j);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				xmlbean result=new xmlbean();
				String text = document.get("title"); 
				if (text != null) { 
					String text1=text.substring(0, 2);
					String text2=text.substring(2);
				    String highLightText= "<font color='#FF0000'>"+text1+"</font>"+text2;
					result.setTitle(highLightText);
				}
				
				result.setDescribe(document.get("describe")); 
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
			
		}
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
