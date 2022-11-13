package com.nutrientFeature.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "NUTRIENT_FEATURE")
@Getter
@Setter
//@NoArgsConstructor
public class NutrientFeatureVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEATURE_NO")
    Integer featureNo;
    @Column(name = "FEATURE_NAME")
    String featureName;

}
