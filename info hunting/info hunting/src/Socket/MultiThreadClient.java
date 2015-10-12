package Socket;
import java.io.*;
import java.net.Socket;
import Distributed.*;
import Path.*;
/**
 * <p>类名：MultiThreadClient
 * <p>作用：建立Socket通信的客户端，向服务器端发送信息
 * <p>@author : born to try
 */
public class MultiThreadClient extends Thread {
	/**    
	*<br>函数名：run     
	*<br>作  用：工作线程，向服务器端发送信息，并接受服务器端反馈信息，监听服务器端状态
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
                		System.out.println("主服务器已不在线,正在选举主服务器，请稍候……");
                		IPlist.iplist.remove(goalip);
       			        WriteIPlist.go();
       			        ReadIPlist.go();
                        Select_MainServer.go();        //选举算法，选举出新主服务器
                        }catch(Exception ee){
                		System.out.println("错误"+ee);
                	 }
             }
      }
}
            

        
    

