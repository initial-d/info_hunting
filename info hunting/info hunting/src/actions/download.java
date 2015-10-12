package actions;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import duqu.URLEncoderHZ;
/**
 * <p>类名：download
 * <p>作用：文件下载
 * <p>@author :born to try
 */
public class download extends MySuperAction{
	private String contentType;
	private String fileName;
	public String getFileName() {
		
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	private String kind;
	private String url1;
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getUrl1() {
		return url1;
	}
	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	
	
	    public InputStream getInputStream() throws Exception{
    	InputStream  inputStream=null; 
    	String path;
    	path=URLEncoderHZ.encode(url1, "utf-8");
    	URL url = new URL(path);
		inputStream  = url.openStream();
    //	System.out.print(fileName);
    //	System.out.print(inputStream);
    	return inputStream;
    }
   
	public String execute() throws Exception{
		System.out.print(kind);
		System.out.print(url1);	
		String url2=url1.substring(url1.indexOf("resources/"));
	    fileName=url2.substring(10);
	    fileName=new String(getFileName().getBytes(),"ISO8859-1");
	    System.out.print(fileName);
    	return "success";
    }
}
