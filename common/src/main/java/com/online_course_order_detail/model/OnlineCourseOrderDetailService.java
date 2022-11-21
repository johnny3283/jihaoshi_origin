package com.online_course_order_detail.model;

import java.sql.Connection;
import java.util.List;

import com.cart.model.CartCourseVO;

public class OnlineCourseOrderDetailService {
    private OnlineCourseOrderDetailDAO_interface dao;

//    public OnlineCourseOrderDetailService() {
//        dao = new OnlineCourseOrderDetailJDBCDAO();
//    }

    public void save(String orderNo, CartCourseVO prod, Connection conn) {
        dao.insert(orderNo, prod, conn);
    }

//    public void updateAuthorityDetail(OnlineCourseOrderDetailVO onlineCourseOrderDetailVO) {
//        dao.update(onlineCourseOrderDetailVO);
//
//    }
//
//    public void deleteAuthorityDetail(OnlineCourseOrderDetailVO onlineCourseOrderDetailVO) {
//        dao.delete(onlineCourseOrderDetailVO);
//    }
//
//    public OnlineCourseOrderDetailVO getoneOnlineCourseOrderDetail(Integer orderNo, Integer courseNo) {
//        return dao.findByPrimaryKey(orderNo, courseNo);
//    }

    public List<OnlineCourseOrderDetailVO> getAll() {
        return dao.getAll();
    }
}
