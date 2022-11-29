<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
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

    <style>
    .orderDetail{
    	display:flex;
    	justify-content:space-around;
    	width:80%;
    	padding:6px;
    	margin:0 10%;
    	box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
        border-radius:2px;
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
    
    </style>
	
</head>
<body>
<%@ include file="../navbar.file" %>
						<div class="orderDetail">
							<div>線上課程訂單編號:${order.orderNo}</div>
							<div>會員編號:${order.memberNo}</div>
							<div>訂單成立日期:${order.orderTime}</div>
							<div>訂單數量:${order.orderVolume}</div>
							<div>訂單價格:${order.orderPrice}</div>	 
						</div>
					    <div>
							<c:forEach var="detail" items="${order.orderDetailList}">
								<div class="orderPhoto">
									
									<div>
										<div>線上課程編號:${detail.courseNo}</div>
										<div>線上課程名稱:${detail.courseName}</div>
										<div>線上課程價格:${detail.coursePrice}</div>
										<br>
										<c:choose>
										<c:when test="">
											<div>
												<button style="border-radius:1rem; border: 1px solid #ccc;"><a href="<%=request.getContextPath()%>/MemberOnlineCourseCommentServlet?action=getMember_For_Display" style="text-decoration: none;color:#333;">查看我的評價</a></button>			
											</div>
										</c:when>
										<c:otherwise>					
											<form action="${ctxPath}/onlinecoursecomment/addOnlineCourseComment.jsp" >
											<div>										
											&emsp;&emsp;
											<input type="submit" value="我要評價" style="border-radius:1rem; border: 1px solid #ccc;">
											<input type="hidden" name="courseName" value="${detail.courseName}">
											<input type="hidden" name="courseNo" value="${detail.courseNo}">
											</div>
											</form>
										</c:otherwise>
										</c:choose>										
									</div>									
								    <div>
								    	<img src="data:image/*;base64, ${detail.orderPhotoBaseStr64}">
								    </div>
								</div>
							</c:forEach>
						</div>
						<script>
						</script>
</body>
</html>