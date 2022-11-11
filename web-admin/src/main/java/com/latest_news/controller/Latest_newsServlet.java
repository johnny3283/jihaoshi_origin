package com.latest_news.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.latest_news.model.Latest_newsService;
import com.latest_news.model.Latest_newsVO;

@WebServlet("/Latest_newsServlet")
//@MultipartConfig(fileSizeThreshold = 0, maxFileSize = 5)
public class Latest_newsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
//			 Store this set in the request scope, in case we need to
//			 send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("news_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入消息編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/latest_news/select_page.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			Integer news_no = null;
			try {
				news_no = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("消息編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/latest_news/select_page.jsp");
				failureView.forward(req, res);
				
				

				return;// 程式中斷

			}
			/*************************** 2.開始查詢資料 *****************************************/
			Latest_newsService latest_newsSvc = new Latest_newsService();
			Latest_newsVO latest_newsVO = latest_newsSvc.getOneLatest_news(news_no);
			if (latest_newsVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/latest_news/select_page.jsp");
				failureView.forward(req, res);
				System.out.println(5);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("latest_newsVO", latest_newsVO); // 資料庫取出的latest_newsVO物件,存入req
			String url = "/latest_news/listOneLatest_news.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneLatest_news.jsp
			successView.forward(req, res);
		}
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String news_name = req.getParameter("news_name");
			if (news_name == null || news_name.trim().length() == 0) {
				errorMsgs.put("news_name", "消息標題請勿空白");
				
			}
			
			String news_content = req.getParameter("news_content").trim();
			if (news_content == null || news_content.trim().length() == 0) {
				errorMsgs.put("news_content", "消息內容請勿空白");
				
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/latest_news/InsertLatest_news.jsp");
				System.out.println("失敗");
				failureView.forward(req, res);
				
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Latest_newsService latest_newsSvc = new Latest_newsService();
			latest_newsSvc.addLatest_news(news_name, news_content);
//			System.out.println(4);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/latest_news/listAllLatest_news.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllLatest_news.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer news_no = Integer.valueOf(req.getParameter("news_no"));

			/*************************** 2.開始查詢資料 ****************************************/
			Latest_newsService latest_newsSvc = new Latest_newsService();
			Latest_newsVO latest_newsVO = latest_newsSvc.getOneLatest_news(news_no);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			String param "&news_content=" + latest_newsVO.getNews_content() ;
//			=	"?news_name=" + latest_newsVO.getNews_name() + 
//							
			req.setAttribute("latest_newsVO", latest_newsVO);         // 資料庫取出的empVO物件,存入req
			String url = "/latest_news/updateLatest_news.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
			
			
					
//			String url = "/latest_news/updateLatest_news.jsp" + param;
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_latest_news_input.jsp
//			successView.forward(req, res);
		}
	
		
		
		if ("update".equals(action)) { // 來自update_latest_news_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer news_no = Integer.valueOf(req.getParameter("news_no").trim());

			String news_name = req.getParameter("news_name").trim();
			if (news_name == null || news_name.trim().length() == 0) {
				errorMsgs.put("news_name", "消息標題請勿空白");
			}

			String news_content = req.getParameter("news_content").trim();
			if (news_content == null || news_content.trim().length() == 0) {
				errorMsgs.put("news_content", "消息內容請勿空白");
			}
			
			Latest_newsVO latest_newsVO = new Latest_newsVO();
			latest_newsVO.setNews_name(news_name);
			latest_newsVO.setNews_content(news_content);
			latest_newsVO.setNews_no(news_no);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/latest_news/update_latest_news_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			Latest_newsService latest_newsSvc = new Latest_newsService();
			latest_newsVO = latest_newsSvc.updateLatest_news(news_name,news_content, news_no);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("latest_newsVO", latest_newsVO); // 資料庫update成功後,正確的的latest_newsVO物件,存入req
			String url = "/latest_news/listAllLatest_news.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneLatest_news.jsp
			successView.forward(req, res);
		}
		
		
		
		if ("delete".equals(action)) {
			
			/***************************1.接收請求參數***************************************/
			Integer news_no = Integer.valueOf(req.getParameter("news_no"));
			/***************************2.開始刪除資料***************************************/
			Latest_newsService latest_newsSvc = new Latest_newsService();
			latest_newsSvc.deleteLatest_news(news_no);
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/		
			String url = "/latest_news/listAllLatest_news.jsp";
			RequestDispatcher successview = req.getRequestDispatcher(url);
			successview.forward(req, res);
		}
		
	}
}
