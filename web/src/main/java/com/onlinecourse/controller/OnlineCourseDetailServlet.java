package com.onlinecourse.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.online_course.model.OnlineCourseService;
import com.online_course.model.OnlineCourseVO;
import com.onlinecoursecomment.model.OnlineCourseCommentService;
import com.onlinecoursecomment.model.OnlineCourseCommentVO;

@WebServlet("/onlineCourse/detail")
public class OnlineCourseDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer courseNo = Integer.parseInt(req.getParameter("courseNo"));
		OnlineCourseService service = new OnlineCourseService();
		OnlineCourseVO vo = service.getOneOnlineCourse(courseNo);
		// System.out.println("courseNo"+courseNo);
		OnlineCourseCommentService onlineCourseCommentSvc = new OnlineCourseCommentService();
		List<OnlineCourseCommentVO> commentlist = onlineCourseCommentSvc.getOnlineCourseCommentByCourseNo(courseNo);
		
		List<Object> list= new ArrayList<Object>();
		list.add(vo);
		if(commentlist!=null) {
			list.add(commentlist);
		}
		// System.out.println("list"+list);
		Gson gson = new Gson();
		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().write(gson.toJson(list));
		
	}
}
