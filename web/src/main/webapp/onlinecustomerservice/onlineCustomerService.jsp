<%@ page import="com.cart.model.CartProdVO"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>線上即時客服-會員</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="../css/user.css" type="text/css" />
<link type="text/css"
	href="<%=request.getContextPath()%>/css/jihaoshi.css" rel="stylesheet">
<style>
#pageHead {
	width: 100%;
	height: 30%;
}

a {
	font-size: 20px;
}
</style>
</head>
<body onload="connect();" onunload="disconnect();">
	<img src="<%=request.getContextPath()%>/images/JihaoshiPageHead.jpg"
		id="pageHead">
	<div class="block_N" style="margin: 0px auto;"></div>
	<div id="WRAPPER" class="ecsite-layout style_shopping ecsite-search">
		<div id="CONTENT" class="layout-wrapper">
			<div class="layout-center" style="text-align: center">
				<!--側邊欄區塊開始-->
				<dl class="block_W">
					<dd id="CategoryContainer">
						<ul class="treeview">
							<li id="cate_D" class="expanded"><H1>功能列表</H1>
								<ul class="main">
									<li>
										<a href="<%=request.getContextPath()%>/faqservlet?action=getAll">FAQ列表</a>
									</li>
									<li>
										<a href="<%=request.getContextPath()%>/index.jsp">回首頁</a>
									</li>
								</ul>
							</li>
						</ul>
					</dd>
				</dl>
				<!--側邊欄區塊結束-->
				<div class="block_C s_list">
					<div class="Cm">
						<div id="ItemContainer" class="Cm_C">
							<h3 id="statusOutput" class="statusOutput"></h3>
							<div id="messagesArea" class="panel message-area"></div>
							<div class="panel input-area">
								<span style="padding-left: 140px;">請輸入訊息 : </span> <input
									id="message" class="text-field" type="text"
									placeholder="Message"
									onkeydown="if (event.keyCode == 13) sendMessage();" />
								<!-- 鍵盤代碼13(Enter) -->
								<input type="submit" id="sendMessage" class="button" value="傳送"
									onclick="sendMessage();" /> <input type="button"
									id="disconnect" class="button" value="離開對話"
									onclick="disconnect();" />
							</div>
							<dl class="col3f" id="DRAA0A-A900BUT82">
							</dl>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<script>
		var MyPoint = "/CustomerServerWS/member";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		// ws是Socket自己的通訊協定
		var statusOutput = document.getElementById("statusOutput");
		var messagesArea = document.getElementById("messagesArea");
		var self = 'member';
		var webSocket;

		function connect() {
			// create a websocket
			webSocket = new WebSocket(endPointURL); // 透過建構子連結到後端

			webSocket.onopen = function(event) {
				updateStatus("Connect Success!"); // 動態改變上方文字
				document.getElementById('sendMessage').disabled = false; //(預設) //傳送按鈕
				document.getElementById('disconnect').disabled = false; //離開對話按鈕
				addListener();
			};

			webSocket.onmessage = function(event) {
				var jsonObj = JSON.parse(event.data); // JSON.parse():將文字資料還原成json物件
				if ("history" === jsonObj.type) {
					messagesArea.innerHTML = '';
					var ul = document.createElement('ul'); // 產生一個ul
					ul.id = "area";
					messagesArea.appendChild(ul); // 加入視窗中
					// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
					var messages = JSON.parse(jsonObj.message);
					for (var i = 0; i < messages.length; i++) {
						var historyData = JSON.parse(messages[i]);
						var showMsg = historyData.message;
						var li = document.createElement('li');
						// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
						historyData.sender === self ? li.className += 'me'
								: li.className += 'friend';
						li.innerHTML = showMsg;
						ul.appendChild(li);
					}
					// Element.scrollTop屬性可以設置和獲取元素被向上捲動的高度(pixels). 
					// 元素的scrollTop是元素頂端和能被看見的最頂端之間的距離.當元素並未產生滾動條,那麼 scrollTop 的值預設為 0.
					messagesArea.scrollTop = messagesArea.scrollHeight;
					// Element.scrollHeight只讀屬性是元素內容高度的度量，包括由於溢出而在屏幕上不可見的內容 。
				} else if ("chat" === jsonObj.type) {
					var li = document.createElement('li');
					jsonObj.sender === self ? li.className += 'me'
							: li.className += 'friend';
					li.innerHTML = jsonObj.message;
					console.log(li);
					document.getElementById("area").appendChild(li);
					messagesArea.scrollTop = messagesArea.scrollHeight; // 有新訊息會拉到最下面
				}
				;
			}
			webSocket.onclose = function(event) {
				updateStatus("Disconnected!");
			};

		}
		function sendMessage() {

			var inputMessage = document.getElementById("message");
			var message = inputMessage.value.trim();

			if (message === "") {
				alert("Input a message");
				inputMessage.focus();
			} else {
				var jsonObj = {
					"type" : "chat",
					"sender" : self,
					"receiver" : "customerService",
					"message" : message
				}
			}
			;
			webSocket.send(JSON.stringify(jsonObj));
			// 轉換成文字再傳送
			inputMessage.value = "";
			inputMessage.focus();
		}

		function addListener() {
			var jsonObj = {
				"type" : "history",
				"sender" : self,
				"receiver" : "customerService",
				"message" : ""
			};
			webSocket.send(JSON.stringify(jsonObj)); //傳送至sever端
		}

		function disconnect() {
			webSocket.close();
			document.getElementById('sendMessage').disabled = true;
			document.getElementById('disconnect').disabled = true;
		}

		function updateStatus(newStatus) {
			statusOutput.innerHTML = newStatus;
		}
	</script>
</body>
</html>
