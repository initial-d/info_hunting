package Socket;
import java.io.*;
import java.net.*;
import java.rmi.RemoteException;
import Distributed.*;
/**
 * <p>类名：MultiThreadServer
 * <p>作用：建立Socket通信的服务器端，监听多个客户端状态
 * <p>@author : born to try
 */
public class MultiThreadServer extends Thread{
          int port=8888;
          ServerSocket serverSocket;
       /**    
      	*<br>函数名：MultiThreadServer     
      	*<br>作  用：MultiThreadServer的构造函数
      	*<br>参  数：无
      	*<br>返回类型：无
      	*/   
     public MultiThreadServer() throws IOException{
          serverSocket=new ServerSocket(port);
          System.out.println("监听服务启动");
     }
       /**    
    	*<br>函数名：service     
    	*<br>作  用：接受客户端的连接，并启动工作线程
        */
     public void service() throws IOException{
          while(true){
         	 Socket socket = null;
        	 socket = serverSocket.accept();   //接收客户连接,只要客户进行了连接,就会触发accept()，从而建立连接
             Thread workThread = new Thread(new Handler(socket));
             workThread.start();
          } 
      }
 }
/**
 * <p>类名：Handler
 * <p>作用：工作线程，接受客户端所发socket并做出回应
 */
class Handler implements Runnable{
          Socket socket;
       /**    
      	*<br>函数名：Handler    
      	*<br>作  用：Handler的构造函数，从调用者那里取得socket
        */
     public Handler(Socket socket){
          this.socket=socket;
     }
      private BufferedReader getReader(Socket socket) throws IOException{
          InputStream socketIn=socket.getInputStream();
          return new BufferedReader(new InputStreamReader(socketIn));
     }
      /**    
       *<br>函数名：run    
       *<br>作  用：线程的执行方法
      */
     public void run(){
          try{
               System.out.println("服务器"+socket.getInetAddress()+"在线");
               BufferedReader br=getReader(socket);
               while((br.readLine())!=null){
            	   OutputStream socketOut = socket.getOutputStream();
                   socketOut.write("shutdown\r\n".getBytes());
               }
    	      }catch (Exception e) {
    		       System.out.println("服务器"+socket.getInetAddress()+"已掉线");
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

