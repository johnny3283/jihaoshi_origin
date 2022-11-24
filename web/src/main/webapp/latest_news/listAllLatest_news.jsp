<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.latest_news.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
 
 
<%
Latest_newsService latest_newsSvc = new Latest_newsService();
List<Latest_newsVO> list = latest_newsSvc.getAll();
Latest_newsVO vo = null;
for(int i = 0; i < list.size(); i++) {
	vo = list.get(i);
	if(vo.getNews_pic() != null)
		vo.setShowPhoto("data:image/png;base64,"+Base64.getEncoder().encodeToString(vo.getNews_pic()));
}


pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有最新消息資料</title>
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
	background-color:  #F0E68C;
	border: 2px solid black;
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
		<tr>
			<td>
				<h3>所有最新消息資料</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/latest_news/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>消息編號</th>
			<th>消息標題</th>
			<th>編輯時間</th>
			<th>消息內文</th>
			<th>消息圖片</th>

		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="latest_newsVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${latest_newsVO.news_no}</td>
				<td>${latest_newsVO.news_name}</td>
				<td>${latest_newsVO.update_date}</td>
				<td>${latest_newsVO.news_content}</td>
				<td><img src="${latest_newsVO.showPhoto}"></td>



			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>