package com.orderDetail.model;

import java.util.List;

public class OrderDetailService {
    OrderDetailDAO dao=new OrderDetailDAOImpl();
    public List<OrderDetailVO> listOrderDeatails(String orderNo){
        return dao.listOrderDetails(orderNo);
    }
}
