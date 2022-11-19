package com.mealCollectionDetail.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionDetailService {

    @Autowired
    private CollectionDetailDAO dao;

    //    public CollectionDetailService(CollectionDetailDAO dao) {
//        this.dao = dao;
//    }
    public CollectionDetailVO findByMemberNoAndMealNo(Integer memberNo,Integer mealNo) {
        return dao.findByMemberNoAndAndMealNo(memberNo, mealNo);
    }
    public void insert(CollectionDetailVO collectionDetail) {
        dao.save(collectionDetail);
    }

    public List<CollectionDetailVO> listAll(Integer memberNo) {
        return dao.findByMemberNo(memberNo);
    }

    public void deletById(Integer detailId) {
        dao.deleteById(detailId);
    }
}
