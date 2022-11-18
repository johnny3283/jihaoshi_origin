package com.forum_article.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Forum_articleVO implements java.io.Serializable{
	private Integer article_no;
	private String article_name;
	private Integer member_no;
	private Date article_time;
	private String article_content;
	private Integer article_status;
	
	
	
	
	public Integer getArticle_no() {
		return article_no;
	}
	public void setArticle_no(Integer article_no) {
		this.article_no = article_no;
	}
	public String getArticle_name() {
		return article_name;
	}
	public void setArticle_name(String article_name) {
		this.article_name = article_name;
	}
	public Integer getMember_no() {
		return member_no;
	}
	public void setMember_no(Integer member_no) {
		this.member_no = member_no;
	}
	public Date getArticle_time() {
		return article_time;
	}
	public void setArticle_time(Date article_time) {
		this.article_time = article_time;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}
	public Integer getArticle_status() {
		return article_status;
	}
	public void setArticle_status(Integer article_status) {
		this.article_status = article_status;
	}
//	  // for join dname from deptno
//    public com.forum_article_report.model.Forum_article_reportVO getForum_article_reportVO() {
//    	com.forum_article_report.model.Forum_article_reportService forum_article_reportSvc = new com.forum_article_report.model.Forum_article_reportService();
//    	com.forum_article_report.model.Forum_article_reportVO forum_article_reportVO = forum_article_reportSvc.getOneForum_article_report(article_no);
//	    return forum_article_reportVO;	
//	}
}
