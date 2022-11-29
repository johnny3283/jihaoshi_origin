package com.meal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meal.model.MealService;
import com.meal.model.MealVO;

@WebServlet("/meal/mealController")
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
        RequestDispatcher productPage = null;
        if ("listAll".equals(action)) {

            List<MealVO> meals = mealSV.getAllLaunch();
            if (meals != null) {
                for (MealVO meal : meals) {
                    meal.setShowPhoto("data:image/png;base64," + Base64.getEncoder().encodeToString(meal.getMealPhoto()));
                }
                req.setAttribute("meals", meals);
                productPage = req.getRequestDispatcher("/nutrient/aLLProdDetailList");
                productPage.forward(req, res);
            }
        }

        if ("findByprod".equals(action)) {
            Integer mealNo = Integer.valueOf(req.getParameter("mealNo"));
            MealVO meal = mealSV.findByMealNo(mealNo);
            if (meal != null && meal.getLaunch().equals(1)) {
                meal.setShowPhoto("data:image/png;base64," + Base64.getEncoder().encodeToString(meal.getMealPhoto()));
                req.setAttribute("meal", meal);
                productPage = req.getRequestDispatcher("/nutrient/prodDetailList");
                productPage.forward(req, res);
            } else {
                productPage = req.getRequestDispatcher("ProductNotFound.jsp");
                productPage.forward(req, res);
            }

        }
        if ("nameKeywordSearch".equals(action)) {
            String nameKeyword = req.getParameter("nameKeyword");
            List<MealVO> meals = mealSV.findByNameKeyword(nameKeyword);
            if (meals != null) {
                for (MealVO meal : meals) {
                    meal.setShowPhoto("data:image/png;base64," + Base64.getEncoder().encodeToString(meal.getMealPhoto()));
                }
                req.setAttribute("meals", meals);
                productPage = req.getRequestDispatcher("/nutrient/aLLProdDetailList");
                productPage.forward(req, res);
            }
        }

        if ("hashtag".equals(action)) {
            productPage = req.getRequestDispatcher("/nutrient/hashtag");
            productPage.forward(req, res);
        }
        if ("randomAssign".equals(action)) {
            Integer mealAmount = Integer.valueOf(req.getParameter("mealAmount"));
            String repeat = req.getParameter("repeat");
            List<MealVO> allMeals = mealSV.getAll();
            List<MealVO> meals = null;
            if ("yes".equals(repeat)) {
                meals = new ArrayList<>();
                for (int i = 0; i < mealAmount; i++) {
                    int randomNum = (int) (Math.random() * 13);
                    MealVO meal = allMeals.get(randomNum);
                    meal.setShowPhoto("data:image/png;base64," + Base64.getEncoder().encodeToString(meal.getMealPhoto()));
                    meals.add(meal);
                }
            }
            if ("no".equals(repeat)) {
              outter:for (int i = 0; i < mealAmount; i++) {
                    int randomNum = (int) (Math.random() * 13);
                    MealVO meal = allMeals.get(randomNum);
                    if (meals == null) {
                        meals = new ArrayList<>();
                    }
                    for (int j = 0; j <= i - 1; j++) {
                        if (meals.get(j).getMealNo().equals(meal.getMealNo())) {
                            i--;
                            continue outter;
                        }
                    }
                  meal.setShowPhoto("data:image/png;base64," + Base64.getEncoder().encodeToString(meal.getMealPhoto()));
                  meals.add(meal);

                }
            }
            Integer totalPrice=0;
            for (MealVO meal : meals) {
                totalPrice+=meal.getMealPrice();
            }
            req.setAttribute("meals", meals);
            req.setAttribute("totalPrice",totalPrice);
            productPage = req.getRequestDispatcher("/meal/RandomProdPage.jsp");
            productPage.forward(req, res);
        }

    }
}
