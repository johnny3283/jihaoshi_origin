<%@ page import="com.forum_comment_report.model.Forum_comment_reportVO"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_comment_report.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
// Forum_comment_reportVO forum_comment_reportVO = (Forum_comment_reportVO) request.getAttribute("forum_comment_reportVO"); //Forum_comment_reportServlet.java(Concroller), 存入req的Forum_comment_reportVO物件
Forum_comment_reportService forum_comment_reportSvc = new Forum_comment_reportService();
List<Forum_comment_reportVO> list = forum_comment_reportSvc.getAll();
pageContext.setAttribute("list", list);
String type = request.getParameter("type");
%>

<html>
<head>
<title>論壇留言檢舉資料</title>

<style>
table#table-1 {
	background-color: #F0E68C;
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



<script>
console.log(pageNumber);
</script>

<div id="head">
	<table id="table-1">
		<tr>
			<td>
				<h3>論壇留言檢舉資料</h3>
				<button class="button">
					<a href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp" style="text-decoration: none;color:#333;">回首頁</a>
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
			<th>論壇文章編號</th>
			<th>會員編號</th>
			<th>檢舉事由</th>
			<th>檢舉狀態</th>
			
		</tr>
		<div style="margin: 0px auto;">
		<%@ include file="forum_comment_report_page1.file"%>
		</div>
		<c:forEach var="forum_comment_reportVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">


			<tr>
<%-- 				<td>${forum_comment_reportVO.comment_report_no}</td> --%>
<%-- 				<td>${forum_comment_reportVO.comment_no}</td> --%>
				<td>${forum_comment_reportVO.article_name}</td>
				<td>${forum_comment_reportVO.member_no}</td>
				<td>${forum_comment_reportVO.report_reason}</td>
				<td>[${forum_comment_reportVO.report_status}]
				  ${(forum_comment_reportVO.report_status==0)? '未處理':''}
				  ${(forum_comment_reportVO.report_status==1)? '未通過':''}
				   ${(forum_comment_reportVO.report_status==2)? '通過':''}
				</td>
				
				<td>
					<FORM METHOD="post" ACTION="/web-admin/Forum_comment_reportServlet" style="margin-bottom: 0px;">
						 <c:if test="${forum_comment_reportVO.report_status!=2}">	
							<input type="submit" value="論壇留言檢舉處理" class="button">
						 </c:if>
						 <input type="hidden" name="type" value="2">  
						<input type="hidden" name="whichPage" value="<%=whichPage%>"/>
						
						
						<input type="hidden" name="comment_report_no" value="${forum_comment_reportVO.comment_report_no}">
					
					 <c:if test="${forum_comment_reportVO.report_status==0}">	
						<input type="hidden" name="action" value="change_status_0">
					 </c:if> 
					 <c:if test="${forum_comment_reportVO.report_status==1}">	
						<input type="hidden" name="action" value="change_status_1">
					 </c:if> 
					 <c:if test="${forum_comment_reportVO.report_status==2}">	
						<input type="hidden" name="action" value="change_status_2">
					 </c:if> 
					 
					</FORM>
				</td>
				
				

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
</div>	
	
</body>
</html>