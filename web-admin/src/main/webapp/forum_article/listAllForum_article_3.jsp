<%@ page import="com.forum_article.model.Forum_articleVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_article.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
// Forum_articleVO forum_articleVO = (Forum_articleVO) request.getAttribute("forum_articleVO"); //Forum_articleServlet.java(Concroller), 存入req的Forum_articleVO物件
Forum_articleService forum_articleSvc = new Forum_articleService();
List<Forum_articleVO> list = forum_articleSvc.getAll();
pageContext.setAttribute("list", list);
%>
<% 
    session.setAttribute("member_no", 2);
    //Integer member_no = (Integer)session.getAttribute("member_no"); %>
<html>
<head>
<title>論壇文章資料3</title>

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
	width: 1000px;
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
				<h3>論壇文章資料</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>論壇文章編號</th>
			<th>文章標題</th>
			<th>會員編號</th>
			<th>編輯時間</th>
			<th>文章內容</th>
			<th>文章狀態</th>
		</tr>
		<%@ include file="forum_article_page1.file"%>
		<c:forEach var="forum_articleVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">


			<tr>
				<td>${forum_articleVO.article_no}</td>
				<td>${forum_articleVO.article_name}</td>
				<td>${forum_articleVO.member_no}</td>
				<td>${forum_articleVO.article_time}</td>
				<td>${forum_articleVO.article_content}</td>
				<td>[${forum_articleVO.article_status}]
				  ${(forum_articleVO.article_status==0)? '隱藏':''}
				  ${(forum_articleVO.article_status==1)? '顯示':''}
				
				</td>

				<td>
					<FORM METHOD="post" ACTION="/web-admin/Forum_articleServlet" style="margin-bottom: 0px;">
						 <c:if test="${forum_articleVO.member_no==member_no}">
						   <input type="submit" value="修改文章狀態"> 
						</c:if> 
						<c:if test="${forum_articleVO.member_no!=member_no}">
						 <input type="submit" value="檢舉" style="border-color: red"> 
						</c:if> 
						
						<input type="hidden" name="article_no" value="${forum_articleVO.article_no}">
					
					 <c:if test="${forum_articleVO.article_status==1}">	
						<input type="hidden" name="action" value="change_status_0">
					 </c:if> 
					 <c:if test="${forum_articleVO.article_status==0}">	
						<input type="hidden" name="action" value="change_status_1">
					 </c:if> 
					
					</FORM>
				</td>
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" ACTION="/web-admin/Forum_articleServlet" -->
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 							name="article_no" value="${forum_articleVO.article_no}"> --%>
<!-- 						<input type="hidden" name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
			</tr>

		</c:forEach>
	</table>
	<%@ include file="forum_article_page2.file"%>
</body>
</html>