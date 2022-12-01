<%@ page import="com.forum_comment.model.Forum_commentVO"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_comment.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
// Forum_commentVO forum_commentVO = (Forum_commentVO) request.getAttribute("forum_commentVO"); //Forum_commentServlet.java(Concroller), 存入req的Forum_commentVO物件
Forum_commentService forum_commentSvc = new Forum_commentService();
// List<Forum_commentVO> list = forum_commentSvc.getAll(1);
List<Forum_commentVO> list = forum_commentSvc.getAll();
pageContext.setAttribute("list", list);
String type = request.getParameter("type");
%>

<html>
<head>
<!--  <link href="/web-admin/bootstrap-4.6.2/dist/css/bootstrap.min.css" rel="stylesheet" > -->
 <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
  <script src="/web-admin/js/jquery-3.6.1.min.js" ></script>
  <script src="/web-admin/bootstrap-4.6.2/dist/js/bootstrap.bundle.min.js" ></script>
<title>論壇留言資料</title>
<style>
        div.divflex{
        display:flex;
        width:100%;
        margin:0;
        height:100vh-30%;
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

.btn {
	background-color: #FFBA3B;
}


/* .btn:hover { */
/* 		    color: var(--bs-btn-hover-color); */
/* 		    background-color: #FFBA3B; */
/* 		    border-color: var(--bs-btn-hover-border-color); */
/* 		} */


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

</style>

</head>
<body bgcolor='white'>
<%@ include file="../navbar.file" %>
<p>
<!--    <a class="btn"  id="comment" data-toggle="collapse" href="#multiCollapseExample1" role="button" aria-expanded="false" aria-controls="multiCollapseExample1">修改文章留言狀態</a>  -->
    <button class="btn"  id="comment" type="button" data-toggle="collapse" data-target="#multiCollapseExample1" aria-expanded="false" aria-controls="multiCollapseExample1" style="background-color: #FFBA3B">修改文章留言狀態</button> 
  	<button class="btn" id="forum_comment" type="button" data-toggle="collapse" data-target="#multiCollapseExample2" aria-expanded="false" aria-controls="multiCollapseExample2" style="background-color: #FFC14F">文章留言檢舉處理</button>
<!--   <button class="btn" type="button" data-toggle="collapse" data-target=".multi-collapse" aria-expanded="false" aria-controls="multiCollapseExample1 multiCollapseExample2">修改文章留言狀態與文章留言檢舉處理</button> -->
</p>
<div class="row">
  <div class="col">
    <div class="collapse multi-collapse" id="multiCollapseExample1">
      <div class="card card-body">
     	<div id="head">
     	<table id="table-1">
		<tr>
			<td>
				<h3>論壇留言資料</h3>
				<button class="button">
					<a href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp" style="text-decoration: none;color:#333;">回首頁</a>
				</button>
			</td>
		</tr>
	</table>
</div>
	<table>
		<tr>
<!-- 			<th>論壇文章留言編號</th> -->
			<th>論壇文章標題</th>
			<th>會員編號</th>
			<th>編輯時間</th>
			<th>留言內容</th>
			<th>留言狀態</th>
			
		</tr>
		<div style="margin: 0px auto;">
		<%@ include file="forum_comment_page1.file"%>
		</div>
		<c:forEach var="forum_commentVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">


			<tr>
<%-- 				<td>${forum_commentVO.comment_no}</td> --%>
				<td>${forum_commentVO.article_name}</td>
				<td>${forum_commentVO.member_no}</td>
				<td>${forum_commentVO.comment_time}</td>
				<td>${forum_commentVO.comment_content}</td>
				
				<td>[${forum_commentVO.comment_status}]
				  ${(forum_commentVO.comment_status==0)? '隱藏':''}
				  ${(forum_commentVO.comment_status==1)? '顯示':''}
			<td>
					<FORM METHOD="post" ACTION="/web-admin/Forum_commentServlet?whichPage=" <%=pageIndex%> style="margin-bottom: 0px;">
						 
						   <input type="submit" value="修改留言狀態" class="button"> 
						
						<input type="hidden" name="type" value="1">
						
						<input type="hidden" name="comment_no" value="${forum_commentVO.comment_no}">
					
					 <c:if test="${forum_commentVO.comment_status==1}">	
						<input type="hidden" name="action" value="change_status_0">
					 </c:if> 
					 <c:if test="${forum_commentVO.comment_status==0}">	
						<input type="hidden" name="action" value="change_status_1">
					 </c:if> 
					
					</FORM>
				</td>
				
			</tr>

		</c:forEach>
	</table>
	<%@ include file="forum_comment_page2.file"%>
    </div>
  </div>
  <div class="col">
    <div class="collapse multi-collapse" id="multiCollapseExample2">
      <div class="card card-body">
         <jsp:include page="/forum_comment_report/listAllForum_comment_report.jsp" />
    </div>
  </div>
</div>
<script>
	let type = <%=type%>
	$(document).ready(function(){
		if(type == 1){
			$("#comment").trigger("click");
		} else if(type == 2){
			$("#forum_comment").trigger("click");
		}
	
	})
	
</script>
	



</body>
</html>