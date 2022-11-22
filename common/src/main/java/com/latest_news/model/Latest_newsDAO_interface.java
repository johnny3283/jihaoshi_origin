package com.latest_news.model;

import java.io.InputStream;
import java.util.*;

public interface Latest_newsDAO_interface {
	public void insert(Latest_newsVO latest_newsVO);
	public void update(Latest_newsVO latest_newsVO);
	public void delete(Integer news_no);
	public Latest_newsVO findByPrimarykey(Integer news_no);
	public List<Latest_newsVO> findByNewsName(String newsName);
	public List<Latest_newsVO> getAll();
//	public void uploadfile(InputStream in);
	 
}
