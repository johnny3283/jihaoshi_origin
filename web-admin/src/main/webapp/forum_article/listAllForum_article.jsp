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

String type = request.getParameter("type");
%>
<% 
   // session.setAttribute("member_no", 2);
    //Integer member_no = (Integer)session.getAttribute("member_no"); %>
<html>
<head>
  <link href="/web-admin/bootstrap-4.6.2/dist/css/bootstrap.min.css" rel="stylesheet" >
  <script src="/web-admin/js/jquery-3.6.1.min.js" ></script>
  <script src="/web-admin/bootstrap-4.6.2/dist/js/bootstrap.bundle.min.js" ></script>
<title>論壇文章資料</title>
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
			background-color:  #F0E68C;
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
		
		.btn {
			background-color: #FFBA3B;
		}
	</style>

</head>
<body bgcolor='white'> 
<img src="<%=request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">
<p>
  <a class="btn"  id="article" data-toggle="collapse" href="#multiCollapseExample1" role="button" aria-expanded="false" aria-controls="multiCollapseExample1">修改文章狀態</a> 
  <button class="btn " id="article_forum" type="button" data-toggle="collapse" data-target="#multiCollapseExample2" aria-expanded="false" aria-controls="multiCollapseExample2">論壇文章檢舉處理</button>
<!--   <button class="btn " type="button" data-toggle="collapse" data-target=".multi-collapse" aria-expanded="false" aria-controls="multiCollapseExample1 multiCollapseExample2">修改文章與論壇文章檢舉處理</button> -->
</p>
<div class="row">
  <div class="">  <!-- 原本有class="col" -->
    <div class="collapse multi-collapse" id="multiCollapseExample1">
      <div class="card card-body">
      
      
       <table id="table-1" style="width: 100%">
		<tr>
			<td>
				<h3>論壇文章資料</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	   </table>

	<table style="width: 100%">
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
					<FORM METHOD="post" ACTION="/web-admin/Forum_articleServlet" <%=pageNumber%> style="margin-bottom: 0px;">
						 
						   <input type="submit" value="修改文章狀態"> 
						   
					
						<input type="hidden" name="type" value="1">
						
						<input type="hidden" name="whichPage" value="<%=whichPage%>"/>
						
						<input type="hidden" name="article_no" value="${forum_articleVO.article_no}">
					
					 <c:if test="${forum_articleVO.article_status==1}">	
						<input type="hidden" name="action" value="change_status_0">
					 </c:if> 
					 <c:if test="${forum_articleVO.article_status==0}">	
						<input type="hidden" name="action" value="change_status_1">
					 </c:if> 
					
					</FORM>
				</td>
				
			</tr>

		</c:forEach>
	</table>
	<%@ include file="forum_article_page2.file"%>
	
	
      </div>
    </div>
  </div>
  <div class="">	<!-- 原本有class="col" -->
    <div class="collapse multi-collapse" id="multiCollapseExample2">
      <div class="card card-body">
         <jsp:include page="/forum_article_report/listAllForum_article_report.jsp" />
    </div>
  </div>
</div>
<script>
	let type = <%=type%>
	$(document).ready(function(){
		if(type == 1){
			$("#article").trigger("click")
		} else if(type == 2){
			$("#article_forum").trigger("click");
		}
	})
	
</script>
	
</body>
</html>