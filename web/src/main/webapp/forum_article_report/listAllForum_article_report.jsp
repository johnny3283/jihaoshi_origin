<%@ page import="com.forum_article_report.model.Forum_article_reportVO"%>
<%@ page pageEncoding="UTF-8"%>
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
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
table {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: 0;
  padding: 0;
  width: 95%;
  table-layout: fixed;
}

table caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

table tr {
  background-color: #f8f8f8;
  border: 1px solid #ddd;
  padding: .35em;
}

table th,
table td {
  padding: .625em;
  text-align: center;
}

table th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
}
.button {
	border-radius:1rem; 
 	border: 1px solid #ccc;
}
#head {
	display:flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;"
}
#content {
	display: flex; 
	justify-content: center; 
	align-items: center;
}
#forumtable {
	display: flex;
    justify-content: center;
    flex-direction: column;
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
				<h3>論壇文章檢舉資料</h3>
				<button class="button">
					<a href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp" style="text-decoration: none;color:#333;">回論壇文章首頁</a>
				</button>
			</td>
		</tr>
	</table>
</div>
<div id="content">
	<div id="forumtable">
		<table style="margin: 0px auto;">
			<tr>
			<!--  <th>論壇文章檢舉編號</th> -->
				<th>論壇文章標題</th>
				<th>會員編號</th>
				<th>檢舉事由</th>
			<!-- <th>檢舉狀態</th> -->				
			</tr>
			<div style="margin: 0px auto;">
			<%@ include file="forum_article_report_page1.file" %>
			</div>
			<c:forEach var="forum_article_reportVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
			<%--  <td>${forum_article_reportVO.article_report_no}</td> --%>
			<%--  <td><a href="/web/Forum_articleServlet?article_no=${forum_article_reportVO.article_no}&action=getOne_For_Display"> --%>
			<%--  ${forum_article_reportVO.article_no} </a></td>  --%>				
				<td><a href="/web/Forum_articleServlet?article_no=${forum_article_reportVO.article_no}&action=getOne_For_Display">${forum_article_reportVO.article_name} </a></td>					
				<td>${forum_article_reportVO.member_no}</td>
				<td>${forum_article_reportVO.report_reason}</td>
			</tr>
			</c:forEach>
		</table>
		<br>
		<%@ include file="forum_article_report_page2.file" %>
	</div>
</div>
	<br>
	
</body>
</html>