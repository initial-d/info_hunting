package actions;
import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pinyin.SpellSearch;

import chuli.remove;
import chuli.sort;

import Distributed.*;


import beans.xmlbean;
//import java.util.Map;
import actions.MySuperAction;
/**
 * <p>类名：Mp3SearchAction
 * <p>作用：搜索Mp3
 * <p>@author :born to try
 */
public class Mp3SearchAction extends  MySuperAction {
private static final long serialVersionUID = 1L;
	private String key;
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
       if(getKey()==null)
       {    return "error";   
       }else{
    	    System.out.print(key);
	       // Mp3search searcher=new Mp3search(); 
    	    
    	    for(int j=0;j<IPlist.iplist.size();j++)
    	    {
    	    	
    	    	String dizhi=IPlist.iplist.get(j);
    	    	System.out.print(dizhi);
    	    	
    	    	SERVER_SIDE mp3 =null ;
    	   
			try {
				mp3 = (SERVER_SIDE) Naming.lookup("rmi://" + dizhi + "/Ser");
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
						
						onelist1=mp3.remoteMp3serach(getKey());
						System.out.print(onelist1.size());
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
				mp3=null;
				}
	        //List onelist1=new ArrayList();
	       
    	    
	        int i=0;
        	i=Fresult.size();
        	request.getSession().setAttribute("key1",key);
	        request.getSession().setAttribute("onelist1",Fresult);
	        request.getSession().setAttribute("num",i);
	        long time1= timeOfSearch;
	        double  time2=(double)time1/1000;
	        request.getSession().setAttribute("time",time2);
	        if   (key.getBytes().length   ==   key.length())   { 
	             SpellSearch  pinyin=new SpellSearch();
	             key=pinyin.Search(key);
	           //  System.out.print(key);
	     }   
	        request.getSession().setAttribute("key","<font color='#FF0000'>"+key+"</font>");
	     	//searcher=null;
		    return "success";	 			
	 		} 
 		 }
}	
    

  