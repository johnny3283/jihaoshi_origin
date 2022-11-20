package com.nutrient.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meal.model.MealVO;
import com.nutrientFeatureDetail.model.NutrientFeatureDetailService;

@Component
@Controller
@Service
@Repository
@RequestMapping("/nutrient")
public class NutrientFeatureDetailController {

    private NutrientFeatureDetailService nutrientFeatureDetailSV;

    public NutrientFeatureDetailController(NutrientFeatureDetailService nutrientFeatureDetailSV) {
        this.nutrientFeatureDetailSV = nutrientFeatureDetailSV;
    }

    @PostMapping("/detailSave")
    public String saveAll(@Param("mealNo") Integer mealNo, HttpServletRequest req) {
        String[] featureNosString = req.getParameterValues("featureNo");
        List<Integer> featureNos = new ArrayList<>();

        for (int i = 0; i < featureNosString.length; i++) {
            featureNos.add(Integer.valueOf(featureNosString[i]));
        }
        nutrientFeatureDetailSV.saveAll(mealNo, featureNos);

        return "meal/mealController?action=findByprod&mealNo="+mealNo;

    }

    @RequestMapping("/prodDetailList")
    public String prodDetailList(HttpServletRequest req, HttpServletResponse res) {
        Integer mealNo = Integer.valueOf(req.getParameter("mealNo"));
        MealVO meal = (MealVO) req.getAttribute("meal");
        meal.setNutrientFeatureDetails(nutrientFeatureDetailSV.findAllByMealNo(mealNo));
        return "meal/ProductPage.jsp";
    }
    @RequestMapping("/aLLProdDetailList")
    public String aLLProdDetailList(HttpServletRequest req, HttpServletResponse res) {
        List<MealVO> meals=(List<MealVO>) req.getAttribute("meals");
        for (int i = 0; i < meals.size(); i++) {
            meals.get(i).setNutrientFeatureDetails(nutrientFeatureDetailSV.findAllByMealNo(meals.get(i).getMealNo()));
        }
        return "meal/ListMealProduct.jsp";
    }
    @RequestMapping("/hashtag")
    public String findByHashtag(Model model, @Param("featureName") String featureName) {
        List<MealVO> meals = new ArrayList<>();
        meals = nutrientFeatureDetailSV.findAllByFeatureName(featureName);
        for (MealVO meal : meals) {
            meal.setShowPhoto("data:image/png;base64,"+ Base64.getEncoder().encodeToString(meal.getMealPhoto()));
        }
        model.addAttribute("meals", meals);
        return "nutrient/aLLProdDetailList";
    }
}
