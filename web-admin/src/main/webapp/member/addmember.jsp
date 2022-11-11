<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
MemberVO MemberVO = (MemberVO) request.getAttribute("memberVO");
%>

<html>
<head>

<title>會員新增 - addmember.jsp</title>

<style>
body {
	background-color: lightgray;
	}
table {
	width: 455px;
	background-color: lightgray;
	margin-top: 1px;
	margin-bottom: 1px;
	magin-left: 50%;
	font-size: 18px;
}

table, th, td {
	border: 0px solid #CCCCFF;
	background-color: lightgray;
}

th, td {
	padding: 5px;
}

h3 {
	text-align: center;
}

form {
	border: 1px solid black;
	width: 500px;
	height: 500px;
	margin-left: 30%;
	position: absolute;
	background-color: lightgray;
}

#submit {
	position: absolute;
	margin-left: 40%;
}

input {
	background-color: lightgray;
}
</style>



</head>
<body bgcolor='white'>



	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="MemberServlet" name="form1">
		<h3 id="form-title">會員註冊:</h3>
		<table>
			<tr>
				<td>會員帳號:</td>
				<td><input type="TEXT" name="memberAccount" size="45"
					value="<%=(MemberVO == null) ? "" : MemberVO.getMemberAccount()%>" /></td>
			</tr>
			<tr>
				<td>會員密碼:</td>
				<td><input type="password" name="memberPassword" size="45"
					value="<%=(MemberVO == null) ? "" : MemberVO.getMemberPassword()%>" /></td>
			</tr>
			<tr>
				<td>會員姓名:</td>
				<td><input type="TEXT" name="memberName" size="45"
					value="<%=(MemberVO == null) ? "" : MemberVO.getMemberName()%>" /></td>
			</tr>
			<tr>
				<td>會員電話:</td>
				<td><input type="TEXT" name="memberPhone" size="45"
					value="<%=(MemberVO == null) ? "" : MemberVO.getMemberPhone()%>" /></td>
			</tr>
			<tr>
				<td>會員暱稱:</td>
				<td><input type="TEXT" name="memberNickname" size="45"
					value="<%=(MemberVO == null) ? "" : MemberVO.getMemberNickname()%>" /></td>
			</tr>
			<tr>
				<td>會員地址:</td>
				<td><input type="TEXT" name="memberAddress" size="45"
					value="<%=(MemberVO == null) ? "" : MemberVO.getMemberAddress()%>" /></td>
			</tr>
			<tr>
				<td>會員email:</td>
				<td><input type="TEXT" name="memberEmail" size="45"
					value="<%=(MemberVO == null) ? "" : MemberVO.getMemberEmail()%>" /></td>
			</tr>
			<tr>
				<td><input type="hidden" name="action" value="insert">
					<input type="submit" value="送出新增" id="submit"></td>
			</tr>
		</table>

	</FORM>
</body>
<script>
	
</script>
</html>