package pinyin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import Path.RealPath;

public class NotExist {
   public boolean notin(String str){
		boolean ret=false;
		try{
	      String dbURL="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)}; DBQ=" + RealPath.Path + "SQL\\PinYin.mdb";
		  String   user="";
		  String   password="";   
		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");   
		  Connection con=DriverManager.getConnection(dbURL,user,password);
		  Statement   s=con.createStatement();   
	
		  ResultSet   r=s.executeQuery("select * from change where zi='"+str.trim()+"'");   
		
			  if(r.next()){
				  ret = false;
		      }else{
			   ret = true;
		      } 
			  
		  r.close();
		  s.close();
		  con.close();   

		  return ret;
		}catch(Exception e){ return false;}		   
   }
}