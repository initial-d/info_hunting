package actions;

import java.io.BufferedReader;
import java.io.FileReader;

import Distributed.*;
/**
 * <p>类名：returnIP
 * <p>作用：返回IP列表
 * <p>@author :born to try
 */
public class returnIP extends  MySuperAction{
	
	public  String	execute(){
			
		ReadIPlist.go();
		System.out.print(IPlist.select.size());
		request.getSession().setAttribute("onelist1",IPlist.select);
		request.setAttribute("mainPage","/IPlist.jsp");
		return  "success";
		}
		
	}

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
	    