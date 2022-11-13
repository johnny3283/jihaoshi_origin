package com.onlinecoursecomment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OnlineCourseCommentDAO implements OnlineCourseCommentDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jihaoshi");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO Online_course_comment "
		+ "(member_no,course_no,comment_content,comment_score,comment_status)"
		+ "VALUES (?, ?, ?, ?, ?)";
	
	private static final String GET_ALL_STMT = 
		"SELECT * FROM Online_course_comment order by comment_no";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM Online_course_comment where comment_no = ?";
	private static final String GET_OnlineComments_ByCourseNo_STMT = 
		"SELECT * FROM Online_course_comment where course_no = ? order by comment_no";
	private static final String GET_OnlineComments_ByMemberNo_STMT = 
			"SELECT * FROM Online_course_comment where member_no = ? order by comment_no";
	
	
	private static final String DELETE = 
		"DELETE FROM Online_course_comment where comment_no = ?";
	
	private static final String UPDATE = 
		"UPDATE Online_course_comment Set "
		+ "member_no = ?, course_no = ?, comment_content = ?,"
		+ "comment_score = ?, comment_status = ? where comment_no = ?";
	private static final String UPDATESTATUS = "UPDATE Online_course_comment Set comment_status = ? where comment_no = ?";

	@Override
	public void insert(OnlineCourseCommentVO onlineCourseCommentVO){

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, onlineCourseCommentVO.getMemberNo());
			pstmt.setInt(2, onlineCourseCommentVO.getCourseNo());
			pstmt.setString(3, onlineCourseCommentVO.getCommentContent());
			pstmt.setInt(4, onlineCourseCommentVO.getCommentScore());
			pstmt.setInt(5, onlineCourseCommentVO.getCommentStatus());
			
			pstmt.executeUpdate();

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
	public void update(OnlineCourseCommentVO onlineCourseCommentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, onlineCourseCommentVO.getMemberNo());
			pstmt.setInt(2, onlineCourseCommentVO.getCourseNo());
			pstmt.setString(3, onlineCourseCommentVO.getCommentContent());
			pstmt.setInt(4, onlineCourseCommentVO.getCommentScore());
			pstmt.setInt(5, onlineCourseCommentVO.getCommentStatus());
			pstmt.setInt(6, onlineCourseCommentVO.getCommentNo());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void updateStatus(OnlineCourseCommentVO onlineCourseCommentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);

			pstmt.setInt(1, onlineCourseCommentVO.getCommentNo());
			pstmt.setInt(2, onlineCourseCommentVO.getCommentStatus());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer commentNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, commentNo);


			pstmt.executeUpdate();

			// Handle any driver errors
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
	public OnlineCourseCommentVO findByPrimaryKey(Integer commentNo) {

		OnlineCourseCommentVO onlineCourseCommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, commentNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				onlineCourseCommentVO = new OnlineCourseCommentVO();
				onlineCourseCommentVO.setCommentNo(rs.getInt("comment_no"));
				onlineCourseCommentVO.setMemberNo(rs.getInt("member_no"));
				onlineCourseCommentVO.setCourseNo(rs.getInt("course_no"));
				onlineCourseCommentVO.setCommentContent(rs.getString("comment_content"));
				onlineCourseCommentVO.setCommentScore(rs.getInt("comment_score"));
				onlineCourseCommentVO.setCommentStatus(rs.getInt("comment_status"));
			}

			// Handle any driver errors
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
		return onlineCourseCommentVO;
	}

	@Override
	public List<OnlineCourseCommentVO> getAll() {
		List<OnlineCourseCommentVO> list = new ArrayList<>();
		OnlineCourseCommentVO onlineCourseCommentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				onlineCourseCommentVO = new OnlineCourseCommentVO();
				onlineCourseCommentVO.setCommentNo(rs.getInt("comment_no"));
				onlineCourseCommentVO.setMemberNo(rs.getInt("member_no"));
				onlineCourseCommentVO.setCourseNo(rs.getInt("course_no"));
				onlineCourseCommentVO.setCommentContent(rs.getString("comment_content"));
				onlineCourseCommentVO.setCommentScore(rs.getInt("comment_score"));
				onlineCourseCommentVO.setCommentStatus(rs.getInt("comment_status"));
				list.add(onlineCourseCommentVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public Set<OnlineCourseCommentVO> getOnlineCommentsByCourseNo(Integer courseNo) {
		Set<OnlineCourseCommentVO> set = new LinkedHashSet<>();
		OnlineCourseCommentVO onlineCourseCommentVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_OnlineComments_ByCourseNo_STMT);
			pstmt.setInt(1, courseNo);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				onlineCourseCommentVO = new OnlineCourseCommentVO();
				onlineCourseCommentVO.setCommentNo(rs.getInt("comment_no"));
				onlineCourseCommentVO.setMemberNo(rs.getInt("member_no"));
				onlineCourseCommentVO.setCourseNo(rs.getInt("course_no"));
				onlineCourseCommentVO.setCommentContent(rs.getString("comment_content"));
				onlineCourseCommentVO.setCommentScore(rs.getInt("comment_score"));
				onlineCourseCommentVO.setCommentStatus(rs.getInt("comment_status"));
				set.add(onlineCourseCommentVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}
	
	public List<OnlineCourseCommentVO> getOnlineCommentsByMemberNo(Integer memberNo){
		List<OnlineCourseCommentVO> list = new ArrayList<>();
		OnlineCourseCommentVO onlineCourseCommentVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_OnlineComments_ByMemberNo_STMT);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				onlineCourseCommentVO = new OnlineCourseCommentVO();
				onlineCourseCommentVO.setCommentNo(rs.getInt("comment_no"));
				onlineCourseCommentVO.setMemberNo(rs.getInt("member_no"));
				onlineCourseCommentVO.setCourseNo(rs.getInt("course_no"));
				onlineCourseCommentVO.setCommentContent(rs.getString("comment_content"));
				onlineCourseCommentVO.setCommentScore(rs.getInt("comment_score"));
				onlineCourseCommentVO.setCommentStatus(rs.getInt("comment_status"));
				list.add(onlineCourseCommentVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
}