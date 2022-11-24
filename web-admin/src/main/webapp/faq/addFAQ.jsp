<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.faq.model.*"%>
<%
FAQVO faqVO = (FAQVO) request.getAttribute("faqVO");
%>
<%	
	String str[] = new String [5];
	str[0]="購物 Shopping";	
	str[1]="折價券 Coupon";	
	str[2]="會員服務 Member";	
	str[3]="訂單 Order";	
	str[4]="包裹寄送 Delivery"; 	
	request.setAttribute("myStr", str);
%>
<html>
<head>
<title>新增FAQ</title>
<link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
<style>
#pageHead {
	width: 100%;
	height: 30%;
}

a {
	font-size: 20px;
}
h3 {	
	font-size:20px;
}
</style>
</head>
<body>
	<img src="<%=request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">
	<div class="block_N" style="margin: 0px auto;"></div>
	<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
		<div id="CONTENT" class="layout-wrapper">
			<div class="layout-center" style="text-align: center">
				<!--側邊欄區塊開始-->
				<dl class="block_W">
					<dd id="CategoryContainer">
						<ul class="treeview">
							<li id="cate_D" class="expanded"><H1>功能列表</H1>
								<ul class="main">
									<li>
									<a href="<%=request.getContextPath()%>/faq/addFAQ.jsp">新增FAQ</a>
									</li>
									<li>
									<a href="<%=request.getContextPath()%>/faqservlet?action=getAll">FAQ列表</a>
									</li>
								</ul>
						</ul>
					</dd>
				</dl>
				<!--側邊欄區塊結束-->
				<div class="block_C s_list">
					<div class="Cm">
						<div id="ItemContainer" class="Cm_C">
							<h3>FAQ新增</h3><br>
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul><br>
							</c:if>					
							<form method="post"
								action="<%=request.getContextPath()%>/faqservlet" id="addFAQ">
								<div>
									<label>FAQ問題：</label><br>
									<br> <input type="text" name="faqQue"  value="<%= (faqVO==null)? "" : faqVO.getFaqQue()%>" ><br>
									<br> <label>FAQ答案：</label><br>
									<br>
									<textarea name="faqAns"><%= (faqVO==null)? "" : faqVO.getFaqAns()%></textarea>
									<br>
									<br> <label>FAQ類別：</label><br>
									<br> <select name="faqClass">
											<option disabled selected>請選擇FAQ類別</option>
										<c:forEach var="faqClass" items="${myStr}"> 
											<option value="${faqClass}" ${(faqVO.faqClass==faqClass)?'selected':'' } >${faqClass}</option>
										</c:forEach>
									</select> <br>
									<br> <input type="hidden" name="action" value="add">
									<input type="submit" name="送出資料" form="addFAQ"> 
									<input type="reset" name="清除資料" form="addFAQ">
								</div>
							</form>
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
