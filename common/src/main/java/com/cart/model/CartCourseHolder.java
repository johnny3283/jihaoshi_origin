package com.cart.model;

import java.util.List;

public interface CartCourseHolder {
    void put(String key, List<CartCourseVO> cartCourses);

    List<CartCourseVO> get(String key);

    void remove(String key);
}
