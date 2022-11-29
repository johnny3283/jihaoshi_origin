<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.forum_article_report.model.Forum_article_reportVO"%>

<%
Forum_article_reportVO forum_article_reportVO = (Forum_article_reportVO) request.getAttribute("forum_article_reportVO"); 
//Forum_article_reportServlet.java (Concroller) 存入req的forum_article_reportVO物件 (包括幫忙取出的forum_article_reportVO, 也包括輸入資料錯誤時的forum_article_reportVO物件)
%>
 
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>論壇文章檢舉修改</title>
 
<style>
table#table-1 {
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

.button {
	border-radius:1rem; 
 	border: 1px solid #ccc;
}
#content {
	display:flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;"
}
#head {
	display:flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;"
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
<%@ include file="../navbar.file" %>
<br>
<div id="head">
	<table id="table-1">
		<tr>
			<td>
				<h3>論壇留言修改</h3>
				<button class="button">
					<a href="<%=request.getContextPath()%>/forum_article_report/forum_article_report_select_page.jsp">回首頁</a>
				</button>
			</td>
		</tr>
	</table>
</div>
<div>
	<h3>資料修改</h3>
</div>
<div>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
</div>
	<FORM METHOD="post" ACTION="/web-admin/Forum_article_reportServlet" name="form1">
		<table>
			<tr>
				<td>論壇文章檢舉編號:</td>
				<td><input type="TEXT" name="article_report_no" size="45"
					value="<%=forum_article_reportVO.getArticle_report_no()%>"  required/></td>
			</tr>


			<tr>
				<td>論壇文章編號:</td>
				<td><input type="TEXT" name="article_no" size="45"
					value="<%=forum_article_reportVO.getArticle_no()%>"  required/></td>
			</tr>
			
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="member_no" size="45"
					value="<%=forum_article_reportVO.getMember_no()%>"  required/></td>
			</tr>

			<tr>
				<td>檢舉事由:</td>
				<td><textarea type="TEXT" name="report_reason" size="45"
						value="<%=forum_article_reportVO.getReport_reason()%>"  required/></textarea></td>
			</tr>

		
			<tr>
				<td>檢舉狀態:</td>
				<td><input type="TEXT" name="report_status" size="45"
						value="<%=forum_article_reportVO.getReport_status()%>"  required/></td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="article_report_no"
			value="<%=forum_article_reportVO.getArticle_report_no()%>"> <input
			type="submit" value="送出修改">
	</FORM>
</body>

</html>