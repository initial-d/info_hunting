package pinyin;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import Path.RealPath;

/**
 * <p>类名：Spelltocn
 * <p>作用：正则替换字符串里面的汉字部分。
 * <p>@author :born to try
 */
public class SpelltoCn extends HttpServlet {

	public SpelltoCn() {
		super();
	}


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void init() throws ServletException{ 
		System.out.println("拼音词库加载中.........");
		ServletContext context = getServletContext(); 
		String path=context.getRealPath("/") + "SQL\\PinYin.mdb";
		RealPath.Path=context.getRealPath("/");
	try{	
		Change change=new Change (path);
		change.getkeyPinYin();
		
		System.out.println("拼音词库加载成功");
	}catch(Exception e){System.out.println("拼音词库加载失败");}

	
	}

 
}
