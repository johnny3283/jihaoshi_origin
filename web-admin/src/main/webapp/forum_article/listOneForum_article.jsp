<%@ page import="com.forum_article.model.Forum_articleVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.forum_article.model.*"%>

<%
Forum_articleVO forum_articleVO = (Forum_articleVO) request.getAttribute("forum_articleVO"); //Forum_articleServlet.java(Concroller), 存入req的latest_newsVO物件
%>

<html>
<head>
<title>論壇文章資料</title>
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


<style>
  table#table-1 {
	background-color: #F0E68C;
    border: 2px solid f4f5e3;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 1280px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'> 
<img src="<%=request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">



<table id="table-1">
	<tr><td>
		 <h3>論壇文章資料</h3>
		 <h4><a href="<%= request.getContextPath() %>/forum_article/forum_article_select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>論壇文章編號</th>
		<th>文章標題</th>
		<th>會員編號</th>
		<th>編輯時間</th>
		<th>文章內容</th>
		
	</tr>
	<tr>
		<td><%=forum_articleVO.getArticle_no()%></td>
		<td><%=forum_articleVO.getArticle_name()%></td>
		<td><%=forum_articleVO.getMember_no()%></td>
		<td><%=forum_articleVO.getArticle_time()%></td>
		<td><%=forum_articleVO.getArticle_content()%></td>
		
	</tr>
</table>

</body>
</html>