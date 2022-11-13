package com.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartProdVO;
import com.cart.model.CartService;
import com.order.model.OrderService;
import com.order.model.OrderVO;

@WebServlet("/order/orderController")
public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        OrderService orderSV=new OrderService();

        HttpSession session = req.getSession();
        List<CartProdVO> cartProds = (ArrayList<CartProdVO>) session.getAttribute("cartProds");
        String action = req.getParameter("action");
        CartService cartSV=new CartService();

        if ("orderInsert".equals(action)) {
            //            Integer memberNo = req.getParameter("memberNo");

            Integer memberNo=1;
            String merchantTradeNo=req.getParameter("MerchantTradeNo"); // 店內之交易編號
            String tradeNo=req.getParameter("TradeNo"); // 綠界之交易編號
            Integer totalPrice = cartSV.calculateTotalPrice(cartProds);
            orderSV.orderInsert(merchantTradeNo, memberNo, totalPrice, tradeNo, cartProds);

            res.sendRedirect(req.getContextPath()+"/order/orderController?action=orderList");
        }

        if ("orderList".equals(action)) {
//            Integer memberNo = req.getParameter("memberNo");
            session.removeAttribute("cartProds");
            session.removeAttribute("totalPrice");
            Integer memberNo=1;
            List<OrderVO> orders = orderSV.listOrsers(memberNo);
            req.setAttribute("orders",orders);
            RequestDispatcher orderPage= req.getRequestDispatcher("ListOrder.jsp");
            orderPage.forward(req,res);
            return;
        }
    }
}
