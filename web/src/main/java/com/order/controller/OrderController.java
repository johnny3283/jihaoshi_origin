package com.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartHolder;
import com.cart.model.CartProdVO;
import com.cart.model.CartRedisHolder;

import com.mem.model.MemberVO;
import com.order.model.OrderService;
import com.order.model.OrderVO;

@WebServlet("/order/orderController")
public class OrderController extends HttpServlet {

    private final CartHolder cartHolder;

    // DI style
//    public OrderController(CartHolder cartHolder) {
//        this.cartHolder = cartHolder;
//    }

    public OrderController() {
        this.cartHolder = new CartRedisHolder();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        OrderService orderSV = new OrderService();

        HttpSession session = req.getSession();
        String action = req.getParameter("action");

        if ("orderInsert".equals(action)) {

            String tradeDesc = req.getParameter("TradeDesc");
            MemberVO member = (MemberVO) session.getAttribute("member");
            String merchantTradeNo = req.getParameter("MerchantTradeNo"); // 店內之交易編號
            String tradeNo = req.getParameter("TradeNo"); // 綠界之交易編號
            List<CartProdVO> cartProds = cartHolder.get(merchantTradeNo);

            Integer tradeAmt = Integer.valueOf(req.getParameter("TradeAmt"));

            orderSV.orderInsert(merchantTradeNo, member.getMemberNo(), tradeAmt, tradeNo, cartProds);

            session.removeAttribute("cartProds");
            session.removeAttribute("totalPrice");
            cartHolder.remove(merchantTradeNo);
            res.sendRedirect(req.getContextPath() + "/order/orderController?action=orderList");
        }

        if ("orderList".equals(action)) {
            MemberVO member = (MemberVO) session.getAttribute("member");
            List<OrderVO> orders = orderSV.listOrsers(member.getMemberNo());
            req.setAttribute("orders", orders);
            RequestDispatcher orderPage = req.getRequestDispatcher("ListOrder.jsp");
            orderPage.forward(req, res);
        }
    }
}
