package com.phyCourseComment.model;

import java.io.Serializable;

import com.course.model.PhyCouService;
import com.course.model.PhyCouVO;
import com.mem.model.*;


public class phyCourseCommentVO implements Serializable {
	private Integer commentNo;
	private Integer memberNo;
	private Integer courseNo;
	private String courseName;
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}	
	// for join memberAccount from memberNo
  public MemberVO getMemberVO() {
	   	MemService memberSvc = new MemService();
	   	MemberVO memberVO = memberSvc.getOnePhyCourse(memberNo);
	   	return memberVO;
    }
	// for join courseName from courseNo
  public PhyCouVO PhyCouVO() {
	    PhyCouService PhyCouSvc = new PhyCouService();
	    PhyCouVO PhyCouVO = PhyCouSvc.getOneCou(courseNo);
	    return PhyCouVO;
  }
}