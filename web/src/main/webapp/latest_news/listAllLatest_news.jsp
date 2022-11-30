<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.latest_news.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
 
 
<%
Latest_newsService latest_newsSvc = new Latest_newsService();
List<Latest_newsVO> list = latest_newsSvc.getAll();
Latest_newsVO vo = null;
for(int i = 0; i < list.size(); i++) {
	vo = list.get(i);
	if(vo.getNews_pic() != null)
		vo.setShowPhoto("data:image/png;base64,"+Base64.getEncoder().encodeToString(vo.getNews_pic()));
}


pageContext.setAttribute("list", list);
%>


<html>
<head>
 <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oxygen:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/styles.css">
    <style>
    .orderDetail{
    	

    	width:80%; 
		margin:auto;
/*     	box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2); */
/*         border-radius:2px; */
    }
    .orderPhoto{
   		display:flex;
    	justify-content:space-around;
    	width:80%;
    	padding:6px;
    	margin:50px 10%;
    	box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
        border-radius:2px;
        align-items:center
    }
    
    .orderDetail table tr {

    	box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
    }
    
    .orderDetail table th {
    	min-width: 120px;
    }
    
    .orderDetail table td {
    	width:auto;
    }
    
    ul,li {
    	list-style:none;
    }
    
    li > form {
    	margin-left: 50%;
    	translate:-50%;
    	
    }
    
    </style>
	




</head>

<body>
<%@ include file="../navbar.file" %>




<ul>  
  
<!--   <li> -->
<!--     <FORM METHOD="post" ACTION="/web-admin/Latest_newsServlet" > -->
<!--         <b>輸入消息編號 (如:1):</b> -->
<!--         <input type="text" name="news_no"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->

  <jsp:useBean id="latest_newsSsSvc" scope="page" class="com.latest_news.model.Latest_newsService" />
   
  <li>
     <FORM METHOD="post" ACTION="/web/Latest_newsServlet" >
       <b>選擇消息編號:</b>
       <select size="1" name="news_no">
         <c:forEach var="latest_newsVO" items="${latest_newsSsSvc.all}" > 
          <option value="${latest_newsVO.news_no}">${latest_newsVO.news_no}
         </c:forEach>   
       </select>
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
       <button id='search' name="action" value="getOne_For_Display" style="margin-left: 5px;width:auto;border-radius: 10px; border: .5px solid #f4f5e3;background: #F3E3C3; height:25px"><svg style="" xmlns="http://www.w3.org/2000/svg" width="10" height="10" fill="grey" class="bi bi-search" viewBox="0 0 16 16">
    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="/web/Latest_newsServlet" >
       <b>選擇消息內文:</b>
       <select size="1" name="news_no">
         <c:forEach var="latest_newsVO" items="${latest_newsSsSvc.all}" > 
          <option value="${latest_newsVO.news_no}">${latest_newsVO.news_name}
         </c:forEach>   
       </select>
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
       <button id='search' name="action" value="getOne_For_Display" style="margin-left: 5px;width:auto;border-radius: 10px; border: .5px solid #f4f5e3;background: #F3E3C3; height:25px"><svg style="" xmlns="http://www.w3.org/2000/svg" width="10" height="10" fill="grey" class="bi bi-search" viewBox="0 0 16 16">
    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
     </FORM>
  </li>
</ul>

<div class="orderDetail">
	<table>

		<tr style="text-align: center">
	
			<th>消息編號</th>
			<th>消息標題</th>
			<th>編輯時間</th>
			<th>消息內文</th>
			<th>消息圖片</th>
			

		</tr>
		<%@ include file="page1.file"%>
	
		<c:forEach var="latest_newsVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
	
				<td>${latest_newsVO.news_no}</td>
				<td>${latest_newsVO.news_name}</td>
				<td>${latest_newsVO.update_date}</td>
				<td>${latest_newsVO.news_content}</td>
				<td ><img style="margin: 20px " src="${latest_newsVO.showPhoto}"></td>



			</tr>
		</c:forEach>
	</table>
	</div>
	<%@ include file="page2.file" %>

</body>
</html>