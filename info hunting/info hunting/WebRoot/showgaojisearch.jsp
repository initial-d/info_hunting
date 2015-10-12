

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="beans.xmlbean" %>
<%@ page import="java.util.ArrayList" %>
 

<html>
<head>

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
     padding-left:230px;
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
				</script>

<div id="head">

	
	<div id="navigation">
		
		             <b>全部</b>
                    <a href="wendang.jsp" >文档</a>
                    <a href="tupian.jsp" >图片</a>
                    <a href="shipin.jsp">视频</a>
                    <a href="yinyue.jsp">音乐</a>
                    <a href="upload.jsp">上传</a>
  </div>
		<br>
		<form   action="gaojisearch"  method="post" >
		<table width="867" border="0" cellspacing="0">

  <tr>
    <th width="190" height="49" scope="col">&nbsp;</th>
    <th width="540" scope="col">
		<div  align="left" class=c1>

			<input name="key"  type="text"  size="30" maxlength="20">
				<a id="search"  ><input name="imaget" type="image" onClick="javascript:fsubmit(document.frm);return false;" 
 								src="images/botton.gif" align="top" width="55px" height="28px"/></a>
		</div>
	
	
	
	
	</th>
    </table>


</form>

		
</div>
<div id="top" ><a>The time of this search is <s:property value="#request.time"/> s.找到<s:property value="#request.num"/>条</a></div>
<div id="condition"> <h2></h2>
<div>
<h4>@INFO HUNTING吧</h4>
<a href="http://css.jorux.com/wp-admin/post.php#" >来展示您的产品吧</a>
<br>
www.einfohunting.com
</div>
<p></p>
<h2></h2>
</div>
<div id="content"><h2></h2>
  <%  int previous=1;
    int next=1;  
    int pageno=1; 
    int pagesize;
    Integer a=(Integer)session.getAttribute("onelist2"); 
    pagesize=a.intValue();
    String pre=request.getParameter("previous");
     String pre1=request.getParameter("next");    
     String pre2=request.getParameter("i");    
    if(pre!=null){  pageno=Integer.parseInt(pre);  }  
    if(pre1!=null){pageno=Integer.parseInt(pre1);}
    if(pre2!=null){pageno=Integer.parseInt(pre2);}
    next=pageno+1;  
    previous=pageno-1;  
    int pagecount=1;
    List list =  (List)session.getAttribute("onelist1");
    int count=list.size();   
    if(count%pagesize!=0){  pagecount=count/pagesize+1;  }  
    else{  pagecount=count/pagesize;  }       
    if(next>pagecount){next=pagecount;}     
    if(previous<1){previous=1;}
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
      <tr height="30"><td align="center" style="border:1 solid">★★★ sorry,no result is found!★★★</td></tr>
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
     <td> <a href="showgaojisearch.jsp?previous=<%=previous%>">上一页</a></td>
      <% for(int i=1;i<pagecount+1;i++){ 
               if(pageno!=i){
       %>
     <td><a href="showgaojisearch.jsp?i=<%=i%>">[第<%=i%>页]</a> </td>   
     <% }else{ %>
               <td>[第<%= i %>页]</td>
           <% } } %>
       
     <td><a href="showgaojisearch.jsp?next=<%=next%>">下一页</a></td>
   </tr>    
  </table>
    </td></tr>  
   </s:else> 
   </s:subset>  
</div>

</body>
</html>