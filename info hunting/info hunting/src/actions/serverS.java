package actions;


import actions.InfoSuperAction;
/**
 * <p>������serverS
 * <p>���ã���תҳ��
 * <p>@author :born to try
 */
public class serverS extends InfoSuperAction {
	
	
	public String execute() throws Exception{
		request.setAttribute("mainPage","/pages/admin/server/serverSet.jsp");
		return SUCCESS;
	}
	
}
