<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>論壇文章檢舉首頁: Home</title>

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
   <tr><td><h3>論壇文章檢舉首頁: Home</h3><h4>( Forum_article_report )</h4></td></tr>
</table>

<p>This is the Home page for Forum_article_report: Home</p>

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
  <li><a href='<%= request.getContextPath() %>/forum_article_report/listAllForum_article_report.jsp'>List</a> all Forum_article_report.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="/web-admin/Forum_article_reportServlet" >
        <b>輸入論壇文章檢舉編號 (如:1):</b>
        <input type="text" name="article_report_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="forum_article_reportSvc" scope="page" class="com.forum_article_report.model.Forum_article_reportService" />
   
  
  <li>
     <FORM METHOD="post" ACTION="/web-admin/Forum_article_reportServlet" >
       <b>選擇論壇文章編號:</b>
       <select size="1" name="article_report_no">
         <c:forEach var="forum_article_reportVO" items="${forum_article_reportSvc.all}" > 
          <option value="${forum_article_reportVO.article_report_no}">${forum_article_reportVO.article_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>論壇文章管理</h3>

<ul>
  <li><a href='<%= request.getContextPath() %>/forum_article_report/InsertForum_article_report.jsp'>Add</a> a new Forum_article_report.</li>
</ul>

</body>
</html>