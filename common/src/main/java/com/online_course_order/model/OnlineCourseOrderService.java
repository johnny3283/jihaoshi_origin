package com.online_course_order.model;

import java.util.List;

import com.authority.model.AuthorityDAO_interface;
import com.authority.model.AuthorityJDBCDAO;
import com.authority.model.AuthorityVO;

public class OnlineCourseOrderService {
	private OnlineCourseOrderDAO_interface dao;

	public OnlineCourseOrderService() {
		dao = new OnlineCourseOrderJDBCDAO();
	}

	public void save(OnlineCourseOrderVO onlineCourseOrderVO) {
		dao.insert(onlineCourseOrderVO);
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
	public OnlineCourseOrderVO getOneOnlineCourseOrderMem(Integer memberNo) {
		return dao.findByMemNo(memberNo);
	}

	public List<OnlineCourseOrderVO> getAll() {
		return dao.getAll();
	}

}
