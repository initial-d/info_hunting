package Distributed;
import java.io.*;
import java.util.ArrayList;
/**
 * <p>������IPlist
 * <p>���ã�������̬������������߷�����IP
 * <p>@author :born to try
 */ 
public class IPlist{
	 public static ArrayList<String> select= new ArrayList<String>();
	 public static ArrayList<String> iplist= new ArrayList<String>();
}
/**
 * <p>������ReadIPlist
 * <p>���ã���ȡIP�б���������IP��������ѡ���·�������Select�б�
 */
 class ReadIPlist{ 
	   /** 
		* <br>�������� go
		* <br>��  �ã� ��ȡIP�б���������IP��������ѡ���·�������Select�б�
		*/
       public static void go(){
		try {   
        	  String read;
              String tomcat=getPath("webapps");       
              String filepath=tomcat+"\\WebRoot"+"\\";
              BufferedReader bufread = new BufferedReader(new FileReader(filepath+"SQL\\iplist.data")); 
              while((read=bufread.readLine())!= null) 
               { 
                 IPlist.select.add(read); 
               }
              }catch (Exception e) { 
                 System.out.println(e.getMessage()); 
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
             if(index!=-1)
              {
               path=path.substring(0, index-1);
              }		
 	         path=path+"\\"+Webapps;
 	         return path;	
 	    } 
   }
	    
  
    

 

 