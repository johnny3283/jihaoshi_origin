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


public class PhyCouSignUpDAO implements PhyCouSignUpDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/jihaoshi?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO PHYSICAL_COURSE_SIGNUP_LIST (MEMBER_NO, COURSE_NO, ORDER_PRICE) VALUES ( ?, ?, ?)";
	private static final String GET_ALL_STMT= 
		"SELECT * FROM PHYSICAL_COURSE_SIGNUP_LIST ORDER BY ORDER_NO";
	private static final String GET_ALL_BY_MEMID_STMT= 
		"SELECT * FROM PHYSICAL_COURSE_SIGNUP_LIST WHERE MEMBER_NO = ? ORDER BY ORDER_NO";
	private static final String GET_ONE_BY_ORDER_NO_STMT = 
		"SELECT * FROM PHYSICAL_COURSE_SIGNUP_LIST WHERE ORDER_NO = ?";
	private static final String DELETE = 
		"UPDATE PHYSICAL_COURSE_SIGNUP_LIST SET ORDER_STATUS = ? WHERE ORDER_NO = ?" ;
	private static final String UPDATE_SIGNUP_NUM = 
		"UPDATE PHYSICAL_COURSE SET CURRENT_SIGN_UP_PEOPLE=? WHERE COURSE_NO=? ";


	@Override
	public long insert(PhyCouSignUpVO phyCouSignUpVO, Integer signUpNum) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);	
			con.setAutoCommit(false);

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
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);	
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
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);	
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
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

	
		
		return list;
	}

	@Override
	public PhyCouSignUpVO findByOrderNo(Integer order_no) {
		PhyCouSignUpVO phyCouSignUpVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);	
			pstmt = con.prepareStatement(GET_ONE_BY_ORDER_NO_STMT);
			pstmt.setInt(1,order_no);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				phyCouSignUpVO = new PhyCouSignUpVO();
				phyCouSignUpVO.setOrder_no(rs.getInt("order_no"));
				phyCouSignUpVO.setUpdate_time(rs.getDate("update_time"));
				phyCouSignUpVO.setMember_no(rs.getInt("member_no"));
				phyCouSignUpVO.setOrder_price(rs.getInt("order_price"));
				phyCouSignUpVO.setOrder_status(rs.getInt("order_status"));
				phyCouSignUpVO.setCourse_no(rs.getInt("course_no"));					
				
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
		
				
		return phyCouSignUpVO;
		
	}
	@Override
	public void delete(Integer order_no, Integer signUpNum, Integer course_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);	
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
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

}


