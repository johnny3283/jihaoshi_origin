<%@ page import="com.online_course_order.model.OnlineCourseOrderVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.online_course.model.*"%>
<%@ page import="com.online_course_order.model.*"%>
<!DOCTYPE html>
<html>
<head>
	<title>所有訂單資料</title>
	<link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
	<style>
		#pageHead {
			width: 100%;
			height: 30%;
		}
	</style>
</head>
<body bgcolor='white' style="position:relative">
	<header>
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
				</div>
			</div>
		</div>
	</header>
	<section style="position:absolute;top:75%;left:30%;">
	<table>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<ul>
			<li style="margin-bottom: 20px; padding-left: 100px;">
				<b>輸入會員編號 (如1):</b>
				<input type="text" id="memberNo">
				<button onclick="searchByMemberId()">送出</button>
			</li>
	</table>
	<table id="tableResult" style="display:none">
		<thead>
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
		</thead>
		<tbody id="searchResult"></tbody>
	</table>
	</section>
	<script>
		function changeStatus(orderNo){
			const result = confirm(`確定取消訂單 \${orderNo} ?`);
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
		
		const tableResult = document.querySelector('#tableResult');
		const memberNo = document.querySelector('#memberNo');
		const searchResult = document.querySelector('#searchResult');
		function searchByMemberId() {
			fetch(`onlineCourseOrderServlet?action=getOneMemNo_For_Display&memberNo=\${memberNo.value}`)
				.then(resp => resp.json())
				.then(list => {
					searchResult.innerHTML = '';
					if (list) {
						tableResult.style.display = 'block';
						for (let order of list) {
							searchResult.innerHTML += `
								<tr>
									<td>\${order.memberNo}</td>
									<td>\${order.memberAccount}</td>
									<td>\${order.orderNo}</td>
									<td>\${order.orderTime}</td>
									<td>\${order.orderVolume}</td>
									<td>\${order.orderPrice}</td>
									<td>
										<form method="post" action="onlineCourseOrderServlet">
											<input type="hidden" name="action" value="searchOrderDetail">
											<input type="hidden" name="orderNo" value="\${order.orderNo}">
											<input type="submit" value="查看訂單明細">
										</form>
									</td>
									<td id="tdStatus\${order.orderNo}">
										\${order.orderStatus == 0 ? '<button onclick="changeStatus(' + order.orderNo + ')">訂單已成立</button>' : ''}
										\${order.orderStatus == 1 ? '訂單已取消' : ''}
									</td>
								</tr>
							`;
						}
					}
				});
		}
	</script>
</body>
</html>