package actions;


import actions.InfoSuperAction;
/**
 * <p>类名：serverS
 * <p>作用：跳转页面
 * <p>@author :born to try
 */
public class serverS extends InfoSuperAction {
	
	
	public String execute() throws Exception{
		request.setAttribute("mainPage","/pages/admin/server/serverSet.jsp");
		return SUCCESS;
	}
	
}
