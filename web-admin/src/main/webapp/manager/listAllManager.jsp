<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager.model.*"%>

<%
ManagerService manSvc = new ManagerService();
List<ManagerVO> list = manSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>

<title>listAllManager</title>
<style>
body {
	background-color: lightgray;
}

h1 {
	color: blue;
	display: inline;
	margin-left: 43%;
}
</style>

<style>

table {
	width: 800px;
	/* 	background-color: #79798c; */
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: auto;
	margin-right: auto;
}

table, th, td {
	border: 1px solid black;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
<style>
#td.password{
	color:red;
}
</style>
<body>
	<h1>管理員資料</h1>
	<table>
		<tr>
			<th>管理員編號</th>
			<th>管理員姓名</th>
			<th>管理員帳號</th>
			<th>管理員狀態</th>
			<th>權限名稱</th>

		</tr>
		<c:forEach var="ManagerVO" items="${list}">
			<tr>
				<td>${ManagerVO.managerNo}</td>
				<td>${ManagerVO.managerName}</td>
				<td>${ManagerVO.managerAccount}</td>
				<td>${ManagerVO.managerStatus}</td>
				<td>${ManagerVO.authorityName}</td>

			</tr>
		</c:forEach>
	</table>
	<!-- 	<a href='frontPage.jsp'>到首頁</a> -->
	<br>
	<br>
</body>
</html>