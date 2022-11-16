package com.nutrientFeatureDetail.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.meal.model.MealDAO;
import com.meal.model.MealDAOImpl;
import com.meal.model.MealVO;
import com.nutrientFeature.model.NutrientFeatureDAO;

@Service

public class NutrientFeatureDetailService {

    private NutrientFeatureDetailDAO dao;
    private NutrientFeatureDAO dao2;

    public NutrientFeatureDetailService(NutrientFeatureDetailDAO dao, NutrientFeatureDAO dao2) {
        this.dao = dao;
        this.dao2 = dao2;
    }

    public void saveAll(Integer mealNo, List<Integer> featureNos) {
        List<NutrientFeatureDetailVO> nutrientFeatureDetails = new ArrayList<>();
        NutrientFeatureDetailVO nutrientFeatureDetail=null;
        for (int i=0;i<featureNos.size();i++) {
            nutrientFeatureDetail=new NutrientFeatureDetailVO();
            nutrientFeatureDetail.setMealNo(mealNo);
            nutrientFeatureDetail.setFeatureNo(featureNos.get(i));
            nutrientFeatureDetail.setFeatureName(dao2.findById(featureNos.get(i)).get().getFeatureName());
            nutrientFeatureDetails.add(nutrientFeatureDetail);
        }
        dao.saveAll(nutrientFeatureDetails);

    }

    public List<NutrientFeatureDetailVO> findAllByMealNo(Integer mealNo) {
        return dao.findAllByMealNo(mealNo);
    }
    public List<MealVO> findAllByFeatureName(String featureName) {
        MealDAO mealDAO=new MealDAOImpl();
        List<NutrientFeatureDetailVO> nutrientFeatureDetails = dao.findAllByFeatureName(featureName);
        List<MealVO> meals=new ArrayList<>();
        for (NutrientFeatureDetailVO nutrientFeatureDetail : nutrientFeatureDetails) {
            meals.add(mealDAO.findByMealNo(nutrientFeatureDetail.getMealNo()));
        }
        return meals;
    }
}
