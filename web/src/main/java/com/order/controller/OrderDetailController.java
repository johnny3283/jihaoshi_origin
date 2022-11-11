package com.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderDetail.model.OrderDetailVO;
import com.orderDetail.model.OrderDetailService;

@WebServlet("/order/orderDetailController")
public class OrderDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        OrderDetailService orderDetailSV = new OrderDetailService();

        if ("listOrderDetail".equals(action)) {
            String orderNo = req.getParameter("orderNo");
            List<OrderDetailVO> orderDetails = orderDetailSV.listOrderDeatails(orderNo);
            req.setAttribute("orderDetails",orderDetails);
            RequestDispatcher orderDetail = req.getRequestDispatcher("OrderDetail.jsp");
            orderDetail.forward(req,res);
            return;
        }
    }
}
