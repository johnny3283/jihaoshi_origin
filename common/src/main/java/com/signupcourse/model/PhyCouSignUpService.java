package com.signupcourse.model;

import java.util.List;

import com.course.model.PhyCouVO;


public class PhyCouSignUpService {


	private PhyCouSignUpDAO_interface dao;

	public PhyCouSignUpService() {
		dao = new PhyCouSignUpDAO();
	}


	public PhyCouSignUpVO signup(Integer member_no, Integer course_no, Integer order_price, Integer signUpNum) {
		
		PhyCouSignUpVO phyCouSignUpVO = new PhyCouSignUpVO();
				
		phyCouSignUpVO.setMember_no(member_no);
		phyCouSignUpVO.setCourse_no(course_no);
		phyCouSignUpVO.setOrder_price(order_price);				
		int order_no = (int)dao.insert(phyCouSignUpVO, signUpNum);		
		phyCouSignUpVO.setOrder_no(order_no);	
		
		
		return phyCouSignUpVO;
	}
	

	public List<PhyCouSignUpVO> getAll() {
		return dao.getAll();
	}
	
    public List<PhyCouSignUpVO> getAllSignUpCou(Integer member_no) {
	    return dao.findByMemberNo(member_no);
	}
	
    public void deleteCou(Integer order_no, Integer signUpNum, Integer course_no) {
    	dao.delete(order_no, signUpNum, course_no);
    }

}
