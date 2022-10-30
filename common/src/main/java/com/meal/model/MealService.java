package com.meal.model;

import java.util.List;

public class MealService {
    private MealDAO dao;

    public MealService() {
        dao=new MealDAOImpl();
    }

    public MealVO addMeal(String mealName,String mealContent,Integer mealCal,String mealAllergen,Integer mealPrice,byte[] mealPhoto,String mealRecipe,Integer launch) {
        MealVO meal = new MealVO(mealName, mealContent, mealCal, mealAllergen, mealPrice, mealPhoto, mealRecipe, launch);
        return dao.insert(meal);
    }

    public MealVO findByMealNo(Integer mealNo) {
        return dao.findByMealNo(mealNo);
    }
    public MealVO findByMealNo(Integer mealNo, Double quantity) {
        return dao.findByMealNo(mealNo,quantity);
    }
    public void updateMeal(Integer MealNo, String mealName,String mealContent,Integer mealCal,String mealAllergen,Integer mealPrice,byte[] mealPhoto,String mealRecipe,Integer launch) {
        MealVO meal = new MealVO(MealNo,mealName, mealContent, mealCal, mealAllergen, mealPrice, mealPhoto, mealRecipe, launch);
        dao.update(meal);
    }
    public void updateMeal(Integer MealNo, String mealName,String mealContent,Integer mealCal,String mealAllergen,Integer mealPrice, String mealRecipe,Integer launch) {
        MealVO meal = new MealVO(MealNo,mealName, mealContent, mealCal, mealAllergen, mealPrice, mealRecipe, launch);
        dao.updateWithoutPhoto(meal);
    }

    public List<MealVO> getAll() {
        return dao.getAll();
    }

    public boolean launchSwitch(Integer mealNo, Integer launch) {
        return dao.launchSwitch(mealNo,launch);
    }
}
