package com.phyCourseComment.model;

import java.util.List;


public class phyCourseCommentService {

	private phyCourseCommentDAO_interface dao;

	public phyCourseCommentService() {
		dao = new phyCourseCommentDAO();
	}

	public phyCourseCommentVO addPhyCourseComment(Integer memberno, Integer courseno, String commentcotent) {

		phyCourseCommentVO phyCourseCommentVO = new phyCourseCommentVO();

		phyCourseCommentVO.setMemberNo(memberno);
		phyCourseCommentVO.setCourseNo(courseno);
		phyCourseCommentVO.setCommentContent(commentcotent);
		dao.insert(phyCourseCommentVO);

		return phyCourseCommentVO;
	}
	public phyCourseCommentVO updatePhyCourseComment(Integer commentNo,Integer memberNo,Integer courseNo,String commentContent) {

		phyCourseCommentVO phyVO = new phyCourseCommentVO();
		phyVO.setCommentNo(commentNo);
		phyVO.setMemberNo(memberNo);
		phyVO.setCourseNo(courseNo);
		phyVO.setCommentContent(commentContent);
		dao.update(phyVO);

		return phyVO;
	}
	public void deletePhyCourseComment(Integer commentNo) {
		dao.delete(commentNo);
	}
	public phyCourseCommentVO getPhyCourseComment(Integer commentNo) {
		return dao.findByPrimaryKey(commentNo);
	}
	public List<phyCourseCommentVO> getPhyCourseCommentByCourseNo(Integer courseNo) {
		return dao.getPhyCommentsByCourseNo(courseNo);
	}
	public List<phyCourseCommentVO> getPhyCommentsByMemberNo(Integer memberNo){
		return dao.getPhyCommentsByMemberNo(memberNo);
	}
	public List<phyCourseCommentVO> getAll() {
		return dao.getAll();
	}
	public phyCourseCommentVO updatePhyCourseCommentStatus(Integer commentNo,Integer commentStatus) {
		phyCourseCommentVO phyCourseCommentVO = new phyCourseCommentVO();
		phyCourseCommentVO.setCommentNo(commentNo);
		phyCourseCommentVO.setCommentStatus(commentStatus);
		dao.updateStatus(phyCourseCommentVO);
		
		return phyCourseCommentVO;
	}
}