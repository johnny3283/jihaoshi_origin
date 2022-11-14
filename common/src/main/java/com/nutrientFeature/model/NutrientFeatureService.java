package com.nutrientFeature.model;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NutrientFeatureService {
    private NutrientFeatureDAO dao;

    public NutrientFeatureService(NutrientFeatureDAO dao) {
        this.dao=dao;
    }

    public void save(NutrientFeatureVO nutrientFeature) {
        dao.save(nutrientFeature);
    }

    public List<NutrientFeatureVO> listFeature() {
        return dao.findAll();

    }

}
