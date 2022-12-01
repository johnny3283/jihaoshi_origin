<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>最新消息首頁: Home</title>
<style>
div.divflex {
	display: flex;
	width: 100%;
	margin: 0;
	height: 100vh-30%;
}

div.formdiv {
	style ="width: 80%%;
	background: #FFFAF0;
}
table#table-1 {
	width: 450px;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px; 
    text-align: center;
  }
table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

#content {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	"
}

.button {
	border-radius: 1rem;
	border: 1px solid #ccc;
}
</style>

</head>
<body bgcolor='white'>
	<%@ include file="../navbar.file"%>
	<br>
	<div id="content">
		<div>
			<table id="table-1">
				<tr>
					<td><h3>最新消息首頁</h3></td>
				</tr>
			</table>
		</div>
		<div>
			<h3>資料查詢:</h3>
		</div>
		<div style="text-align: center">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<br>
				<c:forEach var="message" items="${errorMsgs}">
					<span style="color: red">${message}</span>
				</c:forEach>
			</c:if>
		</div>
		<ul>
			<li>
			<button class="button">
			<a href='<%=request.getContextPath()%>/latest_news/listAllLatest_news.jsp' style="text-decoration: none;color:#333;">列出</a>
			</button>
			全部最新消息! 
			<br><br>
			</li>


			<!--   <li> -->
			<!--     <FORM METHOD="post" ACTION="/web-admin/Latest_newsServlet" > -->
			<!--         <b>輸入消息編號 (如:1):</b> -->
			<!--         <input type="text" name="news_no"> -->
			<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
			<!--         <input type="submit" value="送出"> -->
			<!--     </FORM> -->
			<!--   </li> -->

			<jsp:useBean id="latest_newsSvc" scope="page"
				class="com.latest_news.model.Latest_newsService" />

			<li>
				<FORM METHOD="post" ACTION="/web-admin/Latest_newsServlet">
					<b>選擇消息編號:</b> <select size="1" name="news_no">
						<c:forEach var="latest_newsVO" items="${latest_newsSvc.all}">
							<option value="${latest_newsVO.news_no}">${latest_newsVO.news_no}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出" class="button">
				</FORM>
			</li>

			<li>
				<FORM METHOD="post" ACTION="/web-admin/Latest_newsServlet">
					<b>選擇消息內文:</b> <select size="1" name="news_no">
						<c:forEach var="latest_newsVO" items="${latest_newsSvc.all}">
							<option value="${latest_newsVO.news_no}">${latest_newsVO.news_name}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出" class="button">
				</FORM>
			</li>
		</ul>
		<br>

		<h3>最新消息管理</h3>

		<ul>
			<li>
			<button class="button">
			<a href='<%=request.getContextPath()%>/latest_news/InsertLatest_news.jsp' style="text-decoration: none;color:#333;">新增</a>
			</button>
			最新消息.
			</li>
		</ul>

	</div>
</body>
</html>