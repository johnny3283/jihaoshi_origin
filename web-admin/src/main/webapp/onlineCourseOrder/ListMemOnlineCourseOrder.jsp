<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>會員單筆線上課程訂單</title>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>


	<table id="table-1">
		<tr>
			<td>
				<h3>會員單筆線上課程訂單</h3>
				<h4>
					<a href="onlineCourseOrderServlet?action=orderlist">回首頁</a>
				</h4>
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
				<td>
					<form method="post" action="onlineCourseOrderServlet">
						<input type="hidden" name="action" value="searchOrderDetail">
						<input type="hidden" name="orderNo" value="${order.orderNo}">
						<input type="submit" value="查看訂單明細">
					</form>
				</td>
				<td id="tdStatus${order.orderNo}">
					<c:choose>
						<c:when test="${order.orderStatus == 0}">
							<button onclick="changeStatus(${order.orderNo})">訂單已成立</button>
						</c:when>
						<c:when test="${order.orderStatus == 1}">訂單已取消</c:when>					
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
	<script>
		function changeStatus(orderNo){
			const result = confirm(`確定取消訂單 ${orderNo} ?`);
			if (!result) { return; }
			fetch("onlineCourseOrderServlet?action=update",{
				method: "POST",
				headers: {
					"content-type": "application/json"	
				},
				body: JSON.stringify({ orderNo: orderNo })
			})
			.then(resp => resp.json())
			.then(body => {
				const { successful } = body;
			    alert(successful ? '成功' : '失敗');
			    if (successful) {
			    	const tdStatus = document.querySelector(`#tdStatus\${orderNo}`);
			    	tdStatus.textContent = '訂單已取消';
			    }
			})
		}
	</script>
</body>
</html>