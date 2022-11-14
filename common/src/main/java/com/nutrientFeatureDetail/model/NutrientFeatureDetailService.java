package com.nutrientFeatureDetail.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NutrientFeatureDetailService {

    private NutrientFeatureDetailDAO dao;

    public NutrientFeatureDetailService(NutrientFeatureDetailDAO dao) {
        this.dao = dao;
    }

    public void saveAll(Integer mealNo, List<Integer> featureNos) {
        List<NutrientFeatureDetailVO> nutrientFeatureDetails = new ArrayList<>();
        NutrientFeatureDetailVO nutrientFeatureDetail=null;
        for (Integer featureNo :featureNos) {
            nutrientFeatureDetail=new NutrientFeatureDetailVO();
            nutrientFeatureDetail.setMealNo(mealNo);
            nutrientFeatureDetail.setFeatureNo(featureNo);
            nutrientFeatureDetails.add(nutrientFeatureDetail);
        }
        dao.saveAll(nutrientFeatureDetails);

    }
}
