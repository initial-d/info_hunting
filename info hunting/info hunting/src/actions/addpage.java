package actions;


import actions.InfoSuperAction;
/**
 * <p>类名：addpage
 * <p>作用：跳转页面
 * <p>@author :born to try
 */
public class addpage extends InfoSuperAction {
	
	
	public String execute() throws Exception{
		request.setAttribute("mainPage","/pages/add/addInfo.jsp");
		return SUCCESS;
	}
	
}
