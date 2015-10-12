package fenxi;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;


import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.HeaderTokenizer.Token;
/**
 * <p>类名：Analyze
 * <p>作用：关键字分词
 * <p>@author :born to try
 */
public class Analyze {
    public static String[] str =null;
    public static  void doAnalyze(String key){
    	try{ 
    		MMAnalyzer analyzer = new MMAnalyzer();
    		String[] s= new String[100];
    	    Reader r= new StringReader(key);
    	    TokenStream ts = (TokenStream)analyzer.tokenStream("",r);
    	    org.apache.lucene.analysis.Token t;
    	    int i;
    	    t=ts.next();
    	for(i=0;t!=null;i++,t=ts.next()){
    		s[i]=t.termText();
    	}
    	str=new String[i];
    	for(int j=0;j<i;j++){
    		str[j]=s[j];
    	}
    }catch(IOException ex){
    	
   }
   
    }
 }
