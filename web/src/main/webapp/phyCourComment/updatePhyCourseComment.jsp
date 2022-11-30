<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.phyCourseComment.model.*"%>
<%
  phyCourseCommentVO phyVO = (phyCourseCommentVO) request.getAttribute("phyCourseCommentVO");
%>
<html>
<head>
<title>線上課程評論</title>
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
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/phyCourseComment" name="phyCourComment">
		<div id="form">
				<div style="margin: 0px auto;">
				<label>線上課程評論編號：<%=phyVO.getCommentNo()%></label>
				</div>
				<br>
				<div style="margin: 0px auto;">
				<label>會員編號_帳號：<%=phyVO.getMemberNo()%>_<%=phyVO.getMemberVO().getMemberName()%></label>
				</div>
				<br>
				<div style="margin: 0px auto;">
				<label>線上課程編號_名稱：<%=phyVO.getCourseNo()%>_<%=phyVO.getCourseName()%></label>
				</div>
				<br>
				<div style="margin: 0px auto;">
				<label>線上課程評論內容：</label>
				</div>
				<br>
				<div style="margin: 0px auto;">
				<textarea rows="5" cols="25" name="commentContent"><%=phyVO.getCommentContent()%></textarea>
				</div>
				<br>
	            <br>
	            <br>
	            <div style="margin: 0px auto;"> 
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="memberNo" value="<%=phyVO.getMemberNo()%>">
				<input type="hidden" name="courseNo" value="<%=phyVO.getCourseNo()%>">
				<input type="hidden" name="commentNo" value="<%=phyVO.getCommentNo()%>">
				<input type="submit" value="送出修改" style="border-radius:1rem; border: 1px solid #ccc;">
				</div>		
		</div>
		</FORM>
	</div>

	<script type="text/javascript">
		
	</script>
</body>
</html>
