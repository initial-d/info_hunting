package actions;
import actions.InfoSuperAction;
/**
 * <p>������deletePage
 * <p>���ã���תҳ��
 * <p>@author :born to try
 */
public class deletePage extends InfoSuperAction {
	
	
	public String execute() throws Exception{
		request.setAttribute("mainPage","/pages/delete/delete.jsp");
		return SUCCESS;
	}
	
}
