package com.online_course_order_detail.model;

import java.util.List;

public class OnlineCourseOrderDetailService {
	private OnlineCourseOrderDetailDAO_interface dao;

	public OnlineCourseOrderDetailService() {
		dao = new OnlineCourseOrderDetailJDBCDAO();
	}

	public void save(OnlineCourseOrderDetailVO onlineCourseOrderDetailVO) {
		dao.insert(onlineCourseOrderDetailVO);
	}

	public void updateAuthorityDetail(OnlineCourseOrderDetailVO onlineCourseOrderDetailVO) {
		dao.update(onlineCourseOrderDetailVO);

	}

	public void deleteAuthorityDetail(OnlineCourseOrderDetailVO onlineCourseOrderDetailVO) {
		dao.delete(onlineCourseOrderDetailVO);
	}

	public OnlineCourseOrderDetailVO getoneOnlineCourseOrderDetail(Integer orderNo, Integer courseNo) {
		return dao.findByPrimaryKey(orderNo, courseNo);
	}

	public List<OnlineCourseOrderDetailVO> getAll() {
		return dao.getAll();
	}
}
