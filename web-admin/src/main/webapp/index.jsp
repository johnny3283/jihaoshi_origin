<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Jihaoshi</title>
<link type="text/css" href="./css/jihaoshi.css" rel="stylesheet">
<style>
#pageHead {
	width: 100%;
	height: 30%;
}

a {
	font-size: 20px;
}

#info {
	background: transparent;
	border: 0;
	font-size: 13px;
}

.drop {
	position: relative;
}

.submenu {
	position: relative;
	opacity: 0;
	width: 100%;
	z-index: 8;
	transition: opacity 0.5s ease;
}

.submenu-item {
	display: block;
	height: 0px;
	overflow: hidden;
	transition: height 0.5s ease;
}

.drop:hover .submenu {
	opacity: 1;
	border: solid 1px black;
}

.drop:hover .submenu-item {
	overflow: visible;
	height: 30px;
}
</style>
</head>
<body>

	<img src="images/JihaoshiPageHead.jpg" id="pageHead">
	<div class="block_N" style="margin: 0px auto;"></div>
	<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
		<div id="CONTENT" class="layout-wrapper">
			<div class="layout-center" style="text-align: center">
				<!--側邊欄區塊開始-->
				<dl class="block_W">
					<dd id="CategoryContainer">
						<ul class="treeview">
							<li>管理員:<font color=red> ${manager.managerName} </font><br>
								權限編號:<font> ${manager.authorityNo} </font>
							</li>
							<li><c:if test="${not empty manager}">
									<a href="./manager/ManagerServlet?action=Logout">登出</a>
								</c:if></li>
							<li><c:if test="${empty manager}">
									<a href="./manager/login.jsp">登入</a>
								</c:if></li>
							<li id="cate_D" class="expanded"><H1>功能列表</H1>
								<ul class="main">
									<li class="drop"><a href="#">員工管理</a>
										<ul class="submenu">
											<li class="submenu-item"><a
												href="./manager/addManager.jsp">新增員工</a></li>
											<li class="submenu-item"><a
												href="./manager/listAllManager.jsp">員工查詢</a></li>
											<li class="submenu-item"><a
												href="./manager/listAllManager.jsp">員工管理test</a></li>
										</ul></li>
									<li class="drop"><a href="#">會員管理</a>
										<ul class="submenu">
											<li class="submenu-item"><a
												href="./member/listAllMember.jsp">會員查詢</a></li>
											<li class="submenu-item"><a href="#">訂單查詢</a></li>
											<li class="submenu-item"><a href="#">修改會員</a></li>
											<li class="submenu-item"><a
												href="<%=request.getContextPath()%>/member/listAllMember.jsp">會員管理test</a></li>
										</ul></li>
									<li class="drop"><a href="#">網站管理</a>
										<ul class="submenu">
											<li class="submenu-item"><a href="./ztest/webManag.jsp">網站管理test</a></li>

										</ul></li>
									<li class="drop"><a href="#">菜單管理</a>
										<ul class="submenu">
											<li class="submenu-item"><a
												href="./ztest/mealsManag.jsp">菜單管理test</a></li>

										</ul></li>
									<li class="drop"><a href="#">課程管理</a>
										<ul class="submenu">
											<li class="submenu-item"><a
												href="./ztest/courseManag.jsp">課程管理test</a></li>

										</ul></li>

									<li class="drop"><a
										href="${ctxPath}/meal/MealManagerIndex.jsp">菜單管理</a></li>
									<li class="drop"><a
										href="${pageContext.request.contextPath}/onlineCourse/AddOnlineCourse.jsp">新增線上課程</a>
									</li>

									<li class="drop"><a
										href="<%=request.getContextPath()%>/faqservlet?action=getAll">FAQ列表</a>
									</li>
									<li class="drop"><a
										href="<%=request.getContextPath()%>/OnlineCourseCommentReportServlet?action=getAll">線上課程評論檢舉</a>
									<li class="drop"><a
										href="${pageContext.request.contextPath}/onlineCourse/searchAll">線上課程管理</a>
									</li>
									<li class="drop"><a
										href="${pageContext.request.contextPath}/onlineCourseOrderServlet?action=orderlist">訂單管理</a>

										<a
										href="<%=request.getContextPath()%>/OnlineCourseCommentReportServlet?action=getAll">線上課程評論檢舉</a>
									<li class="drop"><a
										href="${pageContext.request.contextPath}/onlineCourse/searchAll">線上課程管理</a>
									</li>
									<li class="drop"><a
										href="<%=request.getContextPath()%>/onlinecustomerservice/customerService.jsp">線上客服</a>
									</li>
                  <li class="drop">
                     <a href="<%=request.getContextPath()%>/latest_news/select_page.jsp">最新消息</a>
                  </li>
                  <li class="drop">
                     <a href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp">康健新知論壇</a>
                  </li>
								</ul>
						</ul>
					</dd>
				</dl>
				<!--側邊欄區塊結束-->
				<div class="block_C s_list">
					<div class="Cm">
						<div id="ItemContainer" class="Cm_C">
							<dl class="col3f" id="DRAA0A-A900BUT82">


							</dl>

						</div>
					</div>
				</div>
			</div>

		</div>
	</div>


</body>
</html>
