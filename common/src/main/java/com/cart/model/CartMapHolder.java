package com.cart.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 之後可改用 Redis 實作
public class CartMapHolder implements CartHolder {
    private static final Map<String, List<CartProdVO>> HOLDER = new HashMap<>();

    @Override
    public void put(String key, List<CartProdVO> cartProds) {
        HOLDER.put(key, cartProds);
    }

    @Override
    public List<CartProdVO> get(String key) {
        return HOLDER.get(key);
    }

    @Override
    public void remove(String key) {
        HOLDER.remove(key);
    }
}
