package com.cart.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<CartProdVO> cartProds = (ArrayList<CartProdVO>) session.getAttribute("cartProds");
        MealService mealSV = new MealService();
        CartService cartSV=new CartService();
        String action = req.getParameter("action");
        if ("cartAdd".equals(action)) {

            Integer mealNo = Integer.valueOf(req.getParameter("mealNo"));
            Double quantity = Double.valueOf(req.getParameter("quantityCart"));
            Integer amount = Integer.valueOf(req.getParameter("amount"));
            MealVO meal = mealSV.findByMealNo(mealNo);
            CartProdVO prod = null;

            if (cartProds == null) { // 購物車內沒有東西時

                cartProds = new ArrayList<>();
                prod = new CartProdVO();
                prod.setMeal(meal);
                prod.setQuantity(quantity);
                prod.setAmount(amount);
                prod.setPrice((int) (meal.getMealPrice() * quantity * amount));
                prod.setCal((int) (meal.getMealCal() * quantity));
                cartProds.add(prod);
                Map<String, Integer> totalPrice = cartSV.getTotalPrice(cartProds);
                session.setAttribute("totalPrice", totalPrice);
                session.setAttribute("cartProds", cartProds);
            } else { // 購物車內有東西時
                for (int i = 0; i < cartProds.size(); i++) { // 尋找是否有同編號同份量商品
                    prod = cartProds.get(i);
                    if (prod.getMeal().getMealNo().equals(mealNo) && prod.getQuantity().equals(quantity)) {
                        prod.setAmount(prod.getAmount() + amount); // 找到就改變數量跟價格
                        prod.setPrice((int) (meal.getMealPrice() * quantity * amount));
                        Map<String, Integer> totalPrice = cartSV.getTotalPrice(cartProds);
                        session.setAttribute("totalPrice", totalPrice);
                        session.setAttribute("cartProds", cartProds);
                        res.sendRedirect(req.getHeader("referer"));
                        return;
                    }
                }
                // 查無同編號同份量商品時
                prod = new CartProdVO();
                prod.setMeal(meal);
                prod.setQuantity(quantity);
                prod.setAmount(amount);
                prod.setPrice((int) (meal.getMealPrice() * quantity * amount));
                prod.setCal((int) (meal.getMealCal() * quantity));
                cartProds.add(prod);
                Map<String, Integer> totalPrice = cartSV.getTotalPrice(cartProds);
                session.setAttribute("totalPrice", totalPrice);
                session.setAttribute("cartProds", cartProds);
            }
            res.sendRedirect(req.getHeader("referer"));
        }
        if ("cartModify".equals(action)) {
            Integer amount = Integer.valueOf(req.getParameter("amount"));
            Integer cartIndex = Integer.valueOf(req.getParameter("cartIndex"));
            CartProdVO cartProd = cartProds.get(cartIndex);
            cartProd.setAmount(amount);
            cartProd.setPrice((int) (cartProd.getMeal().getMealPrice() * cartProd.getQuantity() * cartProd.getAmount()));
            cartProds.set(cartIndex, cartProd);
            Map<String, Integer> totalPrice = cartSV.getTotalPrice(cartProds);
            session.setAttribute("totalPrice", totalPrice);
            session.setAttribute("cartProds", cartProds);
            res.sendRedirect(req.getHeader("referer"));
        }
        if ("cartDelete".equals(action)) {
            Integer cartIndex = Integer.valueOf(req.getParameter("cartIndex"));
            cartProds.remove(cartProds.get(cartIndex));

            Map<String, Integer> totalPrice = cartSV.getTotalPrice(cartProds);
            session.setAttribute("totalPrice", totalPrice);
            session.setAttribute("cartProds", cartProds);
            res.sendRedirect(req.getHeader("referer"));
        }
    }

}
