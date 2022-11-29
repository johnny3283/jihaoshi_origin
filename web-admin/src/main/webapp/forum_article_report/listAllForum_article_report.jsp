<%@ page import="com.forum_article_report.model.Forum_article_reportVO"%>
<%@ page pageEncoding="UTF-8"%>
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
	border: 2px solid black;
	text-align: center;
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

</head>
<body bgcolor='white'>
<br>
<div id="head">
	<table id="table-1">
		<tr>
			<td>
				<h3>論壇文章檢舉資料</h3>
				<button class="button">
					<a href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp" style="text-decoration: none;color:#333;">回首頁</a>
				</button>
			</td>
		</tr>
	</table>
</div>
<div id="content">
	<table style="width: 97.5vw">
		<tr>
<!-- 			<th>論壇文章檢舉編號</th> -->
			<th>論壇文章編號</th>
			<th>會員編號</th>
			<th>檢舉事由</th>
			<th>檢舉狀態</th>
			
		</tr>
		<div style="margin: 0px auto;">
		<%@ include file="forum_article_report_page1.file"%>
		</div>
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
							<input type="submit" value="論壇文章檢舉處理" class="button">
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
	<br>
	<%@ include file="forum_article_report_page2.file"%>
</div>	
</body>
</html>