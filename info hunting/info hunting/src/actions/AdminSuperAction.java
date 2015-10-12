package actions;

import model.AdminShowType;
import model.InfoSingle;
/**
 * <p>类名：AdminSuperAction
 * <p>作用：父类
 * <p>@author :born to try
 */
public class AdminSuperAction extends MySuperAction{
	protected AdminShowType showType;
	protected InfoSingle infoSingle;
	
	public AdminSuperAction(){

		showType=new AdminShowType();
		infoSingle=new InfoSingle();
	}
	public AdminShowType getShowType() {
		return showType;
	}
	
	public void setShowType(AdminShowType showType) {
		this.showType = showType;
	}

	public InfoSingle getInfoSingle() {
		return infoSingle;
	}

	public void setInfoSingle(InfoSingle infoSingle) {
		this.infoSingle = infoSingle;
	}	
}
