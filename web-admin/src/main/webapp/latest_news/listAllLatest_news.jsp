<%@ page pageEncoding="UTF-8"%>
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
<style>
        div.divflex{
        display:flex;
        width:100%;
        margin:0;
        height:100vh-30%;
        }
        div.formdiv{
        width:80%;
        background: #FFFAF0;
        }
    </style>

<style>
table#table-1 {
 	width: 95%;
	border: 2px solid black;
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
		<tr>
			<td>
				<h3>所有最新消息資料</h3>
				<button class="button">
					<a href="<%=request.getContextPath()%>/latest_news/select_page.jsp" style="text-decoration: none;color:#333;">回最新消息查詢</a>
				</button>
			</td>
		</tr>
	</table>
</div>
<div id="content">
	<table>
		<tr>
			<th>消息編號</th>
			<th>消息標題</th>
			<th>編輯時間</th>
			<th>消息內文</th>
			<th>消息圖片</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<div style="margin: 0px auto;">
		<%@ include file="page1.file"%>
		</div>
		<c:forEach var="latest_newsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${latest_newsVO.news_no}</td>
				<td>${latest_newsVO.news_name}</td>
				<td>${latest_newsVO.update_date}</td>
				<td>${latest_newsVO.news_content}</td>
				<td><img src="${latest_newsVO.showPhoto}"></td>
				<td>
					<FORM METHOD="post" ACTION="/web-admin/Latest_newsServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="news_no" value="${latest_newsVO.news_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="/web-admin/Latest_newsServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="news_no" value="${latest_newsVO.news_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
</div>	

</body>
</html>