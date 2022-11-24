package com.signupcourse.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.course.model.PhyCouVO;


public class PhyCouSignUpJNDIDAO implements PhyCouSignUpDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://15.152.181.134:3306/JihaoDB?serverTimezone=Asia/Taipei";
//	String userid = "tsai";
//	String passwd = "Tibame@cga104";
    public static DataSource ds = null;
	
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jihaoshi");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

	private static final String INSERT_STMT = 
		"INSERT INTO PHYSICAL_COURSE_SIGNUP_LIST (MEMBER_NO, COURSE_NO, ORDER_PRICE) VALUES ( ?, ?, ?)";
//	private static final String INSERT_STMT_NOPIC = 
//		"INSERT INTO Physical_course (course_name, course_hr, course_price, course_teacher, course_date, course_location, course_info, course_status, sign_up_start_day, sign_up_end_day, max_sign_up_people, min_sign_up_people,	current_sign_up_people) VALUES      ( ?, ?, ?, ?,   ?, ?, ?, ?,  ?, ?, ?, ?,  ?)";
	private static final String GET_ALL_STMT= 
		"SELECT * FROM PHYSICAL_COURSE_SIGNUP_LIST ORDER BY ORDER_NO";
	private static final String GET_ALL_BY_MEMID_STMT= 
		"SELECT * FROM PHYSICAL_COURSE_SIGNUP_LIST WHERE MEMBER_NO = ? ORDER BY ORDER_NO";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM PHYSICAL_COURSE_SIGNUP_LIST WHERE MEMBER_NO = ?";
	private static final String DELETE = 
		"UPDATE PHYSICAL_COURSE_SIGNUP_LIST SET ORDER_STATUS = ? WHERE ORDER_NO = ?" ;
	private static final String UPDATE_SIGNUP_NUM = 
		"UPDATE PHYSICAL_COURSE SET CURRENT_SIGN_UP_PEOPLE=? WHERE COURSE_NO=? ";


	@Override
	public long insert(PhyCouSignUpVO phyCouSignUpVO, Integer signUpNum) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);	
			con = ds.getConnection();
			con.setAutoCommit(false);
//			    String cols[] = {"order_no"};
				pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);				
				
				
				pstmt.setInt(1, phyCouSignUpVO.getMember_no());
				pstmt.setInt(2, phyCouSignUpVO.getCourse_no());
				pstmt.setInt(3, phyCouSignUpVO.getOrder_price());

				pstmt.executeUpdate();
				long next_order = -1;
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					next_order = rs.getLong(1);
				} else {
					System.out.println("未取得自增主鍵值");
				}
				rs.close();
				
                pstmt = con.prepareStatement(UPDATE_SIGNUP_NUM);				
				
				pstmt.setInt(1, signUpNum+1);
				pstmt.setInt(2, phyCouSignUpVO.getCourse_no());
				pstmt.executeUpdate();
				
				con.commit();
				con.setAutoCommit(true);
				return next_order ;

			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con!=null ) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());					
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	public List<PhyCouSignUpVO> getAll() {
		List<PhyCouSignUpVO> list = new ArrayList<PhyCouSignUpVO>();
		PhyCouSignUpVO phyCouSignUpVO = null;
		
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
				
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				phyCouSignUpVO = new PhyCouSignUpVO();
				phyCouSignUpVO.setOrder_no(rs.getInt("order_no"));
				phyCouSignUpVO.setMember_no(rs.getInt("membe_no"));
				phyCouSignUpVO.setOrder_price(rs.getInt("order_price"));
				phyCouSignUpVO.setOrder_status(rs.getInt("order_status"));
				phyCouSignUpVO.setCourse_no(rs.getInt("Couse_no"));
				list.add(phyCouSignUpVO);
			}			
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);					
				}
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch ( SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			
		}		
		return list;
	}

	@Override
	public List<PhyCouSignUpVO> findByMemberNo(Integer member_no) {
		List<PhyCouSignUpVO> list = new ArrayList<PhyCouSignUpVO>();
		PhyCouSignUpVO phyCouSignUpVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEMID_STMT);
			pstmt.setInt(1,member_no);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				phyCouSignUpVO = new PhyCouSignUpVO();
				phyCouSignUpVO.setOrder_no(rs.getInt("order_no"));
				phyCouSignUpVO.setUpdate_time(rs.getDate("update_time"));
				phyCouSignUpVO.setMember_no(rs.getInt("member_no"));
				phyCouSignUpVO.setOrder_price(rs.getInt("order_price"));
				phyCouSignUpVO.setOrder_status(rs.getInt("order_status"));
				phyCouSignUpVO.setCourse_no(rs.getInt("course_no"));
				list.add(phyCouSignUpVO);				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs!=null) {
				try {
				    rs.close();
				} catch (SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con!=null) {
				try {
				    con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}

	@Override
	public void delete(Integer order_no, Integer signUpNum, Integer course_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, 2);
			pstmt.setInt(2, order_no);
			
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(UPDATE_SIGNUP_NUM);
			pstmt.setInt(1, signUpNum-1);
			pstmt.setInt(2, course_no);
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
			
		} catch (SQLException se) {
			if (con!=null) {
				try {
					con.rollback();
				} catch (SQLException excep) {			
			          throw new RuntimeException("A database error occured. "
					    + se.getMessage());
			    }
			}
	        throw new RuntimeException("A database error occured. "
			    + se.getMessage());
		} finally {
			if (rs!=null ) {
				try {
				    rs.close();
				} catch (SQLException se ) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException se ) {
					se.printStackTrace(System.err);
				}
			}
			if (con!=null ) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
	}

}


