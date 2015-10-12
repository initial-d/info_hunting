package actions;

import allkindsearch.search;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import chuli.remove;
import chuli.sort;

import Distributed.*;


import beans.xmlbean;

import allkindsearch.gaojiSearch;
/**
 * <p>类名：GaoJiSearch
 * <p>作用：高级搜索
 * <p>@author :born to try
 */
public class GaoJiSearch extends  MySuperAction{
	private String type;
    private String author;
    private String publisher;
    private int num;
    private String kind;
    private String keys;
    private String nokey;
    private long timeOfSearch;
	public long getTimeOfSearch() {
		return timeOfSearch;
	}
	public void setTimeOfSearch(long timeOfSearch) {
		this.timeOfSearch = timeOfSearch;
	}
	public String getNokey() {
		return nokey;
	}

	public void setNokey(String nokey) {
		this.nokey = nokey;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String execute() throws Exception{
		ArrayList<xmlbean> Fresult=new ArrayList<xmlbean>();
		if(getKeys()==null)
	       {    return "error";   
	       }else{ 
	    	    for(int j=0;j<IPlist.iplist.size();j++)
	    	    {
	    	    	
	    	    	String dizhi=IPlist.iplist.get(j);
	    	    	System.out.print(dizhi);
	    	    	
	    	    SERVER_SIDE gaoji =null ;
	    	   
				try {
					gaoji = (SERVER_SIDE) Naming.lookup("rmi://" + dizhi + "/Ser");
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
							
							onelist1=gaoji.remotegaojiserach(getKeys(),type,author,publisher,nokey,kind);
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
					gaoji=null;
					}
		        int i=0;
	        	i= Fresult.size();
	        	request.getSession().setAttribute("onelist1", Fresult);
	        	request.getSession().setAttribute("onelist2",num);
		        request.setAttribute("num",i);
			    return "success";
    }
}
}
