package com.latest_news.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.latest_news.model.Latest_newsService;
import com.latest_news.model.Latest_newsVO;

@WebServlet("/Latest_newsServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//@MultipartConfig(fileSizeThreshold = 0, maxFileSize = 5)
public class Latest_newsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
			System.out.println("News_pic"+ latest_newsVO.getNews_pic());
			
			byte[] new_pic = latest_newsVO.getNews_pic();
			if(new_pic == null) {
				errorMsgs.add("無法展示圖片");
			} else {
				latest_newsVO.setShowPhoto("data:image/png;base64,"+Base64.getEncoder().encodeToString(latest_newsVO.getNews_pic()));
			}
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
	
		
		
		
		
//		if("uploadfile" .equals(action)) {
//			Part part = req.getPart("upfile");//來自於上面的form表單
//			InputStream in = part.getInputStream();
//			
//	        Latest_newsService latest_newsSvc = new Latest_newsService();
//	        latest_newsSvc.uploadfileLatest_news(in);
//			
//		}
		
	}
}
