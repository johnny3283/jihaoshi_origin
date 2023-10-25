package com.mem.model;

import java.util.List;


import com.phyCourseComment.model.phyCourseCommentVO;
import com.phyCourseCommentReport.model.phyCourseCommentReportVO;
import com.onlinecoursecomment.model.OnlineCourseCommentVO;
import com.onlinecoursecommentreport.model.OnlineCourseCommentReportVO;

public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);

	public void update(MemberVO memberVO);

	public void delete(Integer memberNo);

	void mngMember(MemberVO memberVO);
	
	public MemberVO findByPrimaryKey(Integer memberNo);

	public List<MemberVO> getAll();

	public MemberVO selectForLogin(String memberAccount, String memberPassword);
	
	public MemberVO findByEmail(String memberEmail);

	public MemberVO findByAccount(String memberAccount);

}
