package com.nutrientFeature.model;

import java.util.List;

public interface NutrientFeatureDAO {
    void insert();

    void delete();

    NutrientFeatureVO findByPk();

    List<NutrientFeatureVO> getAll();
}
