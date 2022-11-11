package com.forum_article.controller;

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


@WebServlet("/Forum_articleServlet")
//@MultipartConfig(fileSizeThreshold = 0, maxFileSize = 5)

public class Forum_articleServlet extends HttpServlet {
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
			String str = req.getParameter("article_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入論壇文章編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forum_article/forum_article_select_page.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			Integer article_no = null;
			try {
				article_no = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("論壇文章編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forum_article/forum_article_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷

			}
			/*************************** 2.開始查詢資料 *****************************************/
			Forum_articleService forum_articleSvc = new Forum_articleService();
			Forum_articleVO forum_articleVO = forum_articleSvc.getOneForum_article(article_no);
			if (forum_articleVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forum_article/forum_article_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("forum_articleVO", forum_articleVO); // 資料庫取出的forum_articleVO物件,存入req
			String url = "/forum_article/listOneForum_article.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneForum_article.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addForum_article.jsp的請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String article_name = req.getParameter("article_name");
			if (article_name == null || article_name.trim().length() == 0) {
				errorMsgs.put("article_name", "論壇文章標題請勿空白");
				System.out.println("1");
			}
			
			Integer member_no = Integer.valueOf(req.getParameter("member_no").trim());
			
			
			String article_content = req.getParameter("article_content").trim();
			if (article_content == null || article_content.trim().length() == 0) {
				errorMsgs.put("article_content", "論壇文章內容請勿空白");
				
			}

			Forum_articleVO forum_articleVO = new Forum_articleVO();
			forum_articleVO.setArticle_name(article_name);
			forum_articleVO.setMember_no(member_no);
			forum_articleVO.setArticle_content(article_content);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("forum_articleVO", forum_articleVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/forum_article/InsertForum_article.jsp");
				System.out.println("失敗");
				failureView.forward(req, res);
				
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Forum_articleService forum_articleSvc = new Forum_articleService();
			forum_articleVO = forum_articleSvc.addForum_article(article_name, member_no, article_content);
//			System.out.println(4);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/forum_article/listAllForum_article.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllForum_article.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer article_no = Integer.valueOf(req.getParameter("article_no"));

			/*************************** 2.開始查詢資料 ****************************************/
			Forum_articleService forum_articleSvc = new Forum_articleService();
			Forum_articleVO forum_articleVO = forum_articleSvc.getOneForum_article(article_no);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			String param "&article_content=" + forum_articleVO.getArticle_content() ;
//			=	"?article_name=" + forum_articleVO.getarticle_name() + 
//							
			req.setAttribute("forum_articleVO", forum_articleVO); // 資料庫取出的empVO物件,存入req
			String url = "/forum_article/updateForum_article.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);

//			String url = "xxxxxxxxxxxxxxxxxxxxx" + param;
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_latest_news_input.jsp
//			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自updateLatest_news.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer article_no = Integer.valueOf(req.getParameter("article_no").trim());

			String article_name = req.getParameter("article_name").trim();
			if (article_name == null || article_name.trim().length() == 0) {
				errorMsgs.put("article_name", "論壇文章標題請勿空白");
			}

//			Integer member_no = Integer.valueOf(req.getParameter("member_no").trim());

			String article_content = req.getParameter("article_content").trim();
			if (article_content == null || article_content.trim().length() == 0) {
				errorMsgs.put("article_content", "文章內容請勿空白");
			}

//			Integer article_status = Integer.valueOf(req.getParameter("article_status").trim());

			Forum_articleVO forum_articleVO = new Forum_articleVO();
			forum_articleVO.setArticle_name(article_name);
			forum_articleVO.setArticle_content(article_content);
			forum_articleVO.setArticle_no(article_no);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/forum_article/updateForum_article.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 ****************************************/
			Forum_articleService forum_articleSvc = new Forum_articleService();
			forum_articleVO = forum_articleSvc.updateForum_article(article_name, article_content, article_no);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("forum_articleVO", forum_articleVO); // 資料庫update成功後,正確的的latest_newsVO物件,存入req
			String url = "/forum_article/listAllForum_article.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneLatest_news.jsp
			successView.forward(req, res);
		}

		if ("123".equals(action)) { 
			
			String url = "/forum_comment/InsertForum_comment.jsp";
			RequestDispatcher successview = req.getRequestDispatcher(url);
			successview.forward(req, res);
		}
		
		if ("change_status_0".equals(action)) { // 來自listAllForum_article.jsp

			/*************************** 1.接收請求參數 ***************************************/
			Integer article_no = Integer.valueOf(req.getParameter("article_no"));
			/*************************** 2.開始刪除資料 ***************************************/
			Forum_articleService forum_articleSvc = new Forum_articleService();
			forum_articleSvc.change_status_0(article_no);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/forum_article/listAllForum_article_3.jsp";
			RequestDispatcher successview = req.getRequestDispatcher(url);
			successview.forward(req, res);
			
		}
		if ("change_status_1".equals(action)) { // 來自listAllForum_article.jsp

			/*************************** 1.接收請求參數 ***************************************/
			Integer article_no = Integer.valueOf(req.getParameter("article_no"));
			/*************************** 2.開始刪除資料 ***************************************/
			Forum_articleService forum_articleSvc = new Forum_articleService();
			forum_articleSvc.change_status_1(article_no);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/forum_article/listAllForum_article_3.jsp";
			RequestDispatcher successview = req.getRequestDispatcher(url);
			successview.forward(req, res);
			
		}
	}
}
