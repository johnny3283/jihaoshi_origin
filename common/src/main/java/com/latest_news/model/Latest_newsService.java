package com.latest_news.model;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.query.parser.Part;

public class Latest_newsService {

	private Latest_newsDAO_interface dao;

	public Latest_newsService() {
		dao = new Latest_newsJDBCDAO();
	}

	public Latest_newsVO addLatest_news(String news_name, String news_content, byte[] news_pic) {
		Latest_newsVO latest_newsVO = new Latest_newsVO();
		latest_newsVO.setNews_name(news_name);
		latest_newsVO.setNews_content(news_content);
		latest_newsVO.setNews_pic(news_pic);
		dao.insert(latest_newsVO);
		return latest_newsVO;
	}

	public Latest_newsVO updateLatest_news( String news_name, String news_content, Integer news_no) {
		Latest_newsVO latest_newsVO = new Latest_newsVO();
		latest_newsVO.setNews_name(news_name);
		latest_newsVO.setNews_content(news_content);
		latest_newsVO.setNews_no(news_no);
		dao.update(latest_newsVO);
		return latest_newsVO;
	}

	public void deleteLatest_news(Integer news_no) {
		dao.delete(news_no);
	}

	public Latest_newsVO getOneLatest_news(Integer news_no) {
		return dao.findByPrimarykey(news_no);
	}

	public List<Latest_newsVO> getAll() {
		return dao.getAll();
	}
//	public void uploadfileLatest_news(InputStream in) {
//		dao.uploadfile(in);
//	}
		
		
	}

