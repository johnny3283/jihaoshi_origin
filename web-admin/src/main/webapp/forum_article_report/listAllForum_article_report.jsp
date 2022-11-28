<%@ page import="com.forum_article_report.model.Forum_article_reportVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_article_report.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
Forum_article_reportService forum_article_reportSvc = new Forum_article_reportService();
List<Forum_article_reportVO> list = forum_article_reportSvc.getAll();
pageContext.setAttribute("list", list);
String type = request.getParameter("type");
%>

<html>
<head>
<title>論壇文章檢舉資料</title>

<style>
table#table-1 {
	background-color:  #F0E68C;
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
	width: 1280px;
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



	<table id="table-1" style="width: 100%">
		<tr>
			<td>
				<h3>論壇文章檢舉資料</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table style="width: 97.5vw">
		<tr>
<!-- 			<th>論壇文章檢舉編號</th> -->
			<th>論壇文章編號</th>
			<th>會員編號</th>
			<th>檢舉事由</th>
			<th>檢舉狀態</th>
			
		</tr>
		<%@ include file="forum_article_report_page1.file"%>
		<c:forEach var="forum_article_reportVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">


			<tr>
<%-- 				<td>${forum_article_reportVO.article_report_no}</td> --%>
<%-- 				<td>${forum_article_reportVO.article_no}</td>  --%>
				
				<td>${forum_article_reportVO.article_name}</td>
				<td>${forum_article_reportVO.member_no}</td>
				<td>${forum_article_reportVO.report_reason}</td>
				<td>[${forum_article_reportVO.report_status}]
				  ${(forum_article_reportVO.report_status==0)? '未處理':''}
				  ${(forum_article_reportVO.report_status==1)? '未通過':''}
				   ${(forum_article_reportVO.report_status==2)? '通過':''}
				</td>
				
				<td>
					<FORM METHOD="post" ACTION="/web-admin/Forum_article_reportServlet" style="margin-bottom: 0px;">
						 <c:if test="${forum_article_reportVO.report_status!=2}">	
							<input type="submit" value="論壇文章檢舉處理">
						 </c:if>
						<input type="hidden" name="type" value="2">
						<input type="hidden" name="whichPage" value="<%=whichPage%>"/>
						
						
						<input type="hidden" name="article_report_no" value="${forum_article_reportVO.article_report_no}">
					
					 <c:if test="${forum_article_reportVO.report_status==0}">	
						<input type="hidden" name="action" value="change_status_0">
					 </c:if> 
					 <c:if test="${forum_article_reportVO.report_status==1}">	
						<input type="hidden" name="action" value="change_status_1">
					 </c:if> 
					 <c:if test="${forum_article_reportVO.report_status==2}">	
						<input type="hidden" name="action" value="change_status_2">
					 </c:if> 
					 
					</FORM>
				</td>

			</tr>

		</c:forEach>
	</table>
	<%@ include file="forum_article_report_page2.file"%>

	
</body>
</html>