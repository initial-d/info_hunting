<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s2" uri="/struts-tags" %>
<%@page import="java.sql.*"%>
<%@ page import="actions.forDeleteSearch" %>
<%@ page import="Path.*;" %>

<html>
<head>
    <title>��̨-�鿴��Ϣ�б�</title>
    <script type="text/javascript" src="<s2:url value='js/DeleteCheck.js'/>"></script>
</head>
<body>
    <center>
        <table border="0" width="650" cellspacing="0"  cellpadding="0">
            
            
         
                <tr height="30">
                    <td style="text-indent:8">���</td>
                    <td><s2:fielderror/>&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" align="center" style="border:1 solid">
                        <table border="0" width="100%" cellspacing="0" cellpadding="0">
                            <tr align="center" height="30" bgcolor="#F0F0F0">
                                <td width="10%"><b>���</b></td>
                                <td width="15%"><b>�ļ�����</b></td>
                                <td width="25%"><b>�ļ�����</b></td>
                                <td width="20%"><b>����ʱ��</b></td>
                               
                      			<td width="10%"><b>���</b></td>
                                <td width="20%" colspan="2"><b>����</b></td>
                            </tr>
                            <% 	
								String className="sun.jdbc.odbc.JdbcOdbcDriver";
								
								forDeleteSearch dizhi=new forDeleteSearch();
          
      	         				String tomcat=dizhi.getPath("webapps");       
                  				String filepath= tomcat;
   								String recordpath= filepath+"\\webRoot\\SQL\\db_sousuo.mdb";
								String url="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=" + RealPath.Path + "SQL\\db_sousuo.mdb";
								Connection con=DriverManager.getConnection(url);
								Statement stmt=con.createStatement();
			
								try{
									Class.forName(className);
								}catch(java.lang.ClassNotFoundException e){
									System.out.println("�����ˣ�����");
								}
								String r1="select * from tb_info";
								ResultSet rs= stmt.executeQuery(r1);
								int i=0;
								while(rs.next()){
					
									i=i+1;
									String ss=null;
									String state=null;
									int fileType=rs.getInt("fileType");
									String fileName=rs.getString("fileName");
									String upTime=rs.getString("upTime");
									String fileState=rs.getString("fileState");
									int id=rs.getInt("id");
									if(fileType==1){
										ss="�ĵ�";
									}
									if(fileType==2){
										ss="ͼƬ";
									}
									if(fileType==3){
										ss="��Ƶ";
									}
									if(fileType==4){
										ss="����";
									}
									if(fileState.equals("1")){
										state="��";
									}
					
					
							%>
                            
                            
                            
                           
                                
                                    <tr height="30" bgcolor="#F9F9F9">
                                    
                                        <td align="center"><b><%=i %></b></td>
                                        <td style="text-indent:10" align="center"><%=ss %></td>
                                        <td style="text-indent:5" align="center"><%=fileName %></td>
                                        <td align="center"><%=upTime %></td>
                                     	 <td align="center"><% if(fileState.equals("1")){ %><font color="blue">��</font><%}else{ %><font color="red">��</font><%} %></td>
                                       
                                        <td align="center"><a href="admin_CheckShow.action?checkID=<%=id %>">�����</a></td>
                                        <td align="center"><a href="admin_Delete.action?deleteID=<%=id %>" onclick="return really()">��ɾ��</a></td>
                                    </tr>
                         <%} %>
                        </table>
                    </td>
                </tr>
                <tr height="8"><td></td></tr>
                <tr><td align="center" colspan="2"></td></tr>
            
        </table>
    </center>
</body>
</html>