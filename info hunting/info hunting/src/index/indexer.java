package index;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import jeasy.analysis.MMAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.TermVector;
import org.apache.lucene.index.IndexWriter;
import org.dom4j.DocumentException;


import XML.*;

//import com.sun.org.apache.bcel.internal.classfile.CodeException;


import beans.xmlbean; 
/**
 * <p>类名：indexer
 * <p>作用：建立索引
 * <p>@author :born to try
 */
public class indexer extends HttpServlet{
	
	public void index(String  path) throws IOException {
		Change_XmlNode change=new Change_XmlNode();
		try {
			change.change_node();
		} catch (DocumentException e2) {
			e2.printStackTrace();
		}
		List<xmlbean> SearchResult=new ArrayList<xmlbean>();
		SearchResult=resource.getResourse();
		int filenum = 0;		
	    File   indexDir = new File(path);        
		MMAnalyzer luceneAnalyzer = new MMAnalyzer();
        IndexWriter indexWriter = null;
		try {
			indexWriter = new IndexWriter(indexDir,luceneAnalyzer,true);
		} catch (IOException e1) {
		e1.printStackTrace();
		}
		Field Title;
		Field Keywords;
		Field Kind;
		Field Describe;
		Field Date;
		Field Url;
		Field Author;
		Field Publisher;
		 Date startTime = new Date();
        for(int i = 0; i < SearchResult.size(); i++){
	    	xmlbean resource=SearchResult.get(i);
//	    	String filetitle=resource.getUrl().substring(resource.getUrl().lastIndexOf("/")+1, resource.getUrl().length());
//			String Path=filepath+"/webapps/resources/"+filetitle;
				try {
					Document document = new Document();    		  
	    			Title=new Field("title", resource.getTitle(), Field.Store.YES, Field.Index.TOKENIZED);
	    			Keywords=new Field("keywords", resource.getKeywords(), Field.Store.YES, Field.Index.TOKENIZED,TermVector.YES);
	    			Kind=new Field("kind", resource.getKind(), Field.Store.YES, Field.Index.UN_TOKENIZED);
	    			Describe=new Field("describe",resource.getDescribe(),Field.Store.YES, Field.Index.TOKENIZED);
	    			Date=new Field("date", resource.getDate(), Field.Store.YES, Field.Index.UN_TOKENIZED);
	    			Url=new Field("url", resource.getUrl(), Field.Store.YES, Field.Index.NO);
	    			Author=new Field("author", resource.getAuthor(), Field.Store.YES, Field.Index.UN_TOKENIZED);
	    			Publisher=new Field("publisher", resource.getPublisher(), Field.Store.YES, Field.Index.UN_TOKENIZED);	    			
            		document.add(Title);	
            		document.add(Keywords);	
            		document.add(Kind);
            		document.add(Describe);
            		document.add(Date);
            		document.add(Url);
            		document.add(Author);
            		document.add(Publisher);
            		indexWriter.addDocument(document);	
          	//	System.out.println("Indexing for node" + resource.getTitle());
            		filenum++;
        		}
        		    catch (FileNotFoundException e) {
        			   e.printStackTrace();
        		   } catch (IOException e) {
        			   e.printStackTrace();
        		}
        }
        Date endTime = new Date();
      long   timeOfSearch = endTime.getTime() - startTime.getTime();
       System.out.println("共用了 " + timeOfSearch 
                           + " 毫秒"); 
        System.out.println("共索引了"+filenum+"个结点!");
        try {
    		indexWriter.setUseCompoundFile(true);
			indexWriter.optimize();
			indexWriter.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public  void init() throws ServletException {
		indexer i=new indexer();
		if(i!=null)
			try {
				String tomcat=getPath("webapps");       
		        String filepath= tomcat+"\\"+"index";
				i.index(filepath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
