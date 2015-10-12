package XML;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import Distributed.*;
/**
 * <p>������Change_RemoteIP
 * <p>���ã���ȡ����������IP����д��XML
 * <p>@author ��born to try
 */
public class Change_RemoteIP{
     public void change_remote(String sever) throws Exception{
	    String tomcat=getPath("webapps");       
        String filepath= tomcat+"\\"+"ip.xml";
        Document document = null;
	    SAXReader saxReader = new SAXReader();       
	    document = saxReader.read(new File(filepath));
	    List list = document.selectNodes("ip/remote");
	    Iterator iter = list.iterator();
	    while (iter.hasNext()) {
	    	Element remotenode = (Element)iter.next();
	    	remotenode.setText(sever);
	      //String ss=remotenode.getText();
	      //System.out.println(ss);
	    }
	    XMLWriter writer = new XMLWriter(new FileWriter(filepath)); 
	    OutputFormat format = OutputFormat.createPrettyPrint();  
	    writer = new XMLWriter(new FileOutputStream(filepath),format ); 
	    writer.write( document ); 
	    writer.flush();
	    writer.close();
	    new Start_Server();
	     }
         /** 
 	  * <br>�������� getPath
 	  * <br>��  �ã� ��ȡ��������Ŀ¼��ַ 
 	  * <br>������ Webapps
 	  * <br>�������ͣ�String
 	  */
	 public String getPath(String Webapps){		
	    String path=System.getProperty("catalina.home");
        int binindex=path.lastIndexOf("bin");
        if(binindex!=-1)
        {
          path=path.substring(0, binindex-1);
        }		
	    path=path+"\\"+Webapps;
	    return path;	
     }
}