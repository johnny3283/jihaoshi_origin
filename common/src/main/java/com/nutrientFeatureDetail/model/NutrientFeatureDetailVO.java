package com.nutrientFeatureDetail.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NUTRIENT_FEATURE_DETAIL")
@Getter
@Setter
@NoArgsConstructor
public class NutrientFeatureDetailVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DETAIL_NO")
    private Integer detailNo;

    @Column(name = "MEAL_NO")
    private Integer mealNo;

    @Column(name = "FEATURE_NO")
    private Integer featureNo;

    @Column(name = "FEATURE_NAME")
    private String featureName;
}
