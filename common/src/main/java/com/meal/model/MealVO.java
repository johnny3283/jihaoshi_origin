package com.meal.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.nutrientFeatureDetail.model.NutrientFeatureDetailVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealVO implements Serializable {
    private Integer mealNo;
    private String mealName;
    private String mealContent;
    private Integer mealCal;
    private String mealAllergen;
    private Integer mealPrice;
    private byte[] mealPhoto;
    private String showPhoto;
    private Integer saleVolume;
    private String mealRecipe;
    private Integer commentPeople;
    private Integer commentScore;
    private Integer launch;
    private Timestamp updateTime;
    private Integer[] featureNo;
    private List<NutrientFeatureDetailVO> nutrientFeatureDetails;

    public MealVO() {
    }

     public MealVO (String mealName, String mealContent, Integer mealCal, String mealAllergen, Integer mealPrice, byte[] mealPhoto, String mealRecipe, Integer launch) {
        this.mealName = mealName;
        this.mealContent = mealContent;
        this.mealCal = mealCal;
        this.mealAllergen = mealAllergen;
        this.mealPrice = mealPrice;
        this.mealPhoto = mealPhoto;
        this.mealRecipe = mealRecipe;
        this.launch = launch;
    }

    public MealVO(Integer mealNo, String mealName, String mealContent, Integer mealCal, String mealAllergen, Integer mealPrice, byte[] mealPhoto, Integer saleVolume, String mealRecipe, Integer commentPeople, Integer commentScore, Integer launch, Timestamp updateTime) {
        this.mealNo=mealNo;
        this.mealName = mealName;
        this.mealContent = mealContent;
        this.mealCal = mealCal;
        this.mealAllergen = mealAllergen;
        this.mealPrice = mealPrice;
        this.mealPhoto = mealPhoto;
        this.saleVolume = saleVolume;
        this.mealRecipe = mealRecipe;
        this.commentPeople = commentPeople;
        this.commentScore = commentScore;
        this.launch = launch;
        this.updateTime = updateTime;
    }

    public MealVO(Integer mealNo, String mealName, String mealContent, Integer mealCal, String mealAllergen, Integer mealPrice, byte[] mealPhoto, String mealRecipe, Integer launch) {
		this.mealNo=mealNo;
        this.mealName = mealName;
        this.mealContent = mealContent;
        this.mealCal = mealCal;
        this.mealAllergen = mealAllergen;
        this.mealPrice = mealPrice;
        this.mealPhoto = mealPhoto;
        this.mealRecipe = mealRecipe;
        this.launch = launch;
    }

    public MealVO(Integer mealNo, String mealName, String mealContent, Integer mealPrice, Integer launch) {
		this.mealNo=mealNo;
        this.mealName = mealName;
        this.mealContent = mealContent;
        this.mealPrice = mealPrice;
        this.launch = launch;
    }

    public MealVO(Integer mealNo, String mealName, String mealContent, Integer mealCal, String mealAllergen, Integer mealPrice, String mealRecipe, Integer launch) {
		this.mealNo=mealNo;
        this.mealName = mealName;
        this.mealContent = mealContent;
        this.mealCal = mealCal;
        this.mealAllergen = mealAllergen;
        this.mealPrice = mealPrice;
        this.mealRecipe = mealRecipe;
        this.launch = launch;
    }


}
