<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>
<%
List<CartCourseVO> cartCourses = (ArrayList<CartCourseVO>) session.getAttribute("cartCourses");
%>
<html>
<head>
<title>購物車</title>
<link type="text/css" href="${ctxPath}/css/jihaoshi.css"
	rel="stylesheet">
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<img src="../images/JihaoshiPageHead.jpg" id="pageHead">
	<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
		<div id="CONTENT" class="layout-wrapper">
			<div class="layout-center" style="text-align: center">
				<!--側邊欄區塊開始-->
				<dl class="block_W">
					<dd id="CategoryContainer">
						<ul class="treeview">
							<li id="cate_D" class="expanded"><H1>功能列表</H1>
								<ul class="main">
									<li><a
										href="${ctxPath}/onlineCourse/ListAllOnlineCourse.jsp">線上課程瀏覽專區</a>
									</li>
									<li><a
										href="${ctxPath}/order/orderController?action=orderList">訂單管理</a>
									</li>
									<li><a href="${ctxPath}/index.jsp">回首頁</a></li>
								</ul>
						</ul>
					</dd>
				</dl>
				<!--側邊欄區塊結束-->
				<div class="block_C s_list">
					<div class="Cm">
						<div id="ItemContainer" class="Cm_C">
							<c:forEach var="cartCourse" items="${cartCourses}" varStatus="loop">
								<dl class="col3f" id="DRAA0A-A900BUT82">
									<dd class="c1f">
											<img src="data:image/png;base64,${course.onlineCoursePhotoBaseStr64}" onclick="showDetail(${cartCourse.course.courseNo})">
									</dd>
									<dd class="c2f">
										<ul class="tag_box s_label"></ul>
										<h3 class="prod_name" onclick="showDetail(${cartCourse.course.courseNo})">
											${cartCourse.course.courseName}
										</h3>
									</dd>
									<dd class="c3f" id="button_DRAA0A-A900BUT82">
										<ul class="price_box">
											<li>
												<span style="font-size: 18px">價格NT$${cartCourse.course.coursePrice}</span>
											</li>
										</ul>

										<br>
										<form method="post" action="cartCourseController" enctype="application/x-www-form-urlencoded"
										 id="cartDelete${loop.index}">
											<input name="action" type="hidden" value="cartDelete" >
											<input name="cartCourseIndex" type="hidden" value="${loop.index}">
										</form>
										<button type="submit" form="cartDelete${loop.index}">移除</button>
										<br>
										<br>
									</dd>
								</dl>
							</c:forEach>
							<div id="totalPrice">
								<c:choose>
									<c:when test="${totalCoursePrice==0|| empty totalCoursePrice}">
										<span style="font-size: 16px;">購物車中還沒有東西喔</span>
										<span style="font-size: 16px;"><a href="<%=request.getContextPath()%>/onlineCourse/ListAllOnlineCourse.jsp">去選購</a></span>
										<br>
									</c:when>
									<c:otherwise>
										<span style="font-size: 16px;">總價：${totalCoursePrice}元 </span>
										<span style="font-size: 16px;">共<%=cartCourses.size()%>門課程 </span>
										<button type="submit" form="checkout">去結帳</button>
										<form method="post" action="/web/checkout/checkoutCourseController"
											id="checkout" enctype="application/x-www-form-urlencoded">
											<input type="hidden" name="action" value="checkout">
										</form>
									</c:otherwise>
								</c:choose>

							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<script>
	function showDetail(courseNo) {
		sessionStorage.setItem('courseNo', courseNo);
		location = '../onlineCourse/OnlineCourseDetail.html';
	}
	</script>
</body>
</html>
