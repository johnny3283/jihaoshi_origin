package com.phyCourseCommentReport.model;

import java.util.List;

public interface phyCourseCommentReportDAO_interface {
	
	public void insert(phyCourseCommentReportVO phyCourseCommentReportVO);

	public void updateStatus(phyCourseCommentReportVO phyCourseCommentReportVO);

	public void delete(Integer reportNo);

	public phyCourseCommentReportVO findByPrimaryKey(Integer reportNo);
	public List<phyCourseCommentReportVO> getAll();
	// 查詢某會員所有被檢舉的文章(一對多)(回傳 Set)
	public List<phyCourseCommentReportVO> getPhyCommentReportsByMemberNo(Integer memberNo);
	// 查詢某狀態的所有檢舉文章(一對多)(回傳 Set)
	public List<phyCourseCommentReportVO> getPhycommentReportsByReportStatus(Integer reportStatus);
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	// public List<> getAll(Map<String, String[]> map);
}