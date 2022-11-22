package com.forum_comment_report.model;

import java.util.*;

public interface Forum_comment_reportDAO_interface {
	public void insert(Forum_comment_reportVO forum_comment_reportVO);
	public void update(Forum_comment_reportVO forum_comment_reportVO);
	public void delete(Integer comment_report_no);
	public void change_status_0(Integer comment_report_no);
	public void change_status_1(Integer comment_report_no);
	public void change_status_2(Integer comment_report_no);
	public Forum_comment_reportVO findByPrimarykey(Integer comment_report_no);
	public List<Forum_comment_reportVO> getAll(Integer memberNo);
	public List<Forum_comment_reportVO> getAll();
}
 