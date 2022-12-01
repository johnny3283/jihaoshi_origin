<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
#search {
	border-style: double;
	border-color: #ecb714;
	border-radius: 10px;
	width: 300px;
	height: 50px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.searchfield {
	margin: 0px auto;
}

.button {
	border-radius: 1rem;
	border: 1px solid #ccc;
}

#content {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	width: 95%;
	margin: auto;
}

#orderTable {
	display: flex;
	justify-content: center;
	flex-direction: column;
}

table {
	border: 1px solid #ccc;
	border-collapse: collapse;
	margin: 0;
	padding: 0;
	width: 95%;
	table-layout: fixed;
}

table caption {
	font-size: 1.5em;
	margin: .5em 0 .75em;
}

table tr {
	background-color: #f8f8f8;
	border: 1px solid #ddd;
	padding: .35em;
}

table th, table td {
	padding: .625em;
	text-align: center;
}

table th {
	font-size: .85em;
	letter-spacing: .1em;
	text-transform: uppercase;
}

@media screen and (max-width: 600px) {
	table {
		border: 0;
	}
	table caption {
		font-size: 1.3em;
	}
	table thead {
		border: none;
		clip: rect(0, 0, 0, 0);
		height: 1px;
		margin: -1px;
		overflow: hidden;
		padding: 0;
		position: absolute;
		width: 1px;
	}
	table tr {
		border-bottom: 3px solid #ddd;
		display: block;
		margin-bottom: .625em;
	}
	table td {
		border-bottom: 1px solid #ddd;
		display: block;
		font-size: .8em;
		text-align: right;
	}
	table td::before {
		/*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
		content: attr(data-label);
		float: left;
		font-weight: bold;
		text-transform: uppercase;
	}
	table td:last-child {
		border-bottom: 0;
	}
}
</style>
</head>
<body>
	<%@ include file="../navbar.file"%>
	<br>
	<div id="searchArea" style="margin: 0px auto; display: flex; justify-content: center; align-items: center;">
		<!--搜尋欄開始-->
		<div id="search">
			<form  class="searchfield" method="post" action="${ctxPath}/meal/mealController" enctype="application/x-www-form-urlencoded" id="keywordSearch">				
				<input type="hidden" name="action" value="keywordSearch">
				<input id="keyword" type="text" class="text ac_input" placeholder="請輸入關鍵字" autocomplete="off">				
				<button id="btn_search" type="submit" class="button" form="keywordSearch" style="border-radius:1rem; border: 1px solid #ccc;">搜尋</button>
			</form>
		</div>
		<!--搜尋欄結束-->
	</div>
	<br>
	<div id="CONTENT">
	<!--商品欄開始-->
		<table class="order">
			<tr>
				<td style="width: 200px" class="order">商品名稱</td>
				<td style="width: 150px" class="order">購買分量</td>
				<td style="width: 100px" class="order">購買數量</td>
				<td style="width: 100px" class="order">商品單價</td>
				<td style="width: 100px" class="order">單一商品總價</td>
			</tr>
			<c:forEach var="orderDetail" items="${orderDetails}" varStatus="loop">
				<tr>
					<td class="order"><a
						href="${ctxPath}/meal/mealController?action=findByprod&mealNo=${orderDetail.meal.mealNo}">
							${orderDetail.meal.mealName}</a></td>
					<td class="order">${orderDetail.quantity}</td>
					<td class="order">${orderDetail.amount}</td>
					<td class="order"><fmt:formatNumber
							value="${orderDetail.price/orderDetail.amount}" pattern="#,###" />
					</td>
					<td class="order"><fmt:formatNumber
							value="${orderDetail.price}" pattern="#,###" /></td>
				</tr>
			</c:forEach>
		</table>		
	</div>
	<!--商品欄結束-->
</body>
</html>
