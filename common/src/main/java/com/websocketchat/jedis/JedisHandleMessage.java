package com.websocketchat.jedis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<String> getHistoryMsg(String sender, String receiver) {
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
		Jedis jedis = null;
		jedis = pool.getResource(); // getResource() : 返回URL
		List<String> historyData = jedis.lrange(key, 0, -1); // lrange():返回列表中指定區間內的元素，區間以偏移量start和end指定(從0開始)
		jedis.close();
		return historyData;
	}

	public static void saveChatMessage(String sender, String receiver, String message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString(); // 發送者名稱:接收者名稱
		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString(); // 接收者名稱:發送者名稱
		Jedis jedis = pool.getResource(); 
		jedis.rpush(senderKey, message); // rpush():將一個或多個值插入到列表的最尾部(最右邊)
		jedis.rpush(receiverKey, message); // 如果列表不存在，一個列表會被創建並執行rpush()

		jedis.close();
	}

}
