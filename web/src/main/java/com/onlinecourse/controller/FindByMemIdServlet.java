package com.onlinecourse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.online_course.model.OnlineCourseService;
import com.online_course.model.OnlineCourseVO;

@WebServlet("/onlineCourse/findByMemId")
public class FindByMemIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OnlineCourseService service= new OnlineCourseService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 記得改唷!!!
		Integer memberId = 1;
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		OnlineCourseVO vo = gson.fromJson(req.getReader(), OnlineCourseVO.class);
		String courseName = vo.getCourseName();
		List<OnlineCourseVO> list;
		if (courseName == null || courseName.isEmpty()) {
			list = service.selectByMemId(memberId);
		} else {
			list = service.selectByCourseNameAndMemId(courseName, memberId);
		}
		resp.getWriter().write(gson.toJson(list));
	}
}
