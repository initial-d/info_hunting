package Distributed;
import java.rmi.*;
import Init.*;
import Path.*;
/**
 * <p>������Regist_Server
 * <p>���ã� RMIԶ�̵��õĳ�ʼ������,����������ע��
 * <p>@author ��born to try
 */
public class Regist_Server {
   /** 
	* <br>��������regist_server
	* <br>��  �ã���ȡ�洢��ϵͳ�б���IP����������IP��Ϣ 
	*/
     public void regist_server() {  
         String HostIP=Host_Remoteinit.getHostIP();
         String RemoteIP=Host_Remoteinit.getRemoteIP();
         SERVER_SIDE call=null;
     try {
         CLIENT_SIDE rmi = new CLIENT_SIDE();              //�����ṩRMI����Ľӿ�
         Naming.rebind("Ser", rmi);						   //���ṩ����Ľӿڰ��ڱ��ص�RMI��������
      	 call=(SERVER_SIDE) Naming.lookup("rmi://" + RemoteIP + "/Ser");  //Զ�̷������ã�ָ����������Զ�̶��������
         if(call.RegistIP(HostIP)){
            	System.out.println(" ����ע��ɹ�");
           }
         } catch (Exception e) {
       	   try{
        		 System.out.println("������������ʧ�ܣ���ʼѡ�ٷ����������Ժ󡭡�");
        		 ReadIPlist.go();                         //��ȡ��ǰ���߷�����IP
        		 Select_MainServer.go();                  //ѡ���㷨��ѡ�ٳ��������������Ա��������
        	    }catch(Exception ee){System.out.println("����"+ee);}
        	}
           try{
                if(!HostIP.equals(RemoteIP))            
             { 
                System.out.println("��ǰ���߷���������Ϊ��"+IPlist.iplist.size());
                System.out.println("��ǰ���߷�����IPΪ��");
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
            System.out.println("����: " + e);
        }
    }
    
}
