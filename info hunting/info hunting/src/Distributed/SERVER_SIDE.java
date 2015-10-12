package Distributed;
import java.rmi.*;
import java.util.ArrayList;
/**
 * <p>类名：SERVER_SIDE
 * <p>作用：定义远程接口
 * <p>@author： born to try
 */
public interface SERVER_SIDE extends Remote { 
   /**    
	*<br>函数名：RegistIP     
	*<br>作  用：新增服务器向主服务器注册,添加IP
	*<br>参  数：IP
	*<br>返回类型：String
	*/
     boolean RegistIP(String IP)throws RemoteException ; 
   /**    
 	*<br>函数名：RemoveIP     
 	*<br>作  用：服务器的退出，删除IP
 	*<br>参  数：IP
 	*<br>返回类型：String
 	*/
     boolean RemoveIP(String IP)throws RemoteException ;   
   /**    
  	*<br>函数名：AddIP     
  	*<br>作  用：添加新增服务器的IP，写入IP列表
  	*<br>参  数：IP
  	*<br>返回类型：String
  	*/
     boolean AddIP(String IP)throws RemoteException;
   /**    
   	*<br>函数名：DelIP    
   	*<br>作  用：在IP列表中删除退出服务器的IP
   	*<br>参  数：IP
   	*<br>返回类型：String
   	*/
     boolean DelIP(String IP)throws RemoteException;
   /**    
  	*<br>函数名：getIPlist     
  	*<br>作  用：获取IP地址列表并返回IP地址列表
   	*/
     ArrayList getIPlist()throws RemoteException ;        
     ArrayList remoteserach(String key,String kinds)throws RemoteException ;
     ArrayList remotegaojiserach(String key,String type, final String author,final String publisher,final String nokey,final String kind)throws RemoteException ;
     ArrayList remoteMp3serach(String key)throws RemoteException ;
     ArrayList remoteTupianserach(String key)throws RemoteException ;
     ArrayList remotevedioserach(String key)throws RemoteException ;
     ArrayList remotewendangserach(String key,String kinds)throws RemoteException ;
     /** 
 	  * <br>函数名：getPath
 	  * <br>作  用：获取远程服务器的目录 
 	  */
     String getPath()throws RemoteException ;
}   