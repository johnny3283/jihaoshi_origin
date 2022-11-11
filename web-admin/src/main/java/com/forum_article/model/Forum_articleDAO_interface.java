package com.forum_article.model;

import java.util.*;

public interface Forum_articleDAO_interface {
	public void insert(Forum_articleVO forum_articleVO);
	public void update(Forum_articleVO forum_articleVO);
	public void delete(Integer article_no);
	public Forum_articleVO findByPrimarykey(Integer article_no);
	public List<Forum_articleVO> getAll();

}