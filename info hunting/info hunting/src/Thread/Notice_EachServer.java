package Thread;
import java.rmi.*;
import Init.*; 
import Distributed.*;
/**
 * <p>������Notice_EachServer
 * <p>���ã������·�����������з������˳�ʱ֪ͨ�����������߳�
 * <p>@author :born to try
 */
public class Notice_EachServer extends Thread{
	   String LocalIP=Host_Remoteinit.getHostIP();    
	   String servername="";
	   String regname="";
	   /** 
		* <br>��������Notice_EachServer
		* <br>��  �ã� ���캯��
		* <br>��  ����  ip,regip
		* <br>�������� String  
		*/   
	public Notice_EachServer(String ip,String regip)
	{
	   servername=ip;
	   regname=regip;     
    }

	 /** 
	  * <br>��������AddMessage
	  * <br>��  �ã� ֪ͨ����������������������
	  * <br>��  ���� ��
	  * <br>�������� void  
	  */
	public void AddMessage(){		
       try{
             SERVER_SIDE call=(SERVER_SIDE) Naming.lookup("rmi://" + servername + "/Ser");
             if(call.AddIP(regname)){
                    System.out.println("�������"+servername+"���"+regname+"�ɹ�");
             }
          }catch(Exception e){System.out.println("����ʧ��");}
    }
	 /** 
	  * <br>��������DeleteMessage
	  * <br>��  �ã� ֪ͨ�����������������˳�
	  * <br>��  ���� ��
	  * <br>�������� void  
	  */
    public void DeleteMessage(){
       try{
             SERVER_SIDE call=(SERVER_SIDE) Naming.lookup("rmi://" + servername + "/Ser");//ָ����������Զ�̶��������
             if(call.DelIP(regname)){
                    System.out.println("������"+servername+"ɾ��"+regname+"�ɹ�");
             }
           } catch(Exception e){System.out.println("����ʧ��");}
	   }
}

