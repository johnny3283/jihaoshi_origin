package com.course.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.course.model.*;
import com.signupcourse.model.PhyCouSignUpService;
import com.signupcourse.model.PhyCouSignUpVO;


@WebServlet("/signup/cou.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class CourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		res.setContentType("application/x-www-form-urlencoded");
	

		if ("apply".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer course_no = Integer.valueOf(req.getParameter("course_no"));
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/course/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PhyCouService phyCouSvc = new PhyCouService();
				PhyCouVO phyCouVO = phyCouSvc.getOneCou(course_no);
				if (phyCouVO== null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/course/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("phyCouVO", phyCouVO); 
				String url = "/course/SingUpForConfirm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("confirm".equals(action)) { 

			
				/***************************1.接收請求參數****************************************/
			    System.out.println("confirm_pass");
		        HttpSession session = req.getSession();
		        PhyCouVO phyCouVO = (PhyCouVO)session.getAttribute("phyCouVO");
		        Integer member_no = (Integer)session.getAttribute("member_no");		    
	
			    Integer course_no = phyCouVO.getCourse_no();
			    System.out.println("course_no_pass");
	
				
				/***************************2.開始新增資料****************************************/
				Integer order_price = phyCouVO.getCourse_price();
				Integer signUpNum = phyCouVO.getCurrent_sign_up_people();		
				PhyCouSignUpService phyCouSignUpSvc = new PhyCouSignUpService();
				PhyCouSignUpVO phyCouSignUpVO = phyCouSignUpSvc.signup(member_no, course_no, order_price, signUpNum);
						
				/***************************3.新增完成,準備轉交(Send the Success view)************/
				req.setAttribute("phyCouSignUpVO", phyCouSignUpVO);         
				req.setAttribute("phyCouVO", phyCouVO);
				String url = "/course/confirmOk.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
		
		
		if ("getAllSignUpCou".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                Integer member_no = Integer.valueOf(req.getParameter("member_no"));		

				
				/***************************2.開始修改資料*****************************************/
				PhyCouSignUpService phyCouSignUpSvc = new PhyCouSignUpService();
				List<PhyCouSignUpVO> list = phyCouSignUpSvc.getAllSignUpCou(member_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/course/listAllSignUpCou.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

  		if ("refund".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer order_no = Integer.valueOf(req.getParameter("order_no"));
				Integer course_no = Integer.valueOf(req.getParameter("course_no"));
				Integer order_status = Integer.valueOf(req.getParameter("order_status"));
				if (order_status == 2) {
					errorMsgs.add("已退費，不能再退費");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/course/listAllSignUpCou.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始刪除資料***************************************/
				PhyCouSignUpService phyCouSignUpSvc = new PhyCouSignUpService();
				PhyCouService phyCouSvc = new PhyCouService();
				PhyCouVO phyCouVO = phyCouSvc.getOneCou(course_no);
				Integer signUpNum = phyCouVO.getCurrent_sign_up_people();
				phyCouSignUpSvc.deleteCou(order_no, signUpNum, course_no );
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/course/listAllSignUpCou.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	
	
	
	if ("listCous_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

			
			/***************************1.將輸入資料轉為Map**********************************/ 
			//採用Map<String,String[]> getParameterMap()的方法 
			//注意:an immutable java.util.Map 
			Map<String, String[]> map = req.getParameterMap();
			
			/***************************2.開始複合查詢***************************************/
			PhyCouService phyCouSvc = new PhyCouService();
			List<PhyCouVO> list  = phyCouSvc.getAll(map);
			
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("listCous_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
			RequestDispatcher successView = req.getRequestDispatcher("/course/listCous_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
	}	
	

}
}
