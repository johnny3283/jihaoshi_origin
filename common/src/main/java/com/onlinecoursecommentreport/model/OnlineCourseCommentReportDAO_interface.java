package com.onlinecoursecommentreport.model;

import java.util.List;
import java.util.Set;

import com.onlinecoursecomment.model.OnlineCourseCommentVO;

public interface OnlineCourseCommentReportDAO_interface {
	
	public void insert(OnlineCourseCommentReportVO onlineCourseCommentReportVO);

	public void updateStatus(OnlineCourseCommentReportVO onlineCourseCommentReportVO);

	public void delete(Integer reportNo);

	public OnlineCourseCommentReportVO findByPrimaryKey(Integer reportNo);
	public List<OnlineCourseCommentReportVO> getAll();
	// 查詢某會員所有被檢舉的文章(一對多)(回傳 Set)
	public List<OnlineCourseCommentReportVO> getOnlineCommentReportsByMemberNo(Integer memberNo);
	// 查詢某狀態的所有檢舉文章(一對多)(回傳 Set)
	public List<OnlineCourseCommentReportVO> getOnlinecommentReportsByReportStatus(Integer reportStatus);
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	// public List<> getAll(Map<String, String[]> map);
}