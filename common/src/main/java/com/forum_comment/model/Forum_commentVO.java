package com.forum_comment.model;

import java.sql.Date;

public class Forum_commentVO implements java.io.Serializable{
	private Integer comment_no;
	private Integer article_no;
	private Integer member_no;
	private Date comment_time;
	private String comment_content;
	private Integer comment_status;
	
	public Integer getComment_no() {
		return comment_no;
	}
	public void setComment_no(Integer comment_no) {
		this.comment_no = comment_no;
	}
	public Integer getArticle_no() {
		return article_no;
	}
	public void setArticle_no(Integer article_no) {
		this.article_no = article_no;
	}
	public Integer getMember_no() {
		return member_no;
	}
	public void setMember_no(Integer member_no) {
		this.member_no = member_no;
	}
	public Date getComment_time() {
		return comment_time;
	}
	public void setComment_time(Date comment_time) {
		this.comment_time = comment_time;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Integer getComment_status() {
		return comment_status;
	}
	public void setComment_status(Integer comment_status) {
		this.comment_status = comment_status;
	}
	
}
