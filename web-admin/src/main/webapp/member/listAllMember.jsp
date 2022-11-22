<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<%
MemService memSvc = new MemService();
List<MemberVO> list = memSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>

<title>listAllMember</title>
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

table,th,td{
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
							<li><a
										href="../index.jsp">到首頁</a></li>						
							<li id="cate_D" class="expanded"><H1>會員管理</H1>
								<ul class="main">
									<li class="drop"><a href="../member/listAllMember.jsp">所有會員</a>
										<ul class="submenu">										
											<li class="submenu-item"><a href="#">訂單查詢</a></li>
										</ul></li>
									<li><a
										href="<%=request.getContextPath()%>/meal/MealInsert.jsp">新增菜單商品</a>
									</li>
									<li><a href="meal/mealController?action=listAll">菜單商品列表</a>
									</li>
									<li><a
										href="<%=request.getContextPath()%>/faqservlet?action=selectFAQ">FAQ列表</a>
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
								<h1>會員資料</h1>
								<FORM METHOD="post" ACTION="../mem/MemberServlet">
									<b>查詢會員資料 :</b> <input type="text" name="memberNo"> <input
										type="hidden" name="action" value="getOne_For_Display">
									<input type="submit" value="送出">
								</FORM>
								<table>
									<tr>
										<th>會員編號</th>
										<th>會員姓名</th>
										<th>會員電話</th>
										<th>會員暱稱</th>
										<th>會員地址</th>
										<th>會員e-mail</th>
										<th>會員狀態</th>

									</tr>
									<c:forEach var="MemberVO" items="${list}">
										<tr>
											<td>${MemberVO.memberNo}</td>
											<td>${MemberVO.memberName}</td>
											<td>${MemberVO.memberPhone}</td>
											<td>${MemberVO.memberNickname}</td>
											<td>${MemberVO.memberAddress}</td>
											<td>${MemberVO.memberEmail}</td>
											<td>${MemberVO.memberState}</td>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/mem/MemberServlet"
													style="margin-bottom: 0px;">
													<input type="submit" value="修改"> <input
														type="hidden" name="memberNo" value="${MemberVO.memberNo}">
													<input type="hidden" name="action"
														value="getOne_For_Update">

												</FORM>
											</td>
											<td>
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/mem/MemberServlet"
													style="margin-bottom: 0px;">
													<input type="submit" value="刪除"> <input
														type="hidden" name="memberNo" value="${MemberVO.memberNo}">
													<input type="hidden" name="action" value="delete">

												</FORM>
											</td>
										</tr>
									</c:forEach>
								</table>
								
								

							</dl>

						</div>
					</div>
				</div>
			</div>

		</div>
	</div>

</body>
</html>