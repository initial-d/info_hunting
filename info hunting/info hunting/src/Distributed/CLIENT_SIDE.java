package Distributed;
//import index.search;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import allkindsearch.*;
import Init.Host_Remoteinit;
import Thread.Notice_EachServer;
import Path.*;
/**
 * <p>类名：CLIENT_SIDE
 * <p>作用：定义远程接口实现
 * <p>@author :born to try
 */
public class CLIENT_SIDE extends UnicastRemoteObject implements SERVER_SIDE {
    String hostIP=Host_Remoteinit.getHostIP();
    String remoteIP=Host_Remoteinit.getRemoteIP();
    /**    
	*<br>函数名：CLIENT_SIDE     
	*<br>作  用：CLIENT_SIDE的构造函数
	*<br>参  数：无
	*<br>返回类型：无
	*/
    public CLIENT_SIDE() throws RemoteException { 
    	super();
    }
    /**    
	*<br>函数名：RegistIP     
	*<br>作  用：实现新增服务器向主服务器注册,添加IP
	*<br>参  数：IP
	*<br>返回类型：String
	*/
   public boolean RegistIP(String IP)throws RemoteException{     
	 String RegistIP=IP;
	 if(!(IPlist.iplist.contains(RegistIP))){
  	      IPlist.iplist.add(RegistIP);
          }
     for(int i=0;i<IPlist.iplist.size();i++){
          String address=IPlist.iplist.get(i).toString();
          if(!(address.equals(RegistIP) || address.equals(hostIP))) { 
    	       Notice_EachServer notice=new Notice_EachServer(IPlist.iplist.get(i).toString(),RegistIP);
               notice.AddMessage();
             }  
        }
     WriteIPlist.go(); //把IP列表写入文件中去 
     System.out.println("当前在线服务器数量："+IPlist.iplist.size());
     System.out.println("当前在线服务器IP为：");
     for(int i=0;i<IPlist.iplist.size();i++){
        	System.out.println(IPlist.iplist.get(i).toString());
        }return true;
    }
       /**    
	*<br>函数名：RemoveIP     
	*<br>作  用：实现远程服务器的退出，删除IP
	*<br>参  数：IP
	*<br>返回类型：String
	*/
   public boolean RemoveIP(String IP)throws RemoteException{      
       try{
            for(int i=0;i<IPlist.iplist.size();i++){
		    if(IPlist.iplist.get(i).toString().equals(IP)){
		        IPlist.iplist.remove(i);
		        Notice_EachServer n=new Notice_EachServer(IPlist.iplist.get(i).toString(),IP);
 		        n.DeleteMessage();
                break;
               }
		    WriteIPlist.go();
            }return true;
          }catch(Exception e){return false;}
    } 
   /**    
 	*<br>函数名：AddIP     
 	*<br>作  用：添加新增服务器的IP，写入IP列表
 	*<br>参  数：IP
 	*<br>返回类型：String
 	*/
   public boolean AddIP(String IP)throws RemoteException{
	   if(!(IPlist.iplist.contains(IP))){
		    IPlist.iplist.add(IP);
		    System.out.println("本地"+IP+"添加成功");
		    WriteIPlist.go();        
		    System.out.println("当前在线服务器数量："+IPlist.iplist.size());
		    System.out.println("当前在线服务器IP为：");
		    for(int i=0;i<IPlist.iplist.size();i++) {
		        	System.out.println(IPlist.iplist.get(i).toString());
		        }
		   }return true;
		
	}
   /**    
  	*<br>函数名：DelIP    
  	*<br>作  用：在IP列表中删除退出服务器的IP
  	*<br>参  数：IP
  	*<br>返回类型：String
  	*/
   public boolean DelIP(String IP)throws RemoteException{
	   try{
		   for(int p=0;p<IPlist.iplist.size();p++){
			  if(IPlist.iplist.get(p).toString().equals(IP)){
			    System.out.println("IP："+IPlist.iplist.get(p).toString()+"已不在线");
			    IPlist.iplist.remove(p);
			    WriteIPlist.go();
			    break;
			   }
			   }
			  System.out.println("当前在线服务器数量："+IPlist.iplist.size());
			  System.out.println("当前在线服务器的IP为：");
			  for(int i=0;i<IPlist.iplist.size();i++) {
			        System.out.println(IPlist.iplist.get(i).toString());
			        }return true;
			}catch(Exception e){return false;}
   }
   /**    
 	*<br>函数名：getIPlist     
 	*<br>作  用：获取IP地址列表并返回IP地址列表
  	*/
   public ArrayList getIPlist()throws RemoteException{	
	   return IPlist.iplist;
	 }        
   
   public ArrayList remoteserach (String key,String kinds)throws RemoteException{
	   ArrayList a=new ArrayList();
	   search se=new search();
	   try {
		a=se.search(key,kinds);
	    } catch (IOException e) {
	      e.printStackTrace();
	}return a;
   }
   public ArrayList remotegaojiserach (String key,String type, final String author,final String publisher,final String nokey,final String kind)throws RemoteException{
	   ArrayList a=new ArrayList();
	   gaojiSearch gaoji=new gaojiSearch();
	   try {
		a=gaoji.everysearch(key,type,author,publisher, nokey,kind);
	   } catch (IOException e) {
	     e.printStackTrace();
	}return a;
   }
   public ArrayList remoteMp3serach (String key)throws RemoteException {
	   ArrayList a=new ArrayList();
	   Mp3search se1=new Mp3search();
	   try {
			a=se1.mp3search(key);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return a;
   }
   public ArrayList remoteTupianserach (String key)throws RemoteException{
	   ArrayList a=new ArrayList();
	   TupianSearch se2=new TupianSearch();
	   try {
			a=se2.tupiansearch(key);
		} catch (IOException e) {
		  e.printStackTrace();
		}return a;
   }
   public ArrayList remotevedioserach (String key)throws RemoteException{
	   ArrayList a=new ArrayList();
	   vedioSearch se3=new vedioSearch();
	   try {
		a=se3.vediosearch(key);
		} catch (IOException e) {
			e.printStackTrace();
		}return a;
   }
   public ArrayList remotewendangserach (String key,String kinds)throws RemoteException{
	   ArrayList a=new ArrayList();
	   WendangSearch se4=new  WendangSearch();
	   try {
		 a=se4.wendangsearch(key,kinds);
		} catch (IOException e) {
			e.printStackTrace();
		}return a;
   }
     /** 
	  * <br>函数名：getPath
	  * <br>作  用：获取远程服务器的目录 
	  */
   public String getPath()throws RemoteException{    
     return RealPath.Path.substring(RealPath.Path.lastIndexOf("\\",RealPath.Path.length()-2)+1,RealPath.Path.length()-1);
    }

  
}
    
   
 


