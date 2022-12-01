<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
  <title>隨機配餐</title>
  <style>
  #content {
			display:flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
		}
  .button {
 			border-radius:1rem; 
 			border: 1px solid #ccc;
		}
  #form {
			display:flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			text-align:center;
			
		}
  </style>
</head>
<body>
<%@ include file="../navbar.file" %>
<br>
    <div id="content">
    <div>
		<h2 style=" font-size: 25px">請輸入配餐條件</h2>
	</div>
    <br>
    <div id="form">
            <form method="post" action="${ctxPath}/meal/mealController" enctype="application/x-www-form-urlencoded"
                  id="formRandom">
            <input type="hidden" name="action" value="randomAssign">
            <div>
            <label>餐點份數：</label>
            </div>
            <div>
            <input type="range" value="1" min="1" max="10" name="mealAmount" id="mealAmount">
            <span id="amount_value" style="font-size: 18px">1</span><br>
            </div>
            <div> 
            <label>是否接受重複餐點：</label>
            <input type="radio" name="repeat" value="yes" id="yes" checked><label for="yes">&nbsp;是&nbsp;</label>
            <input type="radio" name="repeat" value="no" id="no"><label for="no">&nbsp;否&nbsp;</label><br>
            </div>
            <br>
            <div>
            <button type="submit" form="formRandom" class="button">隨機配餐</button>
            </div>
            </form>
     </div>
</div>
<script>
  $(document).ready(function () {
    $('#mealAmount').mousemove(function () {
      $('#amount_value').html($('#mealAmount').val());
    });
    $('#mealAmount').change(function () {
      $('#amount_value').html($('#mealAmount').val());
    });
    $('#mealAmount').click(function () {
      $('#amount_value').html($('#mealAmount').val());
    });

  });
</script>
</body>
</html>
