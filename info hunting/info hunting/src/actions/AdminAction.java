package actions;
import actions.AdminSuperAction;
import dao.OpDB;

import actions.resourceAdd;


import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.*;
import Path.*;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.TermVector;
import org.apache.lucene.index.IndexWriter;

import actions.resourceAdd;
/**
 * <p>类名：AdminAction
 * <p>作用：管理员操作
 * <p>@author :born to try
 */
public class AdminAction extends AdminSuperAction {
	private static Connection conn = null;
	/** 功能：管理员操作-进行列表显示信息 */
	public String ListShow(){
		request.setAttribute("mainPage","../info/listshow.jsp");
		session.remove("adminOP");	
		String sqlall="";
		sqlall="SELECT * FROM tb_info ORDER BY upTime DESC";
			return SUCCESS;
	}

	
	/** 功能：管理员操作-显示要审核的信息 */
	public String CheckShow(){
		request.setAttribute("mainPage","../info/checkshow.jsp");		
		comebackState();
		String sql="SELECT * FROM tb_info WHERE (id = ?)";
		String checkID=request.getParameter("checkID");
		if(checkID==null||checkID.equals(""))
			checkID="-1";
		Object[] params={checkID};
		OpDB myOp=new OpDB();
		infoSingle=myOp.OpSingleShow(sql, params);		
		if(infoSingle==null){			//信息不存在
			request.setAttribute("mainPage","/pages/error.jsp");
			addFieldError("AdminShowNoExist",getText("city.singleshow.no.exist"));
		}
		return SUCCESS;
	}
	
	/** 功能：管理员操作-审核信息(更新数据库) */
	public String Check()throws Exception{
		session.put("adminOP","Check");			//记录当前操作为“审核信息”			

				
		String checkID=request.getParameter("checkID");
		String sql="UPDATE tb_info SET fileState = 1 WHERE (id = ?)";

		Object[] params={checkID};
		
		OpDB myOp=new OpDB();
		int i=myOp.OpUpdate(sql, params);
		if(i>0){								//审核信息成功			
			return "checkSuccess";			
		}
		else{									//审核信息失败
			comebackState();
			addFieldError("AdminCheckUnSuccess",getText("city.admin.check.no.success"));			
			request.setAttribute("mainPage","/pages/error.jsp");
			return "UnSuccess";
		}
	}
	/** 功能：管理员操作-删除信息(更新数据库) */
	public String Delete(){
		session.put("adminOP","Delete");		//记录当前操作为“删除信息”			
		
		String deleteID=request.getParameter("deleteID");
		String sql="DELETE from tb_info WHERE (id = ?)";
		Object[] params={deleteID};
		
		OpDB myOp=new OpDB();
		int i=myOp.OpUpdate(sql, params);
		if(i>0){								//删除信息成功			
			return "deleteSuccess";			
		}
		else{									//删除信息失败
			comebackState();
			addFieldError("AdminDeleteUnSuccess",getText("city.admin.delete.no.success"));			
			request.setAttribute("mainPage","/pages/error.jsp");
			return "UnSuccess";
		}
	}	
	
	/** 功能：管理员操作-删除信息(更新数据库) */
	public String DeleteUser(){
		session.put("adminOP","Delete");		//记录当前操作为“删除信息”			
		
		String deleteID=request.getParameter("deleteID");
		String sql="DELETE from tb_newUser WHERE (id = ?)";
		Object[] params={deleteID};
		
		OpDB myOp=new OpDB();
		int i=myOp.OpUpdate(sql, params);
		if(i>0){								//删除信息成功			
			return "deleteSuccess";			
		}
		else{									//删除信息失败
			comebackState();
			addFieldError("AdminDeleteUnSuccess",getText("city.admin.delete.no.success"));			
			request.setAttribute("mainPage","/pages/error.jsp");
			return "UnSuccess";
		}
	}	
	



