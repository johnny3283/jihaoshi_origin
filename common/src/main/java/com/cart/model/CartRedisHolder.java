package com.cart.model;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import redis.clients.jedis.Jedis;

public class CartRedisHolder implements CartHolder {

    private static Type typeToken = new TypeToken<List<CartProdVO>>() {
    }.getType();
    private static Jedis jedis = new Jedis("127.0.0.1", 6379);
    private Gson gson = new Gson();

    @Override
    public void put(String key, List<CartProdVO> cartProds) {
        jedis.set(key, gson.toJson(cartProds));
    }

    @Override
    public List<CartProdVO> get(String key) {
        return gson.fromJson(jedis.get(key), typeToken);
    }

    @Override
    public void remove(String key) {
        jedis.del(key);
    }
}
