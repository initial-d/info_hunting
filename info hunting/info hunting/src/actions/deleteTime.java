package actions;


import actions.InfoSuperAction;
/**
 * <p>������deleteTime
 * <p>���ã���תҳ��
 * <p>@author :born to try
 */
public class deleteTime extends InfoSuperAction {
	
	
	public String execute() throws Exception{
		request.setAttribute("mainPage","/pages/delete/deleteByTime.jsp");
		return SUCCESS;
	}
	
}
