package com.onlinecoursecomment.model;

import com.online_course.model.OnlineCourseService;
import com.online_course.model.OnlineCourseVO;

//import com.onlinecourse.model.*;

public class OnlineCourseCommentVO implements java.io.Serializable {
	private Integer commentNo;
	private Integer memberNo;
	private Integer courseNo;
	private String commentContent;
	private Integer commentScore;
	private Integer commentStatus;
	
	public Integer getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(Integer commentnNo) {
		this.commentNo = commentnNo;
	}
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public Integer getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(Integer courseNo) {
		this.courseNo = courseNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Integer getCommentScore() {
		return commentScore;
	}
	public void setCommentScore(Integer commentScore) {
		this.commentScore = commentScore;
	}
	public Integer getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(Integer commentStatus) {
		this.commentStatus = commentStatus;
	}
	// for join memberAccount from memberNo
  public com.mem.model.MemberVO getMemberVO() {
	   	com.mem.model.MemService memberSvc = new com.mem.model.MemService();
	   	com.mem.model.MemberVO memberVO = memberSvc.getOneOnlineCourse(memberNo);
	   	return memberVO;
    }
	// for join courseName from courseNo
  public OnlineCourseVO getOnlineCourseVO() {
	    OnlineCourseService onlineCourseSvc = new OnlineCourseService();
	    OnlineCourseVO onlineCourseVO = onlineCourseSvc.getOneOnlineCourse(courseNo);
	    return onlineCourseVO;
  }	
}