<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<%@page import="java.util.ArrayList" %>
<%@page import="java.util.*" %>
<%@page import="java.sql.*"%>


<html>
	<head>
		<title>列表</title>
	</head>
	
	<body>
	 <center>
        <table border="0" width="650" cellspacing="0"  cellpadding="0">
         
            
                <tr height="30">
                    <td style="text-indent:8">IP列表</td>
                    <td><s2:fielderror/>&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" align="center" style="border:1 solid">
                        <table border="0" width="100%" cellspacing="0" cellpadding="0">
                            <tr align="center" height="20" bgcolor="#F0F0F0">
                                <td width="20%"><b>IP</b></td>
                                
                            </tr>
		<s:set name="onelist" value="#session.onelist1"/>  
<s:subset source="#onelist" start="#request.star" count="#request.sizes">        
   <s:if test="#onelist==null||#onelist.size()==0">
      <tr height="30"><td align="center" style="border:1 solid">★</td></tr>
   </s:if>
   <s:else> 
   <br>&nbsp;&nbsp;&nbsp;&nbsp;           
    <s:iterator status="oneStatus" >
   <s:if test="#oneStatus.odd"><tr height="23"></s:if>
  
   IP：<s:property/> 
  
   <s:if test="#oneStatus.even"></s:if>
   </s:iterator>
  
   </s:else> 
   </s:subset>  
		
		
		
			
			                        </table>
                    </td>
                </tr>
			
			
			 <tr height="8"><td></td></tr>
                <tr><td align="center" colspan="2"><jsp:include page="/pages/page.jsp"/></td></tr>  
				
		
		
		</table>
		</center>
	</body>
</html>
