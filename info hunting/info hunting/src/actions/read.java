package actions;
import java.io.UnsupportedEncodingException;
import duqu.Extractor;
import duqu.PptReader;
import actions.MySuperAction;
/**
 * <p>类名：read
 * <p>作用：预览
 * <p>@author :born to try
 */
public class read  extends  MySuperAction{
	private  String doc;
    private  String kind;
    private  String text1;
    private  String flag;
    private  String title;
    private  String author;
    private String publisher;
    private String describe;
    
    
    public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	 public String execute() {
		String doc=request.getParameter("doc");
		String kind=request.getParameter("kind");
		String title=request.getParameter("title");

		if(kind.equals("doc"))
		{
			try{ 
			Extractor e = new Extractor();
		    text1 = e.getWord(doc);	
	     	
		 } catch (Exception e1) {
				e1.printStackTrace();
			} 
	    request.setAttribute("word",text1);
	    request.setAttribute("title",title);
	    request.setAttribute("url",doc); 
	 //   request.setAttribute("author",author);
	//	request.setAttribute("publisher",publisher);
	//	request.setAttribute("describe",describe);
	    flag="input";
		}
		else if(kind.equals("ppt")){
		try{ 
			
		      PptReader  p=new PptReader();
			  text1=p.doPPTtoImage(doc);
	          System.out.println(text1);
	          } catch (Exception e1) {
		 	    e1.printStackTrace();
		      } 
	          request.setAttribute("url",doc); 
              request.setAttribute("ppt",text1);
              request.setAttribute("title",title);
              request.setAttribute("author",author);
      		  request.setAttribute("publisher",publisher);
      		  request.setAttribute("describe",describe);
              flag="ppt";
  }     
		else if(kind.equals("mp4")||kind.equals("avi")||kind.equals("flv")||kind.equals("rmvb")){
			try {
				doc=new String(getDoc().getBytes(),"ISO8859-1");
			
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 request.setAttribute("url",doc); 
			 request.setAttribute("author",author);
			 request.setAttribute("publisher",publisher);
			 request.setAttribute("describe",describe);
			 request.setAttribute("title",title);
			 flag="vedio";
		}
		else if(kind.equals("mp3")||kind.equals("wma")){
			try {
				doc=new String(getDoc().getBytes(),"ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 request.setAttribute("url",doc); 
			 request.setAttribute("author",author);
			 request.setAttribute("publisher",publisher);
			 request.setAttribute("describe",describe);
			 request.setAttribute("title",title);
			 flag="music";
		}
		else if(kind.equals("jpg")||kind.equals("gif")){
			try {
				doc=new String(getDoc().getBytes(),"ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 request.setAttribute("url",doc); 
			 request.setAttribute("title",title);
			 request.setAttribute("author",author);
			 request.setAttribute("publisher",publisher);
			 request.setAttribute("describe",describe);
			 flag="picture";
		}
	          return flag;
		
		
}
	 
}
