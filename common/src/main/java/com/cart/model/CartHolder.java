package com.cart.model;

import java.util.List;

public interface CartHolder {
    void put(String key, List<CartProdVO> cartProds);

    List<CartProdVO> get(String key);

    void remove(String key);
}
