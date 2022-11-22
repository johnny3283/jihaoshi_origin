<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<%
MemService memSvc = new MemService();
MemberVO MemberVO = (MemberVO) request.getAttribute("MemberVO");
%>
<!DOCTYPE html>
<html>
<head>
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
#td.password {
	color: red;
}
</style>

<title>listOneMember</title>
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<table>
		<tr>
			<th>會員編號</th>
			<th>會員姓名</th>
			<th>會員電話</th>
			<th>會員暱稱</th>
			<th>會員地址</th>
			<th>會員e-mail</th>
			<th>會員狀態</th>

		</tr>
		<tr>

			<td>${MemberVO.memberNo}</td>
			<td>${MemberVO.memberName}</td>
			<td>${MemberVO.memberPhone}</td>
			<td>${MemberVO.memberNickname}</td>
			<td>${MemberVO.memberAddress}</td>
			<td>${MemberVO.memberEmail}</td>
			<td>${MemberVO.memberState}</td>




		</tr>
	</table>
	<a href='frontPage.jsp'>到首頁</a>
	<br>
	<br>

</body>
</html>