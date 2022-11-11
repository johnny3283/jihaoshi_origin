package com.online_course_order.model;

import java.util.List;


public interface OnlineCourseOrderDAO_interface {
	public void insert(OnlineCourseOrderVO onlineCourseOrderVO);
	public void update(OnlineCourseOrderVO onlineCourseOrderVO);
	public void delete(OnlineCourseOrderVO onlineCourseOrderVO);
	public OnlineCourseOrderVO findByPrimaryKey(Integer orderNo);
	public OnlineCourseOrderVO findByMemNo(Integer memberNo);
	public List<OnlineCourseOrderVO> getAll();
	

}
