package Path;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import Path.RealPath;
/**
 * <p>������GetRealPath
 * <p>���ã��ṩ�����ļ�����ʵ·��
 * <p>@author: born to try
 */
public class GetRealPath extends HttpServlet{
	/** 
	* <br>��������GetRealPath
	* <br>��  �ã� ���캯��
	* <br>��  ���� ��
	*/
   public GetRealPath(){
       super();
    }
   /** 
	* <br>��������destroy
	* <br>��  ���� ��
	* <br>�������ͣ� �� 
	*/
   public void destroy(){
       super.destroy();
   }
   /** 
	* <br>��������init
	* <br>��  �ã�ServletContext�ӿ��ṩ����SQL�ļ���;��
	* <br>��  ���� ��
	* <br>�������ͣ� �� 
	*/
   public void init() throws ServletException{
       ServletContext context=getServletContext();
       String path=context.getRealPath("/")+"SQL\\";
       RealPath.Path=context.getRealPath("/");
   }
}
	