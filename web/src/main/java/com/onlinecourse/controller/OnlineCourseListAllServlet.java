package com.onlinecourse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.online_course.model.OnlineCourseService;
import com.online_course.model.OnlineCourseVO;


@WebServlet("/onlineCourse/searchAll")
public class OnlineCourseListAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OnlineCourseService service = new OnlineCourseService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String courseName = req.getParameter("courseName");
		System.out.println(courseName);
		List<OnlineCourseVO> list = null;
		if(null != courseName) {
			list = service.getByCourseName(courseName);
		}else {
			list = service.getAll();

		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.write(jsonStr);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		JsonObject reqBody = gson.fromJson(req.getReader(), JsonObject.class);
		String courseName =reqBody.get("courseName").getAsString();
		System.out.println(courseName);
		List<OnlineCourseVO> list = service.getByCourseName(courseName);
		String jsonStr = gson.toJson(list);
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.write(jsonStr);
	}
	
}