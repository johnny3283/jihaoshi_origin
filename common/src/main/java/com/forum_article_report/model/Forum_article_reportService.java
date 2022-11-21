package com.forum_article_report.model;

import java.util.List;

import com.forum_article.model.Forum_articleDAO_interface;
import com.forum_article.model.Forum_articleJDBCDAO;
import com.forum_article.model.Forum_articleVO;

public class Forum_article_reportService {
private Forum_article_reportDAO_interface dao;
	
	public Forum_article_reportService() {
		dao = new Forum_article_reportJDBCDAO();
	}
	
	public Forum_article_reportVO addForum_article_report(Integer article_no, Integer member_no, String  report_reason) {
	
		Forum_article_reportVO forum_article_reportVO = new Forum_article_reportVO();
		
		forum_article_reportVO.setArticle_no(article_no);
		forum_article_reportVO.setMember_no(member_no);
		forum_article_reportVO.setReport_reason(report_reason);
		dao.insert(forum_article_reportVO);
		return forum_article_reportVO;
	}

	public Forum_article_reportVO updateForum_article_report( Integer article_no, Integer member_no, String report_reason, Integer report_status, Integer article_report_no) {
		Forum_article_reportVO forum_article_reportVO = new Forum_article_reportVO();
		forum_article_reportVO.setArticle_no(article_no);
		forum_article_reportVO.setMember_no(member_no);
		forum_article_reportVO.setReport_reason(report_reason);
		forum_article_reportVO.setReport_status(report_status);
		forum_article_reportVO.setArticle_report_no(article_report_no);
		dao.update(forum_article_reportVO);
		return forum_article_reportVO;
		
	
		
	}

	public void deleteForum_article_report(Integer article_report_no) {
		dao.delete(article_report_no);
	}
	
	public void change_status_0(Integer article_report_no) {
		dao.change_status_0(article_report_no);
	}
	public void change_status_1(Integer article_report_no) {
		dao.change_status_1(article_report_no);
	}
	public void change_status_2(Integer article_report_no) {
		dao.change_status_1(article_report_no);
	}

	public Forum_article_reportVO getOneForum_article_report(Integer article_report_no) {
		return dao.findByPrimaryKey(article_report_no);
	}
	
	public List<Forum_article_reportVO> getAll(Integer memberNo) {
		return dao.getAll(memberNo);
	}
	
	public List<Forum_article_reportVO> getAll() {
		return dao.getAll();
	}

}
