package Distributed;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.util.Iterator;
import java.util.List;
import Init.*; 
/**
 * <p>类名：Start_Server
 * <p>作用：开启服务器
 * <p>@author ：born to try
 */
public class Start_Server{
	/** 
	* <br>函数名：Start_Server
	* <br>作  用：构造方法，在服务器开启的时候进行调用 
	*/
	 public  Start_Server(){        
	 try{
		 new getIPinfo();
	   //System.out.println("本服务器地址： " + HRip.getHostIP());
       //System.out.println("主服务器地址： " + HRip.getRemoteIP());
		 Regist_Server run=new Regist_Server();
		 run.regist_server();
		}catch(Exception e){
			System.out.println("系统错误");
			}
	}
}
/**
 * <p>类名：getIPinfo
 * <p>作用：分布式的初始化，获取XML中本机IP信息和主服务器IP信息
 */
class getIPinfo  {	
	/** 
	* <br>函数名： getIPinfo
	* <br>作  用： 构造函数 ，获取XML文件中的信息，并存储在系统中 
	*/
    public getIPinfo()throws Exception{       
	 String tomcat=getPath("webapps");       
     String filepath= tomcat+"\\"+"ip.xml";
   //String filepath="C:\\Program Files\\apache-tomcat-7.0.19\\webapps\\ip.xml";
     SAXReader reader = new SAXReader(); 
     Document document = null;
     try {
		   document = reader.read("file:\\"+filepath);
		 } catch (DocumentException e) {
			  e.printStackTrace();
		 } 
     List list1 = document.selectNodes("ip/host");
	 Iterator iter1 = list1.iterator();
	 while (iter1.hasNext()) {
	       Element host = (Element)iter1.next();
	       String ho=host.getText();
	       Host_Remoteinit.setHostIP(ho);
	     }
	 List list2 = document.selectNodes("ip/remote");
	 Iterator iter2 = list2.iterator();
	 while (iter2.hasNext()) {
	       Element remote = (Element)iter2.next();
	       String re=remote.getText();
	       Host_Remoteinit.setRemoteIP(re);
	 }
}
    /** 
	 * <br>函数名： getPath
	 * <br>作  用： 获取服务器根目录地址 
	 * <br>参数： Webapps
	 * <br>返回类型：String
	 */
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




 