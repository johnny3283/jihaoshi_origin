<%@page import="com.mem.controller.MemberServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>
		歡迎:<font color=red> ${MemberAcc} </font>您好
		<br><font>會員編號: ${MemberNo} </font>
		<br><font>會員姓名: ${MemberName} </font>
	</h3>

	<FORM METHOD="post" ACTION="MemberServlet">
		<input type="hidden" name="action" value="Logout"> <input
			type="submit" value="登出">
	</FORM>
</body>
</html>