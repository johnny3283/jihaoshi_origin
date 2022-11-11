package com.cart.model;

import java.util.List;
import java.util.Map;

public class CartService {
    public Map<String, Integer> getTotalPrice(List<CartProdVO> cartProds) {
        Integer amountPrice = 0;
        for (CartProdVO CartProd : cartProds) {
            amountPrice += CartProd.getPrice();
        }
        Map<String, Integer> totalPrice = Map.of("totalPrice", amountPrice);
        return totalPrice;
    }
}
