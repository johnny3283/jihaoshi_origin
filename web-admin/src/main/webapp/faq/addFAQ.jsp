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
<style>
#content {
	display: flex; 
	justify-content: center; 
	align-items: center;
	flex-direction: column;
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
.button {
	border-radius:1rem; 
	border: 1px solid #ccc;
}
</style>
</head>
<body>
<%@ include file="../navbar.file" %>
<br>
<div id="content">
	<br>
		<div>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font><br>
		<c:forEach var="message" items="${errorMsgs}">
			<span style="color:red">${message}</span><br>
		</c:forEach>
		</c:if>
	</div>	
	<br>					
	<form method="post" action="<%=request.getContextPath()%>/faqservlet" id="addFAQ">
	<div id="form">
		<div style="margin: 0px auto;">
		<label>FAQ問題：</label>
		</div>
		<br>
		<div style="margin: 0px auto;">
		<input type="text" name="faqQue"  value="<%= (faqVO==null)? "" : faqVO.getFaqQue()%>" >
		</div>
		<br>
		<div style="margin: 0px auto;">
		<label>FAQ答案：</label>
		</div>
		<br>
		<div style="margin: 0px auto;">
		<textarea name="faqAns"><%= (faqVO==null)? "" : faqVO.getFaqAns()%></textarea>
		</div>
		<br>
		<div style="margin: 0px auto;"> 
		<label>FAQ類別：</label>
		</div>
		<br>
		<div style="margin: 0px auto;"> 
		<select name="faqClass">
		<option disabled selected>請選擇FAQ類別</option>
		<c:forEach var="faqClass" items="${myStr}"> 
			<option value="${faqClass}" ${(faqVO.faqClass==faqClass)?'selected':'' } >${faqClass}</option>
		</c:forEach>
		</select>
		</div>
		<br>
		<div style="margin: 0px auto;"> 
		<input type="hidden" name="action" value="add">
		<input type="submit" name="送出資料" form="addFAQ" class="button"> 
		<input type="reset" name="清除資料" form="addFAQ" class="button">
		</div>
	</div>
	</form>
</div>
</body>
</html>
