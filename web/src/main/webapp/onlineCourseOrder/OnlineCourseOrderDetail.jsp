<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.1/font/bootstrap-icons.css">
 	<link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
	<style>
	#pageHead {
		width: 100%;
		height: 30%;
	}
	</style>
</head>
<body>
	<img src="./images/JihaoshiPageHead.jpg" id="pageHead">
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
										href="<%=request.getContextPath()%>/meal/MealInsert.jsp">新增菜單</a>
									</li>
									<li><a href="<%=request.getContextPath()%>/index.jsp">回首頁</a>
									</li>
								</ul>
						</ul>
					</dd>
				</dl>
				<!--側邊欄區塊結束-->
				<div style="width: 85.4%; float: right; background: #FFFAF0">
					<div style="width: 63%; float: right; margin: 3%;">
						<div>
							
							
								 線上課程訂單編號:${order.orderNo}<br>
								 會員編號:${order.memberNo}<br>
								 訂單成立日期:${order.orderTime}<br>
								 訂單數量:${order.orderVolume}<br>
								 訂單價格:${order.orderPrice}<br>
							
							
						</div>
					    <div>
							
								
							   
							
							
							<c:forEach var="detail" items="${order.orderDetailList}">
								<div>
									線上課程編號:${detail.courseNo}<br>
									線上課程價格:${detail.coursePrice}<br>
								    菜單照片<img src="data:image/*;base64, ${detail.orderPhotoBaseStr64}"><br>
									
								
								</div>
							</c:forEach>
						</table>
	<script>
	</script>
</body>
</html>