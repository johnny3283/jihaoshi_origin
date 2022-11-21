package com.online_course_order.model;

import java.util.List;

import com.cart.model.CartCourseVO;


public interface OnlineCourseOrderDAO_interface {
	public void insert(OnlineCourseOrderVO onlineCourseOrderVO, List<CartCourseVO> cartCourses);
	public void update(OnlineCourseOrderVO onlineCourseOrderVO);
	public void delete(OnlineCourseOrderVO onlineCourseOrderVO);
	public OnlineCourseOrderVO findByPrimaryKey(Integer orderNo);
	public List<OnlineCourseOrderVO> findByMemNo(Integer memberNo);
	public List<OnlineCourseOrderVO> getAll();
	

}
