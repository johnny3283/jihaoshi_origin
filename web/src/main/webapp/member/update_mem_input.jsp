<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
MemberVO MemberVO = (MemberVO) request.getAttribute("MemberVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>會員資料修改</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/css/form.css" rel="stylesheet">
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
            <h2 class="active"> Edit Profile </h2><br>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="MemberServlet" name="form1">
		<table>
			<tr>
				<td>會員編號:<font color=red><b>*</b></font></td>
				<td><%=MemberVO.getMemberNo()%></td>
			</tr>
			
			<tr>
				<td>會員帳號:</td>
				<td><input type="TEXT" name="memberAccount" size="45"
					value="<%=MemberVO.getMemberAccount()%>" /></td>
			</tr>            
			<tr>
				<td>會員密碼:</td>
				<td><input type="password" name="memberPassword" size="45"
					value="<%=MemberVO.getMemberPassword()%>" /></td>
			</tr>            
			<tr>
				<td>會員姓名:</td>
				<td><input type="TEXT" name="memberName" size="45"
					value="<%=MemberVO.getMemberName()%>" /></td>
			</tr>
			<tr>
				<td>會員電話:</td>
				<td><input type="TEXT" name="memberPhone" size="45"
					value="<%= MemberVO.getMemberPhone()%>" /></td>
			</tr>
			<tr>
				<td>會員暱稱:</td>
				<td><input type="TEXT" name="memberNickname" size="45"
					value="<%=MemberVO.getMemberNickname()%>" /></td>
			</tr>
			<tr>
				<td>會員地址:</td>
				<td><input type="TEXT" name="memberAddress" size="45"
					value="<%=MemberVO.getMemberAddress()%>" /></td>
			</tr>
			<tr>
				<td>會員email:</td>
				<td><input type="TEXT" name="memberEmail" size="45"
					value="<%=MemberVO.getMemberEmail()%>" /></td>
			</tr>
			</table>
			
			<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="memberNo" value="<%=MemberVO.getMemberNo()%>">
<input type="submit" value="送出修改"></FORM>
</div></div>
</body>
</html>