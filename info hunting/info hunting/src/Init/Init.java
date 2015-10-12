package Init;
import java.rmi.registry.LocateRegistry;
import javax.servlet.*;
import javax.servlet.http.*;
import Distributed.*;
import XML.*;
import Thread.*;
/**
 * <p>类名：Init
 * <p>作用：RMI远程调用的初始化工作
 * <p>@author: born to try
 */
public class Init extends HttpServlet{
	/** 
	* <br>函数名：Init
	* <br>作  用： 构造函数
	* <br>参  数： 无
	*/
   public Init()
    {
      super();
    }
   /** 
	* <br>函数名：init
	* <br>作  用：servlet的初始化函数,开启RMI注册服务
	* <br>参  数： 无
	* <br>返回类型： 空 
	*/
   public void init() throws ServletException{
      CreateIPxml create=new CreateIPxml();               
	  try {
			create.create_xml();                         //生成记录本地信息的IP.xml
			LocateRegistry.createRegistry(1099);         //开启RMI注册服务
			new Start_Server();
		  } catch (Exception e) {
			e.printStackTrace();
		}
      System.out.println("初始化完成");
 }
   /** 
	* <br>函数名：destroy
	* <br>作  用： 系统退出发送注销信息
	* <br>参  数： 无
	* <br>返回类型： 空 
	*/
   public void destroy() {			
	  super.destroy(); 
	  IPlist.iplist.remove(Host_Remoteinit.HostIP);
	  for(int y=0;y<IPlist.iplist.size();y++){
      if(!(IPlist.iplist.get(y).toString().equals(Host_Remoteinit.getHostIP()))){
		   Notice_EachServer notice=new Notice_EachServer(IPlist.iplist.get(y).toString(),Host_Remoteinit.getHostIP());
              notice.DeleteMessage();
              }
		     }	
		}
}