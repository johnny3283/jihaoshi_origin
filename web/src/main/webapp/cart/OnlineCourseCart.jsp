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
<title>線上課程購物車</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<style type="text/css">
#content {
	display: flex; 
	justify-content: center; 
	align-items: center;
}
#cart{
	width:100%;
	display: flex; 
	flex-direction: column;
	align-items: center;
}
#all{
	width:90%;
	display: flex; 
	justify-content: Space-around; 	
	align-items: center;
	
}

</style>
</head>
<body>
<%@ include file="../navbar.file" %>
<br>
<div id="content">
	<div id="cart">		
		<c:forEach var="cartCourse" items="${cartCourses}" varStatus="loop">
		<div id="all" style="border-style:outset;">		
			<div>
				<img src="data:image/png;base64,${cartCourse.course.onlineCoursePhotoBaseStr64}" onclick="showDetail(${cartCourse.course.courseNo})">
			</div>
			<div>
				<h3 class="prod_name" onclick="showDetail(${cartCourse.course.courseNo})"> ${cartCourse.course.courseName}</h3>
				<span style="font-size: 18px">價格NT$${cartCourse.course.coursePrice}</span>
			</div>
			<div>
				<form method="post" action="cartCourseController"
					enctype="application/x-www-form-urlencoded"
					id="cartDelete${loop.index}">
					<input name="action" type="hidden" value="cartDelete"> 
					<input name="cartCourseIndex" type="hidden" value="${loop.index}">
					<button type="submit" form="cartDelete${loop.index}" style="border-radius:1rem; border: 1px solid #ccc;">移除</button>
				</form>
			</div>
		</div>
		</c:forEach>
		
	<div id="totalPrice">
		<c:choose>
			<c:when test="${totalCoursePrice==0|| empty totalCoursePrice}">
				<div style="border-style:outset;">
				<span style="font-size: 16px;">購物車中還沒有東西喔</span>
				<span style="font-size: 16px;"><a
					href="<%=request.getContextPath()%>/onlineCourse/ListAllOnlineCourse.jsp">去選購</a></span>
				</div>
			</c:when>
			<c:otherwise>
				<br><br>
				<div>
				<span style="font-size: 16px;">總價：${totalCoursePrice}元 </span>
				<span style="font-size: 16px;">共<%=cartCourses.size()%>門課程
				</span>
				<button type="submit" form="checkout" style="border-radius:1rem; border: 1px solid #ccc;">去結帳</button>
				<form method="post"
					action="${ctxPath}/checkout/checkoutCourseController" id="checkout"
					enctype="application/x-www-form-urlencoded">
					<input type="hidden" name="action" value="checkout">
				</form>
				</div>
			</c:otherwise>
		</c:choose>
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
