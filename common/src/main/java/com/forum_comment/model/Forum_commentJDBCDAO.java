package com.forum_comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.latest_news.model.Latest_newsVO;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Forum_commentJDBCDAO implements Forum_commentDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/database1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO forum_comment(article_no,member_no, comment_content) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT comment_no,article_no,member_no,comment_time,comment_content,comment_status FROM forum_comment where article_no = ? order by comment_no";
	private static final String GET_ONE_STMT = "SELECT comment_no,article_no,member_no,comment_time,comment_content,comment_status FROM forum_comment where comment_no = ?";
	private static final String DELETE = "DELETE FROM forum_comment where comment_no = ?";
	private static final String UPDATE = "UPDATE forum_comment set article_no=?, member_no=?, comment_content=?, comment_status=? where comment_no = ?";

	@Override
	public void insert(Forum_commentVO forum_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forum_commentVO.getArticle_no());
			pstmt.setInt(2, forum_commentVO.getMember_no());
//			pstmt.setDate(3, forum_commentVO.getComment_time());
			pstmt.setString(3, forum_commentVO.getComment_content());
//			pstmt.setInt(4, forum_commentVO.getComment_status());

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
	public void update(Forum_commentVO forum_commentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, forum_commentVO.getArticle_no());
			pstmt.setInt(2, forum_commentVO.getMember_no());
			pstmt.setString(3, forum_commentVO.getComment_content());
			pstmt.setInt(4, forum_commentVO.getComment_status());
			pstmt.setInt(5, forum_commentVO.getComment_no());

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
	public void delete(Integer comment_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, comment_no);

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
	public Forum_commentVO findByPrimarykey(Integer comment_no) {
		Forum_commentVO forum_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, comment_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// forum_commentVO 也稱為 Domain objects
				forum_commentVO = new Forum_commentVO();
				forum_commentVO.setComment_no(rs.getInt("comment_no"));
				forum_commentVO.setArticle_no(rs.getInt("article_no"));
				forum_commentVO.setMember_no(rs.getInt("member_no"));
				forum_commentVO.setComment_time(rs.getDate("comment_time"));
				forum_commentVO.setComment_content(rs.getString("comment_content"));
				forum_commentVO.setComment_status(rs.getInt("comment_status"));
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
		return forum_commentVO;

	}

	@Override
	public List<Forum_commentVO> getAll(Integer article_no) {

		List<Forum_commentVO> list = new ArrayList<Forum_commentVO>();
		Forum_commentVO forum_commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setInt(1, article_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// forum_commentVO也稱為 Domain objects
				forum_commentVO = new Forum_commentVO();
				forum_commentVO.setComment_no(rs.getInt("comment_no"));
				forum_commentVO.setArticle_no(rs.getInt("article_no"));
				forum_commentVO.setMember_no(rs.getInt("member_no"));
				forum_commentVO.setComment_time(rs.getDate("comment_time"));
				forum_commentVO.setComment_content(rs.getString("comment_content"));
				forum_commentVO.setComment_status(rs.getInt("comment_status"));
				list.add(forum_commentVO);
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

	public static void main(String[] args) {
		Forum_commentJDBCDAO dao = new Forum_commentJDBCDAO();

//		// 新增
//		Forum_commentVO forum_commentVO1 = new Forum_commentVO();
//		forum_commentVO1.setArticle_no(2);
//		forum_commentVO1.setMember_no(1);
////		forum_commentVO1.setComment_time(java.sql.Date.valueOf("2005-01-01"));
//		forum_commentVO1.setComment_content("文章內容1");
////		forum_commentVO1.setComment_status(1);
//		dao.insert(forum_commentVO1);
//
//		// 修改
//		Forum_commentVO forum_commentVO2 = new Forum_commentVO();
//		forum_commentVO2.setArticle_no(2);
//		forum_commentVO2.setMember_no(2);
////		forum_commentVO2.setComment_time(java.sql.Date.valueOf("2002-01-01"));
//		forum_commentVO2.setComment_content("文章內容2");
//		forum_commentVO2.setComment_status(1);
//		forum_commentVO2.setComment_no(2);
//		dao.update(forum_commentVO2);
//
//		// 刪除
//		dao.delete(10);
//
//		// 查詢
//		Forum_commentVO forum_commentVO3 = dao.findByPrimarykey(1);
//		System.out.print(forum_commentVO3.getComment_no() + ",");
//		System.out.print(forum_commentVO3.getArticle_no() + ",");
//		System.out.print(forum_commentVO3.getMember_no() + ",");
//		System.out.print(forum_commentVO3.getComment_time() + ",");
//		System.out.print(forum_commentVO3.getComment_content() + ",");
//		System.out.print(forum_commentVO3.getComment_status() + ",");
//
//		System.out.println("---------------------");

		// 查詢
		List<Forum_commentVO> list = dao.getAll(1);
		for (Forum_commentVO aforum_comment : list) {
			System.out.print(aforum_comment.getComment_no() + ",");
			System.out.print(aforum_comment.getArticle_no() + ",");
			System.out.print(aforum_comment.getMember_no() + ",");
			System.out.print(aforum_comment.getComment_time() + ",");
			System.out.print(aforum_comment.getComment_content() + ",");
			System.out.print(aforum_comment.getComment_status() + ",");
			System.out.println();
		}
	}

}
