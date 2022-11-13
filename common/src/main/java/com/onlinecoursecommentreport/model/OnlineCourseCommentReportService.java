package com.onlinecoursecommentreport.model;

import java.util.List;

public class OnlineCourseCommentReportService {

	private OnlineCourseCommentReportDAO_interface dao;

	public OnlineCourseCommentReportService() {
		dao = new OnlineCourseCommentReportDAO();
	}
	// 新增
	public OnlineCourseCommentReportVO addOnlineCourseCommentReport(Integer memberNo, Integer commentNo, String reportReason) {

		OnlineCourseCommentReportVO onlineCourseCommentReportVO = new OnlineCourseCommentReportVO();

		onlineCourseCommentReportVO.setMemberNo(memberNo);
		onlineCourseCommentReportVO.setCommentNo(commentNo);
		onlineCourseCommentReportVO.setReportReason(reportReason);
		dao.insert(onlineCourseCommentReportVO);

		return onlineCourseCommentReportVO;
	}
	// 查詢
	public OnlineCourseCommentReportVO getOneOnlineCourseCommentReport(Integer reportNo) {
		return dao.findByPrimaryKey(reportNo);	
	}
//	public OnlineCourseCommentVO updateOnlineCourseComment(Integer commentNo,Integer memberNo,Integer courseNo,String commentCentent,Integer commentScore) {
//
//		OnlineCourseCommentVO onlineCourseCommentVO = new OnlineCourseCommentVO();
//		onlineCourseCommentVO.setCommentNo(commentNo);
//		onlineCourseCommentVO.setMemberNo(memberNo);
//		onlineCourseCommentVO.setCourseNo(courseNo);
//		onlineCourseCommentVO.setCommentContent(commentCentent);
//		onlineCourseCommentVO.setCommentScore(commentScore);
//		dao.update(onlineCourseCommentVO);
//
//		return onlineCourseCommentVO;
//	}
	// 刪除
	public void deleteOnlineCourseCommentReport(Integer reportNo) {
		dao.delete(reportNo);
	}
	// 查詢(狀態)
	public List<OnlineCourseCommentReportVO> getOnlinecommentReportsByReportStatus(Integer reportStatus){
		return dao.getOnlinecommentReportsByReportStatus(reportStatus);
	}
	// 查詢(會員)
	public List<OnlineCourseCommentReportVO> getOnlineCommentReportsByMemberNo(Integer memberNo){
		return dao.getOnlineCommentReportsByMemberNo(memberNo);
	}
	// 查詢(全部)
	public List<OnlineCourseCommentReportVO> getAll() {
		return dao.getAll();
	}
	public OnlineCourseCommentReportVO updateReportStatus(Integer reportNo,Integer reportStatus) {
		OnlineCourseCommentReportVO onlineCourseCommentReportVO = new OnlineCourseCommentReportVO();
		onlineCourseCommentReportVO.setReportNo(reportNo);
		onlineCourseCommentReportVO.setReportStatus(reportStatus);
		dao.updateStatus(onlineCourseCommentReportVO);
		
		return onlineCourseCommentReportVO;
	}
}