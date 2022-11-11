<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_comment_report.model.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>Title</title>
</head>

<body>
	<h1>論壇留言檢舉新增:</h1>
	<form method="post" action="/web-admin/Forum_comment_reportServlet" id="form1">
		<div>
			<label>輸入論壇文章留言編號：</label><input type="text" name="comment_no" value="${param.comment_no}" required><br>
			<br> 
			<label>輸入論壇文章編號：</label><input type="text" name="article_no" value="${param.article_no}" required><br>
			<br>
			<label>輸入會員編號：</label><input type="text" name="member_no" value="${param.member_no}" required><br>
			<br>
			<label>輸入檢舉事由：</label><textarea name="report_reason" value="${param.report_reason}" required></textarea>
			<br>
			<br> <input type="hidden" name="action" value="insert">
		
			<button type="submit" form="form1" value="送出">送出新增</button>
		
		</div>
	</form>
</body>

</html>