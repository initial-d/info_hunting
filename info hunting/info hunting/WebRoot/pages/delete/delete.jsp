<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s2" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.dom4j.*" %>
<%@ page import="org.dom4j.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="actions.forDeleteSearch" %>


<html>
<head>
    <title>后台-查看信息列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/table.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
function checkCount(){ 
  var obj=document.getElementsByName("deleteName"); 
  var sum=0; 
  //计算复选框选中个数
	for (var i = 0; i < obj.length; i++) {
		if(obj[i].checked) sum++;
	}
return sum;
} 
//确认是否要删除选中的资源
function test(){ 
  if(checkCount()!=0){ 
      if(!confirm("确定要删除吗？")){ 
        return false; 
        } 
  } 
  else{ 
      //如果没有选择要删除的资源，则提示用户选择要删除的资源
      alert("请选择要删除的资源！"); 
      return false; 
      } 
} 

</script>
    
    <script type="text/javascript" src="<s2:url value='js/DeleteCheck.js'/>"></script>
</head>
<body>


       <form action="deleteInfo.action" method="post" onsubmit="return test();">
    <center>
        <table border="0" width="650" cellspacing="0"  cellpadding="0">
          
            
                <tr height="30">
                    <td style="text-indent:8">资源删除</td>
                    <td><s2:fielderror/>&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" align="center" style="border:1 solid">
                        <table border="0" width="100%" cellspacing="0" cellpadding="0">
                            <tr align="center" height="50" bgcolor="#F0F0F0">
                                <td width="40%"><b>文件名</b></td>
                                <td width="40%"><b>文件描述</b></td>
                                <td width="20%"><b>上传日期</b></td>

                            </tr>
               
            		<% 	forDeleteSearch dizhi=new forDeleteSearch();
          
      	         		String tomcat=dizhi.getPath("webapps");       
                  		String filepath= tomcat+"\\"+"test.xml";

    	             	SAXReader reader = new SAXReader(); 
	               		Document document = null;
	        			try {
			           		document = reader.read("file:\\"+filepath);
		          		} catch (DocumentException e) {
		            		e.printStackTrace();
	                	} 
	                	
	                	
						try{
							Element root = document.getRootElement(); 
						
						Element foo = null; 

						Iterator  it = root.elementIterator("resourceitem"); 
		
			     			String id;
		         			String title;
		         			String describe;
		         			String date;
                 			String url;
	
						while (it.hasNext()) { 	
		
							foo =  (Element) it.next(); 
			 				id=foo.elementText("id");
			 				title=foo.elementText("title");
			 				describe=foo.elementText("describe");
			 				date=foo.elementText("date");
			 				String date1=date.substring(0,4);
			 				String date2=null;
			 				String date3=null;
			 			
			 				if(date.substring(6,7).equals("-")){
			 					date2="0"+date.substring(5,6);
			 					if(date.substring(8,9).equals(" ")){
			 						date3="0"+date.substring(7,8);
			 					}else{
			 						date3=date.substring(7,9);
			 					}
			 					
			 				}else{
			 					date2=date.substring(5,7);
			 					date3=date.substring(8,10);
			 				}
			 				
			 				
			 				String date4=date1+date2+date3;
			 				int s;
			 				s=Integer.parseInt(date4);
			 			
			 				
			 				
			 				
			 				url=foo.elementText("url");
					%>
		
		

									<tr >
										<td  class="spec" align="left">
											 <input type="checkbox" name="deleteName" value="<%=url %>" ><a href="<%=url %>"><%=title %></a>						
										</td>
										<td class="alt" align="center">
											<%=describe %>
										</td>
										<td class="date">
											<%=date %>
										</td>
									</tr>
                            <%}}catch(Exception e){
							
						} %>
                
                        </table>
                    </td>
                </tr>

				
					<tr align="center">
						<td height="50">
							<input type=submit value="  删   除  " class="btn3_mouseout" >
						</td>
					</tr>
				
			
                <tr height="8"><td></td></tr>
                <tr><td align="center" colspan="2"><jsp:include page="/pages/page.jsp"/></td></tr>  
        </table>
    </center>
    </form>
</body>
</html>