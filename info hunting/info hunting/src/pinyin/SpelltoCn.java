package pinyin;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import Path.RealPath;

/**
 * <p>������Spelltocn
 * <p>���ã������滻�ַ�������ĺ��ֲ��֡�
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
		System.out.println("ƴ���ʿ������.........");
		ServletContext context = getServletContext(); 
		String path=context.getRealPath("/") + "SQL\\PinYin.mdb";
		RealPath.Path=context.getRealPath("/");
	try{	
		Change change=new Change (path);
		change.getkeyPinYin();
		
		System.out.println("ƴ���ʿ���سɹ�");
	}catch(Exception e){System.out.println("ƴ���ʿ����ʧ��");}

	
	}

 
}
