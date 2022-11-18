package com.forum_comment.model;

import java.sql.Date;
import java.util.List;

import com.forum_article.model.Forum_articleVO;
import com.forum_comment.model.Forum_commentDAO_interface;
import com.forum_comment.model.Forum_commentJDBCDAO;
import com.forum_comment.model.Forum_commentVO;

public class Forum_commentService {
	
		private Forum_commentDAO_interface dao;

		public Forum_commentService() {
			dao = new Forum_commentJDBCDAO();
		}

		public Forum_commentVO addForum_comment(Integer article_no, Integer member_no,
				String comment_content) {

			Forum_commentVO forum_commentVO = new Forum_commentVO();
			forum_commentVO.setArticle_no(article_no);
			forum_commentVO.setMember_no(member_no);
			forum_commentVO.setComment_content(comment_content);
			dao.insert(forum_commentVO);
			return forum_commentVO;
		}

		public Forum_commentVO updateForum_comment(Integer article_no, Integer member_no,
			String comment_content, Integer comment_status) {
			Forum_commentVO forum_commentVO = new Forum_commentVO();
			forum_commentVO.setArticle_no(article_no);
			forum_commentVO.setMember_no(member_no);
			forum_commentVO.setComment_content(comment_content);
			forum_commentVO.setComment_status(comment_status);
			dao.update(forum_commentVO);
			return forum_commentVO;

		}

		public void deleteForum_comment(Integer comment_no) {
			dao.delete(comment_no);
		}
		
		public void change_status_0(Integer comment_no) {
			dao.change_status_0(comment_no);
		}
		public void change_status_1(Integer comment_no) {
			dao.change_status_1(comment_no);
		}
		
		public List<Forum_commentVO> catch_display(Integer article_no) {
			return dao.catch_display(article_no);
		}

		public Forum_commentVO getOneForum_comment(Integer comment_no) {
			return dao.findByPrimarykey(comment_no);
		}

		public List<Forum_commentVO> getAll(Integer article_no) {
			return dao.getAll(article_no);
		}
		public List<Forum_commentVO> getAll() {
			return dao.getAll();
		}

	}

