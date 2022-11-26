<%@ page import="com.cart.model.CartProdVO"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Jihaoshi</title>
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

table {
	font-size: 20px;
	width: 400px;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: auto;
	margin-right: auto;
}

table, th, td {
	border: 2px solid lightgray;
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
	<div class="block_N" style="margin: 0px auto;"></div>
	<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
		<div id="CONTENT" class="layout-wrapper">
			<div class="layout-center" style="text-align: center">
				<!--側邊欄區塊開始-->
				<dl class="block_W">
					<dd id="CategoryContainer">
						<ul class="treeview">
							<li><c:if test="${not empty errorMsgs}">
									<font style="color: red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if></li>
							<li>歡迎:<font color=blue> ${Guest} </font><font color=blue>
									${member.memberAccount} </font>您好 <br> <font>會員編號:
									${member.memberNo} </font> <br> <font>會員姓名:
									${member.memberName} </font></li>
							<li><c:if test="${not empty member}">
									<a href="<%=request.getContextPath()%>/member/MemberServlet?action=Logout">登出</a>
								</c:if></li>
							<li><c:if test="${empty member}">
									<a href="./member/login.jsp">登入</a>
								</c:if></li>
							<li id="cate_D" class="expanded"><H1>功能列表</H1>
								<ul class="main">
									<li class="drop"><a href="#">會員專區</a>
										<ul class="submenu">
											<li class="submenu-item"><button id="info">個人資料</button></li>
											<li class="submenu-item"><a href="#">訂單查詢</a></li>
											<li class="submenu-item"><a
												href="phyCourComment/addPhyComment.jsp">新增實體課程評價</a></li>
											<li class="submenu-item"><a

												href="<%=request.getContextPath()%>/phyCourseComment?action=getMember_For_Display">我的實體課程評價</a></li>
                        <li class="submenu-item"><a
												href="<%=request.getContextPath()%>/MemberOnlineCourseCommentServlet?action=getMember_For_Display">我的線上課程評價</a></li>
										</ul></li>
                    
									<li class="drop"><a href="#">網站簡介</a></li>

                <li class="drop">
                  <a href="${pageContext.request.contextPath}/latest_news/select_page.jsp">最新消息</a>         
                </li>
                <li class="drop">
                  <a href="${ctxPath}/meal/MealProductIndex.jsp">好食產品專區</a>
                </li>
                <li class="drop">
                  <a href="${ctxPath}/cart/MealCart.jsp">菜單商品購物車<c:if test="${not empty cartProds}"> (${fn:length(cartProds)})</c:if></a>
                </li>
                <li class="drop">
                  <a href="${pageContext.request.contextPath}/onlineCourse/ListAllOnlineCourse.jsp">線上課程瀏覽專區</a>
                </li>
                <li class="drop">
                  <a href="${pageContext.request.contextPath}/onlineCourse/ManageCourse.jsp">會員線上課程管理</a>
                </li>
                 <li class="drop">
                  <a href="${pageContext.request.contextPath}/onlineCourseOrderServlet?action=orderlist">會員線上課程訂單管理</a>
                </li>
                <li class="drop">
                	<a href="${ctxPath}/cart/OnlineCourseCart.jsp">線上課程購物車<c:if test="${not empty cartCourses}"> (${fn:length(cartCourses)})</c:if></a>
				</li>             


                <li class="drop">
                	<c:if test="${not empty member}">
                		<a href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp">康健新知論壇</a>
                	</c:if>
                	<c:if test="${empty member}">
                		<a href="<%=request.getContextPath()%>/forum_article/listAllForum_articlevistor.jsp">康健新知論壇</a>
                	</c:if>
                  
                </li>

                <li class="drop">
                  <a href="<%=request.getContextPath()%>/faqservlet?action=getAll">FAQ及客服專區</a>
                </li>
              </ul>
          </ul>
        </dd>
      </dl>


				<!--側邊欄區塊結束-->
				<div class="block_C s_list" id="cardWrapper">
					<div class="Cm">
						<div id="ItemContainer" class="Cm_C"></div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="js/member.js"></script>
	<script type="text/javascript"></script>
</body>
</html>
