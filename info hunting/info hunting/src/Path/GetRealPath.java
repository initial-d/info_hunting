package Path;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import Path.RealPath;
/**
 * <p>类名：GetRealPath
 * <p>作用：提供访问文件的真实路径
 * <p>@author: born to try
 */
public class GetRealPath extends HttpServlet{
	/** 
	* <br>函数名：GetRealPath
	* <br>作  用： 构造函数
	* <br>参  数： 无
	*/
   public GetRealPath(){
       super();
    }
   /** 
	* <br>函数名：destroy
	* <br>参  数： 无
	* <br>返回类型： 空 
	*/
   public void destroy(){
       super.destroy();
   }
   /** 
	* <br>函数名：init
	* <br>作  用：ServletContext接口提供访问SQL文件的途径
	* <br>参  数： 无
	* <br>返回类型： 空 
	*/
   public void init() throws ServletException{
       ServletContext context=getServletContext();
       String path=context.getRealPath("/")+"SQL\\";
       RealPath.Path=context.getRealPath("/");
   }
}
	