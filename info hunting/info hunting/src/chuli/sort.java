package chuli;

import java.util.ArrayList;

import beans.xmlbean;
/**
 * <p>类名：sort
 * <p>作用：对结果排序
 * <p>@author :born to try
 */
public class sort {
	 public  void Sort( ArrayList<xmlbean> a)
	    {
	        int n = a.size();
	        for(int i=0;i<n-1;i++)
	        {
	            for(int j=0;j<n-i-1;j++)
	            {
	                 if(a.get(j).getScores() < a.get(j+1).getScores())
	                 {
	                	 swap(a.get(j),a.get(j+1));
	                     
	                 }
	            }
	             
	        }
	     }
	 public void swap(xmlbean a,xmlbean b)
	 {
		    String temp1 = null;
		    temp1 = a.getTitle() ;
		    a.setTitle(b.getTitle()) ; 
		    b.setTitle(temp1);
		    
		    String temp2 = null;
		    temp2 = a.getAuthor() ;
		    a.setAuthor(b.getAuthor()) ; 
		    b.setAuthor(temp2);
		    
		    String temp3 = null;
		    temp3 = a.getDate() ;
		    a.setDate(b.getDate()) ; 
		    b.setDate(temp3);
		     
		    String temp4 = null;
		    temp4 = a.getDescribe() ;
		    a.setDescribe(b.getDescribe()) ; 
		    b.setDescribe(temp4);
		    
		    String temp5 = null;
		    temp5 = a.getId() ;
		    a.setId(b.getId()) ; 
		    b.setId(temp5);
		    
		    String temp6 = null;
		    temp6 = a.getKeywords() ;
		    a.setKeywords(b.getKeywords()) ; 
		    b.setKeywords(temp6);
		    
		    String temp7 = null;
		    temp7 = a.getKind() ;
		    a.setKind(b.getKind()) ; 
		    b.setKind(temp7);
		    
		    String temp8 = null;
		    temp8 = a.getPublisher() ;
		    a.setPublisher(b.getPublisher()) ; 
		    b.setPublisher(temp8);
		    
		    String temp9 = null;
		    temp9 = a.getUrl() ;
		    a.setUrl(b.getUrl()) ; 
		    b.setUrl(temp9);
		    
		    float temp0 ;
		    temp0 = a.getScores() ;
		    a.setScores(b.getScores()) ; 
		    b.setScores(temp0);
	 }

}
