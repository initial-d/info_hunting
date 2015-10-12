package actions;

import java.net.InetAddress;

import Distributed.*;
import Socket.*;
import XML.Change_RemoteIP;
/**
 * <p>������serverSet
 * <p>���ã�����������
 * <p>@author :born to try
 */
public class serverSet extends InfoSuperAction{
	String server;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
	public String execute() throws Exception{
		request.setAttribute("mainPage","/serversuccess.jsp");
		Change_RemoteIP setsver=new Change_RemoteIP();
		setsver.change_remote(server);
		InetAddress IP=InetAddress.getLocalHost();
	    String LocalIP=IP.getHostAddress();
	    
		if(server.equals(LocalIP))
		{
			
		new MultiThreadServer().service();
		
		}
		
		else
		{   
			MultiThreadClient a=new MultiThreadClient();
			a.run(server);
	    	a.start();
	    	}
		
		return "success";
	}

}
