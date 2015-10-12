package index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;



import org.dom4j.Element;

import beans.xmlbean;
/**
 * <p>������resources
 * <p>���ã������ĵ���Ӧʵ��
 * <p>@author :born to try
 */
public class resource {
	

	public static List<xmlbean> getResourse()throws RemoteException{
        List<xmlbean> SearchResult=new ArrayList<xmlbean>();
        String tomcat=getPath("webapps");       
        String filepath= tomcat+"\\"+"test.xml";
			SAXReader saxReader=new SAXReader();
			
			File xmlFile = new File(filepath);
			 FileInputStream fis = null;   
		       try
		       {   
		         fis = new FileInputStream(xmlFile);   
		        } 
		        catch (FileNotFoundException e) 
		        {   
		            e.printStackTrace();     
		        }   
	         List  list=null;
	         xmlbean result;
	        
	         try {   
	               
	             org.dom4j.Document doc = saxReader.read(fis);   
	           
	             list = doc.selectNodes("/allresource//resourceitem");
	     	     
	             } catch (DocumentException e) 
	             {   
	             e.printStackTrace();   
	             }      
		        for(Iterator<?>  iterator =  list.iterator();iterator.hasNext();) {
		        	   result=new xmlbean();//ÿ��ѭ����Ҫ�½�һ���µ�Javabean����
		               Element element1 = (Element)iterator.next();
		               result.setTitle(( element1).selectSingleNode("title").getText());
		               int i=0;
					   result.setKeywords((( element1).selectSingleNode("keywords").getText()));
		               result.setKind(( element1).selectSingleNode("kind").getText());
		               result.setDescribe(( element1).selectSingleNode("describe").getText());
		               result.setDate(( element1).selectSingleNode("date").getText());
		               result.setUrl(( element1).selectSingleNode("url").getText());
		               result.setAuthor(( element1).selectSingleNode("author").getText());
		               result.setPublisher(( element1).selectSingleNode("publisher").getText());
		               SearchResult.add(result);
		        }
		        System.out.print("��ʼ����������Դ");
		       return SearchResult;     
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
