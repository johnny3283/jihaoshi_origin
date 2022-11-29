package com.phyCouPromotionDetail.model;

import org.hibernate.*;
import org.hibernate.query.Query;

import com.course.model.PhyCouVO;
import com.phyCouPromotion.model.PhyCouPromotionVO;
import hibernate.util.HibernateUtil;
//import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Emp2;

import java.util.*;

public class PhyCouPromotionDetailHibernateDAO implements PhyCouPromotionDetailDAO_interface {

	private static final String GET_ALL_STMT = "from com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO order by project_no";
	private static final String GET_ONE_STMT = "from PhyCouPromotionDetailVO where project_no=:project_no and course_no=:course_no";
	private static final String GET_MIN_PRICE_STMT = "select min(prom_price) from physical_course_promotion_detail d join physical_course_promotion_project p where p.prom_status=:prom_status and d.course_no=:course_no ";

	@Override
	public void insert(PhyCouPromotionDetailVO phyCouPromotionDetailVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(phyCouPromotionDetailVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}


	@Override
	public void update(PhyCouPromotionDetailVO phyCouPromotionDetailVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<PhyCouPromotionDetailVO> list = null;
		try {
			session.beginTransaction();
			PhyCouPromotionVO phyCouPromotionVO = new PhyCouPromotionVO();
			PhyCouVO phyCouVO = new PhyCouVO();
			Integer project_no = phyCouPromotionDetailVO.getPhyCouPromotionVO().getProject_no();
			Integer course_no = phyCouPromotionDetailVO.getPhyCouVO().getCourse_no();
//			session.saveOrUpdate(phyCouPromotionDetailVO);
			Query<PhyCouPromotionDetailVO> query = session.createQuery(GET_ONE_STMT, PhyCouPromotionDetailVO.class);
			query.setParameter("project_no", project_no);
			query.setParameter("course_no", course_no);
			list = query.getResultList();
			for ( PhyCouPromotionDetailVO vo : list) {
				vo.setProm_price(phyCouPromotionDetailVO.getProm_price());
			}
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void deleteOnePro(Integer project_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        �i���ɦh��(�y)�i�ĥ�HQL�R���j
			Query query = session.createQuery("delete from PhyCouPromotionDetailVO where project_no=:project_no");
			query.setParameter("project_no", project_no);
			query.executeUpdate();
			Query query1 = session.createQuery("delete from PhyCouPromotionVO where project_no=:project_no");
			query1.setParameter("project_no", project_no);
			query1.executeUpdate();

//        �i�Φ��ɦh��(�])�i�ĥΥh�����p���Y��A�A�R�����覡�j
//			PhyCouPromotionDetailVO phyCouPromotionDetailVO = new PhyCouPromotionDetailVO();
//			PhyCouPromotionVO phyCouPromotionVO = new PhyCouPromotionVO();
//			PhyCouVO phyCouVO = new PhyCouVO();
//			phyCouVO.setCourse_no(course_no);
//			phyCouPromotionVO.setProject_no(project_no);
//			phyCouPromotionDetailVO.setPhyCouPromotionVO(phyCouPromotionVO);
//			phyCouPromotionDetailVO.setPhyCouVO(phyCouVO);
//			session.delete(phyCouPromotionDetailVO);

//        �i���ɦh�褣�i(���y)�ĥ�cascade�p�ŧR���j
//        �i�h��emp2.hbm.xml�p�G�]�� cascade="all"�� cascade="delete"�N�|�R���Ҧ��������-�]�A���ݳ����P�P�������䥦���u�N�|�@�ֳQ�R���j
//			EmpVO empVO = (EmpVO) session.get(EmpVO.class, empno);
//			session.delete(empVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	public void deleteOneCou(Integer project_no, Integer course_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
//        �i���ɦh��(�y)�i�ĥ�HQL�R���j
			Query query = session.createQuery("delete from PhyCouPromotionDetailVO where project_no=:project_no and course_no=:course_no");
			query.setParameter("project_no", project_no);
			query.setParameter("course_no", course_no);
			query.executeUpdate();
			
//        �i�Φ��ɦh��(�])�i�ĥΥh�����p���Y��A�A�R�����覡�j
//			PhyCouPromotionDetailVO phyCouPromotionDetailVO = new PhyCouPromotionDetailVO();
//			PhyCouPromotionVO phyCouPromotionVO = new PhyCouPromotionVO();
//			phyCouPromotionVO.setProject_no(project_no);
//			phyCouPromotionDetailVO.setPhyCouPromotionVO(phyCouPromotionVO);
//			session.delete(phyCouPromotionDetailVO);
			
//        �i���ɦh�褣�i(���y)�ĥ�cascade�p�ŧR���j
//        �i�h��emp2.hbm.xml�p�G�]�� cascade="all"�� cascade="delete"�N�|�R���Ҧ��������-�]�A���ݳ����P�P�������䥦���u�N�|�@�ֳQ�R���j
//			EmpVO empVO = (EmpVO) session.get(EmpVO.class, empno);
//			session.delete(empVO);
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	@Override
	public List<PhyCouPromotionDetailVO> findByPrimaryKey(Integer project_no, Integer course_no) {
//		PhyCouPromotionDetailVO phyCouPromotionDetailVO = null;
		List<PhyCouPromotionDetailVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<PhyCouPromotionDetailVO> query = session.createQuery(GET_ONE_STMT, PhyCouPromotionDetailVO.class);
			query.setParameter("project_no", project_no);
			query.setParameter("course_no", course_no);
			list = query.getResultList();
//			phyCouPromotionDetailVO = (PhyCouPromotionDetailVO) session.get(PhyCouPromotionDetailVO.class, project_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public Integer getMinPrice(Integer course_no) {
		Integer minPrice = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createNativeQuery(GET_MIN_PRICE_STMT);
			query.setParameter("prom_status", 0);
			query.setParameter("course_no", course_no);
			minPrice  = (Integer) query.list().get(0);
//			phyCouPromotionDetailVO = (PhyCouPromotionDetailVO) session.get(PhyCouPromotionDetailVO.class, project_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return minPrice;
		
	}
	
	@Override
	public List<PhyCouPromotionDetailVO> getAll() {
		List<PhyCouPromotionDetailVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<PhyCouPromotionDetailVO> query = session.createQuery(GET_ALL_STMT, PhyCouPromotionDetailVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}


	
//	@Override
//	public List<PhyCouPromotionDetailVO> getAll(Map<String, String[]> map) {
//		List<PhyCouPromotionDetailVO> list = null;
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			list = HibernateUtil_CompositeQuery_Emp2.getAllC(map);
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//		return list;
//	}

	public static void main(String[] args) {

		PhyCouPromotionDetailHibernateDAO dao = new PhyCouPromotionDetailHibernateDAO();

		//�� �s�W
		com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO phyCouPromotionDetailVO = new com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO(); // ����POJO
		com.phyCouPromotion.model.PhyCouPromotionVO phyCouPromotionVO = new com.phyCouPromotion.model.PhyCouPromotionVO();
		com.course.model.PhyCouVO phycouVO  = new com.course.model.PhyCouVO();
		phyCouPromotionVO.setProject_no(1);
		phycouVO.setCourse_no(5);
		phyCouPromotionDetailVO.setPhyCouPromotionVO(phyCouPromotionVO);
		phyCouPromotionDetailVO.setPhyCouVO(phycouVO);
		
//		Double price = dao.getMinPrice(5);
//		System.out.println("price: "+ price);
//		
//		dao.deleteOneCou(61, 2);
//		dao.deleteOnePro(61);
	}


}
