<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>論壇留言首頁: Home</title>
 
<style>
  table#table-1 {
	width: 450px;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px; 
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
  #content {
	display:flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;"
  }
	.button {
 	border-radius:1rem; 
 	border: 1px solid #ccc;
  }
</style>

</head>
<body bgcolor='white'>
<%@ include file="../navbar.file" %>
<br>
<div id="content">
	<div>
		<table id="table-1">
   		<tr><td><h3>論壇留言首頁: Home</h3><h4>( Forum_comment )</h4></td></tr>
		</table>
	</div>
	<div>
		<p>This is the Home page for Forum_comment: Home</p>
	</div>
	<div>
		<h3>資料查詢</h3>
	</div>
	<div style="text-align:center">	
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font><br>
	<c:forEach var="message" items="${errorMsgs}">
			<span style="color:red">${message}</span>
	</c:forEach>
	</c:if>
	</div>
	<ul>
  		<li><button class="button"><a href='<%= request.getContextPath() %>/forum_comment/listAllForum_comment.jsp' style="text-decoration: none;color:#333;">List</a></button>&nbsp; all Forum_comment.<br><br></li>
  		<li>
   		<FORM METHOD="post" ACTION="/web/Forum_commentServlet" >
        <b>輸入論壇文章留言編號 (如:1):</b>
        <input type="text" name="comment_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出" class="button">
    	</FORM>
  		</li>

 		 <jsp:useBean id="forum_commentSvc" scope="page" class="com.forum_comment.model.Forum_commentService" />
   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="/web-admin/Forum_articleServlet" > -->
<!--        <b>選擇論壇文章編號:</b> -->
<!--        <select size="1" name="article_no"> -->
<%--          <c:forEach var="Forum_articleVO" items="${forum_articleSvc.all}" >  --%>
<%--           <option value="${forum_articleVO.article_no}">${forum_articleVO.article_no} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
  
  <li>
     <FORM METHOD="post" ACTION="/web/Forum_commentServlet" >
       <b>選擇論壇文章編號:</b>
       <select size="1" name="comment_no">
         <c:forEach var="forum_commentVO" items="${forum_commentSvc.all}" > 
          <option value="${forum_commentVO.comment_no}">${forum_commentVO.article_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出" class="button">
     </FORM>
  </li>
</ul>


<h3>論壇留言管理</h3>

<ul>
  <li><button class="button"><a href='<%= request.getContextPath() %>/forum_comment/InsertForum_comment.jsp' style="text-decoration: none;color:#333;">Add</a></button>&nbsp; a new Forum_comment.</li>
</ul>
</div>
</body>
</html>