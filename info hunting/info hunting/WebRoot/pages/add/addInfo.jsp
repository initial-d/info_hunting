<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s2"%>

<html>
<head>
    <title>发布信息</title>
    <script type="text/javascript" src="<s2:url value='js/InputCheck.js'/>"></script>
</head>
<body>
    <center>
    <table border="0" cellpadding="0" cellspacing="0" width="688" height="100%">
        <tr height="20"><td><img src="images/default_t.jpg"></td></tr>
        <tr>
            <td background="images/default_m.jpg" valign="top" align="center">
           
                <s2:form action="file_Add.action" enctype="multipart/form-data" method="post" theme="simple">
                 <input type="hidden" name="addType" value="add"/>
                <table border="0" width="650" height="300" rules="all" cellspacing="0">
                    <tr height="30"><td style="text-indent:10"><font color="#004790"><b>■发布信息</b></font></td></tr>
                    <tr>
                        <td align="center">
                            <table border="0" width="650" rules="all" cellspacing="8">
  								<tr>
   								 	<td width="73" scope="col" >文件上传&nbsp;</td>
    								<td width="549" scope="col"><s2:file name = "doc" label="上传"/></td>
  								</tr>
  								<tr>
  									<td colspan="2"><s2:fielderror><s2:param value="%{'fileNameError'}"/></s2:fielderror></td></tr>
  								<tr>
  								<tr>
    								<td>关键字&nbsp;</td>
    								<td><s2:textfield name="keywords" size="55" maxlength="20"/>&nbsp;</td>
  								</tr>
  								<tr>
    								<td>&nbsp;</td>
    								<td><font color="#7F7F7F">关键字不得超过20个字符&nbsp;</font></td>
  								</tr>
  								<tr>
  									<td colspan="2"><s2:fielderror><s2:param value="%{'keywordsError'}"/></s2:fielderror></td></tr>
  								<tr>
    								<td>作者&nbsp;</td>
    								<td><s2:textfield  name="author" size="50"/>&nbsp;</td>
  								</tr>
  								<tr>
  									<td colspan="2"><s2:fielderror><s2:param value="%{'authorError'}"/></s2:fielderror></td></tr>
  								<tr>
    								<td>出版社&nbsp;</td>
    								<td><s2:textfield  name="publisher" size="50"/>&nbsp;</td>
  								</tr>
   								<tr>
   									<td colspan="2"><s2:fielderror><s2:param value="%{'publisherError'}"/></s2:fielderror></td>
   								</tr>
  								<tr>
    								<td>文件描述&nbsp;</td>
    								<td><font color="#7F7F7F">
                                            	已用：<input type="text" name="ContentUse" value="0" size="4" disabled style="text-align:center;border:0;"> 个&nbsp;&nbsp;
                                            	剩余：<input type="text" name="ContentRem" value="200" size="4" disabled style="text-align:center;border:0;"> 个 
                                        </font></td>
  								</tr>
  								<tr>
    								<td>&nbsp;</td>
    								<td><font color="#7F7F7F">信息内容不得超过200个字符&nbsp;</font></td>
  								</tr>
  								<tr>
  									<td colspan="2"><s2:fielderror><s2:param value="%{'fileContentError'}"/></s2:fielderror></td>
  								</tr>
  								<tr>
  									<td colspan="2" align="center"><s2:textarea id="content" name="fileContent" rows="12" cols="85" onkeydown="check(content,ContentUse,ContentRem,200)" onkeyup="check(content,ContentUse,ContentRem,200)" onchange="check(content,ContentUse,ContentRem,200)"/></td>
  								</tr>
  								<tr align="center" height="50">
                        			<td colspan="2">
                            			<s2:submit value="发布"/>
                            			<s2:reset value="重填"/>
                        			</td>
                    			</tr>
						</table>
				</table>
                </s2:form>            
            </td>
        </tr>
        <tr height="26"><td><img src="images/default_e.jpg"></td></tr>        
    </table>
    </center>
</body>
</html>