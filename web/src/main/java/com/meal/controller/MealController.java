package com.meal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meal.model.MealService;
import com.meal.model.MealVO;

@WebServlet("/meal/mealController")
@MultipartConfig(fileSizeThreshold = 0, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MealController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Integer mealNo = null;
        String mealName = null;
        String mealContent = null;
        Integer mealCal = null;
        String mealAllergen = null;
        Integer mealPrice = null;
        byte[] mealPhoto = null;
        String mealRecipe = null;
        Integer launch = null;
        String action = req.getParameter("action");
        if ("listAll".equals(action)) {
            MealService meslSV=new MealService();
            List<MealVO> meals=meslSV.getAll();
            if (meals != null) {
                req.setAttribute("lastAllMeal", meals);
                RequestDispatcher productPage = req.getRequestDispatcher("/meal/ListMealProduct.jsp");
                productPage.forward(req, res);
            }
        }

        if ("findByprod".equals(action)) {
            mealNo = Integer.valueOf(req.getParameter("mealNo"));
            MealService mealSV =new MealService();
            MealVO meal = mealSV.findByMealNo(mealNo);
            if (meal != null) {
                req.setAttribute("meal", meal);
                RequestDispatcher productPage = req.getRequestDispatcher("/meal/ProductPage.jsp");
                productPage.forward(req, res);

            }
        }
    }
}
