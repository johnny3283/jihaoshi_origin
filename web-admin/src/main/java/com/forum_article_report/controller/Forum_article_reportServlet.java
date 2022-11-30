package com.forum_article_report.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum_article.model.Forum_articleService;
import com.forum_article_report.model.Forum_article_reportService;
import com.forum_article_report.model.Forum_article_reportVO;

@WebServlet("/Forum_article_reportServlet")
//@MultipartConfig(fileSizeThreshold = 0, maxFileSize = 5)
public class Forum_article_reportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String whichPage = req.getParameter("whichPage");
		System.out.println("page:" + whichPage);
		String type = req.getParameter("type") == null? "1": req.getParameter("type");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
//			 Store this set in the request scope, in case we need to
//			 send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("article_report_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入論壇文章檢舉編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forum_article_report/forum_article_report_select_page.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			Integer article_report_no = null;
			try {
				article_report_no = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("論壇文章檢舉編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forum_article_report/forum_article_report_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷

			}
			/*************************** 2.開始查詢資料 *****************************************/
			Forum_article_reportService forum_article_reportSvc = new Forum_article_reportService();
			Forum_article_reportVO forum_article_reportVO = forum_article_reportSvc
					.getOneForum_article_report(article_report_no);
			if (forum_article_reportVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forum_article_report/forum_article_report_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("forum_article_reportVO", forum_article_reportVO); // 資料庫取出的forum_article_reportVO物件,存入req
			String url = "/forum_article_report/listOneForum_article_report.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneForum_article_report.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addForum_article_report.jsp的請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer article_no = Integer.valueOf(req.getParameter("article_no").trim());

			Integer member_no = Integer.valueOf(req.getParameter("member_no").trim());

			String report_reason = req.getParameter("report_reason");
			if (report_reason == null || report_reason.trim().length() == 0) {
				errorMsgs.put("report_reason", "檢舉事由請勿空白");
				System.out.println("1");
			}

			Forum_article_reportVO forum_article_reportVO = new Forum_article_reportVO();
			forum_article_reportVO.setArticle_no(article_no);
			forum_article_reportVO.setMember_no(member_no);
			forum_article_reportVO.setReport_reason(report_reason);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("forum_article_reportVO", forum_article_reportVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forum_article_report/InsertForum_article_report.jsp");
				System.out.println("失敗");
				failureView.forward(req, res);

				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Forum_article_reportService forum_article_reportSvc = new Forum_article_reportService();
			forum_article_reportVO = forum_article_reportSvc.addForum_article_report(article_no, member_no,
					report_reason);
//			System.out.println(4);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/forum_article_report/listAllForum_article_report.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllForum_article_report.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer article_report_no = Integer.valueOf(req.getParameter("article_report_no"));

			/*************************** 2.開始查詢資料 ****************************************/
			Forum_article_reportService forum_article_reportSvc = new Forum_article_reportService();
			Forum_article_reportVO forum_article_reportVO = forum_article_reportSvc
					.getOneForum_article_report(article_report_no);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			
			req.setAttribute("forum_article_reportVO", forum_article_reportVO); // 資料庫取出的empVO物件,存入req
			String url = "/forum_article_report/updateForum_article_report.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);

		}

		if ("update".equals(action)) { // 來自updateForum_article_report.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer article_report_no = Integer.valueOf(req.getParameter("article_report_no").trim());
			Integer article_no = Integer.valueOf(req.getParameter("article_no").trim());
			Integer member_no = Integer.valueOf(req.getParameter("member_no").trim());

			String report_reason = req.getParameter("report_reason").trim();
			if (report_reason == null || report_reason.trim().length() == 0) {
				errorMsgs.put("report_reason", "檢舉事由請勿空白");
			}
			Integer report_status = Integer.valueOf(req.getParameter("report_status").trim());
			

			Forum_article_reportVO forum_article_reportVO = new Forum_article_reportVO();
			forum_article_reportVO.setArticle_no(article_no);
			forum_article_reportVO.setMember_no(member_no);
			forum_article_reportVO.setReport_reason(report_reason);
			forum_article_reportVO.setReport_status(report_status);
			forum_article_reportVO.setArticle_report_no(article_report_no);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/forum_article_report/updateForum_article_report.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 ****************************************/
			Forum_article_reportService forum_article_reportSvc = new Forum_article_reportService();
			forum_article_reportVO = forum_article_reportSvc.updateForum_article_report(article_no, member_no, report_reason, report_status, article_report_no);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("forum_article_reportVO", forum_article_reportVO); // 資料庫update成功後,正確的的forum_article_reportVO物件,存入req
			String url = "/forum_article_report/listAllForum_article_report.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneForum_article_report.jsp
			successView.forward(req, res);
		}
		
		if ("delete".equals(action)) { // 來自listAllForum_article_report.jsp

			/*************************** 1.接收請求參數 ***************************************/
			Integer article_report_no = Integer.valueOf(req.getParameter("article_report_no"));
			/*************************** 2.開始刪除資料 ***************************************/
			Forum_article_reportService forum_article_reportSvc = new Forum_article_reportService();
			forum_article_reportSvc.deleteForum_article_report(article_report_no);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/forum_article_report/listAllForum_article_report.jsp";
			RequestDispatcher successview = req.getRequestDispatcher(url);
			successview.forward(req, res);
		}
		
		if ("change_status_0".equals(action)) { // 來自listAllForum_article.jsp

			/*************************** 1.接收請求參數 ***************************************/
			Integer article_report_no = Integer.valueOf(req.getParameter("article_report_no"));
			System.out.println("input1: " + article_report_no);
			/*************************** 2.開始刪除資料 ***************************************/
			Forum_article_reportService forum_article_reportSvc = new Forum_article_reportService();
			forum_article_reportSvc.change_status_0(article_report_no);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/forum_article/listAllForum_article.jsp?whichPage = " + whichPage + "&type= " + type;
			RequestDispatcher successview = req.getRequestDispatcher(url);
			successview.forward(req, res);
			
		}
	
	
		if ("change_status_1".equals(action)) { // 來自listAllForum_article.jsp

			/*************************** 1.接收請求參數 ***************************************/
			Integer article_report_no = Integer.valueOf(req.getParameter("article_report_no"));
			System.out.println("input2: " + article_report_no);
			/*************************** 2.開始刪除資料 ***************************************/
			Forum_article_reportService forum_article_reportSvc = new Forum_article_reportService();
			forum_article_reportSvc.change_status_1(article_report_no);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/forum_article/listAllForum_article.jsp?whichPage=" + whichPage + "&type= " + type;
			RequestDispatcher successview = req.getRequestDispatcher(url);
			System.out.println("whichPage"+ whichPage);
			successview.forward(req, res);
	}
//		if ("change_status_2".equals(action)) { // 來自listAllForum_article.jsp
//
//			/*************************** 1.接收請求參數 ***************************************/
//			Integer article_report_no = Integer.valueOf(req.getParameter("article_report_no"));
//			System.out.println("input3: " + article_report_no);
//			/*************************** 2.開始刪除資料 ***************************************/
//			Forum_articleService forum_articleSvc = new Forum_articleService();
//			forum_articleSvc.change_status_1(article_report_no);
//			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//			String url = "/forum_article_report/listAllForum_article_report.jsp?whichPage=" + whichPage;
//			RequestDispatcher successview = req.getRequestDispatcher(url);
//			System.out.println("whichPage"+ whichPage);
//			successview.forward(req, res);
//			
//		}
	}
}
