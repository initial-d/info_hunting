<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map,java.util.TreeMap" %>
<%@ taglib prefix="s2" uri="/struts-tags" %>

<html>
<head>
<title>后台-侧栏</title>
<script src="js/SpryAccordion.js" type="text/javascript"></script>
<link href="css/SpryAccordion.css" rel="stylesheet" type="text/css" />
</head>
<body background="images/back.gif">
    <center>
        <table border="0" width="220" height="100%" cellspacing="0" cellpadding="0" style="margin-top:7">
            <!-- 显示方式 -->
            <tr height="30" bgcolor="#F0F0F0"><td style="text-indent:5;border:1 solid"><font color="#004790"><b>■资源审核</b></font></td></tr>
            <tr height="1"><td></td></tr>
            <s2:form action="admin_ListShow.action?" theme="simple">
           <tr>
                <td align="center" valign="top" style="border:1 solid">
                    <table border="0" width="220" height="30" cellspacing="0" rules="none">
                        <tr height="5"><td align="center" valign="bottom"></td></tr>
                        <tr height="30" bgcolor="lightgrey">
                            <td align="center">
                                
                                <input type="submit" value="审核资源"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            </s2:form>
            <tr height="5"><td></td></tr>
            <!-- 已审核 -->            
            <tr height="30" bgcolor="#F0F0F0"><td style="text-indent:5" style="border:1 solid"><font color="#004790"><b>■已审核信息上传</b></font></td></tr>
            <tr height="1"><td></td></tr>
            <form action="admin_Publish.action">
            <tr>
                <td align="center" valign="top" style="border:1 solid">
                    <table border="0" width="220" height="30" cellspacing="0" rules="none">
                        <tr height="5"><td align="center" valign="bottom"></td></tr>
                        <tr height="30" bgcolor="lightgrey">
                            <td align="center">
                                
                                <input type="submit" value="上传"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            </form>
            <tr height="5"><td></td></tr>
            <tr height="30" bgcolor="#F0F0F0"><td style="text-indent:5;border:1 solid"><font color="#004790"><b>■资源删除</b></font></td></tr>
            <tr height="1"><td></td></tr>
            <!-- 资源 -->            
            <tr height="30" bgcolor="lightgrey">
               <td align="center"><a href="delete.action">普通删除</a></td>              
            </tr>
            <tr height="30" bgcolor="lightgrey">
               <td align="center"><a href="deleteTime.action">按时间删除</a></td>              
            </tr>
            
            <tr height="5"><td></td></tr>
            <tr height="30" bgcolor="#F0F0F0"><td style="text-indent:5;border:1 solid"><font color="#004790"><b>■用户管理</b></font></td></tr>
            <tr height="1"><td></td></tr>
            <!-- 用户 -->            
            <tr height="30" bgcolor="lightgrey">
               <td align="center"><a href="userList.action">用户列表管理</a></td>              
            </tr>
           
            
            <tr height="30" bgcolor="#F0F0F0"><td style="text-indent:5;border:1 solid"><font color="#004790"><b>■服务器管理</b></font></td></tr>
            <tr height="1"><td></td></tr>
            <!-- 用户 -->            
            <tr height="30" bgcolor="lightgrey">
               <td align="center"><a href="serverS.action">服务器设置</a></td>              
            </tr>
            <tr height="30" bgcolor="lightgrey">
               <td align="center"><a href="serverL.action">在线服务器列表</a></td>              
            </tr>
        </table>        
    </center>
</body>
</html>