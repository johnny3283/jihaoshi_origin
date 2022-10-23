package com.meal.model;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;

public class MealVO implements Serializable {
    private Integer mealNo;
    private String mealName;
    private String mealContent;
    private Integer mealCal;
    private String mealAllergen;
    private Integer mealPrice;
    private InputStream mealPhoto;
    private Integer saleVolume;
    private String mealRecipe;
    private Integer commentPeople;
    private Integer commentScore;
    private Integer launch;
    private Date updateTime;

    public MealVO() {
    }

     public MealVO (String mealName, String mealContent, Integer mealCal, String mealAllergen, Integer mealPrice, InputStream mealPhoto, String mealRecipe, Integer launch) {
        this.mealName = mealName;
        this.mealContent = mealContent;
        this.mealCal = mealCal;
        this.mealAllergen = mealAllergen;
        this.mealPrice = mealPrice;
        this.mealPhoto = mealPhoto;
        this.mealRecipe = mealRecipe;
        this.launch = launch;
    }

    public MealVO(Integer mealNo, String mealName, String mealContent, Integer mealCal, String mealAllergen, Integer mealPrice, InputStream mealPhoto, Integer saleVolume, String mealRecipe, Integer commentPeople, Integer commentScore, Integer launch, Date updateTime) {
        this.mealNo = mealNo;
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

    public MealVO(Integer mealNo, String mealName, String mealContent, Integer mealCal, String mealAllergen, Integer mealPrice, InputStream mealPhoto, String mealRecipe, Integer launch) {
        this.mealNo = mealNo;
        this.mealName = mealName;
        this.mealContent = mealContent;
        this.mealCal = mealCal;
        this.mealAllergen = mealAllergen;
        this.mealPrice = mealPrice;
        this.mealPhoto = mealPhoto;
        this.mealRecipe = mealRecipe;
        this.launch = launch;
    }

    public Integer getMealNo() {
        return mealNo;
    }

    public void setMealNo(Integer mealNo) {
        this.mealNo = mealNo;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealContent() {
        return mealContent;
    }

    public void setMealContent(String mealContent) {
        this.mealContent = mealContent;
    }

    public Integer getMealCal() {
        return mealCal;
    }

    public void setMealCal(Integer mealCal) {
        this.mealCal = mealCal;
    }

    public String getMealAllergen() {
        return mealAllergen;
    }

    public void setMealAllergen(String mealAllergen) {
        this.mealAllergen = mealAllergen;
    }

    public Integer getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(Integer mealPrice) {
        this.mealPrice = mealPrice;
    }

    public InputStream getMealPhoto() {
        return mealPhoto;
    }

    public void setMealPhoto(InputStream mealPhoto) {
        this.mealPhoto = mealPhoto;
    }

    public Integer getSaleVolume() {
        return saleVolume;
    }

    public void setSaleVolume(Integer saleVolume) {
        this.saleVolume = saleVolume;
    }

    public String getMealRecipe() {
        return mealRecipe;
    }

    public void setMealRecipe(String mealRecipe) {
        this.mealRecipe = mealRecipe;
    }

    public Integer getCommentPeople() {
        return commentPeople;
    }

    public void setCommentPeople(Integer commentPeople) {
        this.commentPeople = commentPeople;
    }

    public Integer getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(Integer commentScore) {
        this.commentScore = commentScore;
    }

    public Integer getLaunch() {
        return launch;
    }

    public void setLaunch(Integer launch) {
        this.launch = launch;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
