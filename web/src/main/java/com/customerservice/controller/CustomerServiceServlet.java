package com.customerservice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemberVO;

@WebServlet("/customerServiceServlet")
public class CustomerServiceServlet extends HttpServlet {

	int count = 1;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");

		if ("getConnection".equals(action)) {

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			HttpSession session = req.getSession();
			String name = "";
			MemberVO member = (MemberVO) session.getAttribute("member");
			if (member != null) {
				name = (String) member.getMemberAccount();
			} else {
				name = "Visitor" + count;
				count++;
			}

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			session.setAttribute("username", name);
			String url = "/onlinecustomerservice/OnlineCustomerService.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
	}
}