package com.phyCourseCommentReport.model;

import com.mem.model.*;
import com.phyCourseComment.model.phyCourseCommentService;
import com.phyCourseComment.model.phyCourseCommentVO;

public class phyCourseCommentReportVO {
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
	public MemberVO getMemberVO() {
		   	MemService memberSvc = new MemService();
		   	MemberVO memberVO = memberSvc.getOnePhyCourse(memberNo);
		   	return memberVO;
	}
	// for join courseContent from commentNo
	 public phyCourseCommentVO getPhyCourseCommentVO() {
		    phyCourseCommentService onlineCourseCommentSvc = new phyCourseCommentService();
		    phyCourseCommentVO onlineCourseCommentVO = onlineCourseCommentSvc.getPhyCourseComment(commentNo);
		    return onlineCourseCommentVO;
	  }	
}