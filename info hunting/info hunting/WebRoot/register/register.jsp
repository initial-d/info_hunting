<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s2"%>

<html>
<head>
    <title>发布信息</title>
    <script type="text/javascript" src="<s2:url value='js/InputCheck.js'/>"></script>
  
</head>
<body>
    <center>
    <table border="0" cellpadding="0" cellspacing="0" width="688" height="100%">
        <tr height="20"><td><img src="images/default_t.jpg"></td></tr>
        <tr>
            <td background="images/default_m.jpg" valign="top" align="center">
               <s2:form action="user_Add.action" theme="simple">
                <input type="hidden" name="addType" value="add"/>
                <table border="0" width="650" height="300" rules="all" cellspacing="0">
                    <tr height="40"><td style="text-indent:5"><font color="#004790"><h2>■欢迎注册</font></td></tr>
                    <tr>
                        <td align="center">
                            <table border="0" width="650" rules="all" cellspacing="8">                    
                               
                                
                                <tr align="center">
                                	<td style="text-indent:10" align="right">用 户 名：</td>
                                    <td colspan="2" align="left"><s2:textfield label="用户名" name="name"/></td>
                                </tr>
                                <tr><td colspan="3"><s2:fielderror><s2:param value="%{'nameError'}"/></s2:fielderror></td></tr>
                               
                               
                                <tr align="center">
                                	<td style="text-indent:10" align="right">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                                    <td colspan="2" align="left"><s2:password label="密码" name="password" maxlength="10"/></td>
                                </tr>
                                <tr><td colspan="3"><s2:fielderror><s2:param value="%{'passwordError'}"/></s2:fielderror></td></tr>
                                
                                 <tr align="center">
                                	<td style="text-indent:10" align="right">确认密码：</td>
                                    <td colspan="2" align="left"><s2:password label="确认密码" name="passwordCheck" maxlength="10"/></td>
                                </tr>
                                <tr><td colspan="3"><s2:fielderror><s2:param value="%{'passwordCheckError'}"/></s2:fielderror></td></tr>
                                <tr><td colspan="3"><s2:fielderror><s2:param value="%{'passwordNotEqualError'}"/></s2:fielderror></td></tr>
                               
                                
                              
                                 <tr align="center" height="50">
            						<td></td>
                        			<td colspan="2" align="left">
                           	 			<s2:submit value="确定"/>
                            			<s2:reset value="重填"/>
                        			</td>
                    			</tr>
                              
                            </table>                
                        </td>
                    </tr>
                  
                </table>
                </s2:form>            
            </td>
        </tr>
        <tr height="26"><td><img src="images/default_e.jpg"></td></tr>        
    </table>
    </center>
</body>
</html>