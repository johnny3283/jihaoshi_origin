<%@ page import="com.forum_article.model.Forum_articleVO"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.forum_article.model.*"%>

<%
Forum_articleVO forum_articleVO = (Forum_articleVO) request.getAttribute("forum_articleVO"); //Forum_articleServlet.java(Concroller), 存入req的latest_newsVO物件
%>

<html>
<head>
<title>論壇文章資料</title>
<style>    
        div.divflex{
        display:flex;
        width:100%;
        margin:0;
        height:100vh-30%;
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
table {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: 0;
  padding: 0;
  width: 95%;
  table-layout: fixed;
}

table caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

table tr {
  background-color: #f8f8f8;
  border: 1px solid #ddd;
  padding: .35em;
}

table th,
table td {
  padding: .625em;
  text-align: center;
}

table th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
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
#head {
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
<div id="head">
<table id="table-1">
	<tr><td>
		 <h3>論壇文章資料</h3>
		 <button class="button">
			<a href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp" style="text-decoration: none;color:#333;">回最新消息查詢</a>
		</button>
	</td></tr>
</table>
</div>
<br>
<div id="content">
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
</div>
</body>
</html>