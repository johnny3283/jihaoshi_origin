package com.signupcourse.model;

import java.util.*;


public interface PhyCouSignUpDAO_interface {
	
    public long insert(PhyCouSignUpVO phyCouSignUpVO, Integer signUpNum);
    public List<PhyCouSignUpVO> findByMemberNo(Integer member_no);
//    public void update(PhyCouVO phyCouVO);
    public void delete(Integer order_no, Integer signUpNum, Integer course_no);
//    public PhyCouVO findByPrimaryKey(Integer course_no);
    public List<PhyCouSignUpVO> getAll();
    public PhyCouSignUpVO findByOrderNo(Integer order_no);

//    public List<PhyCouSignUpVO> getAll(Map<String, String[]> map); 
//	public List<PhyCouVO> getAll(Map<String, String[]> map);

}
