package com.forum_comment_report.model;

import java.util.List;

import com.forum_article.model.Forum_articleDAO_interface;
import com.forum_article.model.Forum_articleJDBCDAO;
import com.forum_article.model.Forum_articleVO;

public class Forum_comment_reportService {
	private Forum_comment_reportDAO_interface dao;

	public Forum_comment_reportService() {
		dao = new Forum_comment_reportJDBCDAO();
	}

	public Forum_comment_reportVO addForum_comment_report(Integer comment_no, Integer article_no, Integer member_no,
			String report_reason) {

		Forum_comment_reportVO forum_comment_reportVO = new Forum_comment_reportVO();
		forum_comment_reportVO.setComment_no(comment_no);
		forum_comment_reportVO.setArticle_no(article_no);
		forum_comment_reportVO.setMember_no(member_no);
		forum_comment_reportVO.setReport_reason(report_reason);
		dao.insert(forum_comment_reportVO);
		return forum_comment_reportVO;

	}

	public Forum_comment_reportVO updateForum_comment_report(String report_reason, Integer report_status,
			Integer comment_report_no) {
		Forum_comment_reportVO forum_comment_reportVO = new Forum_comment_reportVO();
		forum_comment_reportVO.setReport_reason(report_reason);
		forum_comment_reportVO.setReport_status(report_status);
		forum_comment_reportVO.setComment_report_no(comment_report_no);
		dao.update(forum_comment_reportVO);
		return forum_comment_reportVO;

	}

	public void deleteForum_comment_report(Integer comment_report_no) {
		dao.delete(comment_report_no);
	}
	
	public void change_status_0(Integer comment_report_no) {
		dao.change_status_0(comment_report_no);
	}
	public void change_status_1(Integer comment_report_no) {
		dao.change_status_1(comment_report_no);
	}
	public void change_status_2(Integer comment_report_no) {
		dao.change_status_1(comment_report_no);
	}

	public Forum_comment_reportVO getOneForum_comment_report(Integer comment_report_no) {
		return dao.findByPrimarykey(comment_report_no);
	}

	public List<Forum_comment_reportVO> getAll(Integer memberNo) {
		return dao.getAll(memberNo);
	}
	public List<Forum_comment_reportVO> getAll() {
		return dao.getAll();
	}
}
