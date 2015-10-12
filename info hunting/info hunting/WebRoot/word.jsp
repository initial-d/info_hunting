<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
   <%@ page import="java.util.List"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>文档预览</title>
 
  <%
  String url=(String)request.getAttribute("url");
  String author=(String)request.getAttribute("author");
   %>  
  </head>
  
  <body bgcolor=#f6f6f6>
  




		<table  height="100" background="images/61.gif" align="center">
			
			<tr>
                <td width="1000" height="100%" align="right" valign="bottom">
                  <s:a href="index.jsp"><img src="images/index.gif" border="0"></s:a>
                </td>
            </tr>       
		</table>
  

	
	<br>

<table width="900" height="484" border="0" align="center">
  <tr>
    <th width="670" scope="col">文档名称：<s:property  value="getTitle()"  escape="false"/></th>
    <th width="30" scope="col">&nbsp;</th>
  </tr>
  <tr >
    <td   height="28" >&nbsp;</td>
    <td background="images/more.gif" height="40"></td>
  </tr>
  <tr>
    <td height="156" rowspan="3" align="center" bgcolor=#00B2EE><div>
	
	 <table width="500">
	 	<tr>
	 		<td></td>
	 		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#request.word"/>
	 		</td>
	 	</tr>
	 </table>
	</td>
	<td>  
		<table width="300" height="98" border="0">
        <tr>
		<s:set name="onelist" value="#session.onelist1"/>  
		<s:subset source="#onelist" start="0" count="3">        
   		<s:if test="#onelist==null||#onelist.size()==0">
      		<tr height="30" bgcolor="#FFFFFF"><td align="center" style="border:1 solid">*</td></tr>
   		</s:if>
   		<s:else> 
   			<br>&nbsp;&nbsp;&nbsp;&nbsp;           
    		<s:iterator status="oneStatus" >
   			<s:if test="#oneStatus.odd"><tr height="23"></s:if>
          	<td width="331" height="94" scope="col" bgcolor="f6f6f6"><a href ="reader?doc=<s:property  value="getUrl()"/>&kind=<s:property  value="getKind()"/>&author=<s:property value="getAuthor()"/>&publisher=<s:property value="getPublisher()"/>&describe=<s:property value="getDescribe()"/>&title=<s:property value="getTitle()"/>"><s:property  value="getTitle()"  escape="false"/></a></td><tr>
      
    		<s:if test="#oneStatus.even"></s:if>
   			</s:iterator>
   			 
       	</s:else> 
   		</s:subset> 
     	</tr>
    

     
  	</table>
  </td>
  
  </tr>
 <tr>&nbsp;</tr>
  <tr>&nbsp;</tr>


 
  <tr>
    <td height="60" bgcolor="#f6f6f6">&nbsp;</td>
    <td background="images/guanggao.jpg"></td>
  </tr>
  <tr>
    <td align="center"><a style="color:blue"><h2>详细信息</h2></a></td>
    <td background="images/recommend.gif"></td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF">视频名称：<s:property  value="getTitle()"  escape="false"/></td>
    <td bgcolor="#FFFFFF">王力宏新歌——《火力全开》重磅上阵！</td>
  </tr>
  <tr>
    <td >作&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;者：<s:property value="getAuthor()"/></td>
    <td></td>
  </tr>
  <tr>
    <td height="41" bgcolor="#FFFFFF">出&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;处：<s:property value="getPublisher()"/></td>
    <td bgcolor="#FFFFFF">《源代码》——八分钟的记忆。</td>
  </tr>
  <tr>
    <td >文件描述：<s:property value="getDescribe()"   escape="false"/></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="80" bgcolor="#CCCCFF"><a color="#000000"><s:property  value="getTitle()"  escape="false"/>.<s:property value="getKind()"/></a>
    <form   action="download"  method="post" >
   <a href ="download?fileName=<s:property  value="getTitle()"/>&url1=<%=url %>&kind=<s:property  value="getKind()"/>"><img src="images/download.jpg" width=45 height=20> </a>
   </form>  
    </td>
    <td>
    
   </td>
  </tr>
     

  
</table>
    


  </body>
</html>
