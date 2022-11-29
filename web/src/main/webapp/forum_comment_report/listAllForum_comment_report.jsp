<%@ page import="com.forum_comment_report.model.Forum_comment_reportVO"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_comment_report.model.*"%>
<%@ page import="com.mem.model.MemberVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
// Forum_comment_reportVO forum_comment_reportVO = (Forum_comment_reportVO) request.getAttribute("forum_comment_reportVO"); //Forum_comment_reportServlet.java(Concroller), 存入req的Forum_comment_reportVO物件
Forum_comment_reportService forum_comment_reportSvc = new Forum_comment_reportService();

session = request.getSession();
MemberVO memberVO = ((MemberVO)session.getAttribute("member"));
Integer memberNo = Integer.valueOf(memberVO.getMemberNo());
List<Forum_comment_reportVO> list = forum_comment_reportSvc.getAll(memberNo);
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>論壇留言檢舉資料</title>

<style>
	
	div.divflex {
		display: flex;
		width: 100%;
		margin: 0;
		height: 100vh-30%;
	}
	
	body {
		height: 100vh;
		background-color: #FFFAF0;
	}
	
	div.formdiv {
		style ="width: 80%%;
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
<%@ include file="../navbar.file" %>
<br>
<div id="head">
	<table id="table-1">
		<tr>
			<td>
				<h3>論壇留言檢舉資料</h3>
				<button class="button">
					<a href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp" style="text-decoration: none;color:#333;">回論壇文章首頁</a>
				</button>
			</td>
		</tr>
	</table>
</div>
	<div id="content">
	<table>
		<tr>
<!-- 			<th>論壇文章檢舉編號</th> -->
<!-- 			<th>論壇文章留言編號</th> -->
			<th>論壇文章標題</th>
			<th>會員編號</th>
			<th>檢舉事由</th>
		</tr>
		<div style="margin: 0px auto;">
		<%@ include file="forum_comment_report_page1.file"%>
		</div>
		<c:forEach var="forum_comment_reportVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">


			<tr>
<%-- 				<td>${forum_comment_reportVO.comment_report_no}</td> --%>
<%-- 				<td>${forum_comment_reportVO.comment_no}</td> --%>

				<td><a
					href="/web/Forum_articleServlet?article_no=${forum_comment_reportVO.article_no}&action=getOne_For_Display">
						${forum_comment_reportVO.article_name} </a></td>


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
	</div>
	<br>
	<%@ include file="forum_comment_report_page2.file"%>
</body>
</html>