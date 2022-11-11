package com.forum_article.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.latest_news.model.Latest_newsVO;

public class Forum_articleJDBCDAO implements Forum_articleDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/database1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
			"INSERT INTO forum_article(article_name, member_no, article_content) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT article_no, article_name, member_no, article_time, article_content, article_status FROM forum_article order by article_no";
	private static final String GET_ONE_STMT = 
			"SELECT article_no, article_name, member_no, article_time, article_content, article_status FROM forum_article where article_no = ?";
	private static final String DELETE = 
			"DELETE FROM forum_article where article_no = ?";
	private static final String UPDATE = 
			"UPDATE forum_article set article_name=?, article_content=? where article_no = ?";
private static final String change_status_0 = 
			"UPDATE forum_article set article_status=0 where article_no = ?";
private static final String change_status_1 = 
			"UPDATE forum_article set article_status=1 where article_no = ?";
	@Override
	public void insert(Forum_articleVO forum_articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, forum_articleVO.getArticle_name());
			pstmt.setInt(2, forum_articleVO.getMember_no());
			pstmt.setString(3, forum_articleVO.getArticle_content());
			
			
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
	public void update(Forum_articleVO forum_articleVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, forum_articleVO.getArticle_name());
			pstmt.setString(2, forum_articleVO.getArticle_content());
			pstmt.setInt(3, forum_articleVO.getArticle_no());
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
	public void delete(Integer article_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, article_no);

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
	public Forum_articleVO findByPrimarykey(Integer article_no) {
		Forum_articleVO forum_articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// forum_articleVO 也稱為 Domain objects
				forum_articleVO = new Forum_articleVO();
				forum_articleVO.setArticle_no(rs.getInt("article_no"));
				forum_articleVO.setArticle_name(rs.getString("article_name"));
				forum_articleVO.setMember_no(rs.getInt("member_no"));
				forum_articleVO.setArticle_time(rs.getDate("article_time"));
				forum_articleVO.setArticle_content(rs.getString("article_content"));
				forum_articleVO.setArticle_status(rs.getInt("article_status"));
	
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
		return forum_articleVO;
	}

	@Override
	public List<Forum_articleVO> getAll() {
		List<Forum_articleVO> list = new ArrayList<Forum_articleVO>();
		Forum_articleVO forum_articleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// forum_articleVO 也稱為 Domain objects
				forum_articleVO = new Forum_articleVO();
				forum_articleVO.setArticle_no(rs.getInt("article_no"));
				forum_articleVO.setArticle_name(rs.getString("article_name"));
				forum_articleVO.setMember_no(rs.getInt("member_no"));
				forum_articleVO.setArticle_time(rs.getDate("article_time"));
				forum_articleVO.setArticle_content(rs.getString("article_content"));
				forum_articleVO.setArticle_status(rs.getInt("article_status"));
				
				list.add(forum_articleVO); // Store the row in the list
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
	public void change_status_0(Integer article_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(change_status_0);

			pstmt.setInt(1, article_no);

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
	public void change_status_1(Integer article_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(change_status_1);

			pstmt.setInt(1, article_no);

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
		Forum_articleJDBCDAO dao = new Forum_articleJDBCDAO();
		
		// 新增
//		Forum_articleVO forum_articleVO1 = new Forum_articleVO();
//		
//		forum_articleVO1.setArticle_name("論壇文章超棒");
//		forum_articleVO1.setMember_no(2);
//		forum_articleVO1.setArticle_content("首要追求的就是蔬食、健康、養生、綠色友善的地球環境，並且期待希望達到身、心、靈的平衡與美善。");
//
//		dao.insert(forum_articleVO1);
//		
//		// 修改
//		Forum_articleVO forum_articleVO2 = new Forum_articleVO();
//		
//		forum_articleVO2.setArticle_name("文章名稱1文章名稱1");
//		forum_articleVO2.setArticle_content("文章內容1文章內容1");
//		
//		forum_articleVO2.setArticle_no(1);
//		dao.update(forum_articleVO2);
//		
//		
//		
//		// 刪除
//		dao.delete(5);
		
		dao.change_status_0(2);
		dao.change_status_1(4);
		
		// 查詢
//		Forum_articleVO forum_articleVO3 = dao.findByPrimarykey(1);
//		System.out.print(forum_articleVO3.getArticle_no() + ",");
//		System.out.print(forum_articleVO3.getArticle_name() + ",");
//		System.out.print(forum_articleVO3.getMember_no() + ",");
//		System.out.print(forum_articleVO3.getArticle_time() + ",");
//		System.out.print(forum_articleVO3.getArticle_content() + ",");
//		System.out.print(forum_articleVO3.getArticle_status() + ",");
//		
//		System.out.println("---------------------");
		
		// 查詢
				List<Forum_articleVO> list = dao.getAll();
				for (Forum_articleVO aforum_article : list) {
					System.out.print(aforum_article.getArticle_no() + ",");
					System.out.print(aforum_article.getArticle_name() + ",");
					System.out.print(aforum_article.getMember_no() + ",");
					System.out.print(aforum_article.getArticle_time() + ",");
					System.out.print(aforum_article.getArticle_content() + ",");
					System.out.print(aforum_article.getArticle_status() + ",");
					
					System.out.println();
						}
		
		
		
	}
}
