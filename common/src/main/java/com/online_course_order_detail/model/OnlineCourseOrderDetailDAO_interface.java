package com.online_course_order_detail.model;

import java.sql.Connection;
import java.util.List;

import com.cart.model.CartCourseVO;

public interface OnlineCourseOrderDetailDAO_interface {
	public void insert(String orderNo, CartCourseVO prod, Connection conn);

//	public void update(OnlineCourseOrderDetailVO onlineCourseOrderDetailVO);
//
//	public void delete(OnlineCourseOrderDetailVO onlineCourseOrderDetailVO);

	public OnlineCourseOrderDetailVO findByPrimaryKey(Integer orderNo, Integer courseNo);

	public List<OnlineCourseOrderDetailVO> getAll();
}
