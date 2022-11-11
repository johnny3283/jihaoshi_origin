package com.orderDetail.model;

import java.sql.Connection;
import java.util.List;

import com.cart.model.CartProdVO;

public interface OrderDetailDAO {
    int insert(String orderNo, CartProdVO prod, Connection conn);
    List<OrderDetailVO> listOrderDetails(String orderNo);

}
