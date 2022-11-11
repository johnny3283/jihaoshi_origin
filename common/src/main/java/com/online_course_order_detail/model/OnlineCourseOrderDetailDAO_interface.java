package com.online_course_order_detail.model;

import java.util.List;

public interface OnlineCourseOrderDetailDAO_interface {
	public void insert(OnlineCourseOrderDetailVO onlineCourseOrderDetailVO);

	public void update(OnlineCourseOrderDetailVO onlineCourseOrderDetailVO);

	public void delete(OnlineCourseOrderDetailVO onlineCourseOrderDetailVO);

	public OnlineCourseOrderDetailVO findByPrimaryKey(Integer orderNo, Integer courseNo);

	public List<OnlineCourseOrderDetailVO> getAll();
}
