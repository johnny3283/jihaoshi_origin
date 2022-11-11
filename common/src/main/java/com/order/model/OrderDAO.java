package com.order.model;

import java.util.List;

import com.cart.model.CartProdVO;

public interface OrderDAO {
    void insert(OrderVO order, List<CartProdVO> cartProds);

    List<OrderVO> listOrsers(Integer memberNo);
}
