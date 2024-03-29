package dao;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Path.*;
/**
 * <p>类名：DB
 * <p>作用：连接数据库
 * <p>@author :born to try
 */
public class DB {
	private Connection con;
	private PreparedStatement pstm;
	private String user="";
	private String password="";
	private String className="sun.jdbc.odbc.JdbcOdbcDriver";
	

	private String url="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=" + RealPath.Path + "SQL\\db_sousuo.mdb";
	
	public DB(){
		try{
			Class.forName(className);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	/**创建数据库连接*/
	public Connection getCon(){
		try {
			con=DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			con=null;
			e.printStackTrace();
		}
		return con;
	}
	
	public void doPstm(String sql,Object[] params){
		if(sql!=null&&!sql.equals("")){
			if(params==null)
				params=new Object[0];
			
			getCon();
			if(con!=null){
				try{		
					pstm=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					for(int i=0;i<params.length;i++){
						pstm.setObject(i+1,params[i]);
					}
					pstm.execute();
				}catch(SQLException e){
					e.printStackTrace();
				}				
			}			
		}
	}
	
	public ResultSet getRs() throws SQLException{
		return pstm.getResultSet();		
	}
	public int getCount() throws SQLException{
		return pstm.getUpdateCount();		
	}
	public void closed(){
		try{
			if(pstm!=null)
				pstm.close();			
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			if(con!=null){
				con.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
