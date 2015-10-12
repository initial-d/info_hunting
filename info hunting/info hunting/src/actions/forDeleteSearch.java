package actions;


import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * <p>类名：recordall
 * <p>作用：构建一个记录集
 * <p>@author :born to try
 */
class recordall{
     String id;
	 String title;
	 String describe;
	 String date;

	
		/** 
		* <br>函数名：addjilu
		* <br>作  用：向记录集里面添加数据
		* <br>参  数：String a,String b,String d,String e
		* <br>返回类型： 空 
		*/
public void addjilu(String a,String b,String d,String e){
	this.id=a;
	this.title=b;
	this.describe=d;
	this.date=e;

}
}

/** 
* <p>函数名：ForDeleteSearch
* <p>作  用：显示整个xml文件里面的内容，目的为了删除用
*/
 public class forDeleteSearch  {
   

       int ii=0;
       
	     String id;
		 String title;
		 String describe;
		 String date;
		 
	
		 /** 
		 * <br>函数名：Search
		 * <br>作  用：列出所有的数据
		 * <br>参  数：无
		 * <br>返回类型： 空 
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
	 * <br>函数名：getPath
	 * <br>作  用：获取站点根目录
	 * <br>参  数：Webapps
	 * <br>返回类型：String
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
        
	
	
