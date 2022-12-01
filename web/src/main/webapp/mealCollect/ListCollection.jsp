<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>我的收藏</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
#content {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	width: 95%;
	margin: auto;
}

.button {
	border-radius: 1rem;
	border: 1px solid #ccc;
}

#ordertable {
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
	<div id="CONTENT">
		<c:choose>
			<c:when test="${empty collectionDetails}">
				<div>
					<span style="font-size: 16px;">您還沒有收藏任何商品喔</span>
					<button class="button">
						<a href="${ctxPath}/meal/mealController?action=listAll"
							style="text-decoration: none; color: #333;">去收藏</a>
					</button>
				</div>
				<br>
			</c:when>
			<c:otherwise>
				<div id="orderTable">
					<table class="order" style="margin: 0px auto;">
						<tr>
							<td style="width: 100px" class="order">收藏編號</td>
							<td style="width: 150px" class="order">商品名稱</td>
							<td style="width: 100px" class="order">刪除商品</td>
						</tr>
						<c:forEach var="detail" items="${collectionDetails}"
							varStatus="loop">
							<tr>
								<td class="order">${loop.count}</td>
								<td class="order"><a
									href="${ctxPath}/meal/mealController?action=findByprod&mealNo=${detail.mealNo}">${detail.mealName}</a>
								</td>
								<td class="order">
									<form method="post" action="${ctxPath}/mealCollect/delete"
										id="form${loop.index}">
										<input type="hidden" name="detailNo"
											value="${detail.detailNo}">
									</form>
									<button type="submit" form="form${loop.index}" class="button">刪除收藏</button>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
