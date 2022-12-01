<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>論壇文章首頁</title>
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
<body bgcolor='white'>
<%@ include file="../navbar.file" %>
<br>
<div id="content">
	<div>
		<table id="table-1">
  		 	<tr><td><h3 >論壇文章首頁</h3><h4></h4></td></tr>
		</table>
	</div>
	<div>
		<h3>資料查詢:</h3>
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
<br>
<ul>
  <li>
  <button class="button">
  <a href='<%= request.getContextPath() %>/forum_article/listAllForum_article.jsp' style="text-decoration: none;color:#333;">列出</a>
  </button>
  全部文章狀態與文章檢舉<br><br></li>
  <li>
  <button class="button">
  <a href='<%= request.getContextPath() %>/forum_comment/listAllForum_comment.jsp' style="text-decoration: none;color:#333;">列出</a> 
  </button>
  全部留言狀態與留言檢舉
  <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="/web-admin/Forum_articleServlet" >
        <b>輸入論壇文章編號 (如:1):</b>
        <input type="text" name="article_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出" class="button">
    </FORM>
  </li>

  <jsp:useBean id="forum_articleSvc" scope="page" class="com.forum_article.model.Forum_articleService" />
   
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
  <br>
  <li>
     <FORM METHOD="post" ACTION="/web-admin/Forum_articleServlet" >
       <b>選擇文章標題:</b>
       <select size="1" name="article_no">
         <c:forEach var="forum_articleVO" items="${forum_articleSvc.all}" > 
          <option value="${forum_articleVO.article_no}">${forum_articleVO.article_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出" class="button">
     </FORM>
  </li>
</ul>

<br>


<!-- <ul> -->
<%--   <li><a href='<%= request.getContextPath() %>/forum_article/InsertForum_article.jsp'>新增</a> 論壇文章</li> --%>
<!-- </ul> -->
</div>
</body>
</html>