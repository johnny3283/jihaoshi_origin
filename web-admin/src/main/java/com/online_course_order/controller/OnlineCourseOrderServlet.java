package com.online_course_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.online_course_order.model.OnlineCourseOrderService;
import com.online_course_order.model.OnlineCourseOrderVO;

@WebServlet("/onlineCourseOrderServlet")
public class OnlineCourseOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		OnlineCourseOrderService service = new OnlineCourseOrderService();
		Gson gson = new Gson();
		String action = req.getParameter("action");
		
		switch (action) {
			case "getOneMemNo_For_Display":
				String memberNoStr = req.getParameter("memberNo");
				PrintWriter out = res.getWriter();
				if (memberNoStr != null && !memberNoStr.isEmpty()) {
					Integer memberNo = Integer.parseInt(memberNoStr);
					List<OnlineCourseOrderVO> list = service.getOnlineCourseOrderbyMem(memberNo);
					out.write(gson.toJson(list));
				}
				break;
			case "update":
				JsonObject respBody = new JsonObject();
				try {
					JsonObject reqBody = new Gson().fromJson(req.getReader(), JsonObject.class);
					OnlineCourseOrderVO orderVo = new OnlineCourseOrderVO();
					orderVo.setOrderNo(reqBody.get("orderNo").getAsString());
					orderVo.setOrderStatus(1);
					service.updateOnlineCourseOrder(orderVo);
					respBody = new JsonObject();
					respBody.addProperty("successful", true);
				} catch (Exception e) {
					e.printStackTrace();
					respBody.addProperty("successful", false);
				}
				res.setContentType("application/json; charset=UTF-8");
				PrintWriter writer = res.getWriter();
				writer.write(respBody.toString());	
				break;
			case "orderlist":
				List<OnlineCourseOrderVO> orderList = service.getAll();
				req.setAttribute("list", orderList);
				req.getRequestDispatcher("/onlineCourseOrder/ListAllOnlineCourseOrder.jsp").forward(req, res);
				break;
			case "searchOrderDetail":
				String orderNo = req.getParameter("orderNo");
				OnlineCourseOrderVO order = service.getOrderDetail(orderNo);
				req.setAttribute("order", order);
				req.getRequestDispatcher("/onlineCourseOrder/OnlineCourseOrderDetail.jsp").forward(req, res);
				break;
		}
	}
}
