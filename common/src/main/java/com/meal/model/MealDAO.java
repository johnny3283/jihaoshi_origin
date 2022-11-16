package com.meal.model;

import java.sql.Connection;
import java.util.List;

public interface MealDAO {
    MealVO insert(MealVO meal);

    void update(MealVO meal);

    void updateWithoutPhoto(MealVO meal);

    boolean launchSwitch(Integer mealNo, Integer launch);

    List<MealVO> findByNameKeyword(String nameKeyword);


    MealVO findWithoutAllergen(String mealAllergen);

    MealVO findByMealNo(Integer mealNo);

    List<MealVO> getAll();

    List<MealVO> getAllLaunch();


    MealVO findByMealNo(Integer mealNot, Connection conn);
}
