package com.latest_news.model;
import java.sql.Date;

public class Latest_newsVO implements java.io.Serializable{
	private Integer news_no;
	private String news_name;
	private Date update_date;
	private String news_content;
	
	public Integer getNews_no() {
		return news_no;
	}
	public void setNews_no(Integer news_no) {
		this.news_no = news_no;
	}
	public String getNews_name() {
		return news_name;
	}
	public void setNews_name(String news_name) {
		this.news_name = news_name;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	
}
