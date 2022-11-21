<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_comment_report.model.*"%>
<!DOCTYPE html>
<html lang="en">

<head> 
<meta charset="UTF-8">
<title>Title</title>
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
	<h1  style="align-self:center;margin: 2rem 0">論壇留言檢舉新增:</h1>
	<br>
	<form method="post" action="/web/Forum_comment_reportServlet" id="form1">
		<div style="text-align: center;">
<!-- 			<label>輸入論壇文章留言編號：</label> -->
			<input type="hidden" name="comment_no" value="${param.comment_no}" required>
<!-- 			<label>輸入論壇文章編號：</label> -->
			<input type="hidden" name="article_no" value="${param.article_no}" required>
			
<!-- 			<label>輸入會員編號：</label> -->

			<input type="hidden" name="member_no" value="${param.member_no}" required><br>
			<br>
			<label>輸入檢舉事由</label><br>
			<textarea name="report_reason" value="${param.report_reason}" required></textarea>
			<br>
			<br> <input type="hidden" name="action" value="insert">
		
			<button type="submit" form="form1" value="送出">送出新增</button>
		
		</div>
	</form>
</body>

</html>