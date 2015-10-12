package Path;
import java.io.FileOutputStream;
import java.io.PrintStream;
import Path.RealPath;
import Distributed.*;
/**
 * <p>类名：WriteIPlist
 * <p>作用：将远程登录服务器的IP写入iplist.dat文件
 * <p>@author: born to try
 */

public class WriteIPlist {
	/**    
	*<br>函数名：go  
	*<br>作  用：从动态数组中获取已登陆服务器的IP，并写入.dat文件
	*<br>参  数：无
	*<br>返回类型：void
	*/
	   public static void go(){
		    FileOutputStream out;   
		    PrintStream print;
            try { 
 	              out = new FileOutputStream(RealPath.Path+"SQL\\iplist.data");   
	              print = new PrintStream( out );   
	              for(int j=0;j<IPlist.iplist.size();j++)
	                 {
	                   print.println (IPlist.iplist.get(j));   
	                  }
	              print.close();   
	            } catch (Exception e) {   
	        System.err.println ("将IP写入列表失败");   
	       }   
	   }
}  