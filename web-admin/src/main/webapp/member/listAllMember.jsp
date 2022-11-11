<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<%
MemService memSvc = new MemService();
List<MemberVO> list = memSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>

<title>listAllMember</title>
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
</head>
<body>
	<h1>會員資料</h1>
	<table>
		<tr>
			<th>會員編號</th>
			<th>會員密碼</th>
			<th>會員姓名</th>
			<th>會員電話</th>
			<th>會員暱稱</th>
			<th>會員地址</th>
			<th>會員e-mail</th>
			<th>會員狀態</th>

		</tr>
		<c:forEach var="MemberVO" items="${list}">
			<tr>
				<td>${MemberVO.memberNo}</td>
				<td>${MemberVO.memberPassword}</td>
				<td>${MemberVO.memberName}</td>
				<td>${MemberVO.memberPhone}</td>
				<td>${MemberVO.memberNickname}</td>
				<td>${MemberVO.memberAddress}</td>
				<td>${MemberVO.memberEmail}</td>
				<td>${MemberVO.memberState}</td>
				<td>
					<FORM METHOD="post" 
					ACTION="<%=request.getContextPath()%>/member/MemberServlet" style="margin-bottom: 0px;"> 
						<input type="submit" value="修改">
						<input type="hidden" name="memberNo" value="${MemberVO.memberNo}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
							
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/member/MemberServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> 
						<input type="hidden" name="memberNo" value="${MemberVO.memberNo}"> 	
						<input type="hidden" name="action" value="delete">
						
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href='frontPage.jsp'>到首頁</a>
	<br>
	<br>

</body>
</html>