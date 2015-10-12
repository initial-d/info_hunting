<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s2" uri="/struts-tags" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>

<html>
<head>
    <title>管理员登录</title>
    <base href="<%=basePath%>">
    <link type="text/css" rel="stylesheet" href="<s2:url value='css/style.css'/>">
</head>
<body >
    <center>
   			
    		
   
        <s2:form action="userLog_Login.action" theme="simple">
            <table border="0" cellspacing="0" cellpadding="0" style="margin-top:130">
                <tr align="center"><td><a style="color:blue"><font size=6>欢迎登录</font></a></td></tr>                
                <tr height="180">
               
                    <td  align="center" valign="top">
                         <table border="0" width="90%" cellspacing="0" cellpadding="0">
                             <tr height="50"><td colspan="2"><s2:fielderror/></td></tr>
                             <tr height="30">
                                 <td align="right" width="40%">用户名：&nbsp;&nbsp;</td>
                                 <td style="text-indent:5"><s2:textfield name="user.userName" size="30"/></td>
                             </tr>                
                             <tr height="30">
                                 <td align="right">密&nbsp;&nbsp;码：&nbsp;&nbsp;</td>
                                 <td style="text-indent:5"><s2:password name="user.userPassword" size="30"/></td>
                             </tr>
                             <tr height="60">
                                 <td></td>
                                 <td>
                                     <s2:submit value="登录"/>
                                     <s2:reset value="重置"/>
                                     <a href="user_Add.action?addType=linkTo" style="color:gray">[注册]</a>
                                 </td>
                             </tr>
                         </table>
                    </td>
                </tr>
                <tr><td><img src="images/848.png"></td></tr>
            </table>
        </s2:form>     
    </center>
</body>
</html>