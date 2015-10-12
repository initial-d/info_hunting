<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s2" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.dom4j.*" %>
<%@ page import="org.dom4j.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="actions.*" %>
<%@ page import="java.net.InetAddress" %>

<html>
<head>
    <title>服务器列表</title>
    <script type="text/javascript"></script>
</head>
<body>
 	<center>
 	
    <form action="serverList.action">
	
		<table width="405"  border="0">
		
			<tr>
    			<td  height="100" align="center"><font>在线服务器列表</font></td>
    			
  			</tr>
  			<% for(int i=0;i<5;i++){ %>
  			<% InetAddress   address   =   InetAddress.getLocalHost(); 
					String ipLocal=address.getHostAddress();
				%>
            
           	<tr>
    			<td  height="50" align="center"><strong>IP地址<%=i %>: </strong><%=ipLocal %></td>
    		</tr>
                    
  			 <%} %> 
  			 <tr>
    			<td  height="50" ></td>
    		</tr>
    
		</table>

    </form>
    
 </center>
</body>
</html>