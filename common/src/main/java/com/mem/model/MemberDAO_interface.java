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

	public MemberVO selectForLogin(String mamberAccount, String mamberPassword);
	
	public MemberVO findByEmail(String memberEmail);

	public List<phyCourseCommentVO> getPhyCourseCommentsByMemberNo(Integer memberNo);

	public List<phyCourseCommentReportVO> getPhyCourseCommentReportsByMemberNo(Integer memberNo);

	public MemberVO findByAccount(String memberAccount);
  
	public List<OnlineCourseCommentVO> getOnlineCourseCommentsByMemberNo(Integer memberNo);
  
	public List<OnlineCourseCommentReportVO> getOnlineCourseCommentReportsByMemberNo(Integer memberNo);



}
