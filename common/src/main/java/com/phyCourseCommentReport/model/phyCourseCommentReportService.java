package com.phyCourseCommentReport.model;

import java.util.List;

public class phyCourseCommentReportService {

	private phyCourseCommentReportDAO_interface dao;

	public phyCourseCommentReportService() {
		dao = new phyCourseCommentReportDAO();
	}
	// 新增
	public phyCourseCommentReportVO addPhyCourseCommentReport(Integer memberNo, Integer commentNo, String reportReason) {

		phyCourseCommentReportVO phyCourseCommentReportVO = new phyCourseCommentReportVO();

		phyCourseCommentReportVO.setMemberNo(memberNo);
		phyCourseCommentReportVO.setCommentNo(commentNo);
		phyCourseCommentReportVO.setReportReason(reportReason);
		dao.insert(phyCourseCommentReportVO);

		return phyCourseCommentReportVO;
	}
	// 查詢
	public phyCourseCommentReportVO getOnePhyCourseCommentReport(Integer reportNo) {
		return dao.findByPrimaryKey(reportNo);	
	}

	// 刪除
	public void deletePhyCourseCommentReport(Integer reportNo) {
		dao.delete(reportNo);
	}
	// 查詢(狀態)
	public List<phyCourseCommentReportVO> getPhycommentReportsByReportStatus(Integer reportStatus){
		return dao.getPhycommentReportsByReportStatus(reportStatus);
	}
	// 查詢(會員)
	public List<phyCourseCommentReportVO> getPhyCommentReportsByMemberNo(Integer memberNo){
		return dao.getPhyCommentReportsByMemberNo(memberNo);
	}
	// 查詢(全部)
	public List<phyCourseCommentReportVO> getAll() {
		return dao.getAll();
	}
	public phyCourseCommentReportVO updateReportStatus(Integer reportNo,Integer reportStatus) {
		phyCourseCommentReportVO phyCourseCommentReportVO = new phyCourseCommentReportVO();
		phyCourseCommentReportVO.setReportNo(reportNo);
		phyCourseCommentReportVO.setReportStatus(reportStatus);
		dao.updateStatus(phyCourseCommentReportVO);
		
		return phyCourseCommentReportVO;
	}
}