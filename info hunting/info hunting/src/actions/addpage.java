package actions;


import actions.InfoSuperAction;
/**
 * <p>������addpage
 * <p>���ã���תҳ��
 * <p>@author :born to try
 */
public class addpage extends InfoSuperAction {
	
	
	public String execute() throws Exception{
		request.setAttribute("mainPage","/pages/add/addInfo.jsp");
		return SUCCESS;
	}
	
}
