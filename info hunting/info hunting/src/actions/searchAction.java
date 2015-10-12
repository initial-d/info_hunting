package actions;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pinyin.SpellSearch;
//import java.util.Map;
import chuli.remove;
import chuli.sort;

import Distributed.*;
import actions.MySuperAction;
import beans.xmlbean; 
/**
 * <p>类名：SearchAction
 * <p>作用：主页搜索的Action
 * <p>@author :born to try
 */
public class searchAction extends  MySuperAction {
//private static final long serialVersionUID = 1L;
	private String key;
	private int i;
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	
	private long timeOfSearch;
	public long getTimeOfSearch() {
		return timeOfSearch;
	}
	public void setTimeOfSearch(long timeOfSearch) {
		this.timeOfSearch = timeOfSearch;
	}
	public  String getKey()
	{return key;
	}
    public  void setKey(String  key)
    {this.key = key ;
    }
   
	public String execute()
    {  
	   ArrayList<xmlbean> Fresult=new ArrayList<xmlbean>();
       if(key.isEmpty())
       {    return "error";   
       }else{
    	    System.out.print(key); 
    	   
    	    String kinds = null ;
	        if(i==0){
	        	kinds="all";
	        }else if(i==1){
	        	kinds="fuz";
	        }
    	  //  System.out.print(kinds);
    	    for(int j=0;j<IPlist.iplist.size();j++)
    	    {
    	    	
    	    	String dizhi=IPlist.iplist.get(j);
    	    	System.out.print(dizhi);
    	    	
    	    	SERVER_SIDE searcher =null ;
    	   
			try {
				searcher = (SERVER_SIDE) Naming.lookup("rmi://" + dizhi + "/Ser");
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        List onelist1=new ArrayList();
	        onelist1=null;
	        //if(searcher!=null)
	        Date begintime=new Date();
					try {
						onelist1=searcher.remoteserach(key,kinds);
						System.out.println(onelist1.size());
						System.out.println(kinds);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   for(int k=0;k<onelist1.size();k++){
					
                    	Fresult.add((xmlbean)onelist1.get(k)); 
                        
                  }
				   
				remove re=new remove();
		    	re.removeDuplicateWithOrder(Fresult);
				sort  finalsort=new sort();
				finalsort.Sort(Fresult);
				Date endtime=new  Date();
				timeOfSearch=endtime.getTime()-begintime.getTime();
				searcher=null;
				}
	        
    	  
	        int l=0;
        	l=Fresult.size();
        	 request.getSession().setAttribute("key1",key);
        	 if   (key.getBytes().length   ==   key.length())   { 
                 SpellSearch  pinyin=new SpellSearch();
                 key=pinyin.Search(key);
               //  System.out.print(key);
         }   
        	 request.getSession().setAttribute("key","<font color='#FF0000'>"+key+"</font>");
	        request.getSession().setAttribute("onelist1",Fresult);
	        request.getSession().setAttribute("num",l);
	        long time1= timeOfSearch;
	        double  time2=(double)time1/1000;
	        request.getSession().setAttribute("time",time2);
	       
	        
	       // Fresult.clear();
		    return "success";	 			
	 		} 
 		 }
}	
    

  