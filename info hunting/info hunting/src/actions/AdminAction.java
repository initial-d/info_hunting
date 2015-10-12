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
 * <p>������AdminAction
 * <p>���ã�����Ա����
 * <p>@author :born to try
 */
public class AdminAction extends AdminSuperAction {
	private static Connection conn = null;
	/** ���ܣ�����Ա����-�����б���ʾ��Ϣ */
	public String ListShow(){
		request.setAttribute("mainPage","../info/listshow.jsp");
		session.remove("adminOP");	
		String sqlall="";
		sqlall="SELECT * FROM tb_info ORDER BY upTime DESC";
			return SUCCESS;
	}

	
	/** ���ܣ�����Ա����-��ʾҪ��˵���Ϣ */
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
		if(infoSingle==null){			//��Ϣ������
			request.setAttribute("mainPage","/pages/error.jsp");
			addFieldError("AdminShowNoExist",getText("city.singleshow.no.exist"));
		}
		return SUCCESS;
	}
	
	/** ���ܣ�����Ա����-�����Ϣ(�������ݿ�) */
	public String Check()throws Exception{
		session.put("adminOP","Check");			//��¼��ǰ����Ϊ�������Ϣ��			

				
		String checkID=request.getParameter("checkID");
		String sql="UPDATE tb_info SET fileState = 1 WHERE (id = ?)";

		Object[] params={checkID};
		
		OpDB myOp=new OpDB();
		int i=myOp.OpUpdate(sql, params);
		if(i>0){								//�����Ϣ�ɹ�			
			return "checkSuccess";			
		}
		else{									//�����Ϣʧ��
			comebackState();
			addFieldError("AdminCheckUnSuccess",getText("city.admin.check.no.success"));			
			request.setAttribute("mainPage","/pages/error.jsp");
			return "UnSuccess";
		}
	}
	/** ���ܣ�����Ա����-ɾ����Ϣ(�������ݿ�) */
	public String Delete(){
		session.put("adminOP","Delete");		//��¼��ǰ����Ϊ��ɾ����Ϣ��			
		
		String deleteID=request.getParameter("deleteID");
		String sql="DELETE from tb_info WHERE (id = ?)";
		Object[] params={deleteID};
		
		OpDB myOp=new OpDB();
		int i=myOp.OpUpdate(sql, params);
		if(i>0){								//ɾ����Ϣ�ɹ�			
			return "deleteSuccess";			
		}
		else{									//ɾ����Ϣʧ��
			comebackState();
			addFieldError("AdminDeleteUnSuccess",getText("city.admin.delete.no.success"));			
			request.setAttribute("mainPage","/pages/error.jsp");
			return "UnSuccess";
		}
	}	
	
	/** ���ܣ�����Ա����-ɾ����Ϣ(�������ݿ�) */
	public String DeleteUser(){
		session.put("adminOP","Delete");		//��¼��ǰ����Ϊ��ɾ����Ϣ��			
		
		String deleteID=request.getParameter("deleteID");
		String sql="DELETE from tb_newUser WHERE (id = ?)";
		Object[] params={deleteID};
		
		OpDB myOp=new OpDB();
		int i=myOp.OpUpdate(sql, params);
		if(i>0){								//ɾ����Ϣ�ɹ�			
			return "deleteSuccess";			
		}
		else{									//ɾ����Ϣʧ��
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
			System.out.println("���س���");
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
			//��ȡwebappsĿ¼
		    String path=getPath("webapps");
		    //������Դ
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
			//ת�� �����ɹ�����
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
        		System.out.print("�Ѿ�Ϊ����ӵ���Դ����������");
			
		}
		indexWriter.setUseCompoundFile(true);
		indexWriter.optimize();
		indexWriter.close();
		PreparedStatement ps= conn.prepareStatement("delete from tb_info where (fileState='1')");
		ps.executeUpdate();
		stmt.close();
		conn.close();
		}catch(SQLException e){
			System.out.println("SQL����");
		}
			return SUCCESS;
		
		
	}
public String getPath(String Webapps){
		
		//�ڱ���Ŀ¼���½�һ���ļ���Ž�����ڲ��� �����Ƿ���ȷ
		String   path   =  System.getProperty("catalina.home");	
		
		/*��Ϊ��ͬ�Ļ����� user��dirָ����е���tomcat��Ŀ¼���е���binĿ¼��
		 * ���� ����� bin��λ�ý����жϣ����Ŀ¼�к���bin�򣬽��� ȥ��binĿ¼�Ĵ���
		 * ���� ���ܵõ� tomcat�ĸ�Ŀ¼�ˡ�
		 */
		int binindex=path.lastIndexOf("bin");
       if(binindex!=-1){
       	path=path.substring(0, binindex-1);
       }
		
		path=path+"\\"+Webapps;
		return path;
		
	}
	
	
	/** ���ܣ��ָ��ڡ���ʾ��ʽ���е�ѡ��״̬ */
	private void comebackState(){
		/* ��ȡsession�б����ѡ��״̬��
		 * ��ѡ��״̬������session�У�
		 * ���ڹ���Ա��������ʾ����ť�����б���ʾʱ��
		 * ��ListShow()������ʵ�ֵ�
		 */
		Integer getFileType=(Integer)session.get("fileType");
		String getFileState=(String)session.get("fileState");
		
		/* �ָ�ѡ���״̬ */
		if(getFileState!=null&&getFileType!=null){			
			showType.setFileType(getFileType.intValue());	

			showType.setFileState(getFileState);			
		}
	}
}
