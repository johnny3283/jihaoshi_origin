<%@page import="com.course.model.PhyCouVO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.phyCourseComment.model.*"%>
<%
phyCourseCommentVO phyCourseCommentVO = (phyCourseCommentVO) request.getAttribute("phyCourseCommentVO");
%>
<html>
<head>
<title>線上課程評論</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
<style>
#pageHead {
	width: 100%;
	height: 30%;
}
a {
	font-size: 20px;
}
table {
	width: 1050px;
	margin-top: 5px;
	margin-bottom: 5px;
}
table, th, td {
	border: 1px solid #CCCCFF;
}
th, td {
	padding: 8px;
	text-align: center;
}
</style>
</head>
<body>
	<img src="<%=request.getContextPath()%>/images/JihaoshiPageHead.jpg"
		id="pageHead">
	<div class="block_N" style="margin: 0px auto;">
		<!--搜尋欄開始-->
		<div class="Nm"
			style="display: flex; justify-content: center; align-items: center;">
			<ul class="searchfield">
				<li><input id="keyword" type="text" class="text ac_input"
					placeholder="請輸入關鍵字" autocomplete="off"></li>
				<li><input id="btn_search" type="button" class="button"
					value="找菜單"></li>
			</ul>
		</div>
		<!--搜尋欄結束-->
	</div>
	<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
		<div id="CONTENT" class="layout-wrapper">
			<div class="layout-center" style="text-align: center">
				<!--側邊欄區塊開始-->
				<dl class="block_W">
					<dd id="CategoryContainer">
						<ul class="treeview">
						<li>歡迎:<font color=blue> ${Guest} </font><font color=blue>
									${member.memberAccount} </font>您好 <br> <font>會員編號:
									${member.memberNo} </font> <br> <font>會員姓名:
									${member.memberName} </font></li>
							<li id="cate_D" class="expanded"><H1>功能列表</H1>
								<ul class="main">
									<li><a href="<%=request.getContextPath()%>/index.jsp">回首頁</a>
									</li>
								</ul>
							</li>
						</ul>
					</dd>
				</dl>
				<!--側邊欄區塊結束-->
				<div class="block_C s_list">
					<div class="Cm">
						<div id="ItemContainer" class="Cm_C">
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
							<br>
							<form method="post"
								action="<%=request.getContextPath()%>/phyCourseComment">
								<div>
									<label>實體課程評論：</label><br>
									<br>
									<input type="text" name="courseNo" placeholder="請輸入課程編號"><br>
									<textarea name="commentContent"><%=(phyCourseCommentVO==null)? "" : phyCourseCommentVO.getCommentContent()%></textarea>
									<br> <input type="hidden" name="action" value="add">
									<input type="submit" value="送出資料">
									<input type="reset" value="清除資料">
								</div>
							</form>
							<dl class="col3f" id="DRAA0A-A900BUT82">
							</dl>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		
	</script>
</body>
</html>