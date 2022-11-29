<%@ page import="com.forum_article.model.Forum_articleVO"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_article.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
// Forum_articleVO forum_articleVO = (Forum_articleVO) request.getAttribute("forum_articleVO"); //Forum_articleServlet.java(Concroller), 存入req的Forum_articleVO物件
Forum_articleService forum_articleSvc = new Forum_articleService();
List<Forum_articleVO> list = forum_articleSvc.getAll();
pageContext.setAttribute("list", list);
%> 

<html>
<head>
<title>論壇文章資料</title>
    <style>
        div.divflex{
        display:flex;
        width:100%;
        margin:0;
        height:100vh-30%;
        }
        body{
        height: 100vh;
        background-color:#FFFAF0;
        }
        div.formdiv{
        style="width:80%%;
        background: #FFFAF0;
        }
    </style>


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
<%@ include file="../navbar.file" %>
<br>
	<table id="table-1">
		<tr>
			<td>
				<h3>論壇文章資料</h3>
				<button style="border-radius:1rem; border: 1px solid #ccc;">
					<a href="<%=request.getContextPath()%>/index.jsp" style="text-decoration: none;color:#333;">回即好食首頁</a>
				</button>
			</td>
		</tr>
	</table>
<div id="content" style="display:flex;flex-direction: column;">
	<table>
		<tr>
			<th>論壇文章編號</th>
			<th>文章標題</th>
			<th>會員編號</th>
			<th>編輯時間</th>
			<th>文章內容</th>
			
		</tr>
		<div style="margin: 0px auto;">
		<%@ include file="forum_article_page1.file"%>
		</div>
		<c:forEach var="forum_articleVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">


			<tr>
				<td>${forum_articleVO.article_no}</td>
				<td>${forum_articleVO.article_name}</td>
				<td>${forum_articleVO.member_no}</td>
				<td>${forum_articleVO.article_time}</td>
				<td>${forum_articleVO.article_content}</td>
				
   
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" ACTION="/web/Forum_article_reportServlet" -->
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="檢舉">  -->
<%-- 						<input type="hidden" name="article_no" value="${forum_articleVO.article_no}"> --%>
<!-- 						<input type="hidden" name="action" value="insert"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" ACTION="/web/Forum_article_reportServlet" -->
<!-- 						style="margin-bottom: 0px;"> -->
<%-- 						<input type="hidden" name="article_no" value="${forum_articleVO.article_no}"> --%>
<!-- 						<input type="hidden" name="action" value="123"> -->
<!-- 						<input type="submit" value="留言"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
<!-- 			</tr> -->

		</c:forEach>
	</table>
	<div style="margin: 0px auto;">
	<%@ include file="forum_article_page2.file"%>
	</div>
</div>
</body>
</html>