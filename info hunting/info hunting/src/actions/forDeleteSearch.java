package actions;


import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * <p>������recordall
 * <p>���ã�����һ����¼��
 * <p>@author :born to try
 */
class recordall{
     String id;
	 String title;
	 String describe;
	 String date;

	
		/** 
		* <br>��������addjilu
		* <br>��  �ã����¼�������������
		* <br>��  ����String a,String b,String d,String e
		* <br>�������ͣ� �� 
		*/
public void addjilu(String a,String b,String d,String e){
	this.id=a;
	this.title=b;
	this.describe=d;
	this.date=e;

}
}

/** 
* <p>��������ForDeleteSearch
* <p>��  �ã���ʾ����xml�ļ���������ݣ�Ŀ��Ϊ��ɾ����
*/
 public class forDeleteSearch  {
   

       int ii=0;
       
	     String id;
		 String title;
		 String describe;
		 String date;
		 
	
		 /** 
		 * <br>��������Search
		 * <br>��  �ã��г����е�����
		 * <br>��  ������
		 * <br>�������ͣ� �� 
		 */

	public  void Search() throws UnsupportedEncodingException{
			
		String tomcat=getPath("webapps");       
        String filepath= tomcat+"\\"+"test.xml";

    	SAXReader reader = new SAXReader(); 
		Document document = null;
		try {
			document = reader.read("file:\\"+filepath);
		} catch (DocumentException e) {
			e.printStackTrace();
		} 

		Element root = document.getRootElement(); 
		Element foo = null; 

		Iterator  it = root.elementIterator("resourceitem"); 
		

		
		while (it.hasNext()) { 	
			
			foo =  (Element) it.next(); 
			
			String keywords= foo.elementText("keywords"); 
	
	    	 id=foo.elementText("id");
			 title=foo.elementText("title");
			  describe=foo.elementText("describe");
			  date=foo.elementText("date");
                 
                           ii++;
	    		           continue;
				    

  }
 }

	
	 /** 
	 * <br>��������getPath
	 * <br>��  �ã���ȡվ���Ŀ¼
	 * <br>��  ����Webapps
	 * <br>�������ͣ�String
	 */
	public String getPath(String Webapps){
		
		String path=System.getProperty("catalina.home");
	   int binindex=path.lastIndexOf("bin");
       if(binindex!=-1){
       	path=path.substring(0, binindex-1);
       }		
		path=path+"\\"+Webapps;
		return path;	
	}
	



}		
        
	
	
