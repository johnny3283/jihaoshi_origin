package com.online_course.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.meal.model.MealService;
import com.online_course.model.OnlineCourseService;
import com.online_course.model.OnlineCourseVO;

@WebServlet("/onlineCourse/onlineCourse")
@MultipartConfig
public class OnlineCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OnlineCourseService courseSV = new OnlineCourseService();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");   //

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();  //為了存錯誤訊息
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);  //為了存錯誤訊息  key,value

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("courseNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入線上課程編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/ListOneOnlineCourse.jsp"); //設定跳轉的頁面
				failureView.forward(req, res); //執行跳轉
				return;// 程式中斷
			}

			Integer courseNo = null;
			try {
				courseNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("線上課程編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/onlineCourse/SelectPage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料(呼叫去拿Service方法，關係到商業邏輯判斷) *****************************************/
			OnlineCourseService onlinecourseSvc = new OnlineCourseService();
			OnlineCourseVO onlinecourseVO = onlinecourseSvc.getOneOnlineCourse(courseNo);
			if (onlinecourseVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/onlineCourse/SelectPage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("onlinecourseVO", onlinecourseVO); // 資料庫取出的empVO物件,存入req
			String url = "/onlineCourse/ListOneOnlineCourse.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer courseNo = Integer.valueOf(req.getParameter("courseNo"));
			
		

			/*************************** 2.開始查詢資料 ****************************************/
			OnlineCourseService onlinecourseSvc = new OnlineCourseService();
			OnlineCourseVO onlinecourseVO = onlinecourseSvc.getOneOnlineCourse(courseNo);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("onlinecourseVO", onlinecourseVO); // 資料庫取出的empVO物件,存入req
			String url = "/onlineCourse/UpdateOnlineCourseInput.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			String courseName = req.getParameter("courseName");
			String courseNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,10}$";
			if (courseName == null || courseName.trim().length() == 0) {
				errorMsgs.add("線上課程名稱請勿空白");
			} else if (!courseName.trim().matches(courseNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("線上課程名稱 : 只能是中、英文字母、數字和_ , 且長度必需在3到20之間");
			}

			String courseHr = req.getParameter("courseHr").trim();
			if (courseHr == null || courseHr.trim().length() == 0) {
				errorMsgs.add("課程時數請勿空白");
			}

			String courseTeacher = req.getParameter("courseTeacher").trim();
			if (courseTeacher == null || courseTeacher.trim().length() == 0) {
				errorMsgs.add("課程師資請勿空白");
			}

			String courseInfo = req.getParameter("courseInfo").trim();
			if (courseInfo == null || courseInfo.trim().length() == 0) {
				errorMsgs.add("線上課程簡介請勿空白");
			}

			Integer coursePrice = null;
			try {
				coursePrice = Integer.valueOf(req.getParameter("coursePrice").trim());
			} catch (NumberFormatException e) {
				coursePrice = 0;
				errorMsgs.add("線上課程價格請填數字");
			}

			String courseStatusStr = req.getParameter("courseStatus");
			if (courseStatusStr == null) {
				errorMsgs.add("線上課程狀態請勾選");
			}

			Integer commentPeople = null;
			try {
				commentPeople = Integer.valueOf(req.getParameter("commentPeople").trim());
			} catch (NumberFormatException e) {
				commentPeople = 0;
				errorMsgs.add("評論人數請勿空白");

			}
			Integer commentScore = null;
			try {
				commentScore = Integer.valueOf(req.getParameter("commentScore").trim());
			} catch (NumberFormatException e) {
				commentScore = 0;
				errorMsgs.add("評論分數請勿空白");
			}
			
			Part onlineCoursePhoto = req.getPart("photo");
			if (onlineCoursePhoto != null) {
				errorMsgs.add("請上傳圖片");
			}	

			Integer courseNo = Integer.valueOf(req.getParameter("courseNo"));
			
			Part part = req.getPart("photo");
			InputStream is = part.getInputStream();
			byte[] pic = is.readAllBytes();

			OnlineCourseVO onlinecourseVO = new OnlineCourseVO();
			onlinecourseVO.setCourseName(courseName);
			onlinecourseVO.setCourseHr(courseHr);
			onlinecourseVO.setCourseTeacher(courseTeacher);
			onlinecourseVO.setCourseInfo(courseInfo);
			onlinecourseVO.setCoursePrice(coursePrice);
			onlinecourseVO.setCourseStatus(courseStatusStr == null ? null : Integer.valueOf(courseStatusStr));
			onlinecourseVO.setCommentPeople(commentPeople);
			onlinecourseVO.setCommentScore(commentScore);
			onlinecourseVO.setCourseNo(courseNo);
			onlinecourseVO.setOnlineCoursePhoto(pic);
			Encoder encoder = Base64.getEncoder();
			if(pic !=null) {
				String photoBase64Str = encoder.encodeToString(pic);
				onlinecourseVO.setOnlineCoursePhotoBaseStr64(photoBase64Str);			
			}
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("onlinecourseVO", onlinecourseVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/onlineCourse/UpdateOnlineCourseInput.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			/*************************** 2.開始修改資料 *****************************************/
			OnlineCourseService onlinecourseSvc = new OnlineCourseService();
			onlinecourseSvc.updateOnlineCourse(onlinecourseVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("onlinecourseVO", onlinecourseVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/onlineCourse/ListOneOnlineCourse.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String courseName = req.getParameter("courseName");
			String onlinecourseNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,20}$";
			if (courseName == null || courseName.trim().length() == 0) {
				errorMsgs.add("課程名稱請勿空白");
			} else if (!courseName.trim().matches(onlinecourseNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("課程名稱: 只能是中、英文字母、數字和_ , 且長度必需在3到20之間");
			}

			String courseHr = req.getParameter("courseHr");
			if (courseHr == null || courseHr.trim().length() == 0) {
				errorMsgs.add("課程時數請勿空白");
			}

			String courseTeacher = req.getParameter("courseTeacher");
			if (courseTeacher == null || courseTeacher.trim().length() == 0) {
				errorMsgs.add("課程師資請勿空白");
			}

			String courseInfo = req.getParameter("courseInfo");
			if (courseInfo == null || courseInfo.trim().length() == 0) {
				errorMsgs.add("線上課程簡介請勿空白");
			}

			Integer coursePrice = null;
			try {
				coursePrice = Integer.valueOf(req.getParameter("coursePrice"));
			} catch (Exception e) {
				coursePrice = 0;
				errorMsgs.add("線上課程價格請填數字.");
			}

			Integer courseStatus = null;
			try {
				courseStatus = Integer.valueOf(req.getParameter("courseStatus"));
			} catch (Exception e) {
				courseStatus = 0;
				errorMsgs.add("線上課程狀態請勾選");
			}

			Integer commentPeople = null;
			try {
				commentPeople = Integer.valueOf(req.getParameter("commentPeople"));
			} catch (Exception e) {
				commentPeople = 0;
				errorMsgs.add("評論人數請勿空白");

			}

			Integer commentScore = null;
			try {
				commentScore = Integer.valueOf(req.getParameter("commentScore"));
			} catch (Exception e) {
				commentScore = 0;
				errorMsgs.add("評論分數請勿空白");
			}

			
				
	
			
			OnlineCourseVO onlinecourseVO = new OnlineCourseVO();
			onlinecourseVO.setCourseName(courseName);
			onlinecourseVO.setCourseHr(courseHr);
			onlinecourseVO.setCourseTeacher(courseTeacher);
			onlinecourseVO.setCourseInfo(courseInfo);
			onlinecourseVO.setCoursePrice(coursePrice);
			onlinecourseVO.setCourseStatus(courseStatus);
			onlinecourseVO.setCommentPeople(commentPeople);
			onlinecourseVO.setCommentScore(commentScore);
			
			
			Part part = req.getPart("photo");
			if(part !=null) {
				byte[] photo = part.getInputStream().readAllBytes();
				onlinecourseVO.setOnlineCoursePhoto(photo);
				Encoder encoder = Base64.getEncoder();
				if(photo !=null) {
					String photoBase64Str = encoder.encodeToString(photo);
					onlinecourseVO.setOnlineCoursePhotoBaseStr64(photoBase64Str);			
				}
				
			}else {
				errorMsgs.add("請選擇圖片上傳，檔案為jpg檔");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("onlinecourseVO", onlinecourseVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/onlineCourse/AddOnlineCourse.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			OnlineCourseService onlinecourseSvc = new OnlineCourseService();
			onlinecourseSvc.save(onlinecourseVO);
//			List<OnlineCourseVO> list = courseSV.getAll();
//			req.setAttribute("list", list);
//			req.getRequestDispatcher("/onlineCourse/ListAllOnlineCourse.jsp").forward(req, res);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("onlinecourseVO", onlinecourseVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/onlineCourse/ListOneOnlineCourse.jsp";
			//			String url = "/onlineCourse/ListAllOnlineCourse.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs2 = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs2", errorMsgs2);
//
//			/*************************** 1.接收請求參數 ***************************************/
//			Integer courseNo = Integer.valueOf(req.getParameter("courseNo"));
//
//			/*************************** 2.開始刪除資料 ***************************************/
//			OnlineCourseVO onlinecourseVO = new OnlineCourseVO();
//			onlinecourseVO.setCourseNo(courseNo);
//			OnlineCourseService onlinecourseSvc = new OnlineCourseService();
//			onlinecourseSvc.deleteOnlineCourse(onlinecourseVO);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//			String url = "/onlinecourse/listAllOnlineCourse.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//			successView.forward(req, res);
//			res.sendRedirect("onlinecourse/listAllOnlineCourse.jsp");
//		}
		
	
	}

}
