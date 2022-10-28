package com.meal.model;

import java.util.List;

public interface MealDAO {
    MealVO insert(MealVO meal);

    void update(MealVO meal);

    void updateWithoutPhoto(MealVO meal);

    boolean launchSwitch(Integer mealNo, Integer launch);

    MealVO findByMealName(String mealName);

    MealVO findWithoutAllergen(String mealAllergen);

    MealVO findByMealNo(Integer mealNo);

    List<MealVO> getAll();
}
