<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO"%>
<%@ page import="java.util.Set"%>
<%@ page import="com.phyCouPromotion.model.*"%>

<%
/*   PhyCouPromotionVO phyCouPromotionVO = (PhyCouPromotionVO) request.getAttribute("phyCouPromotionVO");
  Set<PhyCouPromotionDetailVO> set = (Set) request.getAttribute("set");
  
  System.out.println("=======================================================");
  System.out.println(phyCouPromotionVO);
  System.out.println(set);
  System.out.println("=======================================================");
  
  StringBuilder sb = new StringBuilder();
  Integer prom_price = null;
  int i = set.size();
  for ( PhyCouPromotionDetailVO cos : set) {
	  if ( i-- != 1) {
	      sb.append(String.valueOf(cos.getPhyCouVO().getCourse_no())+",");
	  } else {
	      sb.append(String.valueOf(cos.getPhyCouVO().getCourse_no()));
	      prom_price = cos.getProm_price();
		 
	  }
  }
  String sCous = sb.toString();
   System.out.println(sCous); 
  pageContext.setAttribute("sCous",sCous);
  pageContext.setAttribute("prom_price",prom_price); */
  
  PhyCouPromotionVO phyCouPromotionVO = (PhyCouPromotionVO) request.getAttribute("phyCouPromotionVO");
  Set<PhyCouPromotionDetailVO> set = (Set) request.getAttribute("set");
  System.out.println("=======================================================");
  System.out.println(phyCouPromotionVO);
  System.out.println(set);
  System.out.println("=======================================================");
  if ( set != null ) {
	  StringBuilder sb = new StringBuilder();
	  Integer prom_price = null;
	
	  int i = set.size();
	  for ( PhyCouPromotionDetailVO cos : set) {
		  if ( i-- != 1) {
		      sb.append(String.valueOf(cos.getPhyCouVO().getCourse_no())+",");
		  } else {
		      sb.append(String.valueOf(cos.getPhyCouVO().getCourse_no()));
		      prom_price = cos.getProm_price();
			 
		  }
	  } 
	  String proCous = sb.toString();
	  pageContext.setAttribute("proCous",proCous);
	  pageContext.setAttribute("prom_price",prom_price);
	  
  } else {
 	  String proCous = (String) request.getAttribute("tep_proCous");
 	  Integer prom_price = (Integer) request.getAttribute("prom_price");
	  java.sql.Date update_time = (java.sql.Date) request.getAttribute("update_time");
	  pageContext.setAttribute("proCous",proCous);
	  pageContext.setAttribute("prom_price",prom_price);
	  pageContext.setAttribute("update_time",update_time);
 	  System.out.println(proCous);
      
  }
%>
<html>
<head>
<title>促銷專案資料</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 1000px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  #pageHead {
    width: 1000px;
    height: 23%;
  }
</style>

</head>
<body bgcolor='white'>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">

<table id="table-1">
	<tr><td>
		 <h3>促銷專案主檔資料</h3>
	</td>	
	<tr><td>
		<h4><a href="select_page.jsp">回課程促銷首頁</a></h4>
	</td></tr></tr>
</table>

<table>
	<tr>
		<th>促銷專案編號</th>
		<th>促銷專案名稱</th>
		<th>開始日期</th>
		<th>結束日期</th>
		<th>促銷活動敘述</th>
		<th>促銷活動狀態</th>
		<th>編輯時間</th>
	
	</tr>
	<tr>
		<td><%=phyCouPromotionVO.getProject_no()%></td>
		<td><%=phyCouPromotionVO.getProject_name()%></td>
		<td><%=phyCouPromotionVO.getStart_date()%></td>
		<td><%=phyCouPromotionVO.getEnd_date()%></td>
		<td><%=phyCouPromotionVO.getProm_description()%></td>
		<td><%=phyCouPromotionVO.getProm_status()%></td>
		<td><%=phyCouPromotionVO.getUpdate_time()%></td>
</table>

</body>
</html>
