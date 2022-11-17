package com.phyCouPromotionDetail.model;

import java.util.List;
import java.util.Map;

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
		
		PhyCouPromotionVO phyCouPromotionlVO = new PhyCouPromotionVO();
		phyCouPromotionlVO.setProject_no(project_no);
		phyCouPromotionDetailVO.setPhyCouPromotionVO(phyCouPromotionlVO);
		
		PhyCouVO phyCouVO = new PhyCouVO();
		phyCouVO.setCourse_no(course_no);
		phyCouPromotionDetailVO.setPhyCouVO(phyCouVO);
		
		phyCouPromotionDetailVO.setProm_price(prom_price);

		dao.update(phyCouPromotionDetailVO);

		return phyCouPromotionDetailVO;
	}

	public void deletePro(Integer project_no) {
		dao.delete(project_no);
	}

	public PhyCouPromotionDetailVO getOnePro(Integer project_no) {
		return dao.findByPrimaryKey(project_no);
	}

	public List<PhyCouPromotionDetailVO> getAll() {
		return dao.getAll();
	}
	
//	public List<PhyCouPromotionDetailVO> getAll(Map<String, String[]> map) {
//		return dao.getAll(map);
//	}
}
