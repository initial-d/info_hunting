package Socket;
import java.io.*;
import java.net.Socket;
import Distributed.*;
import Path.*;
/**
 * <p>������MultiThreadClient
 * <p>���ã�����Socketͨ�ŵĿͻ��ˣ���������˷�����Ϣ
 * <p>@author : born to try
 */
public class MultiThreadClient extends Thread {
	/**    
	*<br>��������run     
	*<br>��  �ã������̣߳���������˷�����Ϣ�������ܷ������˷�����Ϣ��������������״̬
    */
   public void run(String goalip) throws InterruptedException {
       Socket socket = null;
       int port=8888;
       try {                    
            socket = new Socket(goalip, port);
            while(true)
             {
              OutputStream socketOut = socket.getOutputStream();
              socketOut.write("shutdown\r\n".getBytes());
              socket.getInputStream();
             }
            } catch (IOException e){                    
                    try{
                		System.out.println("���������Ѳ�����,����ѡ���������������Ժ򡭡�");
                		IPlist.iplist.remove(goalip);
       			        WriteIPlist.go();
       			        ReadIPlist.go();
                        Select_MainServer.go();        //ѡ���㷨��ѡ�ٳ�����������
                        }catch(Exception ee){
                		System.out.println("����"+ee);
                	 }
             }
      }
}
            

        
    

