package XML;

import java.net.InetAddress;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.io.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
/**
 * <p>������CreateIPxml
 * <p>���ã�����XML���洢������IP��Ϣ
 * <p>@author ��born to try
 */
public class CreateIPxml{
    public void create_xml() throws Exception {
		InetAddress IP=InetAddress.getLocalHost();
	    String LocalIP=IP.getHostAddress();
	    Document document=DocumentHelper.createDocument();
	    Element root=document.addElement("ip");
	    Element host=root.addElement("host");
	    host.setText(LocalIP);
	    Element remote=root.addElement("remote");
	    remote.setText(LocalIP);
	    try{
	    	String tomcat=getPath("webapps");	    
	        String filepath= tomcat+"\\"+"ip.xml";
	        XMLWriter output = new XMLWriter(
	        new FileWriter( new File(filepath)));
	        OutputFormat format = OutputFormat.createPrettyPrint(); 
	        output = new XMLWriter(new FileOutputStream(filepath), format );
	        output.write( document );
	        output.flush();
	        output.close();
	        }catch(IOException e)
	        {
	         System.out.println(e.getMessage());
	        }
	         }
    /** 
	 * <br>�������� getPath
	 * <br>��  �ã� ��ȡ��������Ŀ¼��ַ 
	 * <br>������ Webapps
	 * <br>�������ͣ�String
	 */
    public String getPath(String webapps){		//��ȡ��������Ŀ¼��ַ 
		String path=System.getProperty("catalina.home");
	    int binindex=path.lastIndexOf("bin");
	    if(binindex!=-1){
           path=path.substring(0, binindex-1);
          }		
		path=path+"\\"+webapps;
		return path;	
	}
}