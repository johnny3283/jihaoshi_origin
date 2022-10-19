package com.meal.controller;

import com.meal.model.MealDAO;
import com.meal.model.MealDAOImpl;
import com.meal.model.MealVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/mealController")
@MultipartConfig(fileSizeThreshold = 0, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MealController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        String mealName = req.getParameter("mealName");
        String mealContent = req.getParameter("mealContent");
        Integer mealCal = Integer.valueOf(req.getParameter("mealCal").trim());
        String mealAllergen = req.getParameter("mealAllergen");
        Integer mealPrice = Integer.valueOf(req.getParameter("maelPrice"));
        InputStream mealPhoto = req.getPart("mealPhoto").getInputStream();
        String mealRecipe = req.getParameter("mealRecipe");
        MealVO meal = new MealVO(mealName, mealContent, mealCal, mealAllergen, mealPrice, mealPhoto, mealRecipe);
        MealDAO dao = new MealDAOImpl();
        dao.insert(meal);

//        InputStream in = req.getPart("mealPhoto").getInputStream();

    }
}
