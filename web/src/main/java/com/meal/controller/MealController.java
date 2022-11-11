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

    }
}
