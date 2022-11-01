<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>論壇文章首頁: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
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
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>論壇文章首頁: Home</h3><h4>( Forum_article )</h4></td></tr>
</table>

<p>This is the Home page for Forum_article: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%= request.getContextPath() %>/forum_article/listAllForum_article.jsp'>List</a> all Forum_article.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="/third/Forum_articleServlet" >
        <b>輸入論壇文章編號 (如:1):</b>
        <input type="text" name="article_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="forum_articleSvc" scope="page" class="com.forum_article.model.Forum_articleService" />
   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="/third/Forum_articleServlet" > -->
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
     <FORM METHOD="post" ACTION="/third/Forum_articleServlet" >
       <b>選擇文章標題:</b>
       <select size="1" name="article_no">
         <c:forEach var="forum_articleVO" items="${forum_articleSvc.all}" > 
          <option value="${forum_articleVO.article_no}">${forum_articleVO.article_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>
 

<h3>論壇文章管理</h3>

<ul>
  <li><a href='<%= request.getContextPath() %>/forum_article/InsertForum_article.jsp'>Add</a> a new Forum_article.</li>
</ul>

</body>
</html>