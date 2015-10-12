<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s2"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搜索一下</title>
<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css">
		.mouseOut{
			background:#708090;
			color:#FFFAFA;
		}
		.mouseOver{
			background:#FFFAFA;
			color:#000000;
		}
	
	</style>
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
<script type="text/javascript">
		var xmlHttp;
		var completeDiv;
		var inputFiled;
		var nameTable;
		var nameTableBody;
		
		function createXMLHttp(){
			
			if(window.XMLHttpRequest){
	    		xmlHttp = new XMLHttpRequest();
			}
			if (window.ActiveXObject){
    			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    			if (!xmlHttp){
        		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
    			}
    		}
		}
		
		function initVars(){
			inputFiled = document.getElementById("names");
			nameTable = document.getElementById("name_table");
			nameTableBody = document.getElementById("name_table_body");
			completeDiv = document.getElementById("pop");
		}
		
		function findNames(){
			
			initVars();
			if(inputFiled.value.length>0){
				createXMLHttp();
				var url="FindNameServlet?names="+escape(inputFiled.value);
				xmlHttp.open("GET",url,true);
				xmlHttp.onreadystatechange =callback;
				xmlHttp.send(null);
			}
		}
		
		
		function callback(){
			if(xmlHttp.readyState==4){
				if(xmlHttp.status==200){
					var name = xmlHttp.responseXML.getElementsByTagName("name")[0].firstChild.data;
					clearName();
					setName(xmlHttp.responseXML.getElementsByTagName("name"));					
				}else if(xmlHttp.status==204){
					clearName();
				}
			}
		}
		
		function setName(nameStr){
		
			clearName();
			var size = nameStr.length;
			setOffsets();
			
			var row,cell,txtNode;
			
			for(var i = 0;i<size; i++){
				var nextNode = nameStr[i].firstChild.data;
				row = document.createElement("tr");
				cell = document.createElement("td");
				
				cell.onmouseover = function(){this.className='mouseOut';};
				cell.onmouseout = function(){this.className='mouseOver';};
				cell.setAttribute("bgcolor","#FFFAFA");
				cell.setAttribute("border","0");
				cell.onclick = function(){populateName(this);};
			
				txtNode = document.createTextNode(nextNode);
				cell.appendChild(txtNode);
				row.appendChild(cell);
				nameTableBody.appendChild(row);
			}
		}
		
		function setOffsets(){
			
			var end = inputFiled.offsetWidth;
			var left = caculateOffSetLeft(inputFiled);
			var top = caculateOffSetTop(inputFiled) + inputFiled.offsetHeight;
			
			completeDiv.style.border = "black 1px solid";
			completeDiv.style.left = left + "px";
			completeDiv.style.top = top + "px";
			completeDiv.style.width = end + "px";
			nameTable.style.width = end + "px";
		}
		
		function caculateOffSetLeft(filed){
			return caculateOffSet(filed,"offsetLeft");
		}
		
		function caculateOffSetTop(filed){
			return caculateOffSet(filed,"offsetTop");
		}
		
		function caculateOffSet(filed,attr){
			var offset = filed[attr];
			//while(filed){
			//	offset += filed[attr];
			//	filed = filed.offsetParent;
			//}
			return offset;
		}
		
		function populateName(cell){
			inputFiled.value = cell.firstChild.nodeValue;
			clearName();
		}
		
		function clearName(){
			var size = nameTableBody.childNodes.length;
			for(var i = size -1 ;i>= 0 ;i--){
				nameTableBody.removeChild(nameTableBody.childNodes[i]);
			}
			completeDiv.style.border = "none";
		}
	</script>
</head>
<script>
function Focus(){
  document.getElementById("input").focus();
}
</script>


<body bgcolor=#FFFFFF  onload="Focus()">
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
                    <b>全部</b>
                    <a href="wendang.jsp" >文档</a>
                    <a href="tupian.jsp" >图片</a>
                    <a href="shipin.jsp">视频</a>
                    <a href="yinyue.jsp">音乐</a>
                    <a href="file_Add.action?addType=linkTo" style="color:gray">上传</a>
                    
                </td>
                <td align="right" valign="bottom">
                     <a href="userLogin.jsp" style="color:gray">[登陆]</a>
                     <a href="user_Add.action?addType=linkTo" style="color:gray">[注册]</a>
                </td>
            </tr>

</table>
	<s2:form    action="search"  method="post" >
	<table align="center" width="580" height="302" background="images/infoHunting.jpg">

	  <tr>
    		<td width="400" height="167" align="right" valign="bottom">
      			<a  class=c1>   	
          		<input name="key"  type="text"  id="input"  value="" onfocus="this.value=''"  size="26" onkeyup="findNames();"  maxlength="20" autocomplete="off" />
        		</a> 
        	</td>
    		<td  width="60" valign="bottom"><a id="search"><input name="imaget" type="image" onClick="javascript:fsubmit(document.frm);return false;" 
 								src="images/botton.gif" align="top" width="55px" height="28px"/></a></td>
    		<td  width="100" valign="bottom" align="left"><a href ="Search.jsp" style="color:white">高级搜索</a></td>
  	</tr>
  	<tr>
    		<td align="center">
    			<label for="all"><input type="radio" name="i" value="0"  checked id="all" ><a style="color:white">普通查询</a></label>
    	
    			<label for="fuz"><input type="radio" name="i" value="1"  id="fuz"><a style="color:white">模糊查询</a></label>
    		</td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
  </tr>
	
</table>
</s2:form>
<br><br><br><br><br>

<div id="style11">
<a href="#"   onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://localhost:8080/newdenglu');">设为主页</a></div>
<br>
<div align="center" id="style11">
	<a href="us/zhongji.html" style="color:gray">关于我们</a> | <a href="log_isLogin.action" style="color:gray">[进入后台]</a>
    <br>copyright from 2011 @born to try!
</div>

</body>
</html>
