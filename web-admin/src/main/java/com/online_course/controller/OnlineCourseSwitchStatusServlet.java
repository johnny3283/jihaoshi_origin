package com.online_course.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.online_course.model.OnlineCourseService;

@WebServlet("/onlineCourse/courseStatus")
public class OnlineCourseSwitchStatusServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private OnlineCourseService service = new OnlineCourseService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		JsonObject reqBody = gson.fromJson(req.getReader(), JsonObject.class);
		Integer courseNo =reqBody.get("courseNo").getAsInt();
		Integer courseStatus =reqBody.get("courseStatus").getAsInt();
		courseStatus = Math.abs(courseStatus-1);
		boolean result = service.courseSwitch(courseNo, courseStatus);
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.write("{\"successful\":"+result+"}");
	}

}
