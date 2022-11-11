<%@ page import="com.forum_article_report.model.Forum_article_reportVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_article_report.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
Forum_article_reportService forum_article_reportSvc = new Forum_article_reportService();
List<Forum_article_reportVO> list = forum_article_reportSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>論壇文章檢舉資料</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>


	<table id="table-1">
		<tr>
			<td>
				<h3>論壇文章檢舉言資料</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/forum_article_report/forum_article_report_select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>論壇文章檢舉編號</th>
			<th>論壇文章編號</th>
			<th>會員編號</th>
			<th>檢舉事由</th>
			<th>檢舉狀態</th>
			
		</tr>
		<%@ include file="forum_article_report_page1.file"%>
		<c:forEach var="forum_article_reportVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">


			<tr>
				<td>${forum_article_reportVO.article_report_no}</td>
				<td>${forum_article_reportVO.article_no}</td>
				<td>${forum_article_reportVO.member_no}</td>
				<td>${forum_article_reportVO.report_reason}</td>
				<td>${forum_article_reportVO.report_status}</td>
				

				<td>
					<FORM METHOD="post" ACTION="/web-admin/Forum_article_reportServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="article_report_no" value="${forum_article_reportVO.article_report_no}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" ACTION="/web-admin/Forum_article_reportServlet" -->
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 							name="article_no" value="${forum_articleVO.article_no}"> --%>
<!-- 						<input type="hidden" name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
			</tr>

		</c:forEach>
	</table>
	<%@ include file="forum_article_report_page2.file"%>
</body>
</html>