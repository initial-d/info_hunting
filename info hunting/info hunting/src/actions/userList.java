package actions;


import actions.InfoSuperAction;
/**
 * <p>������userlist
 * <p>���ã��û��б�
 * <p>@author :born to try
 */
public class userList extends InfoSuperAction {
	
	
	public String execute() throws Exception{
		request.setAttribute("mainPage","/pages/user/userList.jsp");
		return SUCCESS;
	}
	
}
