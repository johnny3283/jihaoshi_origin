package com.course.model;

import java.io.InputStream;
import java.sql.Date;

public class PhyCouVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer course_no;
	private String course_name;
	private Integer course_hr;
	private Integer course_price;
	private String course_teacher ;
	private Date course_date;
	private String course_location;
	private String course_info;
	private Integer course_status;
	private Date create_date;
	private Date update_time;
	private Date sign_up_start_day;
	private Date sign_up_end_day;
	private Integer max_sign_up_people;
	private Integer min_sign_up_people;
	private Integer current_sign_up_people;
	private byte[] pic;
	
	
	public Integer getCourse_no() {
		return course_no;
	}
	public void setCourse_no(Integer course_no) {
		this.course_no = course_no;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public Integer getCourse_hr() {
		return course_hr;
	}
	public void setCourse_hr(Integer course_hr) {
		this.course_hr = course_hr;
	}
	public Integer getCourse_price() {
		return course_price;
	}
	public void setCourse_price(Integer course_price) {
		this.course_price = course_price;
	}
	public String getCourse_teacher() {
		return course_teacher;
	}
	public void setCourse_teacher(String course_teacher) {
		this.course_teacher = course_teacher;
	}
	public Date getCourse_date() {
		return course_date;
	}
	public void setCourse_date(Date course_date) {
		this.course_date = course_date;
	}
	public String getCourse_location() {
		return course_location;
	}
	public void setCourse_location(String course_location) {
		this.course_location = course_location;
	}
	public String getCourse_info() {
		return course_info;
	}
	public void setCourse_info(String course_info) {
		this.course_info = course_info;
	}
	public Integer getCourse_status() {
		return course_status;
	}
	public void setCourse_status(Integer course_status) {
		this.course_status = course_status;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Date getSign_up_start_day() {
		return sign_up_start_day;
	}
	public void setSign_up_start_day(Date sign_up_start_day) {
		this.sign_up_start_day = sign_up_start_day;
	}
	public Date getSign_up_end_day() {
		return sign_up_end_day;
	}
	public void setSign_up_end_day(Date sign_up_end_day) {
		this.sign_up_end_day = sign_up_end_day;
	}
	public Integer getMax_sign_up_people() {
		return max_sign_up_people;
	}
	public void setMax_sign_up_people(Integer max_sign_up_people) {
		this.max_sign_up_people = max_sign_up_people;
	}
	public Integer getMin_sign_up_people() {
		return min_sign_up_people;
	}
	public void setMin_sign_up_people(Integer min_sign_up_people) {
		this.min_sign_up_people = min_sign_up_people;
	}
	public Integer getCurrent_sign_up_people() {
		return current_sign_up_people;
	}
	public void setCurrent_sign_up_people(Integer current_sign_up_people) {
		this.current_sign_up_people = current_sign_up_people;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
	
		
	
	
																

}
