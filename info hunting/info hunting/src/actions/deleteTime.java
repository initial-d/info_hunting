package actions;


import actions.InfoSuperAction;
/**
 * <p>类名：deleteTime
 * <p>作用：跳转页面
 * <p>@author :born to try
 */
public class deleteTime extends InfoSuperAction {
	
	
	public String execute() throws Exception{
		request.setAttribute("mainPage","/pages/delete/deleteByTime.jsp");
		return SUCCESS;
	}
	
}
