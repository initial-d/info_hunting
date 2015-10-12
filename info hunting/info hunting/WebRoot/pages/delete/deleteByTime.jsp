<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s2" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.dom4j.*" %>
<%@ page import="org.dom4j.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="actions.*" %>


<html>
<head>
    <title>后台-查看信息列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/table.css" rel="stylesheet" type="text/css">

</head>
<body>


       <form action="deleteByTime.action" method="post" onsubmit="return test();">
    <center>
        <table border="0" width="550" cellspacing="0"  cellpadding="0">
            
            
                <tr height="30">
                    <td style="text-indent:8">资源删除</td>
                    <td><s2:fielderror/>&nbsp;</td>
                </tr>
          		
          		<tr aligh="left">
          			<td >删除</td>
          			<td><input type="text" name="year" class="dele"></td>
          			<td>年</td>
          			<td><input type="text" name="month" class="dele"></td>
          			<td>月</td>
          			<td><input type="text" name="day" class="dele"></td>
          			<td>日  以前的文件</td>
          		</tr>

				
				<tr align="center">
					<td height="50">
						<input type=submit value="  删   除  " class="btn3_mouseout" >
					</td>
				</tr>
				
			
                <tr height="8"><td></td></tr>
               
        </table>
    </center>
    </form>
</body>
</html>