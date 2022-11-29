package com.phyCouPromotionDetail.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.course.model.PhyCouVO;
import com.phyCouPromotion.model.PhyCouPromotionVO;

public class PhyCouPromotionDetailService {

	private PhyCouPromotionDetailDAO_interface dao;

	public PhyCouPromotionDetailService() {
		dao = new PhyCouPromotionDetailHibernateDAO();
	}

	public PhyCouPromotionDetailVO addProDetail(Integer project_no, Integer course_no, Integer prom_price) {
		
		PhyCouPromotionDetailVO phyCouPromotionDetailVO = new PhyCouPromotionDetailVO();
	
		PhyCouPromotionVO phyCouPromotionlVO = new PhyCouPromotionVO();
		phyCouPromotionlVO.setProject_no(project_no);
		phyCouPromotionDetailVO.setPhyCouPromotionVO(phyCouPromotionlVO);
		
		PhyCouVO phyCouVO = new PhyCouVO();
		phyCouVO.setCourse_no(course_no);
		phyCouPromotionDetailVO.setPhyCouVO(phyCouVO);
		
		phyCouPromotionDetailVO.setProm_price(prom_price);

		dao.insert(phyCouPromotionDetailVO);

		return phyCouPromotionDetailVO;
	}

	
	public PhyCouPromotionDetailVO updateProDetail(Integer project_no, Integer course_no, Integer prom_price) {

		PhyCouPromotionDetailVO phyCouPromotionDetailVO = new PhyCouPromotionDetailVO();
		PhyCouPromotionVO phyCouPromotionVO = new PhyCouPromotionVO();
		PhyCouVO phyCouVO = new PhyCouVO();
		
		phyCouPromotionVO.setProject_no(project_no);
		phyCouPromotionDetailVO.setPhyCouPromotionVO(phyCouPromotionVO);
		phyCouVO.setCourse_no(course_no);
		phyCouPromotionDetailVO.setPhyCouVO(phyCouVO);
		phyCouPromotionDetailVO.setProm_price(prom_price);

		dao.update(phyCouPromotionDetailVO);

		return phyCouPromotionDetailVO;
	}

	public void deleteOneCou(Integer project_no, Integer course_no) {
		dao.deleteOneCou(project_no, course_no);
	}

	public void deleteOnePro(Integer project_no) {
		dao.deleteOnePro(project_no);
	}
	
	public List<PhyCouPromotionDetailVO> getOneDetail(Integer project_no, Integer course_no) {
		return dao.findByPrimaryKey(project_no, course_no);
	}

	public Integer getMinPrice(Integer course_no) {
		return dao.getMinPrice(course_no);
	}
	
	public List<PhyCouPromotionDetailVO> getAll() {
		return dao.getAll();
	}
	
//	public List<PhyCouPromotionDetailVO> getAll(Map<String, String[]> map) {
//		return dao.getAll(map);
//	}
}
