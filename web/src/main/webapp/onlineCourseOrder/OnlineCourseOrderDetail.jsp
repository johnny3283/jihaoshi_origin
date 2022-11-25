<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	 <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oxygen:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/styles.css">
    <style>
    .orderDetail{
    	display:flex;
    	justify-content:space-around;
    	width:80%;
    	padding:6px;
    	margin:0 10%;
    	box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
        border-radius:2px;
    }
    .orderPhoto{
   		display:flex;
    	justify-content:space-around;
    	width:80%;
    	padding:6px;
    	margin:50px 10%;
    	box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
        border-radius:2px;
        align-items:center
    }
    
    </style>
	
</head>
<body>
	 <!-- Navbar Start -->
    <nav class="navbar navbar-expand-lg bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#"> <img src="./css/LOGO(去背).png" alt="Logo" width="90" height="90">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                            href="${pageContext.request.contextPath}/latest_news/select_page.jsp">最新消息</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="${ctxPath}/meal/MealProductIndex.jsp" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">好食產品專區</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="${ctxPath}/cart/MealCart.jsp">菜單商品購物車<c:if
                                        test="${not empty cartProds}"> (${fn:length(cartProds)})</c:if></a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle"
                            href="${pageContext.request.contextPath}/onlineCourse/ListAllOnlineCourse.jsp" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">線上課程專區</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="${ctxPath}/cart/OnlineCourseCart.jsp">線上課程購物車<c:if
                                        test="${not empty cartCourses}"> (${fn:length(cartCourses)})</c:if></a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">實體課程專區</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href=""></a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                            href="<%=request.getContextPath()%>/forum_article/forum_article_select_page.jsp">康健新知論壇</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle"
                            href="<%=request.getContextPath()%>/faqservlet?action=getAll" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">FAQ及客服專區</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="">FAQ</a></li>
                            <li><a class="dropdown-item" href="">線上客服</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">會員專區</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="">資料管理</a></li>
                            <li><a class="dropdown-item" href="">訂單管理</a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="">商品訂單</a></li>
                                    <li><a class="dropdown-item" href="">線上課程訂單</a></li>
                                </ul>
                            </li>
                            <li><a class="dropdown-item" href="">我的課程</a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="">線上課程</a></li>
                                    <li><a class="dropdown-item" href="">實體課程</a></li>
                                </ul>
                            </li>
                            <li><a class="dropdown-item" href="">收藏清單</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="./member/login.jsp">登入 | 註冊</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- Navbar End -->
		
		
			
						<div class="orderDetail">
							<div>線上課程訂單編號:${order.orderNo}</div>
							<div>會員編號:${order.memberNo}</div>
							<div>訂單成立日期:${order.orderTime}</div>
							<div>訂單數量:${order.orderVolume}</div>
							<div>訂單價格:${order.orderPrice}</div>	 
						</div>
					    <div>
							<c:forEach var="detail" items="${order.orderDetailList}">
								<div class="orderPhoto">
									<div>
										<div>線上課程編號:${detail.courseNo}</div>
										<div>線上課程價格:${detail.coursePrice}</div>
									</div>
								    <div>
								    	<img src="data:image/*;base64, ${detail.orderPhotoBaseStr64}">
								    </div>
								</div>
							</c:forEach>
						</div>
</body>
</html>