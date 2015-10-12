<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s2"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script>
function Focus(){
  document.getElementById("input").focus();
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搜索一下</title>
<style>
a{
	color:#00c
	}
a:active{
	color:#f60
}
#nv{
	font-size:16px;
	margin:0 0 4px;
	text-align:left;
	text-indent:17px
}
#nv a,#nv b{
	font-size:14px;
}
.l{
	float:left;
	width:1px;
	height:25px;
	background:#696969;
}
.c1{
	width:1px;
	padding:1px;
	background-color:#000000;
}
.c1 input{
	font-size:20px;
	height:27px;
}
#search{
	
	padding:30px 3px 20px 5px;
}
#goright {
	
	padding:-0 0 20px 5px;
}
.style9 {color: #0000FF}
.style10 {color: #ECE9D8}
#style11 {
	color: #333;
	font-size: 14px;
	text-align: center;
}
</style>
</head>

<body bgcolor=#FFFFFF   onload="Focus()">
<script language="javascript">


function fsubmit(obj){
	obj.submit();
}
</script>
<span class="style10"></span>
<table border="0" width="100%" cellspacing="0" cellpadding="0">
            <!-- 顶部菜单 -->
            <tr height="20">
                <td style="text-indent:10" valign="bottom">
                     <a href="index.jsp">全部</a>
                    <a href="wendang.jsp" >文档</a>
                    <a href="tupian.jsp" >图片</a>  
                    <b>视频</b>
                    <a href="yinyue.jsp">音乐</a>
                    <a href="upload.jsp">上传</a>
                    
                </td>
                <td align="right" valign="bottom">
                   <a href="userLogin.jsp" style="color:gray">[登陆]</a>
                     <a href="user_Add.action?addType=linkTo" style="color:gray">[注册]</a>
                </td>
            </tr>
</table>
<s2:form    action="vediosearch"  method="post" >
	<table align="center" width="580" height="302" background="images/infoHunting_4.jpg">

	  <tr>
    		<td width="400" height="167" align="right" valign="bottom">
      			<a  class=c1>   	
          		<input name="key"  type="text" id="input"   value="" onfocus="this.value=''"  size="26" onkeyup="findNames();"  maxlength="20" autocomplete="off" />
        		</a> 
        	</td>
    		<td  width="60" valign="bottom"><a id="search"><input name="imaget" type="image" onClick="javascript:fsubmit(document.frm);return false;" 
 								src="images/botton.gif" align="top" width="55px" height="28px"/></a></td>
    		<td  width="100" valign="bottom" align="left"><a href ="Search.jsp" style="color:white">高级搜索</a></td>
  	</tr>
  	<tr>
    		<td >
    			
    		</td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
  </tr>
	
</table>
</s2:form>
<br><br><br><br><br><br>
<div id="style11">
<a href="#"   onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://localhost:8080/newdenglu');">把设为主页</a></div>
<br>
<div align="center" id="style11">
	<a href="us/zhongji.html" style="color:gray">关于我们</a> | <a href="log_isLogin.action" style="color:gray">后台管理</a>
</div>
</body>
</html>
