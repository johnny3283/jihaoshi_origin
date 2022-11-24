<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>


<%
ManagerVO mgrVO = (ManagerVO) request.getAttribute("mgrVO"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改</title>
<link type="text/css" href="../css/form.css" rel="stylesheet">
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;	
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>




</head>
<body>
<div class="wrapper fadeInDown">
        <div id="formContent">
            <!-- Tabs Titles -->
            <h2 class="active"> 修改員工 </h2><br>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="ManagerServlet" name="form1">
		<table>
			<tr>
				<td>員工編號:<font color=red><b>*</b></font></td>
				<td><%=mgrVO.getManagerNo()%></td>
			</tr>
			
			<tr>
				<td>員工帳號:</td>
				<td><input type="TEXT" name="managerAccount" size="45"
					value="<%=mgrVO.getManagerAccount()%>" /></td>
			</tr>            
			<tr>
				<td>員工密碼:</td>
				<td><input type="password" name="managerPassword" size="45"
					value="<%=mgrVO.getManagerPassword()%>" /></td>
			</tr>            
			<tr>
				<td>員工姓名:</td>
				<td><input type="TEXT" name="managerName" size="45"
					value="<%=mgrVO.getManagerName()%>" /></td>
			</tr>
			<tr>
				<td>員工IP:</td>
				<td><input type="TEXT" name="managerIp" size="45"
					value="<%= mgrVO.getManagerIp()%>" /></td>
			</tr>
			<tr>
				<td>員工狀態:</td>
				<td><input type="TEXT" name="managerStatus" size="45"
					value="<%= mgrVO.getManagerStatus()%>" /></td>
			</tr>
			
			</table>
			
			<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="managerNo" value="<%=mgrVO.getManagerNo()%>">
<input type="submit" value="送出修改"></FORM>
</div></div>
</body>
</html>