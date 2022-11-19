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

import com.cart.model.CartProdVO;
import com.cart.model.CartService;
import com.meal.model.MealService;
import com.meal.model.MealVO;

@WebServlet("/cart/cartController")
public class CartController extends HttpServlet {
    MealService mealSV = new MealService();
    CartService cartSV = new CartService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<CartProdVO> cartProds = (ArrayList<CartProdVO>) session.getAttribute("cartProds");

        String action = req.getParameter("action");
        if ("cartAdd".equals(action)) {

            Integer mealNo = Integer.valueOf(req.getParameter("mealNo"));
            Double quantity = Double.valueOf(req.getParameter("quantityCart"));
            Integer amount = Integer.valueOf(req.getParameter("amount"));
            MealVO meal = mealSV.findByMealNo(mealNo);
            meal.setShowPhoto("data:image/png;base64,"+ Base64.getEncoder().encodeToString(meal.getMealPhoto()));
            cartProds=cartSV.getCartProds(quantity, amount, meal, cartProds);
            Integer totalPrice = cartSV.calculateTotalPrice(cartProds);
            session.setAttribute("totalPrice",totalPrice);
            session.setAttribute("cartProds", cartProds);
            res.sendRedirect(req.getHeader("referer"));

        }
        if ("cartModify".equals(action)) {

            Integer amount = Integer.valueOf(req.getParameter("amount"));
            Integer cartIndex = Integer.valueOf(req.getParameter("cartIndex"));
            CartProdVO cartProd = cartProds.get(cartIndex);
            cartProd.setAmount(amount);
            cartProd.setPrice((int) (cartProd.getMeal().getMealPrice() * cartProd.getQuantity() * cartProd.getAmount()));
            cartProds.set(cartIndex, cartProd);
            Integer totalPrice = cartSV.calculateTotalPrice(cartProds);
            session.setAttribute("totalPrice",totalPrice);
            session.setAttribute("cartProds", cartProds);
            res.sendRedirect(req.getHeader("referer"));

        }
        if ("cartDelete".equals(action)) {

            Integer cartIndex = Integer.valueOf(req.getParameter("cartIndex"));
            cartProds.remove(cartProds.get(cartIndex));
            Integer totalPrice = cartSV.calculateTotalPrice(cartProds);
            session.setAttribute("totalPrice",totalPrice);
            session.setAttribute("cartProds", cartProds);
            res.sendRedirect(req.getHeader("referer"));
            
        }
    }

}
