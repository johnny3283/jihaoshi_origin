package com.nutrientFeatureDetail.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NutrientFeatureDetailRepository extends JpaRepository<NutrientFeatureDetailVO,Integer> {
    @Query(name = "SELECT*FROM NUTRIENT_FEATURE_DETAIL WHERE MEAL_NO=?1")
    List<NutrientFeatureDetailVO> findAllByMealNo(Integer mealNo);
    List<NutrientFeatureDetailVO> findAllByFeatureName(String featureName);
}
