package Socket;
import java.io.*;
import java.net.*;
import java.rmi.RemoteException;
import Distributed.*;
/**
 * <p>������MultiThreadServer
 * <p>���ã�����Socketͨ�ŵķ������ˣ���������ͻ���״̬
 * <p>@author : born to try
 */
public class MultiThreadServer extends Thread{
          int port=8888;
          ServerSocket serverSocket;
       /**    
      	*<br>��������MultiThreadServer     
      	*<br>��  �ã�MultiThreadServer�Ĺ��캯��
      	*<br>��  ������
      	*<br>�������ͣ���
      	*/   
     public MultiThreadServer() throws IOException{
          serverSocket=new ServerSocket(port);
          System.out.println("������������");
     }
       /**    
    	*<br>��������service     
    	*<br>��  �ã����ܿͻ��˵����ӣ������������߳�
        */
     public void service() throws IOException{
          while(true){
         	 Socket socket = null;
        	 socket = serverSocket.accept();   //���տͻ�����,ֻҪ�ͻ�����������,�ͻᴥ��accept()���Ӷ���������
             Thread workThread = new Thread(new Handler(socket));
             workThread.start();
          } 
      }
 }
/**
 * <p>������Handler
 * <p>���ã������̣߳����ܿͻ�������socket��������Ӧ
 */
class Handler implements Runnable{
          Socket socket;
       /**    
      	*<br>��������Handler    
      	*<br>��  �ã�Handler�Ĺ��캯�����ӵ���������ȡ��socket
        */
     public Handler(Socket socket){
          this.socket=socket;
     }
      private BufferedReader getReader(Socket socket) throws IOException{
          InputStream socketIn=socket.getInputStream();
          return new BufferedReader(new InputStreamReader(socketIn));
     }
      /**    
       *<br>��������run    
       *<br>��  �ã��̵߳�ִ�з���
      */
     public void run(){
          try{
               System.out.println("������"+socket.getInetAddress()+"����");
               BufferedReader br=getReader(socket);
               while((br.readLine())!=null){
            	   OutputStream socketOut = socket.getOutputStream();
                   socketOut.write("shutdown\r\n".getBytes());
               }
    	      }catch (Exception e) {
    		       System.out.println("������"+socket.getInetAddress()+"�ѵ���");
    		   try {
    			   InetAddress	IP=socket.getInetAddress();
    			   String ip=IP.getHostAddress();
    			   CLIENT_SIDE del=new CLIENT_SIDE();
				   del.DelIP(ip);
			       } catch (RemoteException e1) {
		           e1.printStackTrace();
			}
    	 }
       }
    }

