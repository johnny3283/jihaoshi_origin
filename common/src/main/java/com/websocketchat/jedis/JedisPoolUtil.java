package com.websocketchat.jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	// 宣告靜態變數
	private static JedisPool pool = null;

	// 建構子
	private JedisPoolUtil() { 
	}
	
	// 靜態方法
	public static JedisPool getJedisPool() {
		if (pool == null) {
			// 鎖定資源 synchronized
			synchronized (JedisPoolUtil.class) {
				if (pool == null) {
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxTotal(8); // 連線池中最大連接數
					config.setMaxIdle(8); // 連線池允許的最大空閒連接數
					config.setMaxWaitMillis(10000); // 當連線池連接用盡後，調用者的最大等候時間(單位為毫秒)
					// 藉由建構子產生Jedis物件即可與Redis(參數:連接主機位址資訊與使用的監聽埠號)
					pool = new JedisPool(config, "localhost", 6379);
				}
			}
		}
		return pool;
	}

	// 可在ServletContextListener的contextDestroyed裡呼叫此方法註銷Redis連線池
	public static void shutdownJedisPool() {
		if (pool != null)
			pool.destroy();
	}
}
