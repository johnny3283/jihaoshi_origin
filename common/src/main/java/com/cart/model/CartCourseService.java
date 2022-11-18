package com.cart.model;

import java.util.ArrayList;
import java.util.List;

import com.online_course.model.OnlineCourseDAO_interface;
import com.online_course.model.OnlineCourseJDBCDAO;
import com.online_course.model.OnlineCourseVO;



public class CartCourseService {
     OnlineCourseDAO_interface dao=new OnlineCourseJDBCDAO();

    public List<CartCourseVO> getCartCourses(OnlineCourseVO course, List<CartCourseVO> cartProds) {
    	CartCourseVO prod=null;
        if (cartProds == null) {
            cartProds=new ArrayList<>();
            prod = new CartCourseVO();
            prod.setCourse(course);
            cartProds.add(prod);
            return cartProds;
        } else {
            for (int i = 0; i < cartProds.size(); i++) { // 尋找是否有同編號線上課程
                prod = cartProds.get(i);
                if (prod.getCourse().getCourseNo().equals(course.getCourseNo())) {
                	return cartProds;
                }
            }
        }
            // 查無同編號線上課程
            prod = new CartCourseVO();
            prod.setCourse(course);
            cartProds.add(prod);
            return cartProds;
    }

    public Integer calculateTotalPrice(List<CartCourseVO> cartCourses) {
        Integer totalCoursePrice = 0;
        for (CartCourseVO CartCourse : cartCourses) {
            totalCoursePrice += CartCourse.getCourse().getCoursePrice();
        }
        return totalCoursePrice;
    }

}
