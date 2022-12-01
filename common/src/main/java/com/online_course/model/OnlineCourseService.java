package com.online_course.model;

import java.util.Base64;
import java.util.Base64.Encoder;



import java.util.List;

import com.onlinecoursecomment.model.OnlineCourseCommentVO;

public class OnlineCourseService {
	private OnlineCourseDAO_interface dao;

	public OnlineCourseService() {
		dao = new OnlineCourseJDBCDAO();
	}

	public void save(OnlineCourseVO onlineCourseVO) {
		dao.insert(onlineCourseVO);
	}

	public void updateOnlineCourse(OnlineCourseVO onlineCourseVO) {
		dao.update(onlineCourseVO);
	}

	public void deleteOnlineCourse(OnlineCourseVO onlineCourseVO) {
		dao.delete(onlineCourseVO);
	}

	public OnlineCourseVO getOneOnlineCourse(Integer courseNo) {
		OnlineCourseVO vo = dao.findByPrimaryKey(courseNo);
		Encoder encoder = Base64.getEncoder();
		byte[] photo = vo.getOnlineCoursePhoto();
		if (photo != null) {
			String photoBase64Str = encoder.encodeToString(photo);
			vo.setOnlineCoursePhotoBaseStr64(photoBase64Str);
		}	
		return vo;
	}

	public List<OnlineCourseVO> getAll() {
		Encoder encoder = Base64.getEncoder();
		List<OnlineCourseVO> list = dao.getAll();
		for (OnlineCourseVO vo : list) {
			byte[] photo = vo.getOnlineCoursePhoto();
			if (photo == null) {
				continue;
			}
			String photoBase64Str = encoder.encodeToString(photo);
			vo.setOnlineCoursePhotoBaseStr64(photoBase64Str);
		}
		return list;
	}
	
	public List<OnlineCourseVO> getStatusOnAll() {
		Encoder encoder = Base64.getEncoder();
		List<OnlineCourseVO> list = dao.getStatusOnAll();
		for (OnlineCourseVO vo : list) {
			byte[] photo = vo.getOnlineCoursePhoto();
			if (photo == null) {
				continue;
			}
			String photoBase64Str = encoder.encodeToString(photo);
			vo.setOnlineCoursePhotoBaseStr64(photoBase64Str);
		}
		return list;
	}
	
	public List<OnlineCourseVO> getByCourseName(String courseName) {
		Encoder encoder = Base64.getEncoder();
		List<OnlineCourseVO> list = dao.selectByCourseName(courseName);
		for (OnlineCourseVO vo : list) {
			byte[] photo = vo.getOnlineCoursePhoto();
			if (photo == null) {
				continue;
			}
			String photoBase64Str = encoder.encodeToString(photo);
			vo.setOnlineCoursePhotoBaseStr64(photoBase64Str);
		}
		return list;
	}
	
    public boolean courseSwitch(Integer courseNo, Integer courseStatus) {
        return dao.courseSwitch(courseNo,courseStatus);
    }
    
    public List<OnlineCourseCommentVO> getOnlineCourseComments(Integer courseNo) {
		return dao.getOnlineCourseCommentsByOnlineCourseNo(courseNo);
	}
    
    public List<OnlineCourseVO> selectByMemId(Integer memId) {
    	Encoder encoder = Base64.getEncoder();
		List<OnlineCourseVO> list = dao.selectByMemId(memId);
		for (OnlineCourseVO vo : list) {
			byte[] photo = vo.getOnlineCoursePhoto();
			if (photo == null) {
				continue;
			}
			String photoBase64Str = encoder.encodeToString(photo);
			vo.setOnlineCoursePhotoBaseStr64(photoBase64Str);
		}
    	return list;
    }
    
    public List<OnlineCourseVO> selectByCourseNameAndMemId(String courseName, Integer memId) {
    	Encoder encoder = Base64.getEncoder();
		List<OnlineCourseVO> list = dao.selectByCourseNameAndMemId(courseName, memId);
		for (OnlineCourseVO vo : list) {
			byte[] photo = vo.getOnlineCoursePhoto();
			if (photo == null) {
				continue;
			}
			String photoBase64Str = encoder.encodeToString(photo);
			vo.setOnlineCoursePhotoBaseStr64(photoBase64Str);
		}
    	return list;
    }
    
    public List<OnlineCourseVO> getFree() {
    	Encoder encoder = Base64.getEncoder();
		List<OnlineCourseVO> list = dao.getFree();
		for (OnlineCourseVO vo : list) {
			byte[] photo = vo.getOnlineCoursePhoto();
			if (photo == null) {
				continue;
			}
			String photoBase64Str = encoder.encodeToString(photo);
			vo.setOnlineCoursePhotoBaseStr64(photoBase64Str);
		}
    	return list;
    }
}
