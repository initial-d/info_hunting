package actions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.io.File;
import java.net.InetAddress;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.sql.*;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

//import com.jspsmart.upload.SmartUpload;

import com.opensymphony.xwork2.util.finder.ClassFinder.Info;
import actions.InfoSuperAction;
import dao.OpDB;
import tools.DoString;
/**
 * <p>类名：InfoA
 * <p>作用：文件上传
 * <p>@author :born to try
 */
public class InfoA extends InfoSuperAction {
	
	private File doc;
	private String fileName;
	private String contentType;
	private String dir;
	private String targetFileName;
	private int type;
	private int id;
	private int infoType;
	private String infoTitle;
	private String infoContent;
	private String infoLinkman;
	private String infoPhone;
	private String infoEmail;
	private String infoDate; 
	private String infoState;
	private String infoPayfor;
	private String fileSize;
	private String fileURL;
	private String fileContent;
	private String format;


	private String upTime = null;
	private String fileType;
	private String fileState;

	private String author;
	private String publisher;
	private String keywords;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInfoContent() {
		return infoContent;
	}
	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}
	public String getInfoDate() {
		return infoDate;
	}
	public void setInfoDate(String infoDate) {
		this.infoDate = infoDate;
	}
	public String getInfoLinkman() {
		return infoLinkman;
	}
	public void setInfoLinkman(String infoLinkman) {
		this.infoLinkman = infoLinkman;
	}
	public String getInfoPayfor() {
		return infoPayfor;
	}
	public void setInfoPayfor(String infoPayfor) {
		this.infoPayfor = infoPayfor;
	}
	public String getInfoPhone() {
		return infoPhone;
	}
	public void setInfoPhone(String infoPhone) {
		this.infoPhone=infoPhone;
	}	
	public String getInfoEmail() {		
		return infoEmail;
	}
	public void setInfoEmail(String infoEmail) {
		this.infoEmail = infoEmail;
	}
	public String getInfoState() {
		return infoState;
	}
	public void setInfoState(String infoState) {
		this.infoState = infoState;
	}
	public String getInfoTitle() {
		return infoTitle;
	}
	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}
	public int getInfoType() {
		return infoType;
	}
	public void setInfoType(int infoType) {
		this.infoType = infoType;
	}
	public String getFileName(){
		return fileName;
	}
	public void setFileName(String fileName){
		this.fileName=fileName;
	}
	public String getFileSize(){
		return fileSize;
	}
	public void setFileSize(String fileSize){
		this.fileSize=fileSize;
	}
	public String getFileURL(){
		return fileURL;
	}
	public void setFileURL(String fileURL){
		this.fileURL=fileURL;
	}
	public String getFileContent(){
		return fileContent;
	}
	public void setFileContent(String fileContent){
		this.fileContent=fileContent;
	}
	public String getFormat(){
		return format;
	}
	public void setFormat(String format){
		this.format=format;
	}

	public String getUpTime(){
		return upTime;
	}	
	public void setUpTime(String upTime){
		this.upTime=upTime;
	}
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher(){
		return publisher;
	}
	public void setPublisher(String publisher){
		this.publisher = publisher;
	}

	public void setDoc(File file){
		this.doc = file;
	}
	public void setDocFileName(String fileName){
		this.fileName = fileName;
	}
	public void setDocContentType(String contentType){
		this.contentType = contentType;
	}

	private String generateFileName(String fileName){
		
		return fileName;  //新的文件名称
	}
	public String getDir(){
		return dir;
	}
	public void setDir(String dir){
		this.dir = dir;
	}
	public String getContentType(){
		return contentType;
	}
	public void setContentType(String contentType){
		this.contentType = contentType;
	}
	public String getTargetFileName(){
		return targetFileName;
	}
	public void setTargetFileName(String targetFileName){
		this.targetFileName = targetFileName;
	}
	public String getKeywords(){
		return keywords;
	}
	public void setKeywords(String keywords){
		this.keywords=keywords;
	}
	
	
	public String Add() throws Exception{
		
		String addType=request.getParameter("addType");
		if(addType==null||addType.equals("")){

			request.setAttribute("mainPage","/pages/add/addInfo.jsp");
			addType="linkTo";
		}

		if(addType.equals("add")){
			request.setAttribute("mainPage","/pages/error.jsp");
	
		OpDB myOp=new OpDB();		
		
		 String path = getPath("webapps");
	        String realPath= path+"\\"+"resources";
		//String realPath =  ServletActionContext.getServletContext().getRealPath("/upload"); //upload路径的实际目录
		String targetDirectory = realPath;
		targetFileName = generateFileName(fileName);  //生成保存文件的名称
		setDir(targetDirectory + "\\" + targetFileName);  //保存文件路径
		File target = new File(targetDirectory, targetFileName);  //建立目标文件
		FileUtils.copyFile(doc, target);  //把临时文件复制到目标文件
		
		int position = fileName.lastIndexOf(".");  //获得文件后缀名称
		String  format = fileName.substring(position + 1);
		String name=fileName.substring(0,position);
	
			int size=request.getContentLength();
			InetAddress   address   =   InetAddress.getLocalHost(); 
			String ipLocal=address.getHostAddress();
			String URL = "http://"+ipLocal+":8080/resources"+ "/" + targetFileName;

        	String date=DoString.dateTimeChange(new java.util.Date()); 		
			String state="0";
			String geshi=format;
			if( geshi.equals("pdf") ||  geshi.equals("doc") || geshi.equals("ppt") || geshi.equals("txt") || geshi.equals("xls")){
				type = 1;
			}
			
			if( geshi.equals("gif") ||  geshi.equals("jpg") || geshi.equals("jpeg") || geshi.equals("bmp")){
				type = 2;
			}
			
			
			if( geshi.equals("rmvb") ||  geshi.equals("wmv") || geshi.equals("mp4") || geshi.equals("avi")){
				type = 3;
			}
			
			if( geshi.equals("mp3") ||  geshi.equals("wma")){
				type = 4;
			}
			
		
			
			int id = new Random().nextInt(10000); 
			System.out.println(size);
			System.out.println(name+size+URL+fileContent+format+date+type+state+author+publisher+keywords);
			Object[] params={id,name,size,URL,fileContent,geshi,date,type,state,author,publisher,keywords};
			String sql="insert into tb_info values(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			
			
			
			
			
			int i=myOp.OpUpdate(sql,params);			
			if(i<=0)
				addFieldError("addE",getText("city.info.add.E"));				
			else{
				sql="select * from tb_info where upTime=?";
				Object[] params1={date};				
				int infoNum=myOp.OpSingleShow(sql, params1).getId();				
				addFieldError("addS",getText("city.info.add.S"));				
			}
		}
		
		return SUCCESS;
	}
		
		public void validateAdd(){	
			request.setAttribute("mainPage","/pages/add/addInfo.jsp");
		
			String addType=request.getParameter("addType");	
			if(addType==null||addType.equals(""))
				addType="linkTo";
			
			if(addType.equals("add")){			
				
				
				boolean mark=true;			
				
				int size=request.getContentLength();
				if(fileName==null||fileName.equals("")){
					mark=false;
					addFieldError("fileNameError",getText("city.info.no.fileName"));
				}
				if(keywords==null||keywords.equals("")){
					mark=false;
					addFieldError("keywordsError",getText("city.info.no.keywords"));
				}
				if(author==null||author.equals("")){
					mark=false;
					addFieldError("authorError",getText("city.info.no.author"));
				}
				if(publisher==null||publisher.equals("")){
					mark=false;
					addFieldError("publisherError",getText("city.info.no.publisher"));
				}
				if(fileContent==null||fileContent.equals("")){
					mark=false;
					addFieldError("fileContentError",getText("city.info.no.fileContent"));
				}
				
			}
		}
		public String getPath(String Webapps){
			
			//在本地目录下新建一个文件存放结果用于测试 检索是否正确
			String   path   =  System.getProperty("catalina.home");	
			
			//因为不同的环境中user.dir指向的有的是tomcat根目录，有的是bin目录，
			 // 所以下面对 bin的位置进行判断，如果目录中含有bin则进行去掉bin目录的处理
			int binindex=path.indexOf("bin");
	        if(binindex!=-1){
	        	path=path.substring(0, binindex-1);
	        }			
			path=path+"\\"+Webapps;
			return path;		    	
		}

		
	
}
