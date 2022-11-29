<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.faq.model.*"%>
<%
FAQService faqSvc = new FAQService();
List<FAQVO> list = faqSvc.getAll();
pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>FAQ</title>
<style>
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
#content {
	display: flex; 
	justify-content: center; 
	align-items: center;
}
#FAQtable {
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

table th,
table td {
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
    clip: rect(0 0 0 0);
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
		<div id="FAQtable">
			<table style="margin: 0px auto;">
				<tr>
					<th>FAQ編號</th>
					<th>FAQ問題</th>
					<th>FAQ答案</th>
					<th>FAQ類別</th>
				</tr>
			<div style="margin: 0px auto;">
				<%@ include file="page1FAQ.file" %>
			</div>
			<br> 
			<c:forEach var="faqVO" items="${list}" begin="<%= pageIndex %>" end="<%= pageIndex+rowsPerPage-1 %>">
				<tr>
					<td>${faqVO.faqNo}</td>
					<td>${faqVO.faqQue}</td>
					<td>${faqVO.faqAns}</td>
					<td>${faqVO.faqClass}</td>
				</tr>
			</c:forEach>								
			</table>
			<br>
			<%@ include file="page2FAQ.file" %>
		</div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
