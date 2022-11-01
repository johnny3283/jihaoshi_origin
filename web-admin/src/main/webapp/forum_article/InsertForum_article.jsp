<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_article.model.*"%> 
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<body>
<h1>論壇文章新增:</h1>
    <form method="post" action="/third/Forum_articleServlet" id="form1">
        <div>
<!--            <label>輸入論壇文章編號：</label><input type="text" name="article_no"><br><br> -->
            	<label>輸入消息標題：</label><input type="text" name="article_name" value="${param.article_name}"><br><br>
            	<label>輸入會員編號：</label><input type="text" name="member_no"><br><br> 
<!--                 <label>輸入編輯時間：</label><input type="text" name="update_date"><br><br> -->
            	<label>輸入文章內容：</label><textarea name="article_content" value="${param.article_content}"></textarea><br><br>
           
			<input type="hidden" name="action" value="insert">
<!-- 			<input type="hidden" name="article_no" value=1> -->
            <button type="submit" form="form1" value="送出">送出新增</button>
<!--             <button type="reset" form="form1">清除資料</button> -->
        </div>
    </form>
</body>

</html>