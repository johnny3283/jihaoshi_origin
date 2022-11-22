package com.phyCourseComment.model;

import java.util.List;


public interface phyCourseCommentDAO_interface{
	
	public void insert(phyCourseCommentVO phyCourseCommentVO);
	
    public void update(phyCourseCommentVO phyCourseCommentVO);
    
    public void updateStatus(phyCourseCommentVO phyCourseCommentVO);
    
    public void delete(Integer commentNo);
    
    public phyCourseCommentVO findByPrimaryKey(Integer commentNo);
    
    public List<phyCourseCommentVO> getAll();
    // 查詢某課程的所有評價(一對多)(回傳 Set)
    public List<phyCourseCommentVO> getPhyCommentsByCourseNo(Integer courseNo);
    // 查詢某會員寫的所有評價(一對多)(回傳 Set)
    public List<phyCourseCommentVO> getPhyCommentsByMemberNo(Integer memberNo);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //public List<> getAll(Map<String, String[]> map); 
}