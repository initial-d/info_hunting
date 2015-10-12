package chuli;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import beans.xmlbean;
/**
 * <p>类名：remove
 * <p>作用：删除重复
 * <p>@author :born to try
 */
public class remove {

	public  void removeDuplicateWithOrder(List list) { 
		
		
		for(int a=0;a<list.size();a++){
			   for(int b=a+1;b<list.size();b++){
				   String urlA=(String) ((xmlbean) list.get(a)).getUrl();
				   String newUrlA=urlA.substring(urlA.indexOf("resources/"));
				   String  fileNameA=newUrlA.substring(10);
				   String urlB=(String) ((xmlbean) list.get(b)).getUrl();
				   String newUrlB=urlB.substring(urlB.indexOf("resources/"));
				   String    fileNameB=newUrlB.substring(10);
				   if( fileNameA.equals(fileNameB))
				   {
					   list.remove(b);
				   }			   
			   }
		   }
      
    } 
	
}



