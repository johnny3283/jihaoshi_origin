package com.online_course_order.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.online_course_order_detail.model.OnlineCourseOrderDetailVO;

public class OnlineCourseOrderVO implements Serializable {
	private String orderNo;
	private Integer memberNo;
	private Timestamp orderTime;
	private Integer orderVolume;
	private Integer orderPrice;
	private Integer orderStatus;
	private String  memberAccount;
	private List<OnlineCourseOrderDetailVO> orderDetailList;
	private String tradeNo;

	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	public Integer getOrderVolume() {
		return orderVolume;
	}
	public void setOrderVolume(Integer order_volume) {
		this.orderVolume = order_volume;
	}
	public Integer getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Integer orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<OnlineCourseOrderDetailVO> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OnlineCourseOrderDetailVO> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
}
