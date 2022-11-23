package com.online_course_order.model;

import java.util.Base64;
import java.util.List;

import com.authority.model.AuthorityDAO_interface;
import com.authority.model.AuthorityJDBCDAO;
import com.authority.model.AuthorityVO;

import com.online_course_order_detail.model.OnlineCourseOrderDetailVO;
import com.cart.model.CartCourseVO;


public class OnlineCourseOrderService {
	private OnlineCourseOrderDAO_interface dao;

	public OnlineCourseOrderService() {
		dao = new OnlineCourseOrderJDBCDAO();
	}



	public void orderInsert(String merchantTradeNo, Integer memberNo, Integer volume, Integer totalPrice, String tradeNo, List<CartCourseVO> cartCourses ) {
		OnlineCourseOrderVO order=new OnlineCourseOrderVO();
		order.setOrderNo(merchantTradeNo);
		order.setMemberNo(memberNo);
		order.setOrderVolume(volume);
		order.setOrderPrice(totalPrice);
		order.setTradeNo(tradeNo);
		dao.insert(order,cartCourses);
	}

	public void updateOnlineCourseOrder(OnlineCourseOrderVO onlineCourseOrderVO) {
		dao.update(onlineCourseOrderVO);

	}



	public List<OnlineCourseOrderVO> getOnlineCourseOrderbyMem(Integer memberNo) {
		return dao.findByMemNo(memberNo);
	}

	public List<OnlineCourseOrderVO> getAll() {
		return dao.getAll();
	}

	public OnlineCourseOrderVO getOrderDetail(String orderNo) {
		OnlineCourseOrderVO vo = dao.getOrderDetail(orderNo);
		if (vo != null && vo.getOrderDetailList() != null) {
			for (OnlineCourseOrderDetailVO detail : vo.getOrderDetailList()) {
				if (detail.getOrderPhoto() != null) {
					detail.setOrderPhotoBaseStr64(Base64.getEncoder().encodeToString(detail.getOrderPhoto()));
				}
			}
		}
		return vo;
	}

}
