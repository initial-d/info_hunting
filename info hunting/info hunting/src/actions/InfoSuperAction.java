package actions;

import model.InfoSingle;
import model.SearchInfo;
/**
 * <p>������InfoSuperAction
 * <p>���ã�����
 * <p>@author :born to try
 */
public class InfoSuperAction extends MySuperAction {
	protected InfoSingle infoSingle;
	protected SearchInfo searchInfo;

	public InfoSingle getInfoSingle() {
		return infoSingle;
	}
	public void setInfoSingle(InfoSingle infoSingle) {
		this.infoSingle = infoSingle;
	}
	public SearchInfo getSearchInfo() {
		return searchInfo;
	}
	public void setSearchInfo(SearchInfo searchInfo) {
		this.searchInfo = searchInfo;
	}	
}
