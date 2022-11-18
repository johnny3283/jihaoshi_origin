package com.cart.model;

import java.io.Serializable;

import com.online_course.model.OnlineCourseVO;



public class CartCourseVO implements Serializable {
    private Integer memberNo;
    private OnlineCourseVO course;
    
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public OnlineCourseVO getCourse() {
		return course;
	}
	public void setCourse(OnlineCourseVO course) {
		this.course = course;
	}   
}