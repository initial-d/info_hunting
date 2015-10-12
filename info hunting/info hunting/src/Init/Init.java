package Init;
import java.rmi.registry.LocateRegistry;
import javax.servlet.*;
import javax.servlet.http.*;
import Distributed.*;
import XML.*;
import Thread.*;
/**
 * <p>������Init
 * <p>���ã�RMIԶ�̵��õĳ�ʼ������
 * <p>@author: born to try
 */
public class Init extends HttpServlet{
	/** 
	* <br>��������Init
	* <br>��  �ã� ���캯��
	* <br>��  ���� ��
	*/
   public Init()
    {
      super();
    }
   /** 
	* <br>��������init
	* <br>��  �ã�servlet�ĳ�ʼ������,����RMIע�����
	* <br>��  ���� ��
	* <br>�������ͣ� �� 
	*/
   public void init() throws ServletException{
      CreateIPxml create=new CreateIPxml();               
	  try {
			create.create_xml();                         //���ɼ�¼������Ϣ��IP.xml
			LocateRegistry.createRegistry(1099);         //����RMIע�����
			new Start_Server();
		  } catch (Exception e) {
			e.printStackTrace();
		}
      System.out.println("��ʼ�����");
 }
   /** 
	* <br>��������destroy
	* <br>��  �ã� ϵͳ�˳�����ע����Ϣ
	* <br>��  ���� ��
	* <br>�������ͣ� �� 
	*/
   public void destroy() {			
	  super.destroy(); 
	  IPlist.iplist.remove(Host_Remoteinit.HostIP);
	  for(int y=0;y<IPlist.iplist.size();y++){
      if(!(IPlist.iplist.get(y).toString().equals(Host_Remoteinit.getHostIP()))){
		   Notice_EachServer notice=new Notice_EachServer(IPlist.iplist.get(y).toString(),Host_Remoteinit.getHostIP());
              notice.DeleteMessage();
              }
		     }	
		}
}