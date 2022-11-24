package com.online_course_order_detail.model;

import java.io.Serializable;

public class OnlineCourseOrderDetailVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderNo;
	private Integer courseNo;
	private Integer coursePrice;
	private byte[] orderPhoto;
	private String courseName;
	private String orderPhotoBaseStr64;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(Integer courseNo) {
		this.courseNo = courseNo;
	}

	public Integer getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(Integer coursePrice) {
		this.coursePrice = coursePrice;
	}

	public byte[] getOrderPhoto() {
		return orderPhoto;
	}

	public void setOrderPhoto(byte[] orderPhoto) {
		this.orderPhoto = orderPhoto;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getOrderPhotoBaseStr64() {
		return orderPhotoBaseStr64;
	}

	public void setOrderPhotoBaseStr64(String orderPhotoBaseStr64) {
		this.orderPhotoBaseStr64 = orderPhotoBaseStr64;
	}
}
