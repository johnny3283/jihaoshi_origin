package com.forum_article_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.forum_article.model.Forum_articleVO;
import com.latest_news.model.Latest_newsVO;

public class Forum_article_reportJDBCDAO implements Forum_article_reportDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/jihaoshi?useUnicode=yes&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	private static final String INSERT_STMT = "INSERT INTO FORUM_ARTICLE_REPORT(ARTICLE_NO, MEMBER_NO, REPORT_REASON) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT_FULL = "SELECT ARTICLE_REPORT_NO, ARTICLE_NO, MEMBER_NO, REPORT_REASON, REPORT_STATUS FROM FORUM_ARTICLE_REPORT WHERE REPORT_STATUS = 0 OR REPORT_STATUS = 1 ORDER BY ARTICLE_REPORT_NO";
	private static final String GET_ALL_STMT = "SELECT ARTICLE_REPORT_NO, ARTICLE_NO, MEMBER_NO, REPORT_REASON, REPORT_STATUS FROM FORUM_ARTICLE_REPORT WHERE MEMBER_NO = ?  ORDER BY ARTICLE_REPORT_NO";
	private static final String GET_ONE_STMT = "SELECT ARTICLE_REPORT_NO, ARTICLE_NO, MEMBER_NO, REPORT_REASON, REPORT_STATUS FROM FORUM_ARTICLE_REPORT WHERE ARTICLE_REPORT_NO = ?";
	private static final String DELETE = "DELETE FROM FORUM_ARTICLE_REPORT WHERE ARTICLE_REPORT_NO = ?";
	private static final String UPDATE = "UPDATE FORUM_ARTICLE_REPORT SET ARTICLE_NO=?, MEMBER_NO=?, REPORT_REASON=?, REPORT_STATUS=? WHERE ARTICLE_REPORT_NO = ?";
	
	private static final String change_status_0 = 
			"UPDATE FORUM_ARTICLE_REPORT SET REPORT_STATUS=1 WHERE ARTICLE_REPORT_NO = ?";
	private static final String change_status_1 = 
			"UPDATE FORUM_ARTICLE_REPORT SET REPORT_STATUS=2 WHERE ARTICLE_REPORT_NO = ?";
	private static final String change_status_2 = 
			"UPDATE FORUM_ARTICLE_REPORT SET REPORT_STATUS=0 WHERE ARTICLE_REPORT_NO = ?";
	
	@Override
	public void insert(Forum_article_reportVO forum_article_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forum_article_reportVO.getArticle_no());
			pstmt.setInt(2, forum_article_reportVO.getMember_no());
			pstmt.setString(3, forum_article_reportVO.getReport_reason());
