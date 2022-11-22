<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_comment.model.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>論壇留言新增</title>
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

</head>

<body bgcolor='white'> 
<img src="<%=request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">

<div id="WRAPPER" class="">
    <div class="divflex">
        <div class="" style="text-align:center;background-color:#FFFAF0;widtH:13%; height:100vw; background-color:#F3E3C3;">
            <!--側邊欄區塊開始-->
                    <ul class="treeview">
                        <li id="cate_D" class="expanded"><H1>功能列表</H1>
                            <ul class="main">
                                <li>
                                    <a href="<%=request.getContextPath()%>/index.jsp">回即好食首頁</a>
                                </li>
                            </ul>
                      </li>
                 </ul>
          </div>   
            <!--側邊欄區塊結束-->

	<div style="display:flex;flex-direction:column;width:100%">

	<h1 style="align-self:center;margin: 2rem 0">論壇留言新增:</h1>
	<br>
	<form method="post" action="/web/Forum_commentServlet" id="form1">
		
		
		<div style="text-align: center;">
		
			<label>論壇文章編號：</label><input type="text" name="article_no" value="${param.article_no}" readonly><br>
			<br> 
			<label>會員編號：</label><input type="text" name="member_no" value="${param.member_no}" readonly><br>
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