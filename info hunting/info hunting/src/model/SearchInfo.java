package model;
/**
 * <p>类名：SearchInfo
 * <p>作用：封装信息
 * <p>@author :born to try
 */
public class SearchInfo {
	private String subsql;
	private String sqlvalue;
	private String type="all";

	public String getSqlvalue() {
		return sqlvalue;
	}
	public void setSqlvalue(String sqlvalue) {
		this.sqlvalue = sqlvalue;
	}
	public String getSubsql() {
		return subsql;
	}
	public void setSubsql(String subsql) {
		this.subsql = subsql;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
}
