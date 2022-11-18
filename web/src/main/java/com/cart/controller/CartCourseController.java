package com.cart.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartCourseService;
import com.cart.model.CartCourseVO;
import com.online_course.model.OnlineCourseService;
import com.online_course.model.OnlineCourseVO;

@WebServlet("/cart/cartCourseController")
public class CartCourseController extends HttpServlet {
    OnlineCourseService courseSV = new OnlineCourseService();
    CartCourseService cartCourseSV = new CartCourseService();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<CartCourseVO> cartCourses = (ArrayList<CartCourseVO>) session.getAttribute("cartCourses");

        String action = req.getParameter("action");
        if ("cartAdd".equals(action)) {

            Integer courseNo = Integer.valueOf(req.getParameter("courseNo"));
            OnlineCourseVO course = courseSV.getOneOnlineCourse(courseNo);
            cartCourses=cartCourseSV.getCartCourses(course, cartCourses);
            Integer totalCoursePrice = cartCourseSV.calculateTotalPrice(cartCourses);
            session.setAttribute("totalCoursePrice",totalCoursePrice);
            session.setAttribute("cartCourses", cartCourses);
            System.out.println(cartCourses);
            String url=req.getContextPath()+"/onlineCourse/ListAllOnlineCourse.jsp";
            res.sendRedirect(url);

        }
//        if ("cartModify".equals(action)) {
//
//            Integer amount = Integer.valueOf(req.getParameter("amount"));
//            Integer cartIndex = Integer.valueOf(req.getParameter("cartIndex"));
//            CartProdVO cartProd = cartProds.get(cartIndex);
//            cartProd.setAmount(amount);
//            cartProd.setPrice((int) (cartProd.getMeal().getMealPrice() * cartProd.getQuantity() * cartProd.getAmount()));
//            cartProds.set(cartIndex, cartProd);
//            Integer totalPrice = cartSV.calculateTotalPrice(cartProds);
//            session.setAttribute("totalPrice",totalPrice);
//            session.setAttribute("cartProds", cartProds);
//            res.sendRedirect(req.getHeader("referer"));
//
//        }
        if ("cartDelete".equals(action)) {

            Integer cartCourseIndex = Integer.valueOf(req.getParameter("cartCourseIndex"));
            cartCourses.remove(cartCourses.get(cartCourseIndex));
            Integer totalCoursePrice = cartCourseSV.calculateTotalPrice(cartCourses);
            session.setAttribute("totalCoursePrice",totalCoursePrice);
            session.setAttribute("cartCourses", cartCourses);
            res.sendRedirect(req.getHeader("referer"));
            
        }
    }

}
