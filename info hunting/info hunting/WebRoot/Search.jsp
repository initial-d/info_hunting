<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script>
function Focus(){
  document.getElementById("input").focus();
}
</script>
    <base href="<%=basePath%>">
    
    <title>高级搜索</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body  onload="Focus()">
  
<div style="left:150px; position:absolute; width:1000px; height:800px; margin:0px; margin-top:0px; margin-left:0px;">




<table width="100%" border="0">
	
    <hr size="8px" color="#000000"/>
	<tr>
    
		<td width="210" bgcolor="#FFFFFF">info hunting</td>
		<td align="center" bgcolor="#FFFFFF"><font size="3"><strong>高级搜索</strong></font></td>
		<td width="200" align="right" bgcolor="#FFFFFF"><font size="3"><a href="index.jsp">返回首页</a></font></td>
	</tr>
</table>
	<br />
    
    
<form action="gaojisearch" method="post">
<table align="center" width="921" border="0" bgcolor="#FFFFFF">

	<tr>
		<td width="125" height="30"><font size="3"><strong>搜索结果</strong></font></td>
      <td width="257"><font size="3">
		  <select name="type">
			<option value="every">包含任一关键词</option>	
			<option value="all">包含以下全部的关键词</option>
			<option value="complete">包含以下的完整关键词</option>
			<option value="one">前缀查找（输入资源名字的第一个字）</option>
			</select>
		</td>
      <td width="280"><font size="3">
          <input type="text" id="input"  name="keys" size="40"></font>&nbsp; 
		</td>
	</tr>
     <tr>
		<td width="125" height="30">&nbsp;</td>
		<td width="257"><font size="3">不包含以下关键词</font></td>
<td>
			<input type="text" name="nokey" size="40">&nbsp;		</td>
	</tr>
	 <tr>
		<td width="125" height="30">&nbsp;</td>
		<td width="257"><font size="3">作者</font></td>
<td>
			<input type="text" name="author" size="40">&nbsp;		</td>
	</tr>
    <tr>
		<td width="125" height="30">&nbsp;</td>
		<td width="257"><font size="3">发布者</font></td>
<td>
			<input type="text" name="publisher" size="40">&nbsp; 
	  </td>
        <td width="196"><input type="submit" value="搜&nbsp;索" name="button1"></td>
	</tr>
	
	<tr>
		<td width="125" height="30"><font size="3"><strong>显示条数</strong></font></td>
		<td width="257"><font size="3">选择显示的条数</font></td>
<td>
			<select name="num">
			<option value="5">每页显示5条</option>
			<option value="10">每页显示10条</option>
			<option value="15">每页显示15条</option>
			<option value="20">每页显示20条</option>
			</select>
		</td>
        
	</tr>
	<tr>
		<td width="125" height="30"><font size="3"><strong>搜索类型</strong></font></td>
		<td width="257"><font size="3">请选择搜索类型</font></td>
<td>
			<select name="kind">
			<option value="all">全部</option>
			<option value="document">文档</option>
			<option value="mp3">音乐</option>
            <option value="vedio">视频</option>
            <option value="picture">图片</option>
			</select>
		</td>
        
	</tr>
   
   


</table> </form>

<br/>
<br/>
	<center>
	<P style="HEIGHT: 30px top:50px">
		<A onClick="this.style.behavior='url(#default#homepage)';this.setHomePage('<%=basePath%>')" 
			href="<%=basePath%>">设为主页</A>丨
        <A href="aboutResourceHounter.html">关于info hunting</A>
     </P>
	<hr color="#999999" width="500px" size="8px"/><br />
    欢迎使用info hunting
	
</center>




 
 </div>



 
 
  </body>
</html>
