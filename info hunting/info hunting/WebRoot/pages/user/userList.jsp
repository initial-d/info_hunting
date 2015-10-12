<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s2" uri="/struts-tags" %>


<%@page import="java.util.ArrayList" %>
<%@page import="java.util.*" %>
<%@page import="java.sql.*"%>
<%@ page import="actions.forDeleteSearch" %>
<%@ page import="Path.*;" %>


<html>
	<head>
		<title>列表</title>
	</head>
	
	<body>
	 <center>
        <table border="0" width="650" cellspacing="0"  cellpadding="0">
         
            
                <tr height="30">
                    <td style="text-indent:8">用户列表</td>
                    <td><s2:fielderror/>&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" align="center" style="border:1 solid">
                        <table border="0" width="100%" cellspacing="0" cellpadding="0">
                            <tr align="center" height="20" bgcolor="#F0F0F0">
                                <td width="20%"><b>序号</b></td>
                                <td width="40%"><b>用户名</b></td>
                                <td width="20%"><b>用户密码</b></td>
								<td width="20%"><b>操作</b></td>
                            </tr>
		<% 	forDeleteSearch dizhi=new forDeleteSearch();
			String className="sun.jdbc.odbc.JdbcOdbcDriver";
								
								
          
      	         				String tomcat=dizhi.getPath("webapps");       
                  				String filepath= tomcat;
   								String recordpath= filepath+"\\webRoot\\SQL\\db_sousuo.mdb";
								String url="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=" + RealPath.Path + "SQL\\db_sousuo.mdb";
			Connection con=DriverManager.getConnection(url);
			Statement stmt=con.createStatement();
			
			try{
				Class.forName(className);
			}catch(java.lang.ClassNotFoundException e){
				System.out.println("出错了！！！");
			}
				String r1="select * from tb_newUser";
				ResultSet rs= stmt.executeQuery(r1);
				int i=0;
				while(rs.next()){
					
					i=i+1;
					int id=rs.getInt("id");
					String name=rs.getString("user_name");
					String password=rs.getString("user_password");
					
					
			%>
			<tr >
										<td  class="spec" align="center">
											<%=i %>						
										</td>
										<td class="alt" align="center">
											<%=name %>
										</td>
										<td class="dd" align="center">
											<%=password %>
										</td>
									<td align="center"><a href="admin_DeleteUser.action?deleteID=<%=id %>" onclick="return really()" ><font color="blue">删除</font></a></td>
									</tr>
			<%} %>
			
			                        </table>
                    </td>
                </tr>
			
			
			 <tr height="8"><td></td></tr>
                <tr><td align="center" colspan="2"><jsp:include page="/pages/page.jsp"/></td></tr>  
				
		
		
		</table>
		</center>
	</body>
</html>
