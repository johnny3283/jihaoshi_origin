<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.forum_comment_report.model.Forum_comment_reportVO"%>

<%
Forum_comment_reportVO forum_comment_reportVO = (Forum_comment_reportVO) request.getAttribute("forum_comment_reportVO"); //Forum_comment_reportServlet.java (Concroller) 存入req的forum_comment_reportVO物件 (包括幫忙取出的forum_comment_reportVO, 也包括輸入資料錯誤時的forum_comment_reportVO物件)
%>

<html> 
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>論壇留言檢舉修改</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>論壇留言檢舉修改</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/forum_comment_report/forum_comment_report_select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="/web-admin/Forum_comment_reportServlet" name="form1">
		<table>


			<tr>
				<td>檢舉事由:</td>
				<td><input type="TEXT" name="report_reason" size="45"
					value="<%=forum_comment_reportVO.getReport_reason()%>"  required/></td>
			</tr>

		
			<tr>
				<td>檢舉狀態:</td>
				<td><input type="TEXT" name="report_status" size="45"
						value="<%=forum_comment_reportVO.getReport_status()%>" /></td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="comment_report_no"
			value="<%=forum_comment_reportVO.getComment_report_no()%>"> <input
			type="submit" value="送出修改">
	</FORM>
</body>

</html>