package Distributed;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.rmi.Naming;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import Socket.*;
/**
 * <p>������Select_MainServer
 * <p>���ã�ѡ���㷨��ѡ�ٳ���������
 * <p>@author :born to try
 */

public class Select_MainServer{ 
	/**    
	*<br>��������go    
	*<br>��  �ã�ѡ���㷨��ѡ�ٳ���������
	*/
	public static void go() throws IOException, DocumentException{
		String tomcat=getPath("webapps");       
        String filepath= tomcat+"\\"+"ip.xml";
		int[]lastnum = new int[IPlist.select.size()];
 		for(int i=0;i<IPlist.select.size();i++){
 			lastnum[i]=Integer.parseInt(IPlist.select.get(i).substring(IPlist.select.get(i).lastIndexOf(".")+1, IPlist.select.get(i).length()));
 		}
 		int max=lastnum[0];
 		for(int i=1;i<lastnum.length;i++){
 		    if(max<lastnum[i]) max= lastnum[i];
 		}
 		String MaxIP=IPlist.select.get(0).substring(0, IPlist.select.get(0).lastIndexOf(".")+1)+String.valueOf(max);
 		Document document = null;
 	    SAXReader saxReader = new SAXReader(); 
 	    document = saxReader.read(new File(filepath));
 	    List list = document.selectNodes("/ip/remote");
 	    Iterator iter = list.iterator();
 	    while (iter.hasNext()) {
 	    	   Element Maxremote = (Element)iter.next();
 	    	   Maxremote.setText(MaxIP);
 	    		}
 	    XMLWriter writer = new XMLWriter(new FileWriter(filepath)); 
 	    OutputFormat format = OutputFormat.createPrettyPrint();  
 	    writer = new XMLWriter(new FileOutputStream(filepath),format ); 
 	    writer.write( document ); 
 	    writer.flush();
 	    writer.close();
 	    System.out.println("ѡ�ٳ�����������IPΪ"+MaxIP);
 	    try {
 	    	InetAddress IP = InetAddress.getLocalHost();
 	        String ip=IP.getHostAddress();
 	        CLIENT_SIDE rmi = new CLIENT_SIDE();
      	    Naming.rebind("Rer", rmi);
      	    SERVER_SIDE call=(SERVER_SIDE) Naming.lookup("rmi://" + MaxIP + "/Rer"); //��ѡ�ٳ�������������ע��
            if(call.RegistIP(ip)){
          	System.out.println(" ����ע��ɹ�");
            }
            if(ip.equals(MaxIP)){
          	new MultiThreadServer().service();}              
            else{
          	MultiThreadClient mul=new MultiThreadClient();
          	mul.run(MaxIP);
          	mul.start();}
            } catch (Exception e){
     	      System.out.println("����: " + e);
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
  		int index=path.lastIndexOf("bin");
        if(index!=-1){
           path=path.substring(0, index-1);
         }		
  		path=path+"\\"+Webapps;
  		return path;	
  	}
	}


