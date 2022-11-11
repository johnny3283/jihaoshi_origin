<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.latest_news.model.*"%>
<!DOCTYPE html>
<html lang="en">
 
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<body>
<h1>最新消息新增:</h1>
    <form method="post" action="/web-admin/Latest_newsServlet" enctype="application/x-www-form-urlencoded" id="form1">
        <div>
<!--             <label>輸入消息編號：</label><input type="text" name="news_no"><br><br> -->
            <label>輸入消息標題：</label><input type="text" name="news_name" value="${param.news_name}" required><br><br>
<!--             <label>輸入編輯時間：</label><input type="text" name="update_date"><br><br> -->
            <label>輸入消息內文：</label><textarea name="news_content" value="${param.news_content}" required></textarea><br><br>
           
			<input type="hidden" name="action" value="insert">
<!-- 			<input type="hidden" name="news_no" value=1> -->
            <button type="submit" form="form1" value="送出">送出新增</button>
<!--             <button type="reset" form="form1">清除資料</button> -->
        </div>
    </form>
</body>

</html>