package beans;

import java.io.Serializable;
/**
 * <p>类名：xmlbean
 * <p>作用：封装信息
 * <p>@author :born to try
 */
public class xmlbean implements Serializable{
   private	 String id;
   private   String title;
   private   String keywords; 
   private   String kind;
   private   String describe;
   private   String date;
   private   String url; 
   private   String author;
   private   String publisher;
   private   float    scores;
public float getScores() {
	return scores;
}
public void setScores(float scores) {
	this.scores = scores;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getKeywords() {
	return keywords;
}
public void setKeywords(String keywords) {
	this.keywords = keywords;
}
public String getKind() {
	return kind;
}
public void setKind(String kind) {
	this.kind = kind;
}
public String getDescribe() {
	return describe;
}
public void setDescribe(String describe) {
	this.describe = describe;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
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



}
