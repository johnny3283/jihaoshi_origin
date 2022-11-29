<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_article.model.*"%>
<%@ page import="com.mem.model.MemberVO"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta>
<title>論壇文章新增</title>

<link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
    <style>
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
<%@ include file="../navbar.file" %>
<div id="WRAPPER" class="">
 <div style="display:flex;flex-direction:column;width:100%">
	<h1 style="align-self:center;margin: 2rem 0">論壇文章新增</h1>
	
	<form method="post" action="/web/Forum_articleServlet" id="form1">
		<div style="text-align: center;">
			
			<label>輸入文章標題：</label><input type="text" name="article_name" value="${param.article_name}" required><br>
			<br><br>
			
			<label>輸入文章內容：</label><textarea name="article_content" value="${param.article_content}" class="editor"></textarea>
			<br>
			<br> <input type="hidden" name="action" value="insert">
			
			<button type="submit" form="form1" value="送出" style="border-radius:1rem; border: 1px solid #ccc;">送出新增</button>
			
		</div>
	</form>
	
	<script src="<%=request.getContextPath() %>/forum_article/ckeditor5/build/ckeditor.js"></script>
 <script>
 ClassicEditor
    .create(document.querySelector('.editor'), {
        cloudServices: {
            tokenUrl: 'https://93940.cke-cs.com/token/dev/91a66de3b51363daded4bc4c728bc0c521dfcfff6dc0a75d8f54767efd03?limit=10',
            uploadUrl: 'https://93940.cke-cs.com/easyimage/upload/'
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