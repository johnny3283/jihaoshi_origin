package com.cart.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 之後可改用 Redis 實作
public class CartCourseMapHolder implements CartCourseHolder {
    private static final Map<String, List<CartCourseVO>> HOLDER = new HashMap<>();

    @Override
    public void put(String key, List<CartCourseVO> cartCourses) {
        HOLDER.put(key, cartCourses);
    }

    @Override
    public List<CartCourseVO> get(String key) {
        return HOLDER.get(key);
    }

    @Override
    public void remove(String key) {
        HOLDER.remove(key);
    }
}
