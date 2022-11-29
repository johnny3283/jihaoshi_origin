<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>論壇留言檢舉首頁: Home</title>
 
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
   		<tr><td><h3>論壇留言檢舉首頁: Home</h3><h4>( Forum_comment_report )</h4></td></tr>
		</table>
	</div>
	<div>
		<p>This is the Home page for Forum_comment_report: Home</p>
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
	<ul>
 		 <li><button class="button"><a href='<%= request.getContextPath() %>/forum_comment_report/listAllForum_comment_report.jsp' style="text-decoration: none;color:#333;">List</a></button>&nbsp; all Forum_comment_report.  <br><br></li>
  		 <li>
    	<FORM METHOD="post" ACTION="/web-admin/Forum_comment_reportServlet" >
        <b>輸入論壇文章檢舉編號 (如:1):</b>
        <input type="text" name="comment_report_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出" class="button">
    	</FORM>
  		</li>

  <jsp:useBean id="forum_comment_reportSvc" scope="page" class="com.forum_comment_report.model.Forum_comment_reportService" />
    
</ul>


<h3>論壇留言檢舉管理</h3>

<ul>
  <li><button class="button"><a href='<%= request.getContextPath() %>/forum_comment_report/InsertForum_comment_report.jsp' style="text-decoration: none;color:#333;">Add</a></button>&nbsp; a new Forum_comment_report.</li>
</ul>
</div>
</body>
</html>