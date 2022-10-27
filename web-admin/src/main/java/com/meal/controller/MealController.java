package com.meal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

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
        if ("insert".equals(action)) {
            List<String> errMsgs = new ArrayList<>();
            req.setAttribute("errMsgs", errMsgs);
            mealName = req.getParameter("mealName");
            if (mealName == null || mealName.isBlank()) {

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
            mealPhoto = IOUtils.toByteArray(req.getPart("mealPhoto").getInputStream());
            mealRecipe = req.getParameter("mealRecipe").trim();
            launch = Integer.valueOf(req.getParameter("launch"));
            if (mealRecipe == null || mealContent.trim().length() == 0) {
                errMsgs.add("食譜不得空白");
            }
            MealVO meal = new MealVO(mealName, mealContent, mealCal, mealAllergen, mealPrice, mealPhoto, mealRecipe, launch);

            if (!errMsgs.isEmpty()) {
                req.setAttribute("MealVO", meal);
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/meal/MealInsert.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }
            MealService mealSV=new MealService();
            MealVO lastMeal =  mealSV.addMeal(mealName, mealContent, mealCal, mealAllergen, mealPrice, mealPhoto, mealRecipe, launch);

            if (lastMeal != null) {
//                String photo = Base64.getEncoder().encodeToString(mealPhoto);
//                lastMeal.setShowPhoto(photo);
                req.setAttribute("meal", lastMeal);
                RequestDispatcher productPage = req.getRequestDispatcher("/meal/ProductPage.jsp");
                productPage.forward(req, res);
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
            mealPhoto = IOUtils.toByteArray(req.getPart("mealPhoto").getInputStream());
            mealRecipe = req.getParameter("mealRecipe").trim();
            launch = Integer.valueOf(req.getParameter("launch"));
            if (mealRecipe == null || mealContent.trim().length() == 0) {
                errMsgs.add("食譜不得空白");
            }
            MealVO meal = new MealVO(mealNo, mealName, mealContent, mealCal, mealAllergen, mealPrice, mealPhoto, mealRecipe, launch);

            if (!errMsgs.isEmpty()) {
                req.setAttribute("MealVO", meal);
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/meal/MealUpdate.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }

            MealService meslSV=new MealService();
            String photoBase64= Base64.getEncoder().encodeToString(IOUtils.toByteArray(req.getPart("mealPhoto").getInputStream()));

            if (!photoBase64.isEmpty()) {
                meslSV.updateMeal(mealNo, mealName, mealContent, mealCal, mealAllergen, mealPrice, mealPhoto, mealRecipe, launch);
            }else {
                meslSV.updateMeal(mealNo, mealName, mealContent, mealCal, mealAllergen, mealPrice, mealRecipe, launch);
            }
            MealVO updatedMeal = meslSV.findByMealNo(mealNo);
            if (updatedMeal != null) {
                req.setAttribute("meal", updatedMeal);
                RequestDispatcher productPage = req.getRequestDispatcher("/meal/ProductPage.jsp");
                productPage.forward(req, res);
            }
        }
        if ("listAll".equals(action)) {
            MealService meslSV=new MealService();
            List<MealVO> meals=meslSV.getAll();
            if (meals != null) {
                req.setAttribute("lastAllMeal", meals);
                RequestDispatcher productPage = req.getRequestDispatcher("/meal/ListMealProduct.jsp");
                productPage.forward(req, res);
            }
        }
        if ("toUpdate".equals(action)) {
            mealNo = Integer.valueOf(req.getParameter("mealNo"));
            MealService mealSV =new MealService();
            MealVO meal = mealSV.findByMealNo(mealNo);
            if (meal != null) {
                req.setAttribute("meal", meal);
                RequestDispatcher productPage = req.getRequestDispatcher("/meal/MealUpdate.jsp");
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
                System.out.println("我有到這喔");
                productPage.forward(req, res);

            }
        }
        System.out.println(action);
        if ("launch".equals(action)) {
            mealNo = Integer.valueOf(req.getParameter("mealNo"));
            launch = Integer.valueOf(req.getParameter("launch"));
             MealService mealSV =new MealService();
            int launchOK=mealSV.launchSwitch(mealNo, launch);
            if (launchOK == 1) {
                System.out.println("我有執行喔");
                RequestDispatcher listMealProductPage = req.getRequestDispatcher("/meal/mealController?action=listAll");
                listMealProductPage.forward(req, res);
            }
        }
    }
}
