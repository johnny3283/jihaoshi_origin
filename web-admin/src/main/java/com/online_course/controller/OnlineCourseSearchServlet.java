package com.online_course.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.online_course.model.OnlineCourseService;
import com.online_course.model.OnlineCourseVO;

@WebServlet("/onlineCourse/search")
public class OnlineCourseSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OnlineCourseService service= new OnlineCourseService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		JsonObject reqBody = gson.fromJson(req.getReader(), JsonObject.class);
		String flag =reqBody.get("flag").getAsString();
		if("queryPic".equals(flag)) {
			String courseName=reqBody.get("courseName").getAsString();
			List<OnlineCourseVO> list = service.getByCourseName(courseName);
			String jsonStr = gson.toJson(list);
			System.out.println(jsonStr);
			resp.setContentType("application/json; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.write(jsonStr);
		}else {
			final String courseName = req.getParameter("courseName");
			List<OnlineCourseVO> list = service.getByCourseName(courseName);
			req.setAttribute("list", list);
			req.getRequestDispatcher("/onlinecourse/ListAllOnlineCourse.jsp").forward(req, resp);
		}

	}
	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Gson gson = new Gson();
//		req.setCharacterEncoding("UTF-8");
//		JsonObject reqBody = gson.fromJson(req.getReader(), JsonObject.class);
//		String courseName=reqBody.get("courseName").getAsString();
//		List<OnlineCourseVO> list = service.getByCourseName(courseName);
//		String jsonStr = gson.toJson(list);
//		resp.setContentType("application/json; charset=UTF-8");
//		PrintWriter writer = resp.getWriter();
//		writer.write(jsonStr);
	}
}



