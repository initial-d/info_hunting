

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="beans.xmlbean" %>
<%@ page import="java.util.ArrayList" %>
 

<html>
<head>
<title>搜索结果</title>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<style>
html,body{
margin:0;
padding:0;
}
#head{
width:100%;
background:#FFFFFF;
height:115px;
}
#top{

	background:#f6f6f6;
	left:230px;
	 padding-left:230px;
}
#navigation{
	position:relative; bottom:-15px; 
	left:230px;
}
.cl{
	
    width:1px;
    padding:4px;
    background-color:#000000;
}
.c1 input{
    font-size:18px;
    height:28px;
}
#condition{

float:left;
padding: 25px;
background:#FFFFFF;
width:200px;

}
#condition p {
margin: 0;
padding: 0;
padding-bottom: 15px;
}
#condition h2 {
margin: 0;
padding: 0;
}

#content{
margin-left:200px;
padding: 25px;
background:#FFFFFF;
margin-right:200px;
}
#content p {
margin: 0;
padding: 0;
padding-bottom: 15px;
}
#content h2 {
margin: 0;
padding: 0;
}


.style1 {font-size: large}
</style>

</head>

<body bgcolor=#FFFFFF>

		  		<script>
					function subFrm(){
						document.frm.submit();
						
					}
					function fsubmit(obj){
						obj.submit();
					}
				</script>

<div id="head">

	
	<div id="navigation">
		
		            <b>全部</b>&nbsp;&nbsp;
		            <a href="wendang.jsp">文档</a>&nbsp;&nbsp;&nbsp;         
                    <a href="tupian.jsp" >图片</a>&nbsp;&nbsp;&nbsp;
                    <a href="shipin.jsp">视频</a>&nbsp;&nbsp;&nbsp;
                    <a href="yinyue.jsp">音乐</a>&nbsp;&nbsp;&nbsp;
                    <a href="upload.jsp">上传</a>&nbsp;&nbsp;&nbsp;
  </div>
		<br>
		<form   action="search"  method="post" >
		<table width="867" border="0" cellspacing="0">

  <tr>
    <th width="190" height="49" scope="col">&nbsp;</th>
    <th width="540" scope="col">
		<div  align="left" class=c1>

			<input name="key"  type="text" value="<s:property value="#session.key1"  />" size="30" maxlength="20">
				<a id="search"  ><input name="imaget" type="image" onClick="javascript:fsubmit(document.frm);return false;" 
 								src="images/botton.gif" align="top" width="55px" height="28px"/></a>
		</div>
	
	
	
	
	</th>
    </table>
</form>		

</div>
<div id="top" ><a>你要搜索的关键字是：<s:property value="#session.key" escape="false" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a  align="rignt">共用时：<s:property value="#session.time"/> s.找到<s:property value="#session.num"/>条</a></div>
<div id="condition"> <h2></h2>
<div>
<h4>INFO HUNTING</h4>
<a href="http://css.jorux.com/wp-admin/post.php#" >来展示您的产品吧</a>
<br>
www.einfohunting.com
</div>
<p></p>
<h2></h2>
</div>
<div id="content"><h2></h2>
   <%  int previous=0;
    int next=0;  
    int pageno=1;     
    int pagesize=5; 
    int pagecount=1;
    List list =  (List)session.getAttribute("onelist1");
    int count=list.size();   
    if(count%pagesize!=0)
    {  pagecount=count/pagesize+1;  }  
    else
    {  pagecount=count/pagesize;  }       
    int starter=0;
    if(pageno==1)   
    starter=0;
    else  starter=(pageno-1)*pagesize;
    request.setAttribute("star",starter);
    request.setAttribute("sizes",pagesize);
   
 %>


<s:set name="onelist" value="#session.onelist1"/>  
<s:subset source="#onelist" start="#request.star" count="#request.sizes">        
   <s:if test="#onelist==null||#onelist.size()==0">
      <tr height="30"><td align="center" style="border:1 solid">★★★请重新填写关键字！★★★</td></tr>
   </s:if>
   <s:else> 
   <br>&nbsp;&nbsp;&nbsp;&nbsp;           
    <s:iterator status="oneStatus" >
   <s:if test="#oneStatus.odd"><tr height="23"></s:if>
   <s:property value="getId()"/>
   <br>
 
   <a href ="reader?doc=<s:property  value="getUrl()"/>&kind=<s:property  value="getKind()"/>&author=<s:property value="getAuthor()"/>&publisher=<s:property value="getPublisher()"/>&describe=<s:property value="getDescribe()"/>&title=<s:property value="getTitle()"/>"  ><s:property  value="getTitle()"  escape="false"/></a>

           资源类型：<s:property value="getKind()"/>
   <br>
            资源描述：<s:property value="getDescribe()"   escape="false"/>
   <br>
            上传日期：<s:property value="getDate()"/>
   <br>
             作者：<s:property value="getAuthor()"/>
   <br>
             出处：<s:property value="getPublisher()"/>
   <form   action="download"  method="post" >
   <a href ="download?fileName=<s:property  value="getTitle()"/>&url1=<s:property  value="getUrl()"/>&kind=<s:property  value="getKind()"/>"><img src="images/download.jpg" width=45 height=20> </a>
   </form>
 _____________________________________________________________________________________ 
   <br>
   <s:if test="#oneStatus.even"></s:if>
   </s:iterator>
   <tr height="30"><td align="center"> 
   
  <table>
  <tr> 
     <td> <a href="showsearch2.jsp?previous=<%=previous%>">上一页</a></td>
      <% for(int i=1;i<pagecount+1;i++){ 
               if(pageno!=i){
       %>
     <td><a href="showsearch2.jsp?i=<%=i%>">[第<%=i%>页]</a> </td>   
     <% }else{ %>
               <td>[第<%= i %>页]</td>
           <% } } %>
       
     <td><a href="showsearch2.jsp?next=<%=next%>">下一页</a></td>
   </tr>    
  </table>
    </td></tr>  
   </s:else> 
   </s:subset>  


</div>


</body>
</html>