package actions;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.opensymphony.xwork2.util.finder.ClassFinder.Info;
import actions.InfoSuperAction;
import dao.OpDB;
import tools.DoString;
/**
 * <p>类名：InfoAction
 * <p>作用：验证信息
 * <p>@author :born to try
 */
public class InfoAction extends InfoSuperAction {	

	
	public String SingleShow(){
		request.setAttribute("mainPage","/pages/show/singleshow.jsp");
		
		String id=request.getParameter("id");
		String sql="SELECT * FROM tb_info WHERE (id = ?)";
		Object[] params={id};

		OpDB myOp=new OpDB();
		infoSingle=myOp.OpSingleShow(sql, params);
		if(infoSingle==null){
			request.setAttribute("mainPage","/pages/error.jsp");
			addFieldError("SingleShowNoExist",getText("city.singleshow.no.exist"));
		}		
		return SUCCESS;
	}
	
	public String Add(){

		String addType=request.getParameter("addType");
		if(addType==null||addType.equals("")){
			request.setAttribute("mainPage","AdminTemp.jsp");
			addType="linkTo";
		}
		if(addType.equals("add")){
			request.setAttribute("mainPage","/pages/error.jsp");
			OpDB myOp=new OpDB();			
			Integer type=Integer.valueOf(infoSingle.getFileType());
			String name=infoSingle.getFileName();
			String size=infoSingle.getFileSize();
			String URL=infoSingle.getFileURL();
			String format=infoSingle.getFormat();
			String	title=infoSingle.getInfoTitle();
			String	content=DoString.HTMLChange(infoSingle.getFileContent());
			String	phone=infoSingle.getInfoPhone();
			String author=infoSingle.getAuthor();
			String publisher=infoSingle.getPublisher();
			int scan=0;
			int download=0;
			phone=phone.replaceAll(",","●");
			
			String linkman=infoSingle.getInfoLinkman();
			String email=infoSingle.getInfoEmail();
			String date=DoString.dateTimeChange(new java.util.Date());			
			String state="0";
			String payfor="0";
			String publish="0";
			
			Object[] params={name,size,URL,content,format,scan,download,date,type,state,publish,author,publisher};
			String sql="insert into tb_info values(?,?,?,?,?,?,?,?,?,?,?)";
			
			int i=myOp.OpUpdate(sql,params);			
			if(i<=0)
				addFieldError("addE",getText("city.info.add.E"));				
			else{
				sql="select * from tb_info where upTime=?";
				Object[] params1={date};				
				int infoNum=myOp.OpSingleShow(sql, params1).getId();				
				addFieldError("addS",getText("city.info.add.S"));				
			}
		}		
		return SUCCESS;
	}
	
	

	
	public void validateAdd(){	
		
		request.setAttribute("mainPage","/pages/add/add.jsp");
	
		String addType=request.getParameter("addType");	
		if(addType==null||addType.equals(""))
			addType="linkTo";
		
		if(addType.equals("add")){			
			int type=infoSingle.getInfoType();
			String title=infoSingle.getInfoTitle();
			String content=infoSingle.getInfoContent();
			String author=infoSingle.getAuthor();
			String publisher=infoSingle.getPublisher();
			String phone=infoSingle.getInfoPhone();
			String linkman=infoSingle.getInfoLinkman();
			String email=infoSingle.getInfoEmail();			
			
			boolean mark=true;			
			if(type<=0){
				mark=false;
				addFieldError("typeError",getText("city.info.no.infoType"));								
			}
			if(title==null||title.equals("")){
				mark=false;
				addFieldError("titleError",getText("city.info.no.infoTitle"));
			}
			if(content==null||content.equals("")){
				mark=false;
				addFieldError("contentError",getText("city.info.no.infoContent"));
			}
			if(phone==null||phone.equals("")){
				mark=false;
				addFieldError("phoneError",getText("city.info.no.infoPhone"));
			}
			if(linkman==null||linkman.equals("")){
				mark=false;
				addFieldError("linkmanError",getText("city.info.no.infoLinkman"));
			}
			if(email==null||email.equals("")){
				mark=false;
				addFieldError("emailError",getText("city.info.no.infoEmail"));
			}
			
		}
	}

	
}
