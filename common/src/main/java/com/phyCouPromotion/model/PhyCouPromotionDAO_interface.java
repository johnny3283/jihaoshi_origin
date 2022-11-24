package com.phyCouPromotion.model;

import java.util.*;

import com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO;

public interface PhyCouPromotionDAO_interface {
          public Integer insert(PhyCouPromotionVO phyCouPromotionVO);
          public void update(PhyCouPromotionVO phyCouPromotionVO);
          public void delete(Integer project_no);
          public PhyCouPromotionVO findByPrimaryKey(Integer project_no);
          public Set<PhyCouPromotionDetailVO> getCousByProject_no(Integer project_no);
          public List<PhyCouPromotionVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//          public List<PhyCouPromotionVO> getAll(Map<String, String[]> map); 
}
