<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.faq.model.*"%>
<%
FAQVO faqVO = (FAQVO) request.getAttribute("faqVO"); //FAQServlet.java (Concroller) 存入req的VO物件 (包括幫忙取出的faqVO, 也包括輸入資料錯誤時的faqVO物件)
%>
<%
String str[] = new String[5];
str[0] = "購物 Shopping";
str[1] = "折價券 Coupon";
str[2] = "會員 Member";
str[3] = "訂單 Order";
str[4] = "包裹寄送 Delivery";
request.setAttribute("myStr", str);
%>
<html>
<head>
<title>FAQ修改</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
<style>
#pageHead {
	width: 100%;
	height: 30%;
}

a {
	font-size: 20px;
}
</style>
</head>
<body>
	<img src="<%=request.getContextPath()%>/images/JihaoshiPageHead.jpg"
		id="pageHead">
	<div class="block_N" style="margin: 0px auto;">
		<!--搜尋欄開始-->
		<div class="Nm"
			style="display: flex; justify-content: center; align-items: center;">
			<FORM class="searchfield" METHOD="post"
				ACTION="<%=request.getContextPath()%>/faqservlet">
				<select name="faqClass" class="text ac_input">
					<option disabled selected>請選擇FAQ類別</option>
					<option value="購物 Shopping">購物 Shopping</option>
					<option value="折價券 Coupon">折價券 Coupon</option>
					<option value="會員 Member">會員 Member</option>
					<option value="訂單 Order">訂單 Order</option>
					<option value="包裹寄送 Delivery">包裹寄送 Delivery</option>
				</select> <input type="hidden" name="action" value="getClass_For_Display">
				<input class="button" type="submit" value="送出">
			</FORM>
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
							<li id="cate_D" class="expanded"><H1>功能列表</H1>
								<ul class="main">
									<li><a href="<%=request.getContextPath()%>/faq/addFAQ.jsp">新增FAQ</a>
									</li>
									<li><a
										href="<%=request.getContextPath()%>/faq/indexFAQ.jsp">FAQ列表</a>
									</li>
								</ul>
						</ul>
					</dd>
				</dl>
				<!--側邊欄區塊結束-->
				<div class="block_C s_list">
					<div class="Cm">
						<div id="ItemContainer" class="Cm_C">
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul><br>
							</c:if>

							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/faqservlet"
								name="updateFAQ">
								<div>
									<label>FAQ編號：<%=faqVO.getFaqNo()%></label><br>
									<br> <label>FAQ問題：</label><br>
									<br> <input type="text" name="faqQue"
										value="<%=faqVO.getFaqQue()%>" /><br>
									<br> <label>FAQ答案：</label><br>
									<br>
									<textarea name="faqAns"><%=faqVO.getFaqAns()%></textarea>
									<br>
									<br> <label>FAQ類別：</label><br>
									<br> <select name="faqClass">
										<c:forEach var="faqClass" items="${myStr}">
											<option value="${faqClass}"
												${(faqVO.faqClass==faqClass)?'selected':'' }>${faqClass}</option>
										</c:forEach>
									</select> <br>
									<br> <input type="hidden" name="action" value="update">
									<input type="hidden" name="faqNo" value="<%=faqVO.getFaqNo()%>">
									<input type="submit" value="送出修改">
								</div>
							</FORM>
							<dl class="col3f" id="DRAA0A-A900BUT82">
							</dl>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
