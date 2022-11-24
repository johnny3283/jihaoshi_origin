package com.onlinecoursecomment.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemberVO;
import com.onlinecoursecomment.model.OnlineCourseCommentService;
import com.onlinecoursecomment.model.OnlineCourseCommentVO;

@WebServlet("/MemberOnlineCourseCommentServlet")
public class MemberOnlineCourseCommentServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");
		
		if ("getMember_For_Display".equals(action)) {
			// 會員專區查看自己所有的線上課程評論
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			HttpSession session = req.getSession();
			MemberVO member=(MemberVO)session.getAttribute("member");
			Integer memberNo = member.getMemberNo();
			/*************************** 2.開始查詢資料 ****************************************/
			OnlineCourseCommentService onlineCourseCommentSvc = new OnlineCourseCommentService();
			List<OnlineCourseCommentVO> list = onlineCourseCommentSvc.getOnlineCommentsByMemberNo(memberNo);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("getOne_For_Display", list); // 資料庫取出的list物件,存入request
			String url = "/onlinecoursecomment/listOneMemberComments.jsp"; // 成功轉交
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("add".equals(action)) { // 來自add.jsp的請求
			// 會員新增線上課程評論
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/		
			HttpSession session = req.getSession();
			MemberVO member=(MemberVO)session.getAttribute("member");
			Integer memberNo = member.getMemberNo();
			
			Integer courseNo = Integer.valueOf(req.getParameter("courseNo"));
			
			String commentCentent = req.getParameter("commentContent");
			if (commentCentent == null || commentCentent.trim().length() == 0) {
				errorMsgs.add("請輸入評論內容");
			}

			String str = req.getParameter("commentScore");
			if (str == null || "0".equals(str)) {
				errorMsgs.add("請選擇評論分數");
			}
			Integer commentScore = 0;
			try {
				commentScore = Integer.valueOf(str);
			} catch (Exception e) {}
			
			OnlineCourseCommentVO onlineCourseCommentVO = new OnlineCourseCommentVO();
			onlineCourseCommentVO.setMemberNo(memberNo);
			onlineCourseCommentVO.setCourseNo(courseNo);
			onlineCourseCommentVO.setCommentContent(commentCentent);
			onlineCourseCommentVO.setCommentScore(commentScore);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("onlineCourseCommentVO", onlineCourseCommentVO); // 含有輸入格式錯誤的物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/onlinecoursecomment/addOnlineCourseComment.jsp");
				failureView.forward(req, res);
				return;
			}
//			/*************************** 2.開始新增資料 ***************************************/
//			OnlineCourseCommentService onlineCourseCommentSvc = new OnlineCourseCommentService();
//			onlineCourseCommentVO = onlineCourseCommentSvc.addOnlineCourseComment(memberNo,courseNo,commentCentent,commentScore);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/OnlineCourseCommentServlet?action=getMember_For_Display";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
			successView.forward(req, res);
		}
	}
}
