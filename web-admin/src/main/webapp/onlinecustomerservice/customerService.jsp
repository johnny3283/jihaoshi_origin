<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>線上即時客服-客服人員</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/customerService.css" type="text/css" />
<style>
#content {
	display: flex; 
	justify-content: center; 
	align-items: center;
	
}
#row {
	display: flex;
	flex-direction: column;
	overflow: auto; 
}
#box {
	display: flex; 
	justify-content: center; 
	align-items: center;
	flex-direction: column;
}

</style>
</head>
<body onload="connect();" onunload="disconnect();">
<%@ include file="../navbar.file" %>
<div id="content">
	<br>
	<div id="row"><!-- 列表 -->
		<div>
			<h4 class="statusOutput">LIST</h4>
		</div>
		<div id="list"></div>		
	</div> 
	<br>
	<div id="box">
		<div>
			<h4 id="statusOutput" class="statusOutput"></h4>
		</div>	
		<div id="messagesArea" class="panel message-area"></div>
		<div class="panel input-area">
		<span>請輸入訊息 : </span> 
		<input id="message" class="text-field" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" />
		<!-- 鍵盤代碼13(Enter) -->
		<input type="submit" id="sendMessage" class="button" value="傳送" onclick="sendMessage();" style="border-radius:1rem; border: 1px solid #ccc;"/> 
		<input type="button" id="disconnect" class="button" value="離開對話" onclick="disconnect();" style="border-radius:1rem; border: 1px solid #ccc;"/>
		</div>
	</div>	
</div>
	<script>
		//動態取得路徑
		var MyPoint = "/CustomerServerWS/customerService";
		// localhost:8081
		var host = window.location.host;
		// 專案名稱
		var path = window.location.pathname;
		// substring方法返回一个字符串在開始索引到结束索引之間的一個子集，或從開始索引直到字符串的末尾的一个子集。
		var webCtx = path.substring(0, path.indexOf('/', 1));
		// 網址(連接到server端) ws://localhost:8081/WebSocketChatWeb/FriendWS/${member_account}
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

		var statusOutput = document.getElementById("statusOutput"); // 狀態
		var messagesArea = document.getElementById("messagesArea"); // 視窗
		var self = 'customerService';
		var webSocket;

		function connect() { // 連線
			// create a websocket
			webSocket = new WebSocket(endPointURL); // 透過建構子連結到後端

			webSocket.onopen = function(event) {
				console.log("Connect Success!");
				document.getElementById('sendMessage').disabled = false;
				document.getElementById('disconnect').disabled = false;
			};

			webSocket.onmessage = function(event) {
				var jsonObj = JSON.parse(event.data);
				if ("open" === jsonObj.type) {
					refreshFriendList(jsonObj); // 有人上線就更新列表
				} else if ("history" === jsonObj.type) {
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
					messagesArea.scrollTop = messagesArea.scrollHeight;
				} else if ("close" === jsonObj.type) { // 有人下線就更新列表
					refreshFriendList(jsonObj);
				}

			};

			webSocket.onclose = function(event) {
				console.log("Disconnected!");
			};
		}

		function sendMessage() {
			var inputMessage = document.getElementById("message");
			var friend = statusOutput.textContent;
			var message = inputMessage.value.trim();

			if (message === "") {
				alert("Input a message");
				inputMessage.focus();
			} else if (friend === "") {
				alert("Choose a friend");
			} else {
				var jsonObj = {
					"type" : "chat",
					"sender" : self,
					"receiver" : friend,
					"message" : message
				};
				webSocket.send(JSON.stringify(jsonObj));
				inputMessage.value = "";
				inputMessage.focus();
			}
		}

		// 有好友上線或離線就更新列表
		function refreshFriendList(jsonObj) {
			var friends = jsonObj.users;
			var list = document.getElementById("row");
			row.innerHTML = '';
			for (var i = 0; i < friends.length; i++) {
				if (friends[i] === self) {
					continue;
				} //如果user名稱是自己就跳過
				row.innerHTML += '<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h3 class="user">'
						+ friends[i] + '</h3></div>';
				// <div id= 1 class="column" name="friendName" value= member ><h2>member</h2></div>'
			}
			addListener();
		}
		// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
		function addListener() {
			var container = document.getElementById("row");
			container.addEventListener("click", function(e) {
				var user = e.srcElement.textContent; //選取列表內的文字內容
				// srcElement => target
				// Node.textContent 屬性表示了節點或其後代的文字內容
				updateFriendName(user); // 將選取的名字寫入h3內
				var jsonObj = {
					"type" : "history",
					"sender" : self,
					"receiver" : user,
					"message" : ""
				};
				webSocket.send(JSON.stringify(jsonObj)); //傳送至sever端
			});
		}

		function disconnect() {
			webSocket.close();
			document.getElementById('sendMessage').disabled = true;
			document.getElementById('disconnect').disabled = true;
		}

		function updateFriendName(name) {
			statusOutput.innerHTML = name;
		}
	</script>
</body>
</html>
