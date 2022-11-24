<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_article.model.*"%>
<%@ page import="com.mem.model.MemberVO"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>論壇文章新增</title>

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
                                    	<a href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp">回論壇文章首頁</a>
                                </li>
                            </ul>
                      </li>
                 </ul>
          </div>   
            <!--側邊欄區塊結束-->
 <div style="display:flex;flex-direction:column;width:100%">
	<h1 style="align-self:center;margin: 2rem 0">論壇文章新增</h1>
	
	<form method="post" action="/web/Forum_articleServlet" id="form1">
		<div style="text-align: center;">
			
			<label>輸入文章標題：</label><input type="text" name="article_name" value="${param.article_name}" required><br>
			<br><br>
			
			<label>輸入文章內容：</label><textarea name="article_content" value="${param.article_content}" class="editor"></textarea>
			<br>
			<br> <input type="hidden" name="action" value="insert">
			
			<button type="submit" form="form1" value="送出">送出新增</button>
			
		</div>
	</form>
	
	<script src="<%=request.getContextPath() %>/forum_article/ckeditor5/build/ckeditor.js"></script>
 <script>
 ClassicEditor
    .create(document.querySelector('.editor'), {
        cloudServices: {
            tokenUrl: 'https://93322.cke-cs.com/token/dev/f61fb72c46962eeef89212e54452b4a95a6198649d6d844997f8a0be111e?limit=10',
            uploadUrl: 'https://93322.cke-cs.com/easyimage/upload/'
        },
        toolbar: {
            items: [
                'heading', '|', 'bold', 'italic', 'link', 'bulletedList', 'numberedList',
                '|', 'alignment', 'outdent', 'indent', '|', 'fontColor',
                '|', 'imageUpload', 'blockQuote', 'insertTable', 'mediaEmbed',
                'undo', 'redo'
            ]
        }
    })
    .then(editor => {
        console.log(editor);
    });
 </script>
</body>

</html>