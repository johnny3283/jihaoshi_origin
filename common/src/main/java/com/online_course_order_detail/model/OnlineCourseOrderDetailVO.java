package com.online_course_order_detail.model;

import java.io.Serializable;

public class OnlineCourseOrderDetailVO implements Serializable {
	private Integer orderNo;
	private Integer courseNo;
	private Integer coursePrice;
	private byte[] orderPhoto;
	private String orderPhotoBaseStr64;
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
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
	public String getOrderPhotoBaseStr64() {
		return orderPhotoBaseStr64;
	}
	public void setOrderPhotoBaseStr64(String orderPhotoBaseStr64) {
		this.orderPhotoBaseStr64 = orderPhotoBaseStr64;
	}

	

}
