package actions;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import actions.InfoSuperAction;
import dao.OpDB;
import tools.DoString;
/**
 * <p>类名：UserAction
 * <p>作用：用户注册
 * <p>@author :born to try
 */
public class UserAction extends InfoSuperAction {	
	private String name;
	private String password;
	private String passwordCheck;
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getPassword(){
		return  password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPasswordCheck(){
		return passwordCheck;
	}
	public void setPasswordCheck(String passwordCheck){
		this.passwordCheck=passwordCheck;
	}

	
	public String Add(){

		String addType=request.getParameter("addType");
		if(addType==null||addType.equals("")){
			request.setAttribute("mainPage","/register/register.jsp");
			addType="linkTo";
		}
		if(addType.equals("add")){
			request.setAttribute("mainPage","/pages/error.jsp");
			OpDB myOp=new OpDB();
			int id = new Random().nextInt(10000); 

			
			Object[] params={id,name,password};
			String sql="insert into tb_newUser values(?,?,?)";
			
			int i=myOp.OpUpdate(sql,params);			
			if(i<=0)
				addFieldError("addE",getText("city.info.add.E"));				
			else{
				
				addFieldError("addS",getText("city.info.add.S"));				
			}
		}		
		return SUCCESS;
	}
	
	
	

	public void validateAdd(){	
	
		request.setAttribute("mainPage","/register/register.jsp");
	
		String addType=request.getParameter("addType");	
		if(addType==null||addType.equals(""))
			addType="linkTo";
		
		if(addType.equals("add")){			
			
			
			boolean mark=true;			

			if(name==null||name.equals("")){
				mark=false;
				addFieldError("nameError",getText("city.info.no.name"));
			}
			if(password==null||password.equals("")){
				mark=false;
				addFieldError("passwordError",getText("city.info.no.password"));
			}
			if(passwordCheck==null||passwordCheck.equals("")){
				mark=false;
				addFieldError("passwordCheckError",getText("city.info.no.passwordCheck"));
			}
			if(!getPassword().equals(getPasswordCheck())){
				mark=false;
				addFieldError("passwordNotEqualError",getText("city.info.no.passwordNotEqualCheck"));
			}		
		}
	}

	public void validateSearchShow() {
		request.setAttribute("mainPage","/pages/error.jsp");		
		String subsql=searchInfo.getSubsql();
		String sqlvalue=searchInfo.getSqlvalue();
		String type=searchInfo.getType();
		
		if(subsql==null||subsql.equals("")){
			addFieldError("SearchNoC",getText("city.info.search.no.condition"));
		}
		if(sqlvalue==null||sqlvalue.equals("")){
			addFieldError("SearchNoV",getText("city.info.search.no.value"));
		}
		if(type==null||type.equals("")){
			addFieldError("SearchNoT",getText("city.info.search.no.type"));
		}
	}
}
