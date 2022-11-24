package com.onlinecoursecommentreport.controller;

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
import com.onlinecoursecommentreport.model.OnlineCourseCommentReportService;
import com.onlinecoursecommentreport.model.OnlineCourseCommentReportVO;

@WebServlet("/OnlineCourseCommentReportServlet")
public class OnlineCourseCommentReportServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");
		
		if ("getOneReport_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			String str = req.getParameter("memberNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			Integer memberNo = null;
			try {
				memberNo = new Integer(str);
			} catch (Exception e) {}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/onlinecoursecommentreport/onlinecoursecommentreport_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 ****************************************/
			OnlineCourseCommentReportService onlineCourseCommentReportSvc = new OnlineCourseCommentReportService();
			List<OnlineCourseCommentReportVO> list = onlineCourseCommentReportSvc.getOnlineCommentReportsByMemberNo(memberNo);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("getOneReport_For_Display", list); // 資料庫取出的list物件,存入request

			String url = null;
			if ("getOneReport_For_Display".equals(action))
				url = "/onlinecoursecommentreport/listOneCommentReports.jsp"; // 成功轉交

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("getStatus_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str= req.getParameter("reportStatus");
			
			if (str == null) {
				errorMsgs.add("請選擇狀態");
			}
			Integer reportStatus=null;
			try {
				reportStatus=Integer.valueOf(str);
			} catch (Exception e) {}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/onlinecoursecommentreport/indexOnlineCourseCommentReport.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			OnlineCourseCommentReportService onlineCourseCommentReportSvc = new OnlineCourseCommentReportService();
			List<OnlineCourseCommentReportVO> list= onlineCourseCommentReportSvc.getOnlinecommentReportsByReportStatus(reportStatus);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("list", list); // 資料庫取出的物件,存入req
			String url = "/onlinecoursecommentreport/listOneStatus.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listClassFAQ.jsp
			successView.forward(req, res);
		}
		
		if ("add".equals(action)) { // 來自add.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			HttpSession session = req.getSession();
			MemberVO member=(MemberVO)session.getAttribute("member");
			Integer memberNo = member.getMemberNo();
					
			Integer commentNo = Integer.valueOf(req.getParameter("commentNo"));
			
			String reportReason = req.getParameter("reportReason");
			if (reportReason == null || reportReason.trim().length() == 0) {
				errorMsgs.add("請輸入檢舉原因");
			}

			OnlineCourseCommentReportVO onlineCourseCommentReportVO = new OnlineCourseCommentReportVO();
			onlineCourseCommentReportVO.setMemberNo(memberNo);
			onlineCourseCommentReportVO.setCommentNo(commentNo);
			onlineCourseCommentReportVO.setReportReason(reportReason);
			System.out.println(reportReason);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("onlineCourseCommentReportVO", onlineCourseCommentReportVO); // 含有輸入格式錯誤的物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/onlinecoursecommentreport/addOnlineCourseCommentReport.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			OnlineCourseCommentReportService onlineCourseCommentReportSvc = new OnlineCourseCommentReportService();
			onlineCourseCommentReportVO = onlineCourseCommentReportSvc.addOnlineCourseCommentReport(memberNo,commentNo,reportReason);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
			successView.forward(req, res);
		}
		
		if ("delete".equals(action)){ // 來自listAll.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer reportNo = Integer.valueOf(req.getParameter("reportNo"));

			/*************************** 2.開始刪除資料 ***************************************/
			OnlineCourseCommentReportService onlineCourseCommentReportSvc = new OnlineCourseCommentReportService();
			onlineCourseCommentReportSvc.deleteOnlineCourseCommentReport(reportNo);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/onlinecoursecommentreport/listAllOnlineCourseCommentReport.jsp";        // 成功轉交 .jsp		
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		if ("getAll".equals(action)) {

			/*************************** 2.開始查詢資料 *****************************************/
			OnlineCourseCommentReportService onlineCourseCommentReportSvc = new OnlineCourseCommentReportService();
			List<OnlineCourseCommentReportVO> list = onlineCourseCommentReportSvc.getAll();
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("list", list); // 資料庫取出的物件,存入req
			String url = "/onlinecoursecommentreport/indexOnlineCourseCommentReport.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listClassFAQ.jsp
			successView.forward(req, res);
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自請求

			/*************************** 1.接收請求參數 ****************************************/
			Integer reportNo = Integer.valueOf(req.getParameter("reportNo"));
			//System.out.println(reportNo);
			/*************************** 2.開始查詢資料 ****************************************/
			OnlineCourseCommentReportService onlineCourseCommentReportSvc = new OnlineCourseCommentReportService();
			OnlineCourseCommentReportVO onlineCourseCommentReportVO = onlineCourseCommentReportSvc.getOneOnlineCourseCommentReport(reportNo);
			//System.out.println(onlineCourseCommentReportVO);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("onlineCourseCommentReportVO", onlineCourseCommentReportVO); // 資料庫取出的VO物件,存入req
			String url = "/onlinecoursecommentreport/updateOnlineCourseCommentReport.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
			successView.forward(req, res);
		}
		
		if ("updateStatus".equals(action)) { // 來自update__input.jsp的請求

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer reportNo = Integer.valueOf(req.getParameter("reportNo"));		
			String status = req.getParameter("status");
			//System.out.println(reportNo);
			Integer reportStatus = null;
			try {
				reportStatus =  Integer.valueOf(status);
			} catch (Exception e) {}

			OnlineCourseCommentReportVO onlineCourseCommentReportVO = new OnlineCourseCommentReportVO();
			onlineCourseCommentReportVO.setReportNo(reportNo);
			onlineCourseCommentReportVO.setReportStatus(reportStatus);

			//System.out.println(reportStatus);
			/*************************** 2.開始修改資料 *****************************************/
			OnlineCourseCommentReportService onlineCourseCommentReportSvc = new OnlineCourseCommentReportService();
			onlineCourseCommentReportVO = onlineCourseCommentReportSvc.updateReportStatus(reportNo,reportStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("onlineCourseCommentReportVO", onlineCourseCommentReportVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/OnlineCourseCommentReportServlet?action=getAll";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFAQ.jsp
			successView.forward(req, res);
		}
	}
}