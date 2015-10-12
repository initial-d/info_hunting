package actions;
import java.io.UnsupportedEncodingException;

import duqu.Extractor;
import actions.MySuperAction;
/**
 * <p>类名：readWord
 * <p>作用：读取word
 * <p>@author :born to try
 */
public class readWord  extends  MySuperAction{
	private  String doc;
    private  String url;
    private  String text1;
    private  String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
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
			Extractor e = new Extractor();
		     text1 = e.getWord(doc);			    
		System.out.println(text1);
		 } catch (Exception e1) {
				e1.printStackTrace();
			} 
		 request.setAttribute("word",text1);
	 return "input";
}
	 
}
