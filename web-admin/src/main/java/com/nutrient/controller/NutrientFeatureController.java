package com.nutrient.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nutrientFeature.model.NutrientFeatureService;
import com.nutrientFeature.model.NutrientFeatureVO;

@Controller
@RequestMapping("/nutrient")
public class NutrientFeatureController {
    private NutrientFeatureService nutrientFeatureSV;

    public NutrientFeatureController(NutrientFeatureService nutrientFeatureSV) {
        this.nutrientFeatureSV = nutrientFeatureSV;
    }

    @GetMapping("/insert")
    public String index(ModelMap model) {
        model.addAttribute("nutrientFeature", new NutrientFeatureVO());
        return "nutrient/NutrientFeatureInsert";
    }

    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute("nutrientFeature") NutrientFeatureVO nutrientFeature, BindingResult result) {

        if (result.hasErrors()) {
            return "nutrient/NutrientFeatureInsert";
        }
        nutrientFeatureSV.save(nutrientFeature);
        return "";
    }

    @PostMapping("/detailInsert")
    public String listAll(Model model) {
        List<NutrientFeatureVO> nutrientFeatures;
        nutrientFeatures=nutrientFeatureSV.listFeature();
        model.addAttribute("nutrientFeatures", nutrientFeatures);
        return "nutrient/DetailInsert";

    }
}
