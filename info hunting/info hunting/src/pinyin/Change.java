package pinyin;

import java.io.UnsupportedEncodingException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.sql.*; 
import java.util.*;
/**
 * <p>类名：Change
 * <p>作用：生成拼音词库
 * <p>@author :born to try
 */
 public class Change  {
	 String path="";
	 public Change (String aa){
		 path=aa;
	 }

	public void getkeyPinYin() throws UnsupportedEncodingException, ClassNotFoundException, SQLException{	
		String dbURL = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)}; DBQ=" + path;   			  
   	    String   user="";   
	    String   password="";   
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");     
	    Connection con=DriverManager.getConnection(dbURL,user,password);   

	    Statement   s=con.createStatement();   
	    ResultSet rs=null;
		
	    
		String tomcat=getPath("webapps");       
        String filepath= tomcat+"\\"+"test.xml";

      	SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read("file:\\"+filepath);
		} catch (DocumentException e) {
			e.printStackTrace();
		} 

		Element root = document.getRootElement(); 
		Element foo = null; 

		Iterator  it = root.elementIterator("resourceitem"); 
				
		while (it.hasNext()) { 				
			foo =  (Element) it.next(); 			
			String keywords= foo.elementText("keywords"); 
		
			if(keywords!=null ){	
			 //多关键字以逗号拆分
		    String[] chai;
            if(keywords.indexOf(",")!= -1){
          	  chai=keywords.split(",");
            }else{
          	  chai=keywords.split("，");
            }
            //多关键字以逗号拆分
			
			for(int a=0;a<chai.length;a++){
			    String yin=CntoSpell.makeStringByStringSet(CntoSpell.getPinyin(chai[a].trim()));
			    String[] pinyin=null;
			    if(yin!=null){
			    	if(yin.indexOf(",")!= -1){
			    		pinyin=yin.split(",");
			    	}else{
			          	  pinyin=yin.split("，");
		            }
			    }
			    for(int b=0;b<pinyin.length;b++){
			       NotExist  in=new NotExist();
	               if(in.notin(chai[a].trim())){
		             String	 sql="insert into change values('"+pinyin[b]+"','"+chai[a]+"')";
		             int tt=s.executeUpdate(sql);  
		  //   s.addBatch(sql);
	               }
			    }
			}
		}

}		
	s.close();
	con.close();     
}	
	
	public String getPath(String Webapps){
	   String path=System.getProperty("catalina.home");
	   int binindex=path.lastIndexOf("bin");
       if(binindex!=-1){
       	  path=path.substring(0, binindex-1);
       }		
		path=path+"\\"+Webapps;
		return path;	
	}	
}
