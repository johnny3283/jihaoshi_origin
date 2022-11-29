<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_article_report.model.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>論壇文章檢舉</title>
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
        .button {
 			border-radius:1rem; 
 			border: 1px solid #ccc;
		}
		#content {
			display:flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;"
		}
    </style>

</head>
 
<body bgcolor='white'>
<%@ include file="../navbar.file" %>
<br>
<div id="content">
	<div>   
	<h1 style="align-self:center;margin: 2rem 0">論壇文章檢舉</h1>
	</div>
	<div>
	<button class="button">
	   <a href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp" style="text-decoration: none;color:#333;">回康健新知論壇首頁</a>
	</button>
	</div>
	<br>	
	<form method="post" action="/web/Forum_article_reportServlet" id="form1">
	<div style="display:flex;flex-direction: column;justify-content: center;align-items: center;">		
		<!--  <label>輸入論壇文章編號：</label>  -->
		<input type="hidden" name="article_no" value="${param.article_no}" readonly><br>
		<!--  <label>輸入會員編號：</label> -->
		<input type="hidden"" name="member_no" value="${param.member_no}" readonly>
		<input type="hidden" name="action" value="insert">
		<div style="text-align:center">
			<label>輸入檢舉事由</label>
		</div>
		<br>
		<div>
			<textarea name="report_reason" value="${param.report_reason}" required></textarea>
		</div>
		<br>
		<div style="margin: 0px auto;">			
		<button type="submit" form="form1" value="送出" class="button">送出新增</button>	
		</div>
	</div>
	</form>	
</div>
</body>

</html>