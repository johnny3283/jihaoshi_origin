package com.onlinecourse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online_course.model.OnlineCourseService;
import com.online_course.model.OnlineCourseVO;

@WebServlet("/onlineCourse/search")
public class OnlineCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OnlineCourseService service= new OnlineCourseService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		final String courseName = req.getParameter("courseName");
		List<OnlineCourseVO> list = service.getByCourseName(courseName);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/onlineCourse/ListAllOnlineCourse.jsp").forward(req, resp);
	}
}


