package pinyin;

import java.sql.*;
import Path.*;
/**
 * <p>������SpellSearch
 * <p>���ã��滻����
 * <p>@author :born to try
 */
public class SpellSearch {
	public String Search(String txt){ 
		String str="";
		try{
			 String   dbUrl="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)}; DBQ=" + RealPath.Path + "SQL\\PinYin.mdb";  
			 String   user="";   
			 String   password="";   
			 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		  Connection c=DriverManager.getConnection(dbUrl,user,password);
		  Statement   s=c.createStatement();   
		  ResultSet   rs=s.executeQuery("select * from change where yin='"+txt+"'"); 
		  if(rs.next()){ 
			  do{ 
			 String hanzi=rs.getString("zi");
			 System.out.println(hanzi);
			 if(str.indexOf(hanzi)==-1)
                str+=hanzi+" ";
			  
		  }while(rs.next());

		  rs.close();
		  s.close();
		  c.close();   
		  System.out.println("��Ҫ���ҵ��Ƿ�Ϊ��  "+str);
		  }else{
				str=txt;
		  }	
		  return str;
		}catch(Exception e){ return txt; }		
	}
	
	
}
