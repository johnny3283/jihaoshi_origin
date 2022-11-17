package com.phyCouPromotion.model;

import java.util.List;
import java.util.Map;
import com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO;

public class PhyCouPromotionService {

	private PhyCouPromotionDAO_interface dao;

	public PhyCouPromotionService() {
		dao = new PhyCouPromotionHibernateDAO();
	}

	public PhyCouPromotionVO addPhyCouPromotion(String project_name, java.sql.Date start_date, 
			java.sql.Date end_date, String prom_description, Integer prom_status ) {
		
		PhyCouPromotionVO phyCouPromotionVO = new PhyCouPromotionVO();
		String tmp = "now";
		phyCouPromotionVO.setProject_name(project_name);
		phyCouPromotionVO.setStart_date(start_date);
		phyCouPromotionVO.setEnd_date(end_date);
		phyCouPromotionVO.setProm_description(prom_description);
		phyCouPromotionVO.setProm_status(prom_status);
		
		dao.insert(phyCouPromotionVO);

		return phyCouPromotionVO;
	}

	public PhyCouPromotionVO updatePhyCouPromotion(Integer project_no, String project_name, java.sql.Date start_date, 
			java.sql.Date end_date, String prom_description, Integer prom_status, java.sql.Date update_time) {

		PhyCouPromotionVO phyCouPromotionVO = new PhyCouPromotionVO();
		
		phyCouPromotionVO.setProject_no(project_no);
		phyCouPromotionVO.setProject_name(project_name);
		phyCouPromotionVO.setStart_date(start_date);
		phyCouPromotionVO.setEnd_date(end_date);
		phyCouPromotionVO.setProm_description(prom_description);
		phyCouPromotionVO.setProm_status(prom_status);
		phyCouPromotionVO.setUpdate_time(update_time);

		dao.update(phyCouPromotionVO);

		return phyCouPromotionVO;
	}

	public void deletePro(Integer project_no) {
		dao.delete(project_no);
	}

	public PhyCouPromotionVO getOnePro(Integer project_no) {
		return dao.findByPrimaryKey(project_no);
	}

	public List<PhyCouPromotionVO> getAll() {
		return dao.getAll();
	}
	
//	public List<PhyCouPromotionVO> getAll(Map<String, String[]> map) {
//		return dao.getAll(map);
//	}
}