//			pstmt.setInt(4, forum_article_reportVO.getReport_status());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(Forum_article_reportVO forum_article_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, forum_article_reportVO.getArticle_no());
			pstmt.setInt(2, forum_article_reportVO.getMember_no());
			pstmt.setString(3, forum_article_reportVO.getReport_reason());
			pstmt.setInt(4, forum_article_reportVO.getReport_status());
			pstmt.setInt(5, forum_article_reportVO.getArticle_report_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer article_report_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, article_report_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Forum_article_reportVO findByPrimaryKey(Integer article_report_no) {
		Forum_article_reportVO forum_article_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, article_report_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// forum_article_reportVO 也稱為 Domain objects
				forum_article_reportVO = new Forum_article_reportVO();
				forum_article_reportVO.setArticle_report_no(rs.getInt("article_report_no"));
				forum_article_reportVO.setArticle_no(rs.getInt("article_no"));
				forum_article_reportVO.setMember_no(rs.getInt("member_no"));
				forum_article_reportVO.setReport_reason(rs.getString("report_reason"));
				forum_article_reportVO.setReport_status(rs.getInt("report_status"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return forum_article_reportVO;
	}

	@Override
	public List<Forum_article_reportVO> getAll() {
		List<Forum_article_reportVO> list = new ArrayList<Forum_article_reportVO>();
		Forum_article_reportVO forum_article_reportVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_FULL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// forum_articleVO 也稱為 Domain objects
				forum_article_reportVO = new Forum_article_reportVO();
				forum_article_reportVO.setArticle_report_no(rs.getInt("article_report_no"));
				forum_article_reportVO.setArticle_no(rs.getInt("article_no"));
				forum_article_reportVO.setMember_no(rs.getInt("member_no"));
				forum_article_reportVO.setReport_reason(rs.getString("report_reason"));
				forum_article_reportVO.setReport_status(rs.getInt("report_status"));
				
				list.add(forum_article_reportVO); // Store the row in the list
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
	public List<Forum_article_reportVO> getAll(Integer memberNo) {
		List<Forum_article_reportVO> list = new ArrayList<Forum_article_reportVO>();
		Forum_article_reportVO forum_article_reportVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// forum_articleVO 也稱為 Domain objects
				forum_article_reportVO = new Forum_article_reportVO();
				forum_article_reportVO.setArticle_report_no(rs.getInt("article_report_no"));
				forum_article_reportVO.setArticle_no(rs.getInt("article_no"));
				forum_article_reportVO.setMember_no(rs.getInt("member_no"));
				forum_article_reportVO.setReport_reason(rs.getString("report_reason"));
				forum_article_reportVO.setReport_status(rs.getInt("report_status"));
				
				list.add(forum_article_reportVO); // Store the row in the list
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
	public void change_status_0(Integer article_report_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(change_status_0);

			pstmt.setInt(1, article_report_no);

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
	public void change_status_1(Integer article_report_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(change_status_1);

			pstmt.setInt(1, article_report_no);

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
	public void change_status_2(Integer article_report_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(change_status_2);

			pstmt.setInt(1, article_report_no);

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
			
		

		
	
	

	public static void main(String[] args) {

		Forum_article_reportJDBCDAO dao = new Forum_article_reportJDBCDAO();

		// 新增
		Forum_article_reportVO forum_article_reportVO1 = new Forum_article_reportVO();
		forum_article_reportVO1.setArticle_no(2);
		forum_article_reportVO1.setMember_no(3);
		forum_article_reportVO1.setReport_reason("檢舉事由1");
//		forum_article_reportVO1.setReport_status(1);
		dao.insert(forum_article_reportVO1);

		// 修改
		Forum_article_reportVO forum_article_reportVO2 = new Forum_article_reportVO();

		forum_article_reportVO2.setArticle_no(2);
		forum_article_reportVO2.setMember_no(2);
		forum_article_reportVO2.setReport_reason("檢舉事由2");
		forum_article_reportVO2.setReport_status(1);
		forum_article_reportVO2.setArticle_report_no(1);
		dao.update(forum_article_reportVO2);

		// 刪除
		dao.delete(8);

		// 查詢
		Forum_article_reportVO forum_article_reportVO3 = dao.findByPrimaryKey(1);
		System.out.print(forum_article_reportVO3.getArticle_report_no() + ",");
		System.out.print(forum_article_reportVO3.getArticle_no() + ",");
		System.out.print(forum_article_reportVO3.getMember_no() + ",");
		System.out.print(forum_article_reportVO3.getReport_reason() + ",");
		System.out.print(forum_article_reportVO3.getReport_status() + ",");
	

		System.out.println("---------------------");
		
		// 查詢
		List<Forum_article_reportVO> list = dao.getAll();
		for (Forum_article_reportVO aforum_article_report : list) {
			System.out.print(aforum_article_report.getArticle_report_no() + ",");
			System.out.print(aforum_article_report.getArticle_no() + ",");
			System.out.print(aforum_article_report.getMember_no() + ",");
			System.out.print(aforum_article_report.getReport_reason() + ",");
			System.out.print(aforum_article_report.getReport_status() + ",");

			
			System.out.println();
				}

	}
}
