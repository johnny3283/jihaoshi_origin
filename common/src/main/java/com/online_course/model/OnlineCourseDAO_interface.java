package com.online_course.model;

import java.util.List;

import com.onlinecoursecomment.model.OnlineCourseCommentVO;

public interface OnlineCourseDAO_interface {
	public void insert(OnlineCourseVO onlineCourseVO);

	public void update(OnlineCourseVO onlineCourseVO);

	public void delete(OnlineCourseVO onlineCourseVO);

	public OnlineCourseVO findByPrimaryKey(Integer courseNo);

	public List<OnlineCourseVO> getAll();
	
	public List<OnlineCourseVO> getStatusOnAll();

	public List<OnlineCourseVO> selectByCourseName(String courseName);
	
	boolean courseSwitch(Integer courseNo, Integer courseStatus);
	
	public List<OnlineCourseCommentVO> getOnlineCourseCommentsByOnlineCourseNo(Integer courseNo);

	public List<OnlineCourseVO> selectByMemId(Integer memId);
	
	public List<OnlineCourseVO> selectByCourseNameAndMemId(String courseName, Integer memId);
	
	public List<OnlineCourseVO> getFree();
}
