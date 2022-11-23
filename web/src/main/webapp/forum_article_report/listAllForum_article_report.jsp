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

  <link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
    <style>
        #pageHead { 
            width: 100%;
            height: 30%; 
        }
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
table#table-1 {
	background-color: #F0E68C;
	border: 2px solid f4f5e3;
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
<img src="<%=request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">


	
<!-- 	<h1 style="align-self:center;margin: 2rem 0">論壇文章檢舉資料</h1> -->
	
	
	<table id="table-1">
		<tr>
			<td>
				<h3>論壇文章檢舉資料</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp">回論壇文章首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
			<tr>
<!-- 				<th>論壇文章檢舉編號</th> -->
				<th>論壇文章標題</th>
				<th>會員編號</th>
				<th>檢舉事由</th>
			<!-- <th>檢舉狀態</th> -->
				
			</tr>
			<%@ include file="forum_article_report_page1.file"%>
			<c:forEach var="forum_article_reportVO" items="${list}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

	
			<tr>
<%-- 				<td>${forum_article_reportVO.article_report_no}</td> --%>
<%-- 				<td><a href="/web/Forum_articleServlet?article_no=${forum_article_reportVO.article_no}&action=getOne_For_Display"> --%>
<%-- 					${forum_article_reportVO.article_no} </a></td>  --%>
				
				<td><a href="/web/Forum_articleServlet?article_no=${forum_article_reportVO.article_no}&action=getOne_For_Display">
     					${forum_article_reportVO.article_name} </a></td>
					
				<td>${forum_article_reportVO.member_no}</td>
				<td>${forum_article_reportVO.report_reason}</td>
			</tr>

		</c:forEach>
	</table>
	<%@ include file="forum_article_report_page2.file"%>
</body>
</html>