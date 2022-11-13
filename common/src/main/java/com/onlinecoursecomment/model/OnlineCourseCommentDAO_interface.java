package com.onlinecoursecomment.model;

import java.util.List;
import java.util.Set;


public interface OnlineCourseCommentDAO_interface{
	
	public void insert(OnlineCourseCommentVO onlineCourseCommentVO);
	
    public void update(OnlineCourseCommentVO onlineCourseCommentVO);
    
    public void updateStatus(OnlineCourseCommentVO onlineCourseCommentVO);
    
    public void delete(Integer commentNo);
    
    public OnlineCourseCommentVO findByPrimaryKey(Integer commentNo);
    
    public List<OnlineCourseCommentVO> getAll();
    // 查詢某課程的所有評價(一對多)(回傳 Set)
    public Set<OnlineCourseCommentVO> getOnlineCommentsByCourseNo(Integer courseNo);
    // 查詢某會員寫的所有評價(一對多)(回傳 Set)
    public List<OnlineCourseCommentVO> getOnlineCommentsByMemberNo(Integer memberNo);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //public List<> getAll(Map<String, String[]> map); 
}