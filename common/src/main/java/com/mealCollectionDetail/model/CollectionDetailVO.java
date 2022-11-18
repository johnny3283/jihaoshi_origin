package com.mealCollectionDetail.model;

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
@Table(name = "MEAL_COLLECTION_DETAIL")
@Getter
@Setter
public class CollectionDetailVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DETAIL_NO")
    private Integer detailNo;

    @Column(name = "MEMBER_NO")
    private Integer memberNo;

    @Column(name = "MEAL_NO")
    private Integer mealNo;
}
