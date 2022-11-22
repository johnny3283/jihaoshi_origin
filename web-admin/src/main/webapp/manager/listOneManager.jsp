<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager.model.*"%>

<%
ManagerService mgrSvc = new ManagerService();
ManagerVO MemberVO = (ManagerVO) request.getAttribute("ManagerVO");
%>
<!DOCTYPE html>
<html>
<head>



<title>listOneManager</title>
<link type="text/css" href="../css/jihaoshi.css" rel="stylesheet">
<style>
#pageHead {
	width: 100%;
	height: 30%;
}

a {
	font-size: 20px;
}

#info {
	background: transparent;
	border: 0;
	font-size: 13px;
}

table, th, td {
	border: solid 1px lightgray;
}
</style>
</head>
<body>
	<img src="../images/JihaoshiPageHead.jpg" id="pageHead">
	<div class="block_N" style="margin: 0px auto;">
		<!--搜尋欄開始-->
		<div class="Nm"
			style="display: flex; justify-content: center; align-items: center;">
			<ul class="searchfield">
				<li><input id="keyword" type="text" class="text ac_input"
					placeholder="請輸入關鍵字" autocomplete="off"></li>
				<li><input id="btn_search" type="button" class="button"
					value="找菜單"></li>
			</ul>
		</div>
		<!--搜尋欄結束-->
	</div>
	<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
		<div id="CONTENT" class="layout-wrapper">
			<div class="layout-center" style="text-align: center">
				<!--側邊欄區塊開始-->
				<dl class="block_W">
					<dd id="CategoryContainer">
						<ul class="treeview">
							<li>管理員:<font color=red> ${ManagerAcc} </font><br> <font>權限編號:
									${AuthorityNo} </font>
							</li>
							<li><a href="../index.jsp">到首頁</a></li>
							<li id="cate_D" class="expanded"><H1>員工管理</H1>
								<ul class="main">
									<li class="drop"><a href="../member/listAllMember.jsp">所有管理員</a>

									</li>
								</ul>
						</ul>
					</dd>
				</dl>
				<!--側邊欄區塊結束-->
				<div class="block_C s_list">
					<div class="Cm">
						<div id="ItemContainer" class="Cm_C">
							<dl class="col3f" id="DRAA0A-A900BUT82">
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
								<table>
									<tr>
										<th>管理員編號</th>
										<th>管理員姓名</th>
										<th>管理員帳號</th>
										<th>管理員IP</th>
										<th>管理員狀態</th>


									</tr>
									<tr>

										<td>${ManagerVO.managerNo}</td>
										<td>${ManagerVO.managerName}</td>
										<td>${ManagerVO.managerAccount}</td>
										<td>${ManagerVO.managerIp}</td>
										<td>${ManagerVO.managerStatus}</td>
										<td><FORM METHOD="post"
												ACTION="../manager/ManamgerServlet"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改"> <input
													type="hidden" name="managerNo"
													value="${ManagerVO.managerNo}"> <input
													type="hidden" name="action" value="getOne_For_Update">

											</FORM></td>



									</tr>
								</table>

							</dl>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- 	<a href='frontPage.jsp'>到首頁</a> -->



</body>
</html>