<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cart.model.CartProdVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.*"%>
<%@ page import="com.faq.model.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Oxygen:wght@700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${ctxPath}/css/styles.css">

<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery.smartmenus/1.1.0/jquery.smartmenus.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery.smartmenus/1.1.0/addons/bootstrap/jquery.smartmenus.bootstrap.min.js'></script>
<title>JiHaoShi</title>
<style>
#inbutton {
    position:relative;
    overflow:hidden;
    width:7rem;
    color:black;
    border:2px solid black;
    background-color:transparent;
    cursor:pointer;
    line-height:2;
    margin:0;
    padding:0;
    border-radius:1.5rem;
    font-size:1.125rem;
    outline:none;
    transition:transform .125s;
}
#outbutton {
    position:relative;
    overflow:hidden;
    width:5rem;
    color:black;
    border:2px solid black;
    background-color:transparent;
    cursor:pointer;
    line-height:2;
    margin:0;
    padding:0;
    border-radius:1.5rem;
    font-size:1.125rem;
    outline:none;
    transition:transform .125s;
}
</style>
</head>

<body>
	<!-- Navbar Start -->
	<nav class="navbar navbar-expand-lg bg-light">
		<div class="container-fluid">
			<!-- LOGO -->
			<a class="navbar-brand" href="${ctxPath}/index.jsp">
				<img src="${ctxPath}/css/LOGO.png" alt="Logo" width="80" height="90">
			</a>
			<!-- 螢幕縮小時導覽列會縮小成右上按鈕點開會直向呈現 -->
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<!-- collapse：無論任何螢幕大小，你都不會看到有選單在頂部。 -->
			<!-- nav-collapse：當螢幕變小時，選單會改為直行顯示。 -->
			<div class="collapse navbar-collapse" id="navbarSupportedContent" style="justify-content:space-around;">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">員工管理</a>
							<ul class="dropdown-menu">
								<li>
                  					<a class="dropdown-item" href="${ctxPath}/manager/addManager.jsp">新增員工</a>
               	 				</li>
                				<li>
                 		     		<a class="dropdown-item" href="${ctxPath}/manager/listAllManager.jsp">員工查詢</a>
      				            </li>
							</ul>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">會員管理</a>
							<ul class="dropdown-menu">
								<li>
                  					<a class="dropdown-item" href="${ctxPath}/member/listAllMember.jsp">會員查詢</a>
               	 				</li>
                				<li>
                  					<a class="dropdown-item" href="${ctxPath}">修改會員</a>
               					</li>
							</ul>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="" role="button" data-bs-toggle="dropdown" aria-expanded="false">網站管理</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="${ctxPath}/ztest/webManag.jsp">網站管理test</a></li>
								<li class="dropdown-submenu">
									<a class="dropdown-item">最新消息管理&emsp;></a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="<%=request.getContextPath()%>/latest_news/listAllLatest_news.jsp">最新消息列表</a></li>
										<li><a class="dropdown-item" href="<%=request.getContextPath()%>/latest_news/select_page.jsp">最新消息查詢</a></li>
										<li><a class="dropdown-item" href="<%=request.getContextPath()%>/latest_news/InsertLatest_news.jsp">最新消息新增</a></li>
										<li><a class="dropdown-item" href="<%=request.getContextPath()%>/latest_news/InsertLatest_news.jsp">最新消息修改</a></li>
									</ul>
								</li>
								<li><a class="dropdown-item">康健新知論壇管理&emsp;></a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp">康健新知論壇列表</a></li>
										<li><a class="dropdown-item" href="<%= request.getContextPath() %>/forum_article/listAllForum_article.jsp">康健新知論壇文章列表</a></li>
										<li><a class="dropdown-item" href="<%= request.getContextPath() %>/forum_comment/listAllForum_comment.jsp">康健新知論壇留言列表</a></li>
									</ul>
								</li>
                				<li><a class="dropdown-item">FAQ管理&emsp;></a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="<%=request.getContextPath()%>/faqservlet?action=getAll">FAQ列表</a></li>
										<li><a class="dropdown-item" href="<%=request.getContextPath()%>/faq/addFAQ.jsp">新增FAQ</a></li>
									</ul>
								</li>
								<li>
                  					<a class="dropdown-item" href="<%=request.getContextPath()%>/onlinecustomerservice/customerService.jsp">線上客服</a>
                				</li>
							</ul>
					</li>										
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">菜單管理</a>
							<ul class="dropdown-menu">
								<li>
                  					<a class="dropdown-item" href="${ctxPath}/meal/mealController?action=listAll">菜單商品列表</a>
               	 				</li>
								<li>
                  					<a class="dropdown-item" href="${ctxPath}/meal/MealInsert.jsp">新增菜單商品</a>
               	 				</li>
               	 				<li>
                  					<a class="dropdown-item" href="${ctxPath}/nutrient/insert">新增營養特色<a>
               	 				</li>
							</ul>
					</li>						
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">實體課程管理</a>
							<ul class="dropdown-menu">
								<li>
                  					<a class="dropdown-item" href="${ctxPath}/course/addCou.jsp">新增實體課程</a>
               	 				</li>
								<li>
                  					<a class="dropdown-item" href="${ctxPath}/course/ListAllCourse.jsp">查詢修改實體課程</a>
               	 				</li>
								<li>
                  					<a class="dropdown-item" href="${ctxPath}/phyCouPromotion/addPro.jsp">新增實體課程促銷專案</a>
               	 				</li>
								<li>
                  					<a class="dropdown-item" href="${ctxPath}/phyCouPromotion/select_page.jsp">查詢修改實體課程促銷專案</a>
               	 				</li>
								<li>
                  					<a class="dropdown-item" href="${ctxPath}/phyCouSignUpMgn/listAllSignUpCou.jsp">實體課程報名管理</a>
               	 				</li>
							</ul>
					</li>								
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">線上課程管理</a>
							<ul class="dropdown-menu">
								<li>
                  					<a class="dropdown-item" href="${pageContext.request.contextPath}/onlineCourse/AddOnlineCourse.jsp">新增線上課程</a>
               	 				</li>
								<li>
                  					<a class="dropdown-item" href="${pageContext.request.contextPath}/onlineCourse/searchAll">線上課程管理</a>
               	 				</li>
               	 					<li>
                  					<a class="dropdown-item" href="${pageContext.request.contextPath}/onlineCourseOrderServlet?action=orderlist">線上課程訂單管理</a>
               	 				</li>
               	 				<li>
                  					<a class="dropdown-item" href="<%=request.getContextPath()%>/OnlineCourseCommentReportServlet?action=getAll">線上課程評論檢舉</a>
               	 				</li>
							</ul>					
					</li>
					&emsp;
					<c:if test="${not empty manager}">
					<li class="nav-item">
						&emsp;
						管理員 : &emsp; ${manager.managerName}
						&emsp;
						權限編號 : &emsp; ${manager.authorityNo}
						&emsp; 
						<button id="outbutton">
						<a href="./manager/ManagerServlet?action=Logout" style="display:inline-block; text-decoration:none;color:black;">登出</a>
						</button>
					</li>
					</c:if>

				</ul>
			</div>
		</div>
	</nav>
	<!-- Navbar End -->
	<!-- Feature Section Start-->
	<div class="mx-auto" id="feature">
		<img class="mx-auto d-block" id="feature-img" src="./css/feature.jpg" alt="">
		<div id="feature-text" style="">
			<p class="name">JiHaoShi<br>後台首頁</p>
		</div>
	</div>
	<!-- Feature Section End-->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
