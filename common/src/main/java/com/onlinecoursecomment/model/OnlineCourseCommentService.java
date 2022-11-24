package com.onlinecoursecomment.model;

import java.util.List;

public class OnlineCourseCommentService {

	private OnlineCourseCommentDAO_interface dao;

	public OnlineCourseCommentService() {
		dao = new OnlineCourseCommentDAO();
	}

	public OnlineCourseCommentVO addOnlineCourseComment(OnlineCourseCommentVO vo) {
		dao.insert(vo);
		return vo;
	}

	public OnlineCourseCommentVO updateOnlineCourseComment(Integer commentNo, Integer memberNo, Integer courseNo,
			String commentCentent, Integer commentScore) {

		OnlineCourseCommentVO onlineCourseCommentVO = new OnlineCourseCommentVO();
		onlineCourseCommentVO.setCommentNo(commentNo);
		onlineCourseCommentVO.setMemberNo(memberNo);
		onlineCourseCommentVO.setCourseNo(courseNo);
		onlineCourseCommentVO.setCommentContent(commentCentent);
		onlineCourseCommentVO.setCommentScore(commentScore);
		dao.update(onlineCourseCommentVO);

		return onlineCourseCommentVO;
	}

	public void deleteOnlineCourseComment(Integer commentNo) {
		dao.delete(commentNo);
	}

	public OnlineCourseCommentVO getOneOnlineCourseComment(Integer commentNo) {
		return dao.findByPrimaryKey(commentNo);
	}

	public List<OnlineCourseCommentVO> getOnlineCourseCommentByCourseNo(Integer courseNo) {
		return dao.getOnlineCommentsByCourseNo(courseNo);
	}

	public List<OnlineCourseCommentVO> getOnlineCommentsByMemberNo(Integer memberNo) {
		return dao.getOnlineCommentsByMemberNo(memberNo);
	}

	public List<OnlineCourseCommentVO> getAll() {
		return dao.getAll();
	}

	public OnlineCourseCommentVO updateOnlineCourseCommentStatus(Integer commentNo, Integer commentStatus) {
		OnlineCourseCommentVO onlineCourseCommentVO = new OnlineCourseCommentVO();
		onlineCourseCommentVO.setCommentNo(commentNo);
		onlineCourseCommentVO.setCommentStatus(commentStatus);
		dao.updateStatus(onlineCourseCommentVO);

		return onlineCourseCommentVO;
	}
}