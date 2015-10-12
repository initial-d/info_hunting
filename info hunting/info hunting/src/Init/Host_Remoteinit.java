package Init;
/**
 * <p>类名：Host_Remoteinit
 * <p>作用：记录从IP.xml中获取的信息
 * <p>@author: born to try
 */
public class Host_Remoteinit {
    public static String HostIP;        //记录本机 IP 地址
    public static String RemoteIP;      //记录需要连接的主服务器IP地址
    /** 
	* <br>函数名：getHostip
	* <br>作  用： 获取本机IP地址
	* <br>参  数： 无
	* <br>返回类型： String 
	*/
    public static String getHostIP()  
    {
       return HostIP;
     }
    /** 
	* <br>函数名：setHostip
	* <br>作  用： 设置本机IP地址
	* <br>参  数： hostip
	* <br>返回类型： 空 
	*/
    public static void setHostIP(String hostIP) 
    {	
       HostIP = hostIP;
    }
    /** 
	* <br>函数名：getRemoteip
	* <br>作  用： 获取主服务器IP地址
	* <br>参  数： 无
	* <br>返回类型： String 
	*/
    public  static String getRemoteIP() 
    {
       return RemoteIP;
	}
    /** 
	* <br>函数名：setRemoteip
	* <br>作  用： 获取本机ip地址
	* <br>参  数： remoteip
	* <br>返回类型： 空 
	*/
	public static void setRemoteIP(String remoteIP) 
	 {  	
       RemoteIP = remoteIP;
	}
 }

