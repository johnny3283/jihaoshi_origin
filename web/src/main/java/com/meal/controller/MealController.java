package com.meal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartProdVO;
import com.meal.model.MealService;
import com.meal.model.MealVO;

@WebServlet("/meal/mealController")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MealController extends HttpServlet {
    MealService mealSV = new MealService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<CartProdVO> cartProds = (ArrayList<CartProdVO>) session.getAttribute("cartProds");
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        RequestDispatcher productPage=null;
        if ("listAll".equals(action)) {

            List<MealVO> listMeals = mealSV.getAllLaunch();
            if (listMeals != null) {
                req.setAttribute("listMeals", listMeals);
                productPage = req.getRequestDispatcher("/meal/ListMealProduct.jsp");
                productPage.forward(req, res);
            }
        }

        if ("findByprod".equals(action)) {
            Integer mealNo = Integer.valueOf(req.getParameter("mealNo"));
            MealVO meal = mealSV.findByMealNo(mealNo);
            if (meal != null&& meal.getLaunch().equals(1)) {
                req.setAttribute("meal", meal);
                productPage = req.getRequestDispatcher("/meal/ProductPage.jsp");
                productPage.forward(req, res);
                return;
            }else {
                res.sendRedirect("ProductNotFound.jsp");
            }

        }
        if ("cartAdd".equals(action)) {

            Integer mealNo = Integer.valueOf(req.getParameter("mealNo"));
            Double quantity = Double.valueOf(req.getParameter("quantityCart"));
            Integer amount = Integer.valueOf(req.getParameter("amount"));

            MealVO meal = mealSV.findByMealNo(mealNo);
            CartProdVO prod = null;

            if (cartProds == null) { // 購物車內沒有東西時
                cartProds = new ArrayList<>();
                prod=new CartProdVO();
                prod.setMeal(meal);
                prod.setQuantity(quantity);
                prod.setAmount(amount);
                prod.setPrice((int) (meal.getMealPrice() * quantity * amount));
                cartProds.add(prod);
                session.setAttribute("cartProds", cartProds);
            } else { // 購物車內有東西時
                for (int i = 0; i < cartProds.size(); i++) { // 尋找是否有同編號同份量商品
                     prod = cartProds.get(i);
                    if (prod.getMeal().getMealNo().equals(mealNo) && prod.getQuantity().equals(quantity)) {
                        prod.setAmount(prod.getAmount() + amount); // 找到就改變數量跟價格
                        prod.setPrice((int) (meal.getMealPrice() * quantity * amount));
                        session.setAttribute("cartProds", cartProds);
                        return;
                    }
                }
                // 查無同編號同份量商品時
                prod=new CartProdVO();
                prod.setMeal(meal);
                prod.setQuantity(quantity);
                prod.setAmount(amount);
                prod.setPrice((int) (meal.getMealPrice() * quantity * amount));
                cartProds.add(prod);
                session.setAttribute("cartProds", cartProds);
            }

        }
    }
}
