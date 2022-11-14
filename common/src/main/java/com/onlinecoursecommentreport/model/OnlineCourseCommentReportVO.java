package com.onlinecoursecommentreport.model;

import com.onlinecoursecomment.model.OnlineCourseCommentService;
import com.onlinecoursecomment.model.OnlineCourseCommentVO;

public class OnlineCourseCommentReportVO {
	private Integer reportNo;
	private Integer memberNo;
	private Integer commentNo;
	private String reportReason;
	private Integer reportStatus;
	
	public Integer getReportNo() {
		return reportNo;
	}
	public void setReportNo(Integer reportNo) {
		this.reportNo = reportNo;
	}
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public Integer getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(Integer commentNo) {
		this.commentNo = commentNo;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public Integer getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}
	public String tosString() {
		return reportReason;
	}
	// for join memberAccount from memberNo
	public com.mem.model.MemberVO getMemberVO() {
		   	com.mem.model.MemService memberSvc = new com.mem.model.MemService();
		   	com.mem.model.MemberVO memberVO = memberSvc.getOneOnlineCourse(memberNo);
		   	return memberVO;
	}
	// for join courseContent from commentNo
	 public OnlineCourseCommentVO getOnlineCourseCommentVO() {
		    OnlineCourseCommentService onlineCourseCommentSvc = new OnlineCourseCommentService();
		    OnlineCourseCommentVO onlineCourseCommentVO = onlineCourseCommentSvc.getOneOnlineCourseComment(commentNo);
		    return onlineCourseCommentVO;
	  }	
}