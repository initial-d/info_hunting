package actions;

import java.io.BufferedReader;
import java.io.FileReader;

import Distributed.*;
/**
 * <p>������returnIP
 * <p>���ã�����IP�б�
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
	    