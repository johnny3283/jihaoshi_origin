package com.forum_comment.controller;

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
import com.forum_comment.model.Forum_commentService;
import com.forum_comment.model.Forum_commentVO;

@WebServlet("/Forum_commentServlet")
//@MultipartConfig(fileSizeThreshold = 0, maxFileSize = 5)

public class Forum_commentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String whichPage = req.getParameter("whichPage");
		String type = req.getParameter("type");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
//			 Store this set in the request scope, in case we need to
//			 send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("comment_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入論壇留言編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forum_comment/forum_comment_select_page.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			Integer comment_no = null;
			try {
				comment_no = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("論壇留言編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forum_comment/forum_comment_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷

			}
			/*************************** 2.開始查詢資料 *****************************************/
			Forum_commentService forum_commentSvc = new Forum_commentService();
			Forum_commentVO forum_commentVO = forum_commentSvc.getOneForum_comment(comment_no);
			if (forum_commentVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/forum_comment/forum_comment_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("forum_commentVO", forum_commentVO); // 資料庫取出的forum_articleVO物件,存入req
			String url = "/forum_comment/listOneForum_comment.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneForum_article.jsp
			successView.forward(req, res);
		
		
		}
		if ("insert".equals(action)) { // 來自addForum_article.jsp的請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		
			Integer article_no = Integer.valueOf(req.getParameter("article_no").trim());
			Integer member_no = Integer.valueOf(req.getParameter("member_no").trim());
			
			String comment_content = req.getParameter("comment_content");
			if (comment_content == null || comment_content.trim().length() == 0) {
				errorMsgs.put("comment_content", "論壇留言標題請勿空白");
				
			}
			Forum_commentVO forum_commentVO = new Forum_commentVO();
			forum_commentVO.setArticle_no(article_no);
			forum_commentVO.setMember_no(member_no);
			forum_commentVO.setComment_content(comment_content);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("forum_commentVO", forum_commentVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/forum_comment/InsertForum_comment.jsp");
				System.out.println("失敗");
				failureView.forward(req, res);
				
				return;
			}

			
			/*************************** 2.開始查詢資料 *****************************************/
			Forum_commentService forum_commentSvc = new Forum_commentService();
			forum_commentVO = forum_commentSvc.addForum_comment(article_no, member_no, comment_content);
//			System.out.println(4);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("forum_commentVO", forum_commentVO); // 資料庫取出的forum_articleVO物件,存入req
			String url = "/forum_comment/listOneForum_comment.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneForum_article.jsp
			successView.forward(req, res);
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllForum_article.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer comment_no = Integer.valueOf(req.getParameter("comment_no"));

			/*************************** 2.開始查詢資料 ****************************************/
			Forum_commentService forum_commentSvc = new Forum_commentService();
			Forum_commentVO forum_commentVO = forum_commentSvc.getOneForum_comment(comment_no);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			String param "&article_content=" + forum_articleVO.getArticle_content() ;
//			=	"?article_name=" + forum_articleVO.getarticle_name() + 
//							
			req.setAttribute("forum_commentVO", forum_commentVO); // 資料庫取出的empVO物件,存入req
			String url = "/forum_comment/updateForum_comment.jsp";
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
			Integer member_no = Integer.valueOf(req.getParameter("member_no").trim());

			String comment_content = req.getParameter("comment_content").trim();
			if (comment_content == null || comment_content.trim().length() == 0) {
				errorMsgs.put("comment_content", "論壇留言內容請勿空白");
			}

			Integer comment_status = Integer.valueOf(req.getParameter("comment_status").trim());

			

			Forum_commentVO forum_commentVO = new Forum_commentVO();
			forum_commentVO.setArticle_no(article_no);
			forum_commentVO.setMember_no(member_no);
			forum_commentVO.setComment_content(comment_content);
			forum_commentVO.setComment_status(comment_status);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/forum_comment/updateForum_comment.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 ****************************************/
			Forum_commentService forum_commentSvc = new Forum_commentService();
			forum_commentVO = forum_commentSvc.updateForum_comment(article_no, member_no, comment_content, comment_status);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("forum_commentVO", forum_commentVO); // 資料庫update成功後,正確的的latest_newsVO物件,存入req
			String url = "/forum_comment/listAllForum_comment.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneLatest_news.jsp
			successView.forward(req, res);
		}
		if ("delete".equals(action)) { // 來自listAllForum_article.jsp

			/*************************** 1.接收請求參數 ***************************************/
			Integer comment_no = Integer.valueOf(req.getParameter("comment_no"));
			/*************************** 2.開始刪除資料 ***************************************/
			Forum_commentService forum_commentSvc = new Forum_commentService();
			forum_commentSvc.deleteForum_comment(comment_no);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/forum_comment/listAllForum_comment.jsp";
			RequestDispatcher successview = req.getRequestDispatcher(url);
			successview.forward(req, res);
		}
		if ("change_status_0".equals(action)) { // 來自listAllForum_article.jsp

			/*************************** 1.接收請求參數 ***************************************/
			Integer comment_no = Integer.valueOf(req.getParameter("comment_no"));
			/*************************** 2.開始刪除資料 ***************************************/
			Forum_commentService forum_commentSvc = new Forum_commentService();
			forum_commentSvc.change_status_0(comment_no);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/forum_comment/listAllForum_comment.jsp?whichPage = " + whichPage + "&type=" + type ;
			RequestDispatcher successview = req.getRequestDispatcher(url);
			successview.forward(req, res);
			
		}
		if ("change_status_1".equals(action)) { // 來自listAllForum_article.jsp

			/*************************** 1.接收請求參數 ***************************************/
			Integer comment_no = Integer.valueOf(req.getParameter("comment_no"));
			/*************************** 2.開始刪除資料 ***************************************/
			Forum_commentService forum_commentSvc = new Forum_commentService();
			forum_commentSvc.change_status_1(comment_no);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url ="/forum_comment/listAllForum_comment.jsp?whichPage = " + whichPage + "&type=" + type;
			RequestDispatcher successview = req.getRequestDispatcher(url);
			successview.forward(req, res);
			
		}
			
	}
	}
	

