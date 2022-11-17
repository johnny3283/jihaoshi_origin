package com.course.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import hibernate.util.HibernateUtil;


public class PhyCouDAO implements PhyCouDAO_interface {

	private static final String GET_ALL_STMT = 
		"FROM PhyCouVO ORDER BY COURSE_NO";
	private static final String GET_CAN_SIGNUP_STMT = 
		"FROM PhyCouVO WHERE CURRENT_SIGN_UP_PEOPLE < MAX_SIGN_UP_PEOPLE ;";

	@Override
	public void insert(PhyCouVO phyCouVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			if ( phyCouVO.getPic().length != 0 || phyCouVO.getPic() != null) {
				NativeQuery<?> nativeQuery = session.createNativeQuery("INSERT INTO PHYSICAL_COURSE "
						+ "(course_name, course_hr, course_price, course_teacher, course_date, course_location, "
						+ "course_info, course_status, sign_up_start_day, sign_up_end_day, "
						+ "max_sign_up_people, min_sign_up_people,	current_sign_up_people, pic) "
						+ "VALUES(:COURSE_NAME, :COURSE_HR, :COURSE_PRICE, :COURSE_TEACHER, :COURSE_DATE, :COURSE_LOCATION, "
						+ ":COURSE_INFO, :COURSE_STATUS, :SIGN_UP_START_DAY, :SIGN_UP_END_DAY, :MAX_SIGN_UP_PEOPLE, "
						+ ":MIN_SIGN_UP_PEOPLE,	:CURRENT_SIGN_UP_PEOPLE, :PIC)")
						.setParameter("COURSE_NAME", phyCouVO.getCourse_name())
						.setParameter("COURSE_HR", phyCouVO.getCourse_hr())
						.setParameter("COURSE_PRICE", phyCouVO.getCourse_price())
						.setParameter("COURSE_TEACHER", phyCouVO.getCourse_teacher() )
						.setParameter("COURSE_DATE", phyCouVO.getCourse_date() )
						.setParameter("COURSE_LOCATION", phyCouVO.getCourse_location())
						.setParameter("COURSE_INFO", phyCouVO.getCourse_info())
						.setParameter("COURSE_STATUS", phyCouVO.getCourse_status())
						.setParameter("SIGN_UP_START_DAY", phyCouVO.getSign_up_start_day() )
						.setParameter("SIGN_UP_END_DAY", phyCouVO.getSign_up_end_day())
						.setParameter("MAX_SIGN_UP_PEOPLE", phyCouVO.getMax_sign_up_people())
						.setParameter("MIN_SIGN_UP_PEOPLE", phyCouVO.getMin_sign_up_people())
						.setParameter("CURRENT_SIGN_UP_PEOPLE", phyCouVO.getCurrent_sign_up_people())
						.setParameter("PIC", phyCouVO.getPic());
						int result = nativeQuery.executeUpdate();
						session.getTransaction().commit();
			} else {
				NativeQuery<?> nativeQuery = session.createNativeQuery("INSERT INTO PHYSICAL_COURSE "
						+ "(course_name, course_hr, course_price, course_teacher, course_date, course_location, "
						+ "course_info, course_status, sign_up_start_day, sign_up_end_day, "
						+ "max_sign_up_people, min_sign_up_people,	current_sign_up_people) "
						+ "VALUES(:COURSE_NAME, :COURSE_HR, :COURSE_PRICE, :COURSE_TEACHER, :COURSE_DATE, :COURSE_LOCATION, "
						+ ":COURSE_INFO, :COURSE_STATUS, :SIGN_UP_START_DAY, :SIGN_UP_END_DAY, :MAX_SIGN_UP_PEOPLE, "
						+ ":MIN_SIGN_UP_PEOPLE,	:CURRENT_SIGN_UP_PEOPLE)")
						.setParameter("COURSE_NAME", phyCouVO.getCourse_name())
						.setParameter("COURSE_HR", phyCouVO.getCourse_hr())
						.setParameter("COURSE_PRICE", phyCouVO.getCourse_price())
						.setParameter("COURSE_TEACHER", phyCouVO.getCourse_teacher() )
						.setParameter("COURSE_DATE", phyCouVO.getCourse_date() )
						.setParameter("COURSE_LOCATION", phyCouVO.getCourse_location())
						.setParameter("COURSE_INFO", phyCouVO.getCourse_info())
						.setParameter("COURSE_STATUS", phyCouVO.getCourse_status())
						.setParameter("SIGN_UP_START_DAY", phyCouVO.getSign_up_start_day() )
						.setParameter("SIGN_UP_END_DAY", phyCouVO.getSign_up_end_day())
						.setParameter("MAX_SIGN_UP_PEOPLE", phyCouVO.getMax_sign_up_people())
						.setParameter("MIN_SIGN_UP_PEOPLE", phyCouVO.getMin_sign_up_people())
						.setParameter("CURRENT_SIGN_UP_PEOPLE", phyCouVO.getCurrent_sign_up_people());
						
				int result = nativeQuery.executeUpdate();
				session.getTransaction().commit();				
			}

		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}


	@Override
	public void update(PhyCouVO phyCouVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			if ( phyCouVO.getPic().length != 0 ) {
				NativeQuery<?> nativeQuery = session.createNativeQuery("UPDATE PHYSICAL_COURSE SET "
                        + "COURSE_NAME = :COURSE_NAME, COURSE_HR = :COURSE_HR, "
                        + "COURSE_PRICE = :COURSE_PRICE, COURSE_TEACHER = :COURSE_TEACHER, "
                        + "COURSE_DATE = :COURSE_DATE, COURSE_LOCATION = :COURSE_LOCATION, "
						+ "COURSE_INFO = :COURSE_INFO, COURSE_STATUS = :COURSE_STATUS, "
						+ "SIGN_UP_START_DAY = :SIGN_UP_START_DAY, SIGN_UP_END_DAY =  :SIGN_UP_END_DAY, "
						+ "MAX_SIGN_UP_PEOPLE = :MAX_SIGN_UP_PEOPLE, "
						+ "MIN_SIGN_UP_PEOPLE = :MIN_SIGN_UP_PEOPLE, CURRENT_SIGN_UP_PEOPLE = :CURRENT_SIGN_UP_PEOPLE, "
						+ "PIC = :PIC WHERE COURSE_NO = :COURSE_NO")						
						.setParameter("COURSE_NO", phyCouVO.getCourse_no())
						.setParameter("COURSE_NAME", phyCouVO.getCourse_name())
						.setParameter("COURSE_HR", phyCouVO.getCourse_hr())
						.setParameter("COURSE_PRICE", phyCouVO.getCourse_price())
						.setParameter("COURSE_TEACHER", phyCouVO.getCourse_teacher() )
						.setParameter("COURSE_DATE", phyCouVO.getCourse_date() )
						.setParameter("COURSE_LOCATION", phyCouVO.getCourse_location())
						.setParameter("COURSE_INFO", phyCouVO.getCourse_info())
						.setParameter("COURSE_STATUS", phyCouVO.getCourse_status())
						.setParameter("SIGN_UP_START_DAY", phyCouVO.getSign_up_start_day() )
						.setParameter("SIGN_UP_END_DAY", phyCouVO.getSign_up_end_day())
						.setParameter("MAX_SIGN_UP_PEOPLE", phyCouVO.getMax_sign_up_people())
						.setParameter("MIN_SIGN_UP_PEOPLE", phyCouVO.getMin_sign_up_people())
						.setParameter("CURRENT_SIGN_UP_PEOPLE", phyCouVO.getCurrent_sign_up_people())
						.setParameter("PIC", phyCouVO.getPic());
						int result = nativeQuery.executeUpdate();
						session.getTransaction().commit();
			} else {
				NativeQuery<?> nativeQuery = session.createNativeQuery("UPDATE PHYSICAL_COURSE SET "
	                        + "COURSE_NAME = :COURSE_NAME, COURSE_HR = :COURSE_HR, "
	                        + "COURSE_PRICE = :COURSE_PRICE, COURSE_TEACHER = :COURSE_TEACHER, "
	                        + "COURSE_DATE = :COURSE_DATE, COURSE_LOCATION = :COURSE_LOCATION, "
							+ "COURSE_INFO = :COURSE_INFO, COURSE_STATUS = :COURSE_STATUS, "
							+ "SIGN_UP_START_DAY = :SIGN_UP_START_DAY, SIGN_UP_END_DAY =  :SIGN_UP_END_DAY, "
							+ "MAX_SIGN_UP_PEOPLE = :MAX_SIGN_UP_PEOPLE, "
							+ "MIN_SIGN_UP_PEOPLE = :MIN_SIGN_UP_PEOPLE, CURRENT_SIGN_UP_PEOPLE = :CURRENT_SIGN_UP_PEOPLE, "
							+ "WHERE COURSE_NO = :COURSE_NO")	
						.setParameter("COURSE_NO", phyCouVO.getCourse_no())
						.setParameter("COURSE_NAME", phyCouVO.getCourse_name())
						.setParameter("COURSE_HR", phyCouVO.getCourse_hr())
						.setParameter("COURSE_PRICE", phyCouVO.getCourse_price())
						.setParameter("COURSE_TEACHER", phyCouVO.getCourse_teacher() )
						.setParameter("COURSE_DATE", phyCouVO.getCourse_date() )
						.setParameter("COURSE_LOCATION", phyCouVO.getCourse_location())
						.setParameter("COURSE_INFO", phyCouVO.getCourse_info())
						.setParameter("COURSE_STATUS", phyCouVO.getCourse_status())
						.setParameter("SIGN_UP_START_DAY", phyCouVO.getSign_up_start_day() )
						.setParameter("SIGN_UP_END_DAY", phyCouVO.getSign_up_end_day())
						.setParameter("MAX_SIGN_UP_PEOPLE", phyCouVO.getMax_sign_up_people())
						.setParameter("MIN_SIGN_UP_PEOPLE", phyCouVO.getMin_sign_up_people())
						.setParameter("CURRENT_SIGN_UP_PEOPLE", phyCouVO.getCurrent_sign_up_people());
						
				int result = nativeQuery.executeUpdate();
				session.getTransaction().commit();	
			}
			
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void updateStatus(Integer course_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			PhyCouVO phyCouVO = (PhyCouVO) session.get(PhyCouVO.class, course_no);
			phyCouVO.setCourse_status(2);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}


	@Override
	public PhyCouVO findByPrimaryKey(Integer course_no) {

		PhyCouVO phyCouVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			phyCouVO = (PhyCouVO) session.get(PhyCouVO.class, course_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;			
		}
		return phyCouVO;
	}

	@Override
	public List<PhyCouVO> getAll() {
		List<PhyCouVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<PhyCouVO> query = session.createQuery(GET_ALL_STMT, PhyCouVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<PhyCouVO> getCanSignUp() {
		
		List<PhyCouVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<PhyCouVO> query = session.createQuery(GET_CAN_SIGNUP_STMT, PhyCouVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex ) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	

	@Override
	public List<PhyCouVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(Integer course_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			PhyCouVO phyCouVO = (PhyCouVO) session.get(PhyCouVO.class, course_no);
			session.delete(phyCouVO);
			session.getTransaction().commit();			
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
 	}
}
//	public static void main(String[] args) {
//
//		PhyCouDAO dao = new PhyCouDAO();

		// �s�W
//		PhyCouVO phyCouVO1 = new PhyCouVO();
//		phyCouVO1.setCourse_name("�׶�");
//		phyCouVO1.setCourse_hr(3);
//		phyCouVO1.setCourse_price(1999);
//		phyCouVO1.setCourse_teacher("�j�d�Ѯv");
//		phyCouVO1.setCourse_date(java.sql.Date.valueOf("2022-12-31"));
//		phyCouVO1.setCourse_location("904");
//		phyCouVO1.setCourse_info("�y�L��u�A�@�w�n�ӡA���L�i��");
//		phyCouVO1.setCourse_status(0);
//		phyCouVO1.setCreate_date(java.sql.Date.valueOf("2022-01-01"));
//		phyCouVO1.setUpdate_time(java.sql.Date.valueOf("2022-10-01"));
//		phyCouVO1.setSign_up_start_day(java.sql.Date.valueOf("2022-10-01"));
//		phyCouVO1.setSign_up_end_day(java.sql.Date.valueOf("2022-12-27"));
//		phyCouVO1.setMax_sign_up_people(100);
//		phyCouVO1.setMin_sign_up_people(20);
//		phyCouVO1.setCurrent_sign_up_people(46);
//		dao.insert(phyCouVO1);

		// �ק�
//		PhyCouVO phyCouVO2 = new PhyCouVO();
//		phyCouVO2.setCourse_no(5);
//		phyCouVO2.setCourse_name("�׶�");
//		phyCouVO2.setCourse_hr(3);
//		phyCouVO2.setCourse_price(1999);
//		phyCouVO2.setCourse_teacher("�j�d�Ѯv");
//		phyCouVO2.setCourse_date(java.sql.Date.valueOf("2022-12-31"));
//		phyCouVO2.setCourse_location("904");
//		phyCouVO2.setCourse_info("�y�L��u�A�@�w�n�ӡA���L�i��");
//		phyCouVO2.setCourse_status(0);
//		phyCouVO2.setCreate_date(java.sql.Date.valueOf("2022-01-01"));
//		phyCouVO2.setUpdate_time(java.sql.Date.valueOf("2022-10-01"));
//		phyCouVO2.setSign_up_start_day(java.sql.Date.valueOf("2022-10-01"));
//		phyCouVO2.setSign_up_end_day(java.sql.Date.valueOf("2022-12-27"));
//		phyCouVO2.setMax_sign_up_people(100);
//		phyCouVO2.setMin_sign_up_people(20);
//		phyCouVO2.setCurrent_sign_up_people(46);
//		dao.update(phyCouVO2);

		// �R��
//		dao.delete(4);

		// �d��
//		PhyCouVO phyCouVO3 = dao.findByPrimaryKey(2);
//		System.out.print(phyCouVO3.getCourse_no() + ",");
//		System.out.print(phyCouVO3.getCourse_name() + ",");
//		System.out.print(phyCouVO3.getCourse_hr() + ",");
//		System.out.print(phyCouVO3.getCourse_price() + ",");
//		System.out.print(phyCouVO3.getCourse_teacher() + ",");
//		System.out.print(phyCouVO3.getCourse_date() + ",");
//		System.out.print(phyCouVO3.getCourse_location() + ",");
//		System.out.print(phyCouVO3.getCourse_info() + ",");
//		System.out.print(phyCouVO3.getCourse_status() + ",");
//		System.out.print(phyCouVO3.getCreate_date() + ",");
//		System.out.print(phyCouVO3.getUpdate_time() + ",");
//		System.out.print(phyCouVO3.getSign_up_start_day() + ",");
//		System.out.print(phyCouVO3.getSign_up_end_day() + ",");
//		System.out.print(phyCouVO3.getMax_sign_up_people() + ",");
//		System.out.print(phyCouVO3.getMin_sign_up_people() + ",");		
//		System.out.println(phyCouVO3.getCurrent_sign_up_people());		
//		System.out.println("---------------------");

		// �d��
//		List<PhyCouVO> list = dao.getAll();
//		for (PhyCouVO aCourse : list) {
//			System.out.print(aCourse.getCourse_no() + ",");
//			System.out.print(aCourse.getCourse_name() + ",");
//			System.out.print(aCourse.getCourse_hr() + ",");
//			System.out.print(aCourse.getCourse_price() + ",");
//			System.out.print(aCourse.getCourse_teacher() + ",");
//			System.out.print(aCourse.getCourse_date() + ",");
//			System.out.print(aCourse.getCourse_location() + ",");
//			System.out.print(aCourse.getCourse_info() + ",");
//			System.out.print(aCourse.getCourse_status() + ",");
//			System.out.print(aCourse.getCreate_date() + ",");
//			System.out.print(aCourse.getUpdate_time() + ",");
//			System.out.print(aCourse.getSign_up_start_day() + ",");
//			System.out.print(aCourse.getSign_up_end_day() + ",");
//			System.out.print(aCourse.getMax_sign_up_people() + ",");
//			System.out.print(aCourse.getMin_sign_up_people() + ",");		
//			System.out.println(aCourse.getCurrent_sign_up_people());		
//			System.out.println();
//		}
//	}
//}
