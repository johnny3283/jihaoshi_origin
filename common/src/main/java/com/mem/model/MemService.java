package com.mem.model;

import java.util.List;


import com.phyCourseComment.model.phyCourseCommentVO;
import com.phyCourseCommentReport.model.phyCourseCommentReportVO;

import com.onlinecoursecomment.model.OnlineCourseCommentVO;
import com.onlinecoursecommentreport.model.OnlineCourseCommentReportVO;




public class MemService {
	private MemberDAO_interface dao;

	public MemService() {
		dao = new MemberDAO();
//		dao = new MemberJDBCDAO();
	}

	public MemberVO addmember(String memberAccount, String memberPassword, String memberName, String memberPhone,
			String memberNickname, String memberAddress, String memberEmail) {

		MemberVO MemberVO = new MemberVO();
		MemberVO.setMemberAccount(memberAccount);
		MemberVO.setMemberPassword(memberPassword);
		MemberVO.setMemberName(memberName);
		MemberVO.setMemberPhone(memberPhone);
		MemberVO.setMemberNickname(memberNickname);
		MemberVO.setMemberAddress(memberAddress);
		MemberVO.setMemberEmail(memberEmail);

		dao.insert(MemberVO);

		return MemberVO;
	}

	public MemberVO updateEmp(String memberAccount, Integer memberNo, String memberPassword, String memberName,
			String memberPhone, String memberNickname, String memberAddress, String memberEmail) {

		MemberVO MemberVO = new MemberVO();
		MemberVO.setMemberAccount(memberAccount);
		MemberVO.setMemberNo(memberNo);
		MemberVO.setMemberPassword(memberPassword);
		MemberVO.setMemberName(memberName);
		MemberVO.setMemberPhone(memberPhone);
		MemberVO.setMemberNickname(memberNickname);
		MemberVO.setMemberAddress(memberAddress);
		MemberVO.setMemberEmail(memberEmail);
		dao.update(MemberVO);
		return MemberVO;
	}

	public void deleteEmp(Integer empno) {
		dao.delete(empno);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}

	public MemberVO getOneMem(Integer memberNo) {
		return dao.findByPrimaryKey(memberNo);
	}

	public MemberVO Login(String memberAccount, String memberPassword) {
		return dao.selectForLogin(memberAccount, memberPassword);
	}

//	public MemberVO findByAccount(String memberAccount) {
//		return dao.findByAccount(memberAccount);
//	}
	public MemberVO findByEmail(String memberEmail) {
		return dao.findByEmail(memberEmail);
	}

	public MemberVO getOnePhyCourse(Integer memberNo) {
		return dao.findByPrimaryKey(memberNo);
	}


	public List<phyCourseCommentVO> getPhyCourseCommentsByMemberNo(Integer memberNo) {
		return dao.getPhyCourseCommentsByMemberNo(memberNo);
	}

	public List<phyCourseCommentReportVO> getPhyCourseCommentReportsByMemberNo(Integer memberNo) {
		return dao.getPhyCourseCommentReportsByMemberNo(memberNo);}

	public MemberVO findByAccount(String memberAccount) {
		return dao.findByAccount(memberAccount);
	}
	public MemberVO getOneOnlineCourse(Integer memberNo) {
		return dao.findByPrimaryKey(memberNo);
	}
	public List<OnlineCourseCommentVO> getOnlineCourseCommentsByMemberNo(Integer memberNo) {
		return dao.getOnlineCourseCommentsByMemberNo(memberNo);
	}
	public List<OnlineCourseCommentReportVO> getOnlineCourseCommentReportsByMemberNo(Integer memberNo) {
		return dao.getOnlineCourseCommentReportsByMemberNo(memberNo);

	}
}
