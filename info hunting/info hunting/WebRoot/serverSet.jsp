<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s2" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.dom4j.*" %>
<%@ page import="org.dom4j.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.InetAddress" %>

<html>
<head>
    <title>服务器设置</title>
    <script type="text/javascript"></script>
</head>
<body>
 	<center>
 	
    <form action="serverSet">
	
		<table width="405" height="200" border="0">
		
			<tr>
    			<td width="146" height="150" ></td>
    			
  			</tr>
  			<tr>
    			<td width="146" scope="col" align="left">本机IP地址：</td>
    			<% InetAddress   address   =   InetAddress.getLocalHost(); 
					String ipLocal=address.getHostAddress();
				%>
    			<td width="249" scope="col"><%=ipLocal %></td>
    			
  			</tr>
  			
  			<tr>
    			<td width="146" height="30" align="left">主服务器IP地址：</td>
    			<td width="100" height="30" ><input type="text" name="server" id="textfield" value="" /></td>
    			<td width="146" height="30" align="left" style="color:gray">(可手动修改)</td>
  			</tr>
  			
  			<tr>
    			
    			<td><input name="" type="submit"  value="确定"></td>
  			</tr>
  			
    		<tr>
    			<td colspan="2" width="146" height="150" ></td>
    		</tr>
    
		</table>

    </form>
    
 </center>
</body>
</html>