package actions;
import actions.InfoSuperAction;
/**
 * <p>类名：deletePage
 * <p>作用：跳转页面
 * <p>@author :born to try
 */
public class deletePage extends InfoSuperAction {
	
	
	public String execute() throws Exception{
		request.setAttribute("mainPage","/pages/delete/delete.jsp");
		return SUCCESS;
	}
	
}
