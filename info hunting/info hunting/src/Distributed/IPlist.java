package Distributed;
import java.io.*;
import java.util.ArrayList;
/**
 * <p>类名：IPlist
 * <p>作用：创建动态数组来存放在线服务器IP
 * <p>@author :born to try
 */ 
public class IPlist{
	 public static ArrayList<String> select= new ArrayList<String>();
	 public static ArrayList<String> iplist= new ArrayList<String>();
}
/**
 * <p>类名：ReadIPlist
 * <p>作用：读取IP列表，并将在线IP加入用于选举新服务器的Select列表
 */
 class ReadIPlist{ 
	   /** 
		* <br>函数名： go
		* <br>作  用： 读取IP列表，并将在线IP加入用于选举新服务器的Select列表
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
    	* <br>函数名： getPath
    	* <br>作  用： 获取服务器根目录地址 
   	    * <br>参数： Webapps
    	* <br>返回类型：String
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
	    
  
    

 

 