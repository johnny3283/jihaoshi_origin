package com.onlinecourseorder.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemberVO;
import com.online_course_order.model.OnlineCourseOrderService;
import com.online_course_order.model.OnlineCourseOrderVO;
import com.online_course_order_detail.model.OnlineCourseOrderDetailVO;
import com.onlinecoursecomment.model.OnlineCourseCommentService;
import com.onlinecoursecomment.model.OnlineCourseCommentVO;


@WebServlet("/onlineCourseOrderServlet")
public class OnlineCourseOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OnlineCourseOrderService courseorderSV = new OnlineCourseOrderService();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		Integer memberNo = member.getMemberNo();
		String action = req.getParameter("action");
	
		

		if ("orderlist".equals(action)) {
			List<OnlineCourseOrderVO> list = courseorderSV.getOnlineCourseOrderbyMem(memberNo);
			req.setAttribute("list", list);
			req.getRequestDispatcher("/onlineCourseOrder/ListMemOnlineCourseOrder.jsp").forward(req, res);

		}
	
	
	 if("searchOrderDetail".equals(action)) {
		String orderNo = req.getParameter("orderNo");
		OnlineCourseOrderVO order = courseorderSV.getOrderDetail(orderNo);
		
//		// 取得單筆訂單內的所有課程編號
//		List<Integer> courseNo= new ArrayList<>();
//		List<OnlineCourseOrderDetailVO> detail=order.getOrderDetailList();
//		for(OnlineCourseOrderDetailVO all : detail) {
//			courseNo.add(all.getCourseNo());
//		}
//		// 取得會員評論過的所有課程編號
//		OnlineCourseCommentService commentSV= new OnlineCourseCommentService();
//		List<OnlineCourseCommentVO> comment= commentSV.getOnlineCommentsByMemberNo(memberNo);
//		List<Integer> memComment= new ArrayList<>();
//		for(OnlineCourseCommentVO all : comment) {
//			memComment.add(all.getCourseNo());
//		}
//		List<Integer> noComment= new ArrayList<>();
				
		req.setAttribute("order", order);
		req.getRequestDispatcher("/onlineCourseOrder/OnlineCourseOrderDetail.jsp").forward(req, res);
	}
 }
}
