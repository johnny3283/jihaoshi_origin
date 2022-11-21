package com.online_course_order.model;

import java.util.List;

import com.authority.model.AuthorityDAO_interface;
import com.authority.model.AuthorityJDBCDAO;
import com.authority.model.AuthorityVO;
import com.cart.model.CartCourseVO;
import com.cart.model.CartProdVO;
import com.order.model.OrderVO;

public class OnlineCourseOrderService {
	private OnlineCourseOrderDAO_interface dao;

	public OnlineCourseOrderService() {
		dao = new OnlineCourseOrderJDBCDAO();
	}

	public void orderInsert(String merchantTradeNo, Integer memberNo, Integer totalPrice, String tradeNo, List<CartCourseVO> cartCourses ) {
        OnlineCourseOrderVO order=new OnlineCourseOrderVO();
        order.setOrderNo(merchantTradeNo);
        order.setMemberNo(memberNo);
        order.setOrderPrice(totalPrice);
        order.setTradeNo(tradeNo);
        dao.insert(order,cartCourses);
    }

	public void updateOnlineCourseOrder(OnlineCourseOrderVO onlineCourseOrderVO) {
		dao.update(onlineCourseOrderVO);

	}

	public void deleteOnlineCourseOrder(OnlineCourseOrderVO onlineCourseOrderVO) {
		dao.delete(onlineCourseOrderVO);
	}

	public OnlineCourseOrderVO getOneOnlineCourseOrder(Integer orderNo) {
		return dao.findByPrimaryKey(orderNo);
	}
	public List<OnlineCourseOrderVO> getOnlineCourseOrderbyMem(Integer memberNo) {
		return dao.findByMemNo(memberNo);
	}

	public List<OnlineCourseOrderVO> getAll() {
		return dao.getAll();
	}

}
