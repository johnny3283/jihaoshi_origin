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

import com.cart.model.CartCourseService;
import com.cart.model.CartCourseVO;
import com.online_course_order.model.OnlineCourseOrderService;
import com.online_course_order.model.OnlineCourseOrderVO;

@WebServlet("/order/orderCourseController")
public class OrderCourseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        OnlineCourseOrderService orderSV = new OnlineCourseOrderService();

        HttpSession session = req.getSession();
        List<CartCourseVO> cartCourses = (ArrayList<CartCourseVO>) session.getAttribute("cartCourses");
        String action = req.getParameter("action");
        CartCourseService cartCourseSV = new CartCourseService();

        if ("orderInsert".equals(action)) {
//        	HttpSession session = req.getSession();
//		    Object No = session.getAttribute("MemberNo");
//		    String no = No.toString();

            Integer memberNo = 1;
            String merchantTradeNo = req.getParameter("MerchantTradeNo"); // 店內之交易編號
            String tradeNo = req.getParameter("TradeNo"); // 綠界之交易編號
            Integer totalPrice = cartCourseSV.calculateTotalPrice(cartCourses);

            orderSV.orderInsert(merchantTradeNo, memberNo, totalPrice, tradeNo, cartCourses);
            session.removeAttribute("cartCourses");
            res.sendRedirect(req.getContextPath() + "/order/orderCourseController?action=orderList");

        }

        if ("orderList".equals(action)) {
//        	HttpSession session = req.getSession();
//		    Object No = session.getAttribute("MemberNo");
//		    String no = No.toString();
        	Integer memberNo = 1;
            List<OnlineCourseOrderVO> orders = orderSV.getOnlineCourseOrderbyMem(memberNo);
            req.setAttribute("courseorders", orders);
            RequestDispatcher orderPage = req.getRequestDispatcher(".jsp"); //會員查看所有訂單網址
            orderPage.forward(req, res);
        }
    }
}
