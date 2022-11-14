package com.nutrient.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nutrientFeatureDetail.model.NutrientFeatureDetailService;

@Controller
@RequestMapping("/nutrient/detailSave")
public class NutrientFeatureDetailController {

    private NutrientFeatureDetailService nutrientFeatureDetailSV;

    public NutrientFeatureDetailController(NutrientFeatureDetailService nutrientFeatureDetailSV) {
        this.nutrientFeatureDetailSV = nutrientFeatureDetailSV;
    }

    @PostMapping
    public String saveAll(@Param("mealNo") Integer mealNo, HttpServletRequest req) {
        String[] featureNosString = req.getParameterValues("featureNo");
        List<Integer> featureNos=new ArrayList<>();
        for (int i = 0; i < featureNosString.length; i++) {
            featureNos.add(Integer.valueOf(featureNosString[i]));
        }
        nutrientFeatureDetailSV.saveAll(mealNo,featureNos);

        return "";

    }

}
