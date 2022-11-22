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
<link type="text/css" href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
<style>
#pageHead {
	width: 100%;
	height: 30%;
}

a {
	font-size: 20px;
}
table {
	width: 1050px;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 8px;
	text-align: center;
}
</style>
</head>
<body>
	<img src="<%=request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">
	<div class="block_N" style="margin: 0px auto;">
		<!--搜尋欄開始-->
		<div class="Nm" style="display: flex; justify-content: center; align-items: center;">
			<FORM class="searchfield" METHOD="post" ACTION="<%=request.getContextPath()%>/faqservlet">
				<select name="faqClass" class="text ac_input">
					<option disabled selected>請選擇FAQ類別</option>
					<option value="購物 Shopping">購物 Shopping</option>
					<option value="折價券 Coupon">折價券 Coupon</option>
					<option value="會員 Member">會員 Member</option>
					<option value="訂單 Order">訂單 Order</option>
					<option value="包裹寄送 Delivery">包裹寄送 Delivery</option>
				</select> 
				<input type="hidden" name="action" value="getClass_For_Display">
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
									<li><a
										href="<%=request.getContextPath()%>/faqservlet?action=getAll">FAQ列表</a>
									</li>
									<li>
                                    <a href="<%=request.getContextPath()%>/customerServiceServlet?action=getConnection">線上客服</a>
                                	</li>
									<li>
                                    <a href="<%=request.getContextPath()%>/index.jsp">回首頁</a>
                                	</li>
								</ul>
						</ul>
					</dd>
				</dl>
				<!--側邊欄區塊結束-->
				<div class="block_C s_list">
					<div class="Cm">
						<div id="ItemContainer" class="Cm_C">
							<table>
								<tr>
									<th>FAQ編號</th>
									<th>FAQ問題</th>
									<th>FAQ答案</th>
									<th>FAQ類別</th>
								</tr>
								<%@ include file="page1FAQ.file" %> 
								<c:forEach var="faqVO" items="${list}" 
								 begin="<%= pageIndex %>" end="<%= pageIndex+rowsPerPage-1 %>">
									<tr>
										<td>${faqVO.faqNo}</td>
										<td>${faqVO.faqQue}</td>
										<td>${faqVO.faqAns}</td>
										<td>${faqVO.faqClass}</td>
									</tr>
								</c:forEach>								
							</table>
							<%@ include file="page2FAQ.file" %> 
							<dl class="col3f" id="DRAA0A-A900BUT82">
							</dl>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
</script>
</body>
</html>
