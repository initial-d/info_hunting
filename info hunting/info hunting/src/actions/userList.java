package actions;


import actions.InfoSuperAction;
/**
 * <p>类名：userlist
 * <p>作用：用户列表
 * <p>@author :born to try
 */
public class userList extends InfoSuperAction {
	
	
	public String execute() throws Exception{
		request.setAttribute("mainPage","/pages/user/userList.jsp");
		return SUCCESS;
	}
	
}
