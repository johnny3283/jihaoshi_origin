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

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;<a href='frontPage.jsp'>到首頁</a>   <br><br>
	text-align: center;
}
</style>

<title>listOneMember</title>
</head>
<body>
	<table>
		<tr>
			<th>會員編號</th>
			<th>會員密碼</th>
			<th>會員姓名</th>
			<th>會員電話</th>
			<th>會員暱稱</th>
			<th>會員地址</th>
			<th>會員e-mail</th>

		</tr>
		<tr>
			
			<td>${MemberVO.memberNo}</td>
			<td>${MemberVO.memberPassword}</td>
			<td>${MemberVO.memberName}</td>
			<td>${MemberVO.memberPhone}</td>
			<td>${MemberVO.memberNickname}</td>
			<td>${MemberVO.memberAddress}</td>
			<td>${MemberVO.memberEmail}</td>




		</tr>
	</table>
		<a href='frontPage.jsp'>到首頁</a>   <br><br>

</body>
</html>