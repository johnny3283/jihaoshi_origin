package com.forum_article.model;

import java.util.List;

public class Forum_articleService {

	private Forum_articleDAO_interface dao;
	
	public Forum_articleService() {
		dao = new Forum_articleJDBCDAO();
	}
	
	public Forum_articleVO addForum_article(String article_name, Integer member_no, String  article_content) {
	
		Forum_articleVO forum_articleVO = new Forum_articleVO();
		forum_articleVO.setArticle_name(article_name);
		forum_articleVO.setMember_no(member_no);
		forum_articleVO.setArticle_content(article_content);
		dao.insert(forum_articleVO);
		return forum_articleVO;
	}

	public Forum_articleVO updateForum_article( String article_name, String article_content, Integer article_status, Integer article_no) {
		Forum_articleVO forum_articleVO = new Forum_articleVO();
		forum_articleVO.setArticle_name(article_name);
		forum_articleVO.setArticle_content(article_content);
		forum_articleVO.setArticle_status(article_status);
		forum_articleVO.setArticle_no(article_no);
		dao.update(forum_articleVO);
		return forum_articleVO;
		
	
		
	}

	public void deleteForum_article(Integer article_no) {
		dao.delete(article_no);
	}

	public Forum_articleVO getOneForum_article(Integer article_no) {
		return dao.findByPrimarykey(article_no);
	}

	public List<Forum_articleVO> getAll() {
		return dao.getAll();
	}

	
	
	
}
