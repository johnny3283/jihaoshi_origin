package com.meal.controller;

import com.meal.model.MealDAO;
import com.meal.model.MealDAOImpl;
import com.meal.model.MealVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/mealController")
@MultipartConfig(fileSizeThreshold = 0, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MealController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        Integer mealNo=null;
        String mealName= null;
        String mealContent= null;
        Integer mealCal = null;
        String mealAllergen= null;
        Integer mealPrice= null;
        InputStream mealPhoto= null;
        String mealRecipe= null;
        Integer launch= null;
        String action = req.getParameter("action");
        if ("insert".equals(action)) {
            List<String> errMsgs = new ArrayList<>();
            req.setAttribute("errMsgs", errMsgs);
            mealName = req.getParameter("mealName").trim();
            if (mealName == null || mealName.length() == 0) {
                errMsgs.add("菜單名稱不得空白");
            }
            mealContent = req.getParameter("mealContent").trim();
            if (mealContent == null || mealContent.length() == 0) {
                errMsgs.add("菜單內容不得空白");
            }
            try {
                mealCal = Integer.valueOf(req.getParameter("mealCal").trim());
                if (mealCal <= 0) {
                    errMsgs.add("熱量不得小於零");
                }
            } catch (Exception e) {
                errMsgs.add("熱量不得為空白或文字");
            }
            mealAllergen = req.getParameter("mealAllergen").trim();
            if (mealAllergen == null || mealAllergen.length() == 0) {
                errMsgs.add("若無過敏源，請填寫\"無\"");
            }
            try {
                mealPrice = Integer.valueOf(req.getParameter("maelPrice").trim());
                if (mealPrice <= 0) {
                    errMsgs.add("價錢不得小於零");
                }
            } catch (Exception e) {
                errMsgs.add("價錢不得為空白或文字");
            }
            mealPhoto = req.getPart("mealPhoto").getInputStream();
            mealRecipe = req.getParameter("mealRecipe").trim();
            launch = Integer.valueOf(req.getParameter("launch"));
            if (mealRecipe == null || mealContent.trim().length() == 0) {
                errMsgs.add("食譜不得空白");
            }
            MealVO meal = new MealVO(mealName, mealContent, mealCal, mealAllergen, mealPrice, mealPhoto, mealRecipe, launch);

            if (!errMsgs.isEmpty()) {
                req.setAttribute("MealVO", meal);
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/MealInsert.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }
            MealDAO dao = new MealDAOImpl();
            dao.insert(meal);
            MealVO lastMeal=dao.findByLastUpdate();
            if (lastMeal != null) {
                req.setAttribute("Meal",lastMeal);
                RequestDispatcher productPage=req.getRequestDispatcher("/ProductPage.jsp");
                productPage.forward(req,res);
            }
        }
        if ("update".equals(action)) {
            List<String> errMsgs = new ArrayList<>();
            req.setAttribute("errMsgs", errMsgs);
            mealNo = Integer.valueOf(req.getParameter("mealNo"));
            mealName = req.getParameter("mealName").trim();
            if (mealName == null || mealName.length() == 0) {
                errMsgs.add("菜單名稱不得空白");
            }
            mealContent = req.getParameter("mealContent").trim();
            if (mealContent == null || mealContent.length() == 0) {
                errMsgs.add("菜單內容不得空白");
            }
            try {
                mealCal = Integer.valueOf(req.getParameter("mealCal").trim());
                if (mealCal <= 0) {
                    errMsgs.add("熱量不得小於零");
                }
            } catch (Exception e) {
                errMsgs.add("熱量不得為空白或文字");
            }
            mealAllergen = req.getParameter("mealAllergen").trim();
            if (mealAllergen == null || mealAllergen.length() == 0) {
                errMsgs.add("若無過敏源，請填寫\"無\"");
            }
            try {
                mealPrice = Integer.valueOf(req.getParameter("maelPrice").trim());
                if (mealPrice <= 0) {
                    errMsgs.add("價錢不得小於零");
                }
            } catch (Exception e) {
                errMsgs.add("價錢不得為空白或文字");
            }
            mealPhoto = req.getPart("mealPhoto").getInputStream();
            mealRecipe = req.getParameter("mealRecipe").trim();
            launch = Integer.valueOf(req.getParameter("launch"));
            if (mealRecipe == null || mealContent.trim().length() == 0) {
                errMsgs.add("食譜不得空白");
            }
            MealVO meal = new MealVO(mealNo, mealName, mealContent, mealCal, mealAllergen, mealPrice, mealPhoto, mealRecipe, launch);

            if (!errMsgs.isEmpty()) {
                req.setAttribute("MealVO", meal);
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/MealUpdate.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }
            MealDAO dao = new MealDAOImpl();
            dao.update(meal);
            MealVO updatedMeal = dao.findByMealNo(mealNo);
            if (updatedMeal != null) {
                req.setAttribute("Meal", updatedMeal);
                RequestDispatcher productPage=req.getRequestDispatcher("/ProductPage.jsp");
                productPage.forward(req,res);
            }
        }

    }
}
