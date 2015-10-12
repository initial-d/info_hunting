package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import model.InfoSingle;
import tools.DoString;

/**
 * <p>类名：OpDB
 * <p>作用：操作数据库
 * <p>@author :born to try
 */
public class OpDB {
	private DB mydb;
	public OpDB(){
		mydb=new DB();	
	}
	
	public TreeMap OpGetListBox(String sql,Object[] params){
		TreeMap typeMap=new TreeMap();
		mydb.doPstm(sql, params);
		try {
			ResultSet rs=mydb.getRs();
			if(rs!=null){
				while(rs.next()){					
					Integer sign=Integer.valueOf(rs.getInt("type_sign"));
					String intro=rs.getString("type_intro");
					typeMap.put(sign,intro);					
				}
				rs.close();
			}
		} catch (SQLException e) {					
			e.printStackTrace();
		}finally{
			mydb.closed();			
		}
		return typeMap;	
	}
	
	public List OpListShow(String sql,Object[] params){
		List onelist=new ArrayList();
		mydb.doPstm(sql, params);
		try{
			ResultSet rs=mydb.getRs();
			if(rs!=null){
				while(rs.next()){
					InfoSingle infoSingle=new InfoSingle();
					infoSingle.setId(rs.getInt("id"));
					infoSingle.setFileName(rs.getString("fileName"));
					infoSingle.setFileSize(rs.getString("fileSize"));
					infoSingle.setFileURL(rs.getString("fileURL"));
					infoSingle.setFormat(rs.getString("format"));
					infoSingle.setAuthor(rs.getString("author"));
					infoSingle.setFileContent(rs.getString("fileContent"));
					infoSingle.setPublisher(rs.getString("publisher"));
					infoSingle.setKeywords(rs.getString("keywords"));

					infoSingle.setUpTime(DoString.dateTimeChange(rs.getTimestamp("upTime")));

					infoSingle.setFileState(rs.getString("fileState"));
	
					onelist.add(infoSingle);					
				}
			}
			rs.close();
		}catch (Exception e){		
			e.printStackTrace();
		}finally{
			mydb.closed();			
		}
		return onelist;		
	}
	
	public InfoSingle OpSingleShow(String sql,Object[] params){
		InfoSingle infoSingle=null;
		mydb.doPstm(sql, params);       
		try{
		    ResultSet rs=mydb.getRs();
			if(rs!=null&&rs.next()){
				infoSingle=new InfoSingle();
				infoSingle.setId(rs.getInt("id"));
				infoSingle.setFileName(rs.getString("fileName"));
				infoSingle.setFileSize(rs.getString("fileSize"));
				infoSingle.setFileURL(rs.getString("fileURL"));
				infoSingle.setFormat(rs.getString("format"));
				infoSingle.setAuthor(rs.getString("author"));
				infoSingle.setFileContent(rs.getString("fileContent"));
				infoSingle.setPublisher(rs.getString("publisher"));
				infoSingle.setKeywords(rs.getString("keywords"));
				infoSingle.setUpTime(DoString.dateTimeChange(rs.getTimestamp("upTime")));

				infoSingle.setFileState(rs.getString("fileState"));

				rs.close();				
			}
		}catch(Exception e){
			e.printStackTrace();			
		}finally{
			mydb.closed();
		}
		return infoSingle;
	}
	
	public int OpUpdate(String sql,Object[] params){		
		int i=-1;
		mydb.doPstm(sql, params);
		try{
			i=mydb.getCount();			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			mydb.closed();
		}
		return i;
	}
	
	
	
	public boolean LogOn(String sql,Object[] params){
		mydb.doPstm(sql, params);
		try {
			ResultSet rs=mydb.getRs();
			boolean mark=(rs==null||!rs.next()?false:true);
			rs.close();
			return mark;			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			mydb.closed();
		}
	}
}
