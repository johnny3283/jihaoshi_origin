package com.meal.model;

import java.util.List;

public interface MealDAO {
    void insert(MealVO meal);
    void update(MealVO meal);
    Integer launchOn(Integer mealNo);
    Integer launchOff(Integer mealNo);
    MealVO findBymealName(String mealName);
    MealVO findWithoutAllergen(String mealAllergen);
    MealVO findByLastUpdate();

    MealVO findByMealNo(Integer mealNo);
    List<MealVO> getAll();
}
