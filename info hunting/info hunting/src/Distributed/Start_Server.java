package Distributed;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.util.Iterator;
import java.util.List;
import Init.*; 
/**
 * <p>������Start_Server
 * <p>���ã�����������
 * <p>@author ��born to try
 */
public class Start_Server{
	/** 
	* <br>��������Start_Server
	* <br>��  �ã����췽�����ڷ�����������ʱ����е��� 
	*/
	 public  Start_Server(){        
	 try{
		 new getIPinfo();
	   //System.out.println("����������ַ�� " + HRip.getHostIP());
       //System.out.println("����������ַ�� " + HRip.getRemoteIP());
		 Regist_Server run=new Regist_Server();
		 run.regist_server();
		}catch(Exception e){
			System.out.println("ϵͳ����");
			}
	}
}
/**
 * <p>������getIPinfo
 * <p>���ã��ֲ�ʽ�ĳ�ʼ������ȡXML�б���IP��Ϣ����������IP��Ϣ
 */
class getIPinfo  {	
	/** 
	* <br>�������� getIPinfo
	* <br>��  �ã� ���캯�� ����ȡXML�ļ��е���Ϣ�����洢��ϵͳ�� 
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
	 * <br>�������� getPath
	 * <br>��  �ã� ��ȡ��������Ŀ¼��ַ 
	 * <br>������ Webapps
	 * <br>�������ͣ�String
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




 