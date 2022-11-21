package com.forum_article_report.model;

import java.util.*;

public interface Forum_article_reportDAO_interface {
	 public void insert(Forum_article_reportVO forum_article_reportVO);
     public void update(Forum_article_reportVO forum_article_reportVO);
     public void delete(Integer article_report_no);
     public void change_status_0(Integer article_report_no);
     public void change_status_1(Integer article_report_no);
     public void change_status_2(Integer article_report_no);
     public Forum_article_reportVO findByPrimaryKey(Integer article_report_no);
     public List<Forum_article_reportVO> getAll(Integer memberNo);
     public List<Forum_article_reportVO> getAll();
}
