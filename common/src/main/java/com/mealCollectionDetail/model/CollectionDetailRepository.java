package com.mealCollectionDetail.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionDetailRepository extends JpaRepository<CollectionDetailVO,Integer> {


    CollectionDetailVO findByMemberNoAndAndMealNo(Integer memberNo,Integer mealNo);

    List<CollectionDetailVO> findByMemberNo(Integer memberNo);

}