	public String Publish() throws Exception{
		

		String className="sun.jdbc.odbc.JdbcOdbcDriver";

		String url="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=" + RealPath.Path + "SQL\\db_sousuo.mdb";
		try{
			Class.forName(className);
		}catch(java.lang.ClassNotFoundException e){
			System.out.println("加载出错！");
		}
		try{
			Connection conn;
			String user="";
			String password="";

		
		conn=DriverManager.getConnection(url, user, password);
		Statement stmt = conn.createStatement();
		String sql = "select * from tb_info where fileState = '1'";
		
		ResultSet rs = stmt.executeQuery(sql);
		String tomcat=getPath("webapps");       
        String filepath= tomcat+"\\"+"index";
		File   indexDir = new File(filepath);        
		MMAnalyzer luceneAnalyzer = new MMAnalyzer();
        IndexWriter indexWriter = null;
		try {
			indexWriter = new IndexWriter(indexDir,luceneAnalyzer,false);
		} catch (IOException e1) {
		e1.printStackTrace();
		}
		Field Title;
		Field Keywords;
		Field Kind;
		Field Describe;
		Field Date;
		Field Url;
		Field Author;
		Field Publisher;
		while(rs.next()){

			resourceAdd ra = new resourceAdd();
			//获取webapps目录
		    String path=getPath("webapps");
		    //增加资源
				ra.addResourceitem();
				ra.addId();
			
				String files=rs.getString("fileName");
				System.out.println(files);
				ra.addTitle(files);
				String keywords=rs.getString("keywords");
				ra.addKeyWords(keywords);
				String kind = rs.getString("format");
				ra.addKind(kind);
				String describe = rs.getString("fileContent");
				ra.addDescribe(describe);
				String date = rs.getString("upTime");
				ra.addDate(date);
				String fileURL = rs.getString("fileURL");
				ra.addUrl(fileURL);
				String author=rs.getString("author");
				ra.addAuthor(author);
				String publisher = rs.getString("publisher");
				ra.addpublisher(publisher);
				ra.resouceWrite(path+"\\test.xml");
			//转向 发布成功界面
				Document document = new Document();    		  
    			Title=new Field("title", files, Field.Store.YES, Field.Index.TOKENIZED);
    			Keywords=new Field("keywords", keywords, Field.Store.YES, Field.Index.TOKENIZED,TermVector.YES);
    			Kind=new Field("kind", kind, Field.Store.YES, Field.Index.UN_TOKENIZED);
    			Describe=new Field("describe",describe,Field.Store.YES, Field.Index.TOKENIZED);
    			Date=new Field("date", date, Field.Store.YES, Field.Index.UN_TOKENIZED);
    			Url=new Field("url", fileURL, Field.Store.YES, Field.Index.NO);
    			Author=new Field("author", author, Field.Store.YES, Field.Index.UN_TOKENIZED);
    			Publisher=new Field("publisher", publisher, Field.Store.YES, Field.Index.UN_TOKENIZED);	    			
        		document.add(Title);	
        		document.add(Keywords);	
        		document.add(Kind);
        		document.add(Describe);
        		document.add(Date);
        		document.add(Url);
        		document.add(Author);
        		document.add(Publisher);
        		indexWriter.addDocument(document);	
        		System.out.print("已经为新添加的资源建立好索引");
			
		}
		indexWriter.setUseCompoundFile(true);
		indexWriter.optimize();
		indexWriter.close();
		PreparedStatement ps= conn.prepareStatement("delete from tb_info where (fileState='1')");
		ps.executeUpdate();
		stmt.close();
		conn.close();
		}catch(SQLException e){
			System.out.println("SQL出错！");
		}
			return SUCCESS;
		
		
	}
public String getPath(String Webapps){
		
		//在本地目录下新建一个文件存放结果用于测试 检索是否正确
		String   path   =  System.getProperty("catalina.home");	
		
		/*因为不同的环境中 user。dir指向的有的是tomcat根目录，有的是bin目录，
		 * 所以 下面对 bin的位置进行判断，如果目录中含有bin则，进行 去掉bin目录的处理
		 * 这样 就能得到 tomcat的根目录了。
		 */
		int binindex=path.lastIndexOf("bin");
       if(binindex!=-1){
       	path=path.substring(0, binindex-1);
       }
		
		path=path+"\\"+Webapps;
		return path;
		
	}
	
	
	/** 功能：恢复在“显示方式”中的选择状态 */
	private void comebackState(){
		/* 获取session中保存的选择状态。
		 * 将选择状态保存在session中，
		 * 是在管理员单击“显示”按钮请求列表显示时，
		 * 在ListShow()方法中实现的
		 */
		Integer getFileType=(Integer)session.get("fileType");
		String getFileState=(String)session.get("fileState");
		
		/* 恢复选择的状态 */
		if(getFileState!=null&&getFileType!=null){			
			showType.setFileType(getFileType.intValue());	

			showType.setFileState(getFileState);			
		}
	}
}
