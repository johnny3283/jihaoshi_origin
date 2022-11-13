package com.faq.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faq.model.*;

@WebServlet("/faqservlet")
public class FAQServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");

		if ("add".equals(action)) { // 來自addFAQ.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String faqQue = req.getParameter("faqQue");
			if (faqQue == null || faqQue.trim().length() == 0) {
				errorMsgs.add("請輸入FAQ問題");
			}

			String faqAns = req.getParameter("faqAns");
			if (faqAns == null || faqAns.trim().length() == 0) {
				errorMsgs.add("請輸入FAQ答案");
			}

			String faqClass = req.getParameter("faqClass");
			if (faqClass == null) {
				errorMsgs.add("請選擇FAQ類別");
			}

			FAQVO faqVO = new FAQVO();
			faqVO.setFaqQue(faqQue);
			faqVO.setFaqAns(faqAns);
			faqVO.setFaqClass(faqClass);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("faqVO", faqVO); // 含有輸入格式錯誤的faqVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/faq/addFAQ.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			FAQService faqSvc = new FAQService();
			faqVO = faqSvc.addFAQ(faqQue, faqAns, faqClass);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/faq/indexFAQ.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}

		if ("delete".equals(action)) { // 來自listAllFAQ.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer faqNo = Integer.valueOf(req.getParameter("faqNo"));

			/*************************** 2.開始刪除資料 ***************************************/
			FAQService faqSvc = new FAQService();
			faqSvc.deleteFAQ(faqNo);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/faq/indexFAQ.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllFAQ.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer faqNo = Integer.valueOf(req.getParameter("faqNo"));

			/*************************** 2.開始查詢資料 ****************************************/
			FAQService faqSvc = new FAQService();
			FAQVO faqVO = faqSvc.getOneFAQ(faqNo);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("faqVO", faqVO); // 資料庫取出的faqVO物件,存入req
			String url = "/faq/updateFAQ.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_faq_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_faq_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer faqNo = Integer.valueOf(req.getParameter("faqNo").trim());

			String faqQue = req.getParameter("faqQue");
			if (faqQue == null || faqQue.trim().length() == 0) {
				errorMsgs.add("請輸入FAQ問題");
			}

			String faqAns = req.getParameter("faqAns");
			if (faqAns == null || faqAns.trim().length() == 0) {
				errorMsgs.add("請輸入FAQ答案");
			}

			String faqClass = req.getParameter("faqClass");

			FAQVO faqVO = new FAQVO();
			faqVO.setFaqNo(faqNo);
			faqVO.setFaqQue(faqQue);
			faqVO.setFaqAns(faqAns);
			faqVO.setFaqClass(faqClass);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("faqVO", faqVO); // 含有輸入格式錯誤的faqVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/faq/updateFAQ.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			FAQService faqSvc = new FAQService();
			faqVO = faqSvc.updateFAQ(faqNo, faqQue, faqAns, faqClass);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("faqVO", faqVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url ="/faqservlet?action=getAll";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFAQ.jsp
			successView.forward(req, res);
		}

		if ("getClass_For_Display".equals(action)) { // 來自select_page.jsp的請求

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			String faqClass = req.getParameter("faqClass");

			/*************************** 2.開始查詢資料 *****************************************/
			FAQService faqSvc = new FAQService();
			List<FAQVO> list = faqSvc.getClassFAQ(faqClass);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("lists", list); // 資料庫取出的物件,存入req
			String url = "/faq/listClassFAQ.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listClassFAQ.jsp
			successView.forward(req, res);
		}

		if ("getAll".equals(action)) {

			/*************************** 2.開始查詢資料 *****************************************/
			FAQService faqSvc = new FAQService();
			List<FAQVO> list = faqSvc.getAll();
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("lists", list); // 資料庫取出的物件,存入req
			String url = "/faq/indexFAQ.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listClassFAQ.jsp
			successView.forward(req, res);
		}
	}
}