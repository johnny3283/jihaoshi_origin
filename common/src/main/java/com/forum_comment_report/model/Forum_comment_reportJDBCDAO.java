package com.forum_comment_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.forum_article.model.Forum_articleDAO_interface;
import com.forum_article.model.Forum_articleJDBCDAO;
import com.forum_article.model.Forum_articleVO;
import com.forum_article_report.model.Forum_article_reportJDBCDAO;
import com.forum_article_report.model.Forum_article_reportVO;

public class Forum_comment_reportJDBCDAO implements Forum_comment_reportDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/jihaoshi?useUnicode=yes&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO FORUM_COMMENT_REPORT(COMMENT_NO, ARTICLE_NO, MEMBER_NO, REPORT_REASON) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT_FULL = "SELECT COMMENT_REPORT_NO, COMMENT_NO, ARTICLE_NO, MEMBER_NO, REPORT_REASON, REPORT_STATUS FROM FORUM_COMMENT_REPORT WHERE REPORT_STATUS = 0 OR REPORT_STATUS = 1 ORDER BY COMMENT_REPORT_NO";
	private static final String GET_ALL_STMT = "SELECT COMMENT_REPORT_NO, COMMENT_NO, main.ARTICLE_NO, ARTICLE_NAME, main.MEMBER_NO, REPORT_REASON, REPORT_STATUS FROM FORUM_COMMENT_REPORT main, FORUM_ARTICLE sub WHERE main.article_no = sub.article_no and main.MEMBER_NO = ? ORDER BY COMMENT_REPORT_NO";
	private static final String GET_ONE_STMT = "SELECT COMMENT_REPORT_NO, COMMENT_NO, ARTICLE_NO, ARTICLE_NAME, MEMBER_NO, REPORT_REASON, REPORT_STATUS FROM FORUM_COMMENT_REPORT WHERE COMMENT_REPORT_NO = ?";
	private static final String DELETE = "DELETE FROM FORUM_COMMENT_REPORT WHERE COMMENT_REPORT_NO = ?";
	private static final String UPDATE = "UPDATE FORUM_COMMENT_REPORT SET REPORT_REASON=?, REPORT_STATUS=? WHERE COMMENT_REPORT_NO = ?";
	private static final String change_status_0 = "UPDATE FORUM_COMMENT_REPORT SET REPORT_STATUS=1 WHERE COMMENT_REPORT_NO = ?";
	private static final String change_status_1 = "UPDATE FORUM_COMMENT_REPORT SET REPORT_STATUS=2 WHERE COMMENT_REPORT_NO = ?";
	private static final String change_status_2 = "UPDATE FORUM_COMMENT_REPORT SET REPORT_STATUS=0 WHERE COMMENT_REPORT_NO = ?";
	@Override
	public void insert(Forum_comment_reportVO forum_comment_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forum_comment_reportVO.getComment_no());
			pstmt.setInt(2, forum_comment_reportVO.getArticle_no());
			pstmt.setInt(3, forum_comment_reportVO.getMember_no());
			pstmt.setString(4, forum_comment_reportVO.getReport_reason());

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
	public void update(Forum_comment_reportVO forum_comment_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, forum_comment_reportVO.getReport_reason());
			pstmt.setInt(2, forum_comment_reportVO.getReport_status());
			pstmt.setInt(3, forum_comment_reportVO.getComment_report_no());

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
	public void delete(Integer comment_report_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, comment_report_no);

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
	public Forum_comment_reportVO findByPrimarykey(Integer comment_report_no) {
		Forum_comment_reportVO forum_comment_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, comment_report_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// forum_article_reportVO 也稱為 Domain objects
				forum_comment_reportVO = new Forum_comment_reportVO();
				forum_comment_reportVO.setComment_report_no(rs.getInt("comment_report_no"));
				forum_comment_reportVO.setComment_no(rs.getInt("comment_no"));
				forum_comment_reportVO.setArticle_no(rs.getInt("article_no"));
				forum_comment_reportVO.setArticle_name(rs.getString("article_name"));
				forum_comment_reportVO.setMember_no(rs.getInt("member_no"));
				forum_comment_reportVO.setReport_reason(rs.getString("report_reason"));
				forum_comment_reportVO.setReport_status(rs.getInt("report_status"));

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
		return forum_comment_reportVO;
	}
	
	@Override
	public List<Forum_comment_reportVO> getAll() {
		List<Forum_comment_reportVO> list = new ArrayList<Forum_comment_reportVO>();
		Forum_comment_reportVO forum_comment_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_FULL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// forum_comment_reportVO 也稱為 Domain objects
				forum_comment_reportVO = new Forum_comment_reportVO();
				forum_comment_reportVO.setComment_report_no(rs.getInt("comment_report_no"));
				forum_comment_reportVO.setComment_no(rs.getInt("comment_no"));
				forum_comment_reportVO.setArticle_no(rs.getInt("article_no"));
//				forum_comment_reportVO.setArticle_name(rs.getString("article_name"));
				forum_comment_reportVO.setMember_no(rs.getInt("member_no"));
				forum_comment_reportVO.setReport_reason(rs.getString("report_reason"));
				forum_comment_reportVO.setReport_status(rs.getInt("report_status"));

				list.add(forum_comment_reportVO); // Store the row in the list
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
		return list;
	}
	
	
	
	
	

	@Override
	public List<Forum_comment_reportVO> getAll(Integer memberNo) {
		List<Forum_comment_reportVO> list = new ArrayList<Forum_comment_reportVO>();
		Forum_comment_reportVO forum_comment_reportVO = null;

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
				// forum_comment_reportVO 也稱為 Domain objects
				forum_comment_reportVO = new Forum_comment_reportVO();
				forum_comment_reportVO.setComment_report_no(rs.getInt("comment_report_no"));
				forum_comment_reportVO.setComment_no(rs.getInt("comment_no"));
				forum_comment_reportVO.setArticle_no(rs.getInt("article_no"));
				forum_comment_reportVO.setArticle_name(rs.getString("article_name"));
				forum_comment_reportVO.setMember_no(rs.getInt("member_no"));
				forum_comment_reportVO.setReport_reason(rs.getString("report_reason"));
				forum_comment_reportVO.setReport_status(rs.getInt("report_status"));

				list.add(forum_comment_reportVO); // Store the row in the list
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
		return list;
	}
	
	@Override
	public void change_status_0(Integer comment_report_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(change_status_0);

			pstmt.setInt(1, comment_report_no);

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
	public void change_status_1(Integer comment_report_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(change_status_1);

			pstmt.setInt(1, comment_report_no);

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
	public void change_status_2(Integer comment_report_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(change_status_2);

			pstmt.setInt(1, comment_report_no);

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
		Forum_comment_reportJDBCDAO dao = new Forum_comment_reportJDBCDAO();

		// 新增
		Forum_comment_reportVO forum_comment_reportVO1 = new Forum_comment_reportVO();

		forum_comment_reportVO1.setComment_no(1);
		forum_comment_reportVO1.setArticle_no(2);
		forum_comment_reportVO1.setMember_no(1);
		forum_comment_reportVO1.setReport_reason("檢舉事由1");
		dao.insert(forum_comment_reportVO1);

		// 修改
		Forum_comment_reportVO forum_comment_reportVO2 = new Forum_comment_reportVO();

		forum_comment_reportVO2.setReport_reason("檢舉事由4");
		forum_comment_reportVO2.setReport_status(1);
		forum_comment_reportVO2.setComment_report_no(1);

		dao.update(forum_comment_reportVO2);

		// 刪除
		dao.delete(5);

		// 查詢
		Forum_comment_reportVO forum_comment_reportVO3 = dao.findByPrimarykey(1);
		System.out.print(forum_comment_reportVO3.getComment_report_no() + ",");
		System.out.print(forum_comment_reportVO3.getComment_no() + ",");
		System.out.print(forum_comment_reportVO3.getArticle_no() + ",");
		System.out.print(forum_comment_reportVO3.getMember_no() + ",");
		System.out.print(forum_comment_reportVO3.getReport_reason() + ",");
		System.out.print(forum_comment_reportVO3.getReport_status() + ",");

		System.out.println("---------------------");

		// 查詢
//		List<Forum_comment_reportVO> list = dao.getAll();
//		for (Forum_comment_reportVO aforum_comment_reportVO : list) {
//			System.out.print(aforum_comment_reportVO.getComment_report_no() + ",");
//			System.out.print(aforum_comment_reportVO.getComment_no() + ",");
//			System.out.print(aforum_comment_reportVO.getArticle_no() + ",");
//			System.out.print(aforum_comment_reportVO.getMember_no() + ",");
//			System.out.print(aforum_comment_reportVO.getReport_reason() + ",");
//			System.out.print(aforum_comment_reportVO.getReport_status() + ",");
//
//			System.out.println();
//		}
	}
}
