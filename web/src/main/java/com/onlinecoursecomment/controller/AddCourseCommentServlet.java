package com.onlinecoursecomment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.mem.model.MemberVO;
import com.onlinecoursecomment.model.OnlineCourseCommentService;
import com.onlinecoursecomment.model.OnlineCourseCommentVO;

@WebServlet("/courseComment/add")
public class AddCourseCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Gson gson = new Gson();
			OnlineCourseCommentService service = new OnlineCourseCommentService();
			OnlineCourseCommentVO vo = gson.fromJson(req.getReader(), OnlineCourseCommentVO.class);
			HttpSession session = req.getSession();
//			MemberVO memberVO = (MemberVO) session.getAttribute("member");
//			Integer memberNo = memberVO.getMemberNo();
			vo.setMemberNo(1);
			service.addOnlineCourseComment(vo);
			resp.getWriter().append("{\"successful\": true}");
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().append("{\"successful\": false}");
		}
	}
}
