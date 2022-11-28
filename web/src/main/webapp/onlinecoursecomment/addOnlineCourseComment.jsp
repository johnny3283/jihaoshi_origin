<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.onlinecoursecomment.model.*"%>
<%
OnlineCourseCommentVO onlineCourseCommentVO = (OnlineCourseCommentVO) request.getAttribute("onlineCourseCommentVO");
%>
<html>
<head>
<title>線上課程評論</title>
<style>
#content {
	display: flex; 
	justify-content: center; 
	align-items: center;
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
		<div id="form">
		<label id="courseName" style="text-align:center;">線上課程名稱：${param.courseName}</label>
		<br>
		<label style="text-align:center;">線上課程評論：</label>
		<br>
		<textarea id="commentContent"></textarea>
		<br>
		<label style="text-align:center;">評分：</label>
		<br>
		<div style="margin: 0px auto;">
		<input type="range" id="score" min="0" max="10" name="commentScore" onchange="document.getElementById('show').innerHTML=value">
		&emsp;
		<span id="show"></span>
		</div>
		<br>
		<br>
		<div style="margin: 0px auto;">
		<button onclick="submitComment()" style="border-radius:1rem; border: 1px solid #ccc;">送出資料</button>
		<button onclick="clear()" style="border-radius:1rem; border: 1px solid #ccc;">清除資料</button>
		</div>
		</div>
	</div>
	<script type="text/javascript">
		//document.querySelector('#courseName').textContent = '線上課程名稱 : ' + ;
		const courseNo = ${param.courseNo};
		const commentContent = document.querySelector('#commentContent');
		const score = document.querySelector('#score');
		
		function submitComment() {
			let canSubmit = true;
			if (!commentContent.value) {
				alert('請輸入評論');
				canSubmit = false;
			}
			
			if (score.value === '0') {
				alert('請輸入分數');
				canSubmit = false;
			}
			
			if (canSubmit) {
				fetch('../courseComment/add', {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json'
					},
					body: JSON.stringify({
						courseNo,
						commentContent: commentContent.value,
						commentScore: score.value
					})
				})
					.then(resp => resp.json())
					.then(body => {
						alert(body.successful ? '成功' : '失敗');
						if (body.successful) {
							location = '../MemberOnlineCourseCommentServlet?action=getMember_For_Display';
						}
					});
			}
		}
		
		function clear() {
			commentContent.value = '';
			score.value = 0;
		}
	</script>
</body>
</html>
