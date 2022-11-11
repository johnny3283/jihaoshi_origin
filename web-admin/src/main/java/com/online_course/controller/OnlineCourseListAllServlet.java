package com.online_course.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online_course.model.OnlineCourseService;
import com.online_course.model.OnlineCourseVO;

@WebServlet("/onlineCourse/searchAll")
public class OnlineCourseListAllServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private OnlineCourseService service = new OnlineCourseService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<OnlineCourseVO> list = service.getAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/onlineCourse/ListAllOnlineCourse.jsp").forward(req, resp);
	}

}
