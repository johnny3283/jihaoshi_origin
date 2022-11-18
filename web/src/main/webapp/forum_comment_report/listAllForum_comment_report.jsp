<%@ page import="com.forum_comment_report.model.Forum_comment_reportVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_comment_report.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
// Forum_comment_reportVO forum_comment_reportVO = (Forum_comment_reportVO) request.getAttribute("forum_comment_reportVO"); //Forum_comment_reportServlet.java(Concroller), 存入req的Forum_comment_reportVO物件
Forum_comment_reportService forum_comment_reportSvc = new Forum_comment_reportService();
List<Forum_comment_reportVO> list = forum_comment_reportSvc.getAll();
pageContext.setAttribute("list", list);
%>
 
<html>
<head>
<title>論壇留言檢舉資料</title>

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


	<table id="table-1">
		<tr>
			<td>
				<h3>論壇留言檢舉資料</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp">回論壇文章首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>論壇文章檢舉編號</th>
			<th>論壇文章留言編號</th>
			<th>論壇文章編號</th>
			<th>會員編號</th>
			<th>檢舉事由</th>
		
			
		</tr>
		<%@ include file="forum_comment_report_page1.file"%>
		<c:forEach var="forum_comment_reportVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">


			<tr>
				<td>${forum_comment_reportVO.comment_report_no}</td>
				<td>${forum_comment_reportVO.comment_no}</td>
				
				<td><a href="/web/Forum_articleServlet?article_no=${forum_comment_reportVO.article_no}&action=getOne_For_Display">
					${forum_comment_reportVO.article_no} </a></td> 
				
				
				<td>${forum_comment_reportVO.member_no}</td>
				<td>${forum_comment_reportVO.report_reason}</td>
				
				

<!-- 				<td> -->
<!-- 					<FORM METHOD="post" ACTION="/web-admin/Forum_comment_reportServlet" -->
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="修改"> <input type="hidden" -->
<%-- 							name="comment_report_no" value="${forum_comment_reportVO.comment_report_no}"> --%>
<!-- 						<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" ACTION="/web-admin/Forum_comment_reportServlet" -->
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 							name="comment_report_no" value="${forum_comment_reportVO.comment_report_no}"> --%>
<!-- 						<input type="hidden" name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
			</tr>

		</c:forEach>
	</table>
	<%@ include file="forum_comment_report_page2.file"%>
</body>
</html>