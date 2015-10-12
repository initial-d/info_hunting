package model;

import java.util.*;
/**
 * <p>类名：InfoSingle
 * <p>作用：封装信息
 * <p>@author :born to try
 */
public class InfoSingle {
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
	private String fileName;
	private String fileSize;
	private String fileURL;
	private String fileContent;
	private String format;

	private String upTime = null;
	private int fileType;
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
	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	public String getFileState() {
		return fileState;
	}
	public void setFileState(String fileState) {
		this.fileState = fileState;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getSubInfoTitle(int len){
		if(len<=0||len>this.fileName.length())
			len=this.fileName.length();
		return this.fileName.substring(0,len);		
	}
}
