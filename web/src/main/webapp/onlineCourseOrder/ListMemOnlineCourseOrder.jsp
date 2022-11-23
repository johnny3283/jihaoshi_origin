<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<body >
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

	<table id="table-1">
		<tr>
			<td>
				<h3>會員單筆線上課程訂單</h3>
				
			</td>
		</tr>
	</table>

	<table>
		<tr>

			<th>會員編號</th>
			<th>會員名稱</th>
			<th>線上課程訂單編號</th>
			<th>訂單成立日期</th>
			<th>訂單數量</th>
			<th>訂單價格</th>
			<th>交易編號</th>
			<th>訂單詳細內容</th>
			<th>訂單狀態</th>
		</tr>
		<c:forEach var="order" items="${list}">
			<tr>
				<td>${order.memberNo}</td>
				<td>${order.memberAccount}</td>
				<td>${order.orderNo}</td>
				<td>${order.orderTime}</td>
				<td>${order.orderVolume}</td>
				<td>${order.orderPrice}</td>
				<td>${order.tradeNo}</td>
				<td>
					<form method="post" action="onlineCourseOrderServlet">
						<input type="hidden" name="action" value="searchOrderDetail">
						<input type="hidden" name="orderNo" value="${order.orderNo}">
						<input type="submit" value="查看訂單明細">
					</form>
				</td>
				<td id="tdStatus${order.orderNo}">
					<c:choose>
						<c:when test="${order.orderStatus == 0}">訂單已成立</c:when>
						<c:when test="${order.orderStatus == 1}">訂單已取消</c:when>					
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>