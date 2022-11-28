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
<style>
#content {
	display: flex; 
	justify-content: center; 
	align-items: center;
	flex-direction: column;
}
#search {
	border-style:double;
	border-color:#ecb714;
	border-radius:10px;
	width:250px;
	height:40px;
	display: flex; 
	justify-content: center; 
	align-items: center;
}
.searchfield {
	margin: 0px auto;
}
#form {
	display: flex;
    justify-content: center;
    flex-direction: column;
    border-radius:0.6rem; 
    border: 3px solid #ccc;
    width:450px;
    height:500px;
    border-style:outset;
}
</style>
</head>
<body>
	<%@ include file="../navbar.file" %>
	<div id="searchArea" style="margin: 0px auto; display: flex; justify-content: center; align-items: center;">
		<!--搜尋欄開始-->
		<div id="search">
			<FORM class="searchfield" METHOD="post" ACTION="<%=request.getContextPath()%>/faqservlet">
				<select name="faqClass">
					<option disabled selected>請選擇FAQ類別</option>
					<option value="購物 Shopping">購物 Shopping</option>
					<option value="折價券 Coupon">折價券 Coupon</option>
					<option value="會員服務 Member">會員服務 Member</option>
					<option value="訂單 Order">訂單 Order</option>
					<option value="包裹寄送 Delivery">包裹寄送 Delivery</option>
				</select> 
				<input type="hidden" name="action" value="getClass_For_Display">
				<input class="button" type="submit" value="送出" style="border-radius:1rem; border: 1px solid #ccc;">
			</FORM>
		</div>
		<!--搜尋欄結束-->
	</div>
	<br>
	<div id="CONTENT">
		<div>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
			</ul><br>
		</c:if>
		</div>		
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/faqservlet" name="updateFAQ">
		<div id="form">
			<div style="margin: 0px auto;">		
			<label>FAQ編號：<%=faqVO.getFaqNo()%></label>
			</div>
			<br>
 			<div style="margin: 0px auto;">
			<label>FAQ問題：</label>
			</div>
			<br>
			<div style="margin: 0px auto;"> 
			<input type="text" name="faqQue"value="<%=faqVO.getFaqQue()%>" />
			</div>
			<br>
			<div style="margin: 0px auto;"> 
			<label>FAQ答案：</label>
			</div>			
			<br>
			<div style="margin: 0px auto;">
			<textarea rows="5" cols="25" name="faqAns"><%=faqVO.getFaqAns()%></textarea>
			</div>
			<br>
			<div style="margin: 0px auto;"> 
			<label>FAQ類別：</label>
			</div>
			<br>
			<div style="margin: 0px auto;"> 
			<select name="faqClass">
			<c:forEach var="faqClass" items="${myStr}">
			<option value="${faqClass}" ${(faqVO.faqClass==faqClass)?'selected':'' }>${faqClass}</option>
			</c:forEach>
			</select>
			</div> 
			<br>
			<div style="margin: 0px auto;"> 
			<input type="hidden" name="action" value="update">			
			<input type="hidden" name="faqNo" value="<%=faqVO.getFaqNo()%>">
			<input type="submit" value="送出修改" style="border-radius:1rem; border: 1px solid #ccc;">	
			</div>		
		</div>
		</FORM>		
	</div>					
</body>
</html>
