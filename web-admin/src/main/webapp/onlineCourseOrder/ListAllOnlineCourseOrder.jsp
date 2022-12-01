<%@ page import="com.online_course_order.model.OnlineCourseOrderVO"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.online_course.model.*"%>
<%@ page import="com.online_course_order.model.*"%>
<!DOCTYPE html>
<html>
<head>
	<title>所有訂單資料</title>
	<style>
    ul,li {
    	list-style:none;
    }
	</style>
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
	<section style="position:absolute;top:30%;left:15%;gap:10px; ">
	<table>
		<ul>
			<li style="margin-bottom: 20px;">
				<b><span style="font-size:18px;">請輸入會員編號 (如1):</span></b>
				<input type="text" style="border-radius: 50px;border: 2px solid #5B5B5B;" id="memberNo">
<!-- 				<button onclick="searchByMemberId()">送出</button> -->
				 <button onclick="searchByMemberId()" id='search' name="action" value="getOne_For_Display" style="margin-left: 5px;width:auto;border-radius: 10px; border: .5px solid #f4f5e3;background: #F3E3C3; height:28px"><svg style="" xmlns="http://www.w3.org/2000/svg" width="10" height="10" fill="grey" class="bi bi-search" viewBox="0 0 16 16">
    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
    </FORM>
			</li>
		</ul>
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
											<input class="wrap button" type="submit" value="查看訂單明細">
										</form>
									</td>
									<td id="tdStatus\${order.orderNo}">
									    \${order.orderStatus == 0 ? '<button class ="wrap button" onclick="changeStatus(\'' + order.orderNo + '\')">訂單已成立</button>' : ''}
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