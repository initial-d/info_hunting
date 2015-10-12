package Distributed;
import java.rmi.*;
import java.util.ArrayList;
/**
 * <p>������SERVER_SIDE
 * <p>���ã�����Զ�̽ӿ�
 * <p>@author�� born to try
 */
public interface SERVER_SIDE extends Remote { 
   /**    
	*<br>��������RegistIP     
	*<br>��  �ã���������������������ע��,���IP
	*<br>��  ����IP
	*<br>�������ͣ�String
	*/
     boolean RegistIP(String IP)throws RemoteException ; 
   /**    
 	*<br>��������RemoveIP     
 	*<br>��  �ã����������˳���ɾ��IP
 	*<br>��  ����IP
 	*<br>�������ͣ�String
 	*/
     boolean RemoveIP(String IP)throws RemoteException ;   
   /**    
  	*<br>��������AddIP     
  	*<br>��  �ã����������������IP��д��IP�б�
  	*<br>��  ����IP
  	*<br>�������ͣ�String
  	*/
     boolean AddIP(String IP)throws RemoteException;
   /**    
   	*<br>��������DelIP    
   	*<br>��  �ã���IP�б���ɾ���˳���������IP
   	*<br>��  ����IP
   	*<br>�������ͣ�String
   	*/
     boolean DelIP(String IP)throws RemoteException;
   /**    
  	*<br>��������getIPlist     
  	*<br>��  �ã���ȡIP��ַ�б�����IP��ַ�б�
   	*/
     ArrayList getIPlist()throws RemoteException ;        
     ArrayList remoteserach(String key,String kinds)throws RemoteException ;
     ArrayList remotegaojiserach(String key,String type, final String author,final String publisher,final String nokey,final String kind)throws RemoteException ;
     ArrayList remoteMp3serach(String key)throws RemoteException ;
     ArrayList remoteTupianserach(String key)throws RemoteException ;
     ArrayList remotevedioserach(String key)throws RemoteException ;
     ArrayList remotewendangserach(String key,String kinds)throws RemoteException ;
     /** 
 	  * <br>��������getPath
 	  * <br>��  �ã���ȡԶ�̷�������Ŀ¼ 
 	  */
     String getPath()throws RemoteException ;
}   