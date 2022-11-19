package com.latest_news.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Latest_newsJDBCDAO implements Latest_newsDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/jihaoshi?useUnicode=yes&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
		private static final String INSERT_STMT = 
			"INSERT INTO LATEST_NEWS(NEWS_NAME, NEWS_CONTENT, NEWS_PIC) VALUES (?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT NEWS_NO, NEWS_NAME, UPDATE_DATE, NEWS_CONTENT, NEWS_PIC FROM LATEST_NEWS ORDER BY NEWS_NO";
		private static final String GET_ONE_STMT = 
			"SELECT NEWS_NO, NEWS_NAME, UPDATE_DATE, NEWS_CONTENT, NEWS_PIC FROM LATEST_NEWS WHERE NEWS_NO = ?";
		private static final String DELETE = 
			"DELETE FROM LATEST_NEWS WHERE NEWS_NO = ?";
		private static final String UPDATE = 
			"UPDATE LATEST_NEWS SET NEWS_NAME=?, NEWS_CONTENT=? WHERE NEWS_NO = ?";
//		private static final String UPLOADFILE =
//			"INSERT INTO LATEST_NEWS(NEWS_NAME, NEWS_CONTENT, NEWS_PIC)VALUES (1,1, ?)";
//		private static final String findByNewsName="select * from latest_news where news_name like ?";
		// %r%
		// ps.setString(1, "\%"+r+"\%");
	@Override
	public void insert(Latest_newsVO latest_newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, latest_newsVO.getNews_name());
			pstmt.setString(2, latest_newsVO.getNews_content());
			pstmt.setBytes(3, latest_newsVO.getNews_pic());
			
			pstmt.executeUpdate();

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
	public void update(Latest_newsVO latest_newsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, latest_newsVO.getNews_name());
			pstmt.setString(2, latest_newsVO.getNews_content());
			pstmt.setInt(3,latest_newsVO.getNews_no());

			pstmt.executeUpdate();

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
	public void delete(Integer news_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, news_no);

			pstmt.executeUpdate();

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
	public Latest_newsVO findByPrimarykey(Integer news_no) {
		Latest_newsVO latest_newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, news_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// latest_newsVo 也稱為 Domain objects
				latest_newsVO = new Latest_newsVO();
				latest_newsVO.setNews_no(rs.getInt("news_no"));
				latest_newsVO.setNews_name(rs.getString("news_name"));
				latest_newsVO.setUpdate_date(rs.getDate("update_date"));
				latest_newsVO.setNews_content(rs.getString("news_content"));
				latest_newsVO.setNews_pic(rs.getBytes("news_pic"));
				
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return latest_newsVO;
	}

	@Override
	public List<Latest_newsVO> getAll() {
		List<Latest_newsVO> list = new ArrayList<Latest_newsVO>();
		Latest_newsVO latest_newsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// latest_newsVO 也稱為 Domain objects
				latest_newsVO = new Latest_newsVO();
				latest_newsVO.setNews_no(rs.getInt("news_no"));
				latest_newsVO.setNews_name(rs.getString("news_name"));
				latest_newsVO.setUpdate_date(rs.getDate("update_date"));
				latest_newsVO.setNews_content(rs.getString("news_content"));
				latest_newsVO.setNews_pic(rs.getBytes("news_pic"));
				
				list.add(latest_newsVO); // Store the row in the list
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public List<Latest_newsVO> findByNewsName(String newsName){
		
		return null;
	}
	
//	@Override
//	public void uploadfile(InputStream in) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPLOADFILE);
//
//			pstmt.setBlob(1, in);
//			
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}

	
	public static void main(String[] args) {
		Latest_newsJDBCDAO dao = new Latest_newsJDBCDAO();
		
		// 新增
		Latest_newsVO latest_newsVO1 = new Latest_newsVO();
		latest_newsVO1.setNews_name("消息名稱消息名稱123");
		latest_newsVO1.setNews_content("消息內容消息內容999");
		dao.insert(latest_newsVO1);
		
		// 修改
		Latest_newsVO latest_newsVO2 = new Latest_newsVO();
		
		latest_newsVO2.setNews_name("消息名稱消息名稱");
		latest_newsVO2.setNews_content("消息內容消息內容");
		latest_newsVO2.setNews_no(1);
		dao.update(latest_newsVO2);
		
		// 刪除
		dao.delete(8);
		
		// 查詢
		Latest_newsVO latest_newsVO3 = dao.findByPrimarykey(1);
		System.out.print(latest_newsVO3.getNews_no() + ",");
		System.out.print(latest_newsVO3.getNews_name() + ",");
		System.out.print(latest_newsVO3.getUpdate_date() + ",");
		System.out.print(latest_newsVO3.getNews_content() + ",");
		
		System.out.println("---------------------");
		
		// 查詢
		List<Latest_newsVO> list = dao.getAll();
		for (Latest_newsVO alatest_news : list) {
			System.out.print(alatest_news.getNews_no() + ",");
			System.out.print(alatest_news.getNews_name() + ",");
			System.out.print(alatest_news.getUpdate_date() + ",");
			System.out.print(alatest_news.getNews_content() + ",");
			System.out.println();
				}
	}

}
