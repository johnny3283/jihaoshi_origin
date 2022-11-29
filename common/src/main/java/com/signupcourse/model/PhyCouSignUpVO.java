package com.signupcourse.model;

import java.io.InputStream;
import java.sql.Date;

import com.course.model.PhyCouService;
import com.course.model.PhyCouVO;

public class PhyCouSignUpVO implements java.io.Serializable{
	

	@Override
	public String toString() {
		return "PhyCouSignUpVO [order_no=" + order_no + ", member_no=" + member_no + ", sign_up_date=" + sign_up_date
				+ ", update_time=" + update_time + ", order_price=" + order_price + ", order_status=" + order_status
				+ ", course_no=" + course_no + "]";
	}

	private Integer order_no;
	private Integer member_no;
	private Date sign_up_date;
	private Date update_time;
	private Integer order_price ;
	private Integer order_status;
	private Integer course_no;
	
	
	public Integer getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}
	public Integer getMember_no() {
		return member_no;
	}
	public void setMember_no(Integer member_no) {
		this.member_no = member_no;
	}
	public Date getSign_up_date() {
		return sign_up_date;
	}
	public void setSign_up_date(Date sign_up_date) {
		this.sign_up_date = sign_up_date;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Integer getOrder_price() {
		return order_price;
	}
	public void setOrder_price(Integer order_price) {
		this.order_price = order_price;
	}
	public Integer getOrder_status() {
		return order_status;
	}
	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
	public Integer getCourse_no() {
		return course_no;
	}
	public void setCourse_no(Integer course_no) {
		this.course_no = course_no;
	}

	public com.course.model.PhyCouVO getPhyCouVO() {
		com.course.model.PhyCouService phyCouSvc = new PhyCouService();
		com.course.model.PhyCouVO phyCouVO = phyCouSvc.getOneCou(course_no);
		return phyCouVO;
	}
																

}
