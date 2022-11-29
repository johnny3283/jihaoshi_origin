<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <style>
        body {
            background: #fafafa url(https://jackrugile.com/images/misc/noise-diagonal.png);
            color: #444;
            font: 100%/30px 'Helvetica Neue', helvetica, arial, sans-serif;
            
        }

        strong {
            font-weight: bold;
        }

        em {
            font-style: italic;
        }

        table {
            background: #FFFCEC;
            border-collapse: separate;
            font-size: 12px;
            line-height: 24px;
            margin: 30px auto;
            text-align: center;
          
        }

        th {
            background-color:#faedcd;          
            font-weight: bold;
            padding: 10px 15px;
            position: relative;          
            color:black;
        }

           
        td {
            border-right: 1px solid #fff;
            border-left: 1px solid #e8e8e8;
            border-top: 1px solid #fff;
            border-bottom: 1px solid #e8e8e8;
            padding: 10px 15px;
            position: relative;
            transition: all 300ms;
        }

        td:first-child {
            box-shadow: inset 1px 0 0 #fff;
        }

        td:last-child {
            border-right: 1px solid #e8e8e8;
            box-shadow: inset -1px 0 0 #fff;
        }

        tr {
            background: url(https://jackrugile.com/images/misc/noise-diagonal.png);
        
        }

        tr:nth-child(odd) td {
            background: white url(https://jackrugile.com/images/misc/noise-diagonal.png);
               
        }

        tr:last-of-type td {
            box-shadow: inset 0 -1px 0 #fff;
        }

        tr:last-of-type td:first-child {
            box-shadow: inset 1px -1px 0 #fff;
        }

        tr:last-of-type td:last-child {
            box-shadow: inset -1px -1px 0 #fff;
        }

        tbody:hover td {
            color: transparent;
            text-shadow: 0 0 3px #aaa;
        }

        tbody:hover tr:hover td {
            color: #444;
            text-shadow: 0 1px 0 #fff;
        }
        .h3,h3 {
            font-size: calc(2rem + .8vw) !important;
            line-height: 15px;
            margin: auto;
            text-align: center;
            color:grey;      
            background-color:white;
            
        }
    </style>
    <style>
    html, body {
  height: 100%;
}

.wrap {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.button {
  width: 100px;
  height: 30px;
  font-family: 'Roboto', sans-serif;
  font-size: 11px;
  text-transform: uppercase;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: #000;
  background-color: #fff;
  border: none;
  border-radius: 45px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
  }
  
  
    
    </style>
</head>

<body>
	<%@ include file="../navbar.file" %>
<!-- 	<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search"></div> -->
<!-- 		<div id="CONTENT" class="layout-wrapper"></div> -->
			
<div style="width:80%;margin: auto auto">
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
					<form method="post" action="onlineCourseOrderServlet" enctype="application/x-www-form-urlencoded">
						<input type="hidden" name="action" value="searchOrderDetail">
						<input type="hidden" name="orderNo" value="${order.orderNo}">
						<input class="wrap button" type="submit" value="查看訂單明細">
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
</div>

</body>
</html>