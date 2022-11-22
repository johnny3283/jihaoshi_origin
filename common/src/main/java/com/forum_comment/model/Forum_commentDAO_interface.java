package com.forum_comment.model;

import java.util.*;

import com.forum_comment.model.Forum_commentVO;

public interface Forum_commentDAO_interface {
	public void insert(Forum_commentVO forum_commentVO);
	public void update(Forum_commentVO forum_commentVO);
	public void delete(Integer comment_no);
	public void change_status_0(Integer comment_no);
	public void change_status_1(Integer comment_no);
	public List<Forum_commentVO> catch_display(Integer article_no); 
	public Forum_commentVO findByPrimarykey(Integer comment_no);
	public List<Forum_commentVO> getAll(Integer article_no);
	public List<Forum_commentVO> getAll();
}
