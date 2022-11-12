package com.nutrient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nutrient")
public class NutrientFeatureController {
    @GetMapping("/hello")
    public String hello() {
        return "helloworld";
    }
}
