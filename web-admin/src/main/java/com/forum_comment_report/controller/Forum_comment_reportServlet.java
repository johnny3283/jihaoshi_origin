package com.forum_comment_report.controller;

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
import com.forum_article.model.Forum_articleVO;
import com.forum_comment_report.model.Forum_comment_reportService;
import com.forum_comment_report.model.Forum_comment_reportVO;

@WebServlet("/Forum_comment_reportServlet")
//@MultipartConfig(fileSizeThreshold = 0, maxFileSize = 5)

public class Forum_comment_reportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自forum_comment_reportselect_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
//			 Store this set in the request scope, in case we need to
//			 send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("comment_report_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入論壇文章檢舉編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forum_comment_report/forum_comment_report_select_page.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			Integer comment_report_no = null;
			try {
				comment_report_no = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("論壇文章檢舉編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forum_comment_report/forum_comment_report_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			Forum_comment_reportService forum_comment_reportSvc = new Forum_comment_reportService();
			Forum_comment_reportVO forum_comment_reportVO = forum_comment_reportSvc
					.getOneForum_comment_report(comment_report_no);
			if (forum_comment_reportVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forum_comment_report/forum_comment_report_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("forum_comment_reportVO", forum_comment_reportVO); // 資料庫取出的forum_comment_reportVO物件,存入req
			String url = "/forum_comment_report/listOneForum_comment_report.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneForum_comment_report.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addForum_comment_report.jsp的請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer comment_no = Integer.valueOf(req.getParameter("comment_no").trim());
			Integer article_no = Integer.valueOf(req.getParameter("article_no").trim());
			Integer member_no = Integer.valueOf(req.getParameter("member_no").trim());

			String report_reason = req.getParameter("report_reason").trim();
			if (report_reason == null || report_reason.trim().length() == 0) {
				errorMsgs.put("report_reason", "論壇留言檢舉請勿空白");

			}

			Forum_comment_reportVO forum_comment_reportVO = new Forum_comment_reportVO();
			forum_comment_reportVO.setComment_no(comment_no);
			forum_comment_reportVO.setArticle_no(article_no);
			forum_comment_reportVO.setMember_no(member_no);
			forum_comment_reportVO.setReport_reason(report_reason);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("forum_comment_reportVO", forum_comment_reportVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forum_comment_report/InsertForum_comment_report.jsp");
				System.out.println("失敗");
				failureView.forward(req, res);

				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Forum_comment_reportService forum_comment_reportSvc = new Forum_comment_reportService();
			forum_comment_reportVO = forum_comment_reportSvc.addForum_comment_report(comment_no, article_no, member_no,
					report_reason);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/forum_comment_report/listAllForum_comment_report.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllForum_comment_report.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllForum_comment_report.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer comment_report_no = Integer.valueOf(req.getParameter("comment_report_no"));

			/*************************** 2.開始查詢資料 ****************************************/
			Forum_comment_reportService forum_comment_reportSvc = new Forum_comment_reportService();
			Forum_comment_reportVO forum_comment_reportVO = forum_comment_reportSvc
					.getOneForum_comment_report(comment_report_no);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

			req.setAttribute("forum_comment_reportVO", forum_comment_reportVO); // 資料庫取出的forum_comment_reportVO物件,存入req
			String url = "/forum_comment_report/updateForum_comment_report.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_forum_comment_report.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自updateForum_comment_report.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer comment_report_no = Integer.valueOf(req.getParameter("comment_report_no").trim());

			String report_reason = req.getParameter("report_reason").trim();
			if (report_reason == null || report_reason.trim().length() == 0) {
				errorMsgs.put("report_reason", "檢舉事由請勿空白");
			}

			Integer report_status = Integer.valueOf(req.getParameter("report_status").trim());

			Forum_comment_reportVO forum_comment_reportVO = new Forum_comment_reportVO();

			forum_comment_reportVO.setReport_reason(report_reason);
			forum_comment_reportVO.setReport_status(report_status);
			forum_comment_reportVO.setComment_report_no(comment_report_no);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/forum_article/updateForum_article.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 ****************************************/
			Forum_comment_reportService forum_comment_reportSvc = new Forum_comment_reportService();
			forum_comment_reportVO = forum_comment_reportSvc.updateForum_comment_report(report_reason, report_status,
					comment_report_no);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("forum_comment_reportVO", forum_comment_reportVO); // 資料庫update成功後,正確的的forum_comment_reportVO物件,存入req
			String url = "/forum_comment_report/listAllForum_comment_report.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneForum_comment_report.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllForum_comment_report.jsp

			/*************************** 1.接收請求參數 ***************************************/
			Integer comment_report_no = Integer.valueOf(req.getParameter("comment_report_no"));
			/*************************** 2.開始刪除資料 ***************************************/
			Forum_comment_reportService forum_comment_reportSvc = new Forum_comment_reportService();
			forum_comment_reportSvc.deleteForum_comment_report(comment_report_no);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/forum_comment_report/listAllForum_comment_report.jsp";
			RequestDispatcher successview = req.getRequestDispatcher(url);
			successview.forward(req, res);
		}

	}
}
