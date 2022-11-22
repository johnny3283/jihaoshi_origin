package com.forum_comment_report.model;

public class Forum_comment_reportVO implements java.io.Serializable{
	private Integer comment_report_no;
	private Integer comment_no;
	private Integer article_no;
	private String article_name;
	private Integer member_no;
	private String report_reason;
	private Integer report_status;
	
	public String getArticle_name() {
		return article_name;
	}
	public void setArticle_name(String article_name) {
		this.article_name = article_name;
	}
	public Integer getComment_report_no() {
		return comment_report_no;
	}
	public void setComment_report_no(Integer comment_report_no) {
		this.comment_report_no = comment_report_no;
	}
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
	public String getReport_reason() {
		return report_reason;
	}
	public void setReport_reason(String report_reason) {
		this.report_reason = report_reason;
	}
	public Integer getReport_status() {
		return report_status;
	}
	public void setReport_status(Integer report_status) {
		this.report_status = report_status;
	}
	
	

}
