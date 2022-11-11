<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_comment.model.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>Title</title>
</head>

<body>
	<h1>論壇留言新增:</h1>
	<form method="post" action="/web/Forum_commentServlet" id="form1">
		<div>
		
			<label>論壇文章編號：</label><input type="text" name="article_no" value="${param.article_no}" ><br>
			<br> 
			<label>會員編號：</label><input type="text" name="member_no" value="${param.member_no}" ><br>
			<br>
			
			<label>輸入留言內容：</label><textarea name="comment_content" value="${param.comment_content}" required></textarea>
			<br>
			<br> <input type="hidden" name="action" value="insert">
			<!-- 			<input type="hidden" name="comment_no" value=1> -->
			<button type="submit" form="form1" value="送出">送出新增</button>
			<!--             <button type="reset" form="form1">清除資料</button> -->
		</div>
	</form>
</body>

</html>