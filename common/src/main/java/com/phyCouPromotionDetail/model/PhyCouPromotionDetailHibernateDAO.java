package com.phyCouPromotionDetail.model;

import org.hibernate.*;
import org.hibernate.query.Query; 
import com.phyCouPromotion.model.PhyCouPromotionVO;
import hibernate.util.HibernateUtil;
//import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Emp2;

import java.util.*;

public class PhyCouPromotionDetailHibernateDAO implements PhyCouPromotionDetailDAO_interface {

	private static final String GET_ALL_STMT = "from com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO order by project_no";

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
	public void delete(Integer project_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        �i���ɦh��(�y)�i�ĥ�HQL�R���j
//			Query query = session.createQuery("delete EmpVO where empno=?0");
//			query.setParameter(0, empno);
//			System.out.println("�R��������=" + query.executeUpdate());

//        �i�Φ��ɦh��(�])�i�ĥΥh�����p���Y��A�A�R�����覡�j
			PhyCouPromotionDetailVO phyCouPromotionDetailVO = new PhyCouPromotionDetailVO();
			PhyCouPromotionVO phyCouPromotionVO = new PhyCouPromotionVO();
			phyCouPromotionVO.setProject_no(project_no);
			phyCouPromotionDetailVO.setPhyCouPromotionVO(phyCouPromotionVO);
			session.delete(phyCouPromotionDetailVO);

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
	public PhyCouPromotionDetailVO findByPrimaryKey(Integer project_no) {
		PhyCouPromotionDetailVO phyCouPromotionDetailVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			phyCouPromotionDetailVO = (PhyCouPromotionDetailVO) session.get(PhyCouPromotionDetailVO.class, project_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return phyCouPromotionDetailVO;
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

//	public static void main(String[] args) {
//
//		PhyCouPromotionDetailHibernateDAO dao = new PhyCouPromotionDetailHibernateDAO();
//
//		//�� �s�W
//		com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO phyCouPromotionDetailVO = new com.phyCouPromotionDetail.model.PhyCouPromotionDetailVO(); // ����POJO
//		com.phyCouPromotion.model.PhyCouPromotionVO phyCouPromotionVO = new com.phyCouPromotion.model.PhyCouPromotionVO();
//		phyCouPromotionVO.setProject_no(1);
//		phyCouPromotionDetailVO.setPhyCouPromotionVO(phyCouPromotionVO);

//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEname("�d�ç�1");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000));
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptVO(deptVO);
//		dao.insert(empVO1);



		//�� �ק�
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpno(7001);
//		empVO2.setEname("�d�ç�2");
//		empVO2.setJob("MANAGER2");
//		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		empVO2.setSal(new Double(20000));
//		empVO2.setComm(new Double(200));
//		empVO2.setDeptVO(deptVO);
//		dao.update(empVO2);



		//�� �R��(�p��cascade - �h��emp2.hbm.xml�p�G�]�� cascade="all"��
		// cascade="delete"�N�|�R���Ҧ��������-�]�A���ݳ����P�P�������䥦���u�N�|�@�ֳQ�R��)
//		dao.delete(7014);



		//�� �d��-findByPrimaryKey (�h��emp2.hbm.xml�����]��lazy="false")(�u!)
//		EmpVO empVO3 = dao.findByPrimaryKey(7001);
//		System.out.print(empVO3.getEmpno() + ",");
//		System.out.print(empVO3.getEname() + ",");
//		System.out.print(empVO3.getJob() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
//		// �`�N�H�U�T�檺�g�k (�u!)
//		System.out.print(empVO3.getDeptVO().getDeptno() + ",");
//		System.out.print(empVO3.getDeptVO().getDname() + ",");
//		System.out.print(empVO3.getDeptVO().getLoc());
//		System.out.println("\n---------------------");



		//�� �d��-getAll (�h��emp2.hbm.xml�����]��lazy="false")(�u!)
//		List<PhyCouPromotionDetailVO> list = dao.getAll();
//		
//		for (PhyCouPromotionDetailVO aPro : list) {
//			// �`�N�H�U�T�檺�g�k (�u!)
//			System.out.print(aPro.getPhyCouPromotionVO().getProject_no() + ",");
//			System.out.print(aPro.getPhyCouPromotionVO().getProject_name() + ",");
//			System.out.print(aPro.getPhyCouVO().getCourse_no() + ",");
//			System.out.print(aPro.getPhyCouVO().getCourse_name() + ",");
//			System.out.print(aPro.getProm_price() + ",");
//			System.out.println();
//		}
//	}
}
