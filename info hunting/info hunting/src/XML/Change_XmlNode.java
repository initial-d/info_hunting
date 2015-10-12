package XML;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
 * <p>类名：Change_XmlNode
 * <p>作用：对test.xml做预处理，修改XML中的url信息
 * <p>@author ：born to try
 */
public class Change_XmlNode{
	public void change_node() throws DocumentException, IOException{
		String tomcat=getPath("webapps");       
        String filepath= tomcat+"\\"+"test.xml";
        InetAddress IP=InetAddress.getLocalHost();
	    String LocalIP=IP.getHostAddress();
	    Document document = null;
	    SAXReader reader = new SAXReader(); 
	    document = reader.read(new File(filepath));
	    List list = document.selectNodes("allresource/resourceitem/url");
	    Iterator iter = list.iterator();
	    while (iter.hasNext()) {
	    	    Element url = (Element)iter.next();
	    	    String ur=url.getText();
	    	    String str1=ur.substring(0,7);
	    	    String str2=ur.substring(ur.indexOf(":8080"));
	    	    String str3=str1+LocalIP+str2;
	    		url.setText(str3);
	    	  }
	    XMLWriter writer = new XMLWriter(new FileWriter(filepath)); 
	    OutputFormat format = OutputFormat.createPrettyPrint();  
	    writer = new XMLWriter(new FileOutputStream(filepath),format ); 
	    writer.write( document ); 
	    writer.flush();
	    writer.close();
	    }
	 /** 
	  * <br>函数名： getPath
	  * <br>作  用： 获取服务器根目录地址 
	  * <br>参数： Webapps
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
	

