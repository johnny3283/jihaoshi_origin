<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022/11/4
  Time: 下午 03:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>onlyLogin</title>
</head>
<body>
	<h3>首頁</h3>
	<h3>歡迎: ${MemberAcc} 您好
	<br><font>會員編號: ${MemberNo} </font>
		<br><font>會員姓名: ${MemberName} </font></h3>
	


	<FORM METHOD="post" ACTION="MemberServlet">
		<input type="hidden" name="action" value="Logout"> <input
			type="submit" value="登出">
	</FORM>
	
	<script>
	</script>
</body>
</html>
