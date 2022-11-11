package com.nutrientFeature.model;

import java.io.Serializable;

public class NutrientFeatureVO implements Serializable {
    Integer featureNo;
    String featureName;

    public Integer getFeatureNo() {
        return featureNo;
    }

    public void setFeatureNo(Integer featureNo) {
        this.featureNo = featureNo;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }
}
