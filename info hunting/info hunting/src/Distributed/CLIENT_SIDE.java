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
 * <p>������CLIENT_SIDE
 * <p>���ã�����Զ�̽ӿ�ʵ��
 * <p>@author :born to try
 */
public class CLIENT_SIDE extends UnicastRemoteObject implements SERVER_SIDE {
    String hostIP=Host_Remoteinit.getHostIP();
    String remoteIP=Host_Remoteinit.getRemoteIP();
    /**    
	*<br>��������CLIENT_SIDE     
	*<br>��  �ã�CLIENT_SIDE�Ĺ��캯��
	*<br>��  ������
	*<br>�������ͣ���
	*/
    public CLIENT_SIDE() throws RemoteException { 
    	super();
    }
    /**    
	*<br>��������RegistIP     
	*<br>��  �ã�ʵ����������������������ע��,���IP
	*<br>��  ����IP
	*<br>�������ͣ�String
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
     WriteIPlist.go(); //��IP�б�д���ļ���ȥ 
     System.out.println("��ǰ���߷�����������"+IPlist.iplist.size());
     System.out.println("��ǰ���߷�����IPΪ��");
     for(int i=0;i<IPlist.iplist.size();i++){
        	System.out.println(IPlist.iplist.get(i).toString());
        }return true;
    }
       /**    
	*<br>��������RemoveIP     
	*<br>��  �ã�ʵ��Զ�̷��������˳���ɾ��IP
	*<br>��  ����IP
	*<br>�������ͣ�String
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
 	*<br>��������AddIP     
 	*<br>��  �ã����������������IP��д��IP�б�
 	*<br>��  ����IP
 	*<br>�������ͣ�String
 	*/
   public boolean AddIP(String IP)throws RemoteException{
	   if(!(IPlist.iplist.contains(IP))){
		    IPlist.iplist.add(IP);
		    System.out.println("����"+IP+"��ӳɹ�");
		    WriteIPlist.go();        
		    System.out.println("��ǰ���߷�����������"+IPlist.iplist.size());
		    System.out.println("��ǰ���߷�����IPΪ��");
		    for(int i=0;i<IPlist.iplist.size();i++) {
		        	System.out.println(IPlist.iplist.get(i).toString());
		        }
		   }return true;
		
	}
   /**    
  	*<br>��������DelIP    
  	*<br>��  �ã���IP�б���ɾ���˳���������IP
  	*<br>��  ����IP
  	*<br>�������ͣ�String
  	*/
   public boolean DelIP(String IP)throws RemoteException{
	   try{
		   for(int p=0;p<IPlist.iplist.size();p++){
			  if(IPlist.iplist.get(p).toString().equals(IP)){
			    System.out.println("IP��"+IPlist.iplist.get(p).toString()+"�Ѳ�����");
			    IPlist.iplist.remove(p);
			    WriteIPlist.go();
			    break;
			   }
			   }
			  System.out.println("��ǰ���߷�����������"+IPlist.iplist.size());
			  System.out.println("��ǰ���߷�������IPΪ��");
			  for(int i=0;i<IPlist.iplist.size();i++) {
			        System.out.println(IPlist.iplist.get(i).toString());
			        }return true;
			}catch(Exception e){return false;}
   }
   /**    
 	*<br>��������getIPlist     
 	*<br>��  �ã���ȡIP��ַ�б�����IP��ַ�б�
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
	  * <br>��������getPath
	  * <br>��  �ã���ȡԶ�̷�������Ŀ¼ 
	  */
   public String getPath()throws RemoteException{    
     return RealPath.Path.substring(RealPath.Path.lastIndexOf("\\",RealPath.Path.length()-2)+1,RealPath.Path.length()-1);
    }

  
}
    
   
 


