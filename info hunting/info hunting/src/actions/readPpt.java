package actions;
import java.io.UnsupportedEncodingException;

import duqu.Extractor;
import duqu.PptReader;
import actions.MySuperAction;
/**
 * <p>类名：readPpt
 * <p>作用：读取ppt
 * <p>@author :born to try
 */
public class readPpt  extends  MySuperAction{
	private  String doc;
    private  String url;
    private  String text1;
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	 public String execute() {
		String doc=request.getParameter("doc");
		System.out.print(doc);
	//	try {
	//		doc=new  String(doc.getBytes("ISO-8859-1"),"UTF-8");
	//	} catch (UnsupportedEncodingException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
		try{ 
			
			      PptReader  p=new PptReader();
				  String file = "http://localhost:8080/resources/p.ppt";
				  p.doPPTtoImage(file);
				 
		    
		System.out.println(text1);
		 } catch (Exception e1) {
				e1.printStackTrace();
			} 
	    request.setAttribute("word",text1);
	 return "input";
}
	 
}
