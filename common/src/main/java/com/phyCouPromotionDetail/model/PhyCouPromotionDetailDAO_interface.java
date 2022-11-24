package com.phyCouPromotionDetail.model;

import java.util.*;

public interface PhyCouPromotionDetailDAO_interface {
          public void insert(PhyCouPromotionDetailVO phyCouPromotionDetailVO);
          public void update(PhyCouPromotionDetailVO phyCouPromotionDetailVO);
          public void deleteOnePro(Integer project_no);
          public void deleteOneCou(Integer project_no, Integer course_no);
          public List<PhyCouPromotionDetailVO> findByPrimaryKey(Integer project_no, Integer course_no);
          public List<PhyCouPromotionDetailVO> getAll();
          
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//          public List<PhyCouPromotionDetailVO> getAll(Map<String, String[]> map); 
}
