package com.onlinecourse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.mem.model.MemberVO;
import com.online_course.model.OnlineCourseService;
import com.online_course.model.OnlineCourseVO;

@WebServlet("/onlineCourse/findByMemId")
public class FindByMemIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OnlineCourseService service = new OnlineCourseService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		Integer memberNo = member.getMemberNo();

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		OnlineCourseVO vo = gson.fromJson(req.getReader(), OnlineCourseVO.class);
		String courseName = vo.getCourseName();
		List<OnlineCourseVO> list;
		if (courseName == null || courseName.isEmpty()) {
			list = service.selectByMemId(memberNo);
		} else {
			list = service.selectByCourseNameAndMemId(courseName, memberNo);
		}
		resp.getWriter().write(gson.toJson(list));
	}
}
