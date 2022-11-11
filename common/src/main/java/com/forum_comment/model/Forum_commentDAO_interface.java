package com.forum_comment.model;

import java.util.*;

public interface Forum_commentDAO_interface {
	public void insert(Forum_commentVO forum_commentVO);
	public void update(Forum_commentVO forum_commentVO);
	public void delete(Integer comment_no);
	public Forum_commentVO findByPrimarykey(Integer comment_no);
	public List<Forum_commentVO> getAll(Integer article_no);
}
