package Thread;
import java.rmi.*;
import Init.*; 
import Distributed.*;
/**
 * <p>类名：Notice_EachServer
 * <p>作用：当有新服务器加入或有服务器退出时通知其他机器的线程
 * <p>@author :born to try
 */
public class Notice_EachServer extends Thread{
	   String LocalIP=Host_Remoteinit.getHostIP();    
	   String servername="";
	   String regname="";
	   /** 
		* <br>函数名：Notice_EachServer
		* <br>作  用： 构造函数
		* <br>参  数：  ip,regip
		* <br>返回类型 String  
		*/   
	public Notice_EachServer(String ip,String regip)
	{
	   servername=ip;
	   regname=regip;     
    }

	 /** 
	  * <br>函数名：AddMessage
	  * <br>作  用： 通知其他服务器有新主机加入
	  * <br>参  数： 无
	  * <br>返回类型 void  
	  */
	public void AddMessage(){		
       try{
             SERVER_SIDE call=(SERVER_SIDE) Naming.lookup("rmi://" + servername + "/Ser");
             if(call.AddIP(regname)){
                    System.out.println("向服务器"+servername+"添加"+regname+"成功");
             }
          }catch(Exception e){System.out.println("连接失败");}
    }
	 /** 
	  * <br>函数名：DeleteMessage
	  * <br>作  用： 通知其他服务器有主机退出
	  * <br>参  数： 无
	  * <br>返回类型 void  
	  */
    public void DeleteMessage(){
       try{
             SERVER_SIDE call=(SERVER_SIDE) Naming.lookup("rmi://" + servername + "/Ser");//指定服务器和远程对象的名字
             if(call.DelIP(regname)){
                    System.out.println("服务器"+servername+"删除"+regname+"成功");
             }
           } catch(Exception e){System.out.println("连接失败");}
	   }
}

