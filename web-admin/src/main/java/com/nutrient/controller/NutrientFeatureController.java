package com.nutrient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nutrientFeature.model.NutrientFeatureDAO;
import com.nutrientFeature.model.NutrientFeatureVO;

@Controller
@RequestMapping("/nutrient/nutrientFeatureController")
public class NutrientFeatureController {
    private NutrientFeatureDAO dao;

    public NutrientFeatureController(NutrientFeatureDAO dao) {
        this.dao = dao;
    }

    @PostMapping
    public String insert() {
        NutrientFeatureVO nutrientFeatureVO;

        return "helloworld";
    }
    @GetMapping
    public String save() {

        System.out.println(dao.findById(1).get());

        return "helloworld";
    }
}
