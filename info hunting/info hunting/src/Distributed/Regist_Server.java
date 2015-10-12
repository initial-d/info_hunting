package Distributed;
import java.rmi.*;
import Init.*;
import Path.*;
/**
 * <p>类名：Regist_Server
 * <p>作用： RMI远程调用的初始化工作,向主服务器注册
 * <p>@author ：born to try
 */
public class Regist_Server {
   /** 
	* <br>函数名：regist_server
	* <br>作  用：获取存储在系统中本机IP和主服务器IP信息 
	*/
     public void regist_server() {  
         String HostIP=Host_Remoteinit.getHostIP();
         String RemoteIP=Host_Remoteinit.getRemoteIP();
         SERVER_SIDE call=null;
     try {
         CLIENT_SIDE rmi = new CLIENT_SIDE();              //生成提供RMI服务的接口
         Naming.rebind("Ser", rmi);						   //将提供服务的接口绑定在本地的RMI服务器上
      	 call=(SERVER_SIDE) Naming.lookup("rmi://" + RemoteIP + "/Ser");  //远程方法调用，指定服务器和远程对象的名字
         if(call.RegistIP(HostIP)){
            	System.out.println(" 本机注册成功");
           }
         } catch (Exception e) {
       	   try{
        		 System.out.println("主服务器连接失败，开始选举服务器，请稍后……");
        		 ReadIPlist.go();                         //读取当前在线服务器IP
        		 Select_MainServer.go();                  //选举算法，选举出新主服务器，以便继续运行
        	    }catch(Exception ee){System.out.println("错误"+ee);}
        	}
           try{
                if(!HostIP.equals(RemoteIP))            
             { 
                System.out.println("当前在线服务器数量为："+IPlist.iplist.size());
                System.out.println("当前在线服务器IP为：");
                for(int i=0;i<IPlist.iplist.size();i++)
             {
                System.out.println(IPlist.iplist.get(i).toString());
                WriteIPlist.go();
              }
             }
                else
             {
                WriteIPlist.go();  
             }
           } catch (Exception e) {
            System.out.println("错误: " + e);
        }
    }
    
}
