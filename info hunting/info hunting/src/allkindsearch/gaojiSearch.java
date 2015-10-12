package allkindsearch;
import java.io.File;
/**
 * <p>类名：gaojiSearch
 * <p>作用：高级搜索
 * <p>@author :born to try
 */
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.BitSet;
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
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

import pinyin.SpellSearch;


import beans.xmlbean;
import fenxi.Analyze;
public class gaojiSearch 
{
	ArrayList<xmlbean> returnResult=new ArrayList<xmlbean>();
 public  ArrayList<xmlbean> everysearch(String key,final String type, final String author,final String publisher,final String nokey,final String kind) throws IOException 
 {  
	 if   (key.getBytes().length   ==   key.length())   { 
         SpellSearch  pinyin=new SpellSearch();
         key=pinyin.Search(key);
       //  System.out.print(key);
 }   
	 String tomcat=getPath("webapps");       
     String filepath= tomcat+"\\"+"index";
	File   indexDir = new File(filepath);  
	IndexReader reader = IndexReader.open(indexDir);
	IndexSearcher searcher = new IndexSearcher(reader);
	System.out.print(kind);
	class gaojiFilter extends Filter{ 
		private static final long serialVersionUID = 1L;
		public BitSet bits(IndexReader reader) throws IOException {  
			  BitSet  bitSet = new BitSet(reader.maxDoc());  
			  if(!author.isEmpty()){
			  System.out.println(author);
			  bitSet.set(0, bitSet.size()-1,false);	  
			  Term term = new Term("author",author);
			  TermDocs termDocs = reader.termDocs(term);//从索引中取出含term的文档
			  while(termDocs.next()){
			  bitSet.set(termDocs.doc(),true);
			  }
			  }
			  else if(author.isEmpty())
			  {bitSet.set(0, bitSet.size()-1,true); 
			   System.out.print("success");
			  }
			  
			  BitSet bitSet1 = new BitSet(reader.maxDoc());	   
			  if(!publisher.isEmpty())
				  
			  {   System.out.print(publisher);
				  System.out.println("nima");
			  bitSet1.set(0, bitSet1.size()-1,false);
			  Term term1=new Term("publisher",publisher);
			  TermDocs termDocs1 = reader.termDocs(term1);//从索引中取出含term1的文档
			  while(termDocs1.next()){
			  bitSet1.set(termDocs1.doc(),true);
			  }	
			  }
			  else if(publisher.isEmpty())
			  {   System.out.print("usb");
				  bitSet1.set(0, bitSet1.size()-1,true);
			  }
			  BitSet bitSet2 = new BitSet(reader.maxDoc());
			  bitSet2.set(0, bitSet2.size()-1,true);		  
			  if(nokey!=null){
			  Term term2=new Term("keywords",nokey);
			  TermDocs termDocs2 = reader.termDocs(term2);//从索引中取出含term1的文档
			  while(termDocs2.next()){
			  bitSet2.set(termDocs2.doc(), false);
			  }	
			  Term term3=new Term("title",nokey);
			  TermDocs termDocs3 = reader.termDocs(term3);//从索引中取出含term1的文档
			  while(termDocs3.next()){
			  bitSet2.set(termDocs3.doc(), false);
			  }	
			  Term term4=new Term("describe",nokey);
			  TermDocs termDocs4 = reader.termDocs(term4);//从索引中取出含term1的文档
			  while(termDocs4.next()){
			  bitSet2.set(termDocs4.doc(), false);
			  }	
			  }
			  BitSet bitSet3 = new BitSet(reader.maxDoc());
			  bitSet3.set(0, bitSet3.size()-1,false);//设置位队列的每一位都问TRUE
			if(kind.equals("picture")){
			  Term term = new Term("kind","jpg");
			  TermDocs termDocs = reader.termDocs(term);//从索引中取出含term的文档
			  while(termDocs.next()){
			  bitSet3.set(termDocs.doc(), true);
			  }	 
			  Term term1=new Term("kind","gif");
			  TermDocs termDocs1 = reader.termDocs(term1);//从索引中取出含term的文档
			  while(termDocs1.next()){
			  bitSet3.set(termDocs1.doc(), true);
			  }	 
			  }
			  else if(kind.equals("document")){
				  Term term = new Term("kind","doc");
				  TermDocs termDocs = reader.termDocs(term);//从索引中取出含term的文档
				  while(termDocs.next()){
				  bitSet3.set(termDocs.doc(), true);
				  }	 
				  Term term1=new Term("kind","xls");
				  TermDocs termDocs1 = reader.termDocs(term1);//从索引中取出含term的文档
				  while(termDocs1.next()){
				  bitSet3.set(termDocs1.doc(), true);
				  }	 
				  Term term2=new Term("kind","ppt");
				  TermDocs termDocs2 = reader.termDocs(term2);//从索引中取出含term的文档
				  while(termDocs2.next()){
				  bitSet3.set(termDocs2.doc(), true);
				  }	 
				  Term term3=new Term("kind","pdf");
				  TermDocs termDocs3 = reader.termDocs(term3);//从索引中取出含term的文档
				  while(termDocs3.next()){
				  bitSet3.set(termDocs3.doc(), true);
				  }	 
				  Term term4=new Term("kind","txt");
				  TermDocs termDocs4 = reader.termDocs(term4);//从索引中取出含term的文档
				  while(termDocs4.next()){
				  bitSet3.set(termDocs4.doc(), true);
				  }	 
				  }
			  else  if(kind.equals("mp3")){
				  Term term = new Term("kind","mp3");
				  TermDocs termDocs = reader.termDocs(term);//从索引中取出含term的文档
				  while(termDocs.next()){
				  bitSet3.set(termDocs.doc(), true);
				  }	 
				  Term term1=new Term("kind","wma");
				  TermDocs termDocs1 = reader.termDocs(term1);//从索引中取出含term的文档
				  while(termDocs1.next()){
				  bitSet3.set(termDocs1.doc(), true);
				  }	 
				  }
			  else  if(kind.equals("vedio")){
				  Term term = new Term("kind","mp4");
				  TermDocs termDocs = reader.termDocs(term);//从索引中取出含term的文档
				  while(termDocs.next()){
				  bitSet3.set(termDocs.doc(), true);
				  }	 
				  Term term1=new Term("kind","flv");
				  TermDocs termDocs1 = reader.termDocs(term1);//从索引中取出含term的文档
				  while(termDocs1.next()){
				  bitSet3.set(termDocs1.doc(), true);
				  }	 
				  Term term2=new Term("kind","rmvb");
				  TermDocs termDocs2 = reader.termDocs(term2);//从索引中取出含term的文档
				  while(termDocs2.next()){
				  bitSet3.set(termDocs2.doc(), true);
				  }	 
				  }
			  else  if(kind.equals("all")){
				  bitSet3.set(0, bitSet3.size()-1,true);
				  }
			  BitSet finalbitSet = new BitSet(reader.maxDoc());
			  for(int i=0;i<finalbitSet.size();i++){
				finalbitSet.set(i,bitSet.get(i)&&bitSet1.get(i)&&bitSet2.get(i)&&bitSet3.get(i));
			  }
			  return finalbitSet;
		}	
	}
	if(type.equals("every"))
	{
	BooleanQuery multiKeys = new BooleanQuery();
	Analyze.doAnalyze(key);
	String[] keyword = Analyze.str;	
	for(int i=0;i<keyword.length;i++)
	{
		TermQuery luceneQuery1 = new TermQuery(new Term("title",keyword[i]));
		TermQuery luceneQuery2 = new TermQuery(new Term("keywords",keyword[i]));	
		TermQuery luceneQuery3 = new TermQuery(new Term("describe",keyword[i]));
		multiKeys.add(luceneQuery1, BooleanClause.Occur.SHOULD);
		multiKeys.add(luceneQuery2, BooleanClause.Occur.SHOULD);
		multiKeys.add(luceneQuery3, BooleanClause.Occur.SHOULD);
    }
	
	 Hits hits = null;
	 gaojiFilter  filter=new  gaojiFilter();
     try {
	      hits = searcher.search(multiKeys,filter);
	      System.out.print(hits.length());
          } 
     catch (IOException e) 
     {
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
} 
	    searcher.close();
        searcher=null;
	}else if(type.equals("all"))
	{
	BooleanQuery multiKeys = new BooleanQuery();
	BooleanQuery multiKeys1 = new BooleanQuery();
	BooleanQuery multiKeys2 = new BooleanQuery();
	BooleanQuery multiKeys3 = new BooleanQuery();
	Analyze.doAnalyze(key);
	String[] keyword = Analyze.str;	
	for(int i=0;i<keyword.length;i++)
	{
		TermQuery luceneQuery1 = new TermQuery(new Term("title",keyword[i]));
		multiKeys1.add(luceneQuery1, BooleanClause.Occur.MUST);
		
	}
	for(int i=0;i<keyword.length;i++)
	{
		TermQuery luceneQuery2 = new TermQuery(new Term("keywords",keyword[i]));
		multiKeys2.add(luceneQuery2, BooleanClause.Occur.MUST);
		
	}
	for(int i=0;i<keyword.length;i++)
	{
		TermQuery luceneQuery3 = new TermQuery(new Term("descirbe",keyword[i]));
		multiKeys3.add(luceneQuery3, BooleanClause.Occur.MUST);
		
	}
	multiKeys.add(multiKeys1, BooleanClause.Occur.SHOULD);
	multiKeys.add(multiKeys2, BooleanClause.Occur.SHOULD);
	multiKeys.add(multiKeys3, BooleanClause.Occur.SHOULD);
	 Hits hits = null;
	 gaojiFilter  filter=new gaojiFilter();
     try {
	      hits = searcher.search(multiKeys,filter);
	      System.out.print(hits.length());
          } 
     catch (IOException e) 
     {
	e.printStackTrace();
}	   
	for(int j = 0; j< hits.length(); j++)
	{
		Document document = null;
		try {
			document = hits.doc(j);
		} catch (IOException e) {
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
} 
	    searcher.close();
        searcher=null;	
	}else if(type.equals("one")){
		Term prefix =new Term("title",key);
		PrefixQuery query=new PrefixQuery(prefix);
		Hits hits=null;
		gaojiFilter  filter=new  gaojiFilter();
	     try {
		      hits = searcher.search(query,filter);
	          } 
	     catch (IOException e) 
	     {
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
			SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color='red'>", "</font>"); 
			Highlighter highlighter = new Highlighter(simpleHTMLFormatter,new QueryScorer(query)); 
			highlighter.setTextFragmenter(new SimpleFragmenter(30)); 
			if (text != null) { 
			MMAnalyzer analyzer = new MMAnalyzer();
			TokenStream tokenStream = analyzer.tokenStream("title",new StringReader(text)); 
			String highLightText = highlighter.getBestFragment(tokenStream, text); 
			if(highLightText==null)
			{   
				String text1=text.substring(0, 1);
				String text2=text.substring(1);
				highLightText="<font color='#FF0000'>"+text1+"</font>"+text2;
				result.setTitle(highLightText);
			}else{
			     result.setTitle(highLightText);  
			}
			}    		
			result.setKeywords(document.get("keywords"));
	        result.setKind(document.get("kind"));
	        result.setKeywords(document.get("describe"));
	        result.setDate(document.get("date"));
	        result.setAuthor(document.get("author"));
	        result.setPublisher(document.get("publisher"));
	        result.setUrl(document.get("url"));
	    	returnResult.add(result);   
	} 
		    searcher.close();
	        searcher=null;
		}
	    else if(type.equals("complete")){
	    BooleanQuery completekey = new BooleanQuery();	
		Term duanyu =new Term("title",key);
		PhraseQuery query=new PhraseQuery();
		query.add(duanyu);
		Term duanyu1 =new Term("keywords",key);
		PhraseQuery query1=new PhraseQuery();
		query1.add(duanyu1);
		Term duanyu2 =new Term("describe",key);
		PhraseQuery query2=new PhraseQuery();
		query2.add(duanyu2);
		completekey.add(query, BooleanClause.Occur.SHOULD);
		completekey.add(query1, BooleanClause.Occur.SHOULD);
		completekey.add(query2, BooleanClause.Occur.SHOULD);
		Hits hits=null;
		gaojiFilter  filter=new  gaojiFilter();
	     try {
		      hits = searcher.search(completekey,filter);
	          } 
	     catch (IOException e) 
	     {
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
			SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color='red'>", "</font>"); 
			Highlighter highlighter = new Highlighter(simpleHTMLFormatter,new QueryScorer(query)); 
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
			result.setKeywords(document.get("keywords"));
	        result.setKind(document.get("kind"));
	        result.setKeywords(document.get("describe"));
	        result.setDate(document.get("date"));
	        result.setAuthor(document.get("author"));
	        result.setPublisher(document.get("publisher"));
	        result.setUrl(document.get("url"));
	    	returnResult.add(result);   
	} 
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
