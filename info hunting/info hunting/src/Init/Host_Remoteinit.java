package Init;
/**
 * <p>������Host_Remoteinit
 * <p>���ã���¼��IP.xml�л�ȡ����Ϣ
 * <p>@author: born to try
 */
public class Host_Remoteinit {
    public static String HostIP;        //��¼���� IP ��ַ
    public static String RemoteIP;      //��¼��Ҫ���ӵ���������IP��ַ
    /** 
	* <br>��������getHostip
	* <br>��  �ã� ��ȡ����IP��ַ
	* <br>��  ���� ��
	* <br>�������ͣ� String 
	*/
    public static String getHostIP()  
    {
       return HostIP;
     }
    /** 
	* <br>��������setHostip
	* <br>��  �ã� ���ñ���IP��ַ
	* <br>��  ���� hostip
	* <br>�������ͣ� �� 
	*/
    public static void setHostIP(String hostIP) 
    {	
       HostIP = hostIP;
    }
    /** 
	* <br>��������getRemoteip
	* <br>��  �ã� ��ȡ��������IP��ַ
	* <br>��  ���� ��
	* <br>�������ͣ� String 
	*/
    public  static String getRemoteIP() 
    {
       return RemoteIP;
	}
    /** 
	* <br>��������setRemoteip
	* <br>��  �ã� ��ȡ����ip��ַ
	* <br>��  ���� remoteip
	* <br>�������ͣ� �� 
	*/
	public static void setRemoteIP(String remoteIP) 
	 {  	
       RemoteIP = remoteIP;
	}
 }

