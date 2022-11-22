package com.phyCourseComment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class phyCourseCommentDAO implements phyCourseCommentDAO_interface{
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
		"INSERT INTO physical_course_comment "
		+ "(member_no,course_no,comment_content)"
		+ "VALUES (?, ?, ?)";
	
	private static final String GET_ALL_STMT = 
		"SELECT a.comment_no,a.member_no,a.course_no,\r\n"
		+ "b.course_name,a.comment_content,a.comment_status\r\n"
		+ " FROM physical_course_comment a\r\n"
		+ " join physical_course b\r\n"
		+ " on a.course_no = b.course_no;";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM physical_course_comment where comment_no = ?";
	private static final String GET_PhyComments_ByCourseNo_STMT = 
		"SELECT * FROM physical_course_comment where course_no = ? and comment_status=1 order by comment_no desc";
	private static final String GET_PhyComments_ByMemberNo_STMT = 
			"SELECT * FROM physical_course_comment where member_no = ? and comment_status=1 order by comment_no desc";
	
	
	private static final String DELETE = 
		"DELETE FROM physical_course_comment where comment_no = ?";
	
	private static final String UPDATE = 
		"UPDATE physical_course_comment Set "
		+ "member_no = ?, course_no = ?, comment_content = ?,"
		+ " where comment_no = ?";
	private static final String UPDATESTATUS = "UPDATE physical_course_comment Set comment_status = ? where comment_no = ?";

	@Override
	public void insert(phyCourseCommentVO phyCourseCommentVO){

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, phyCourseCommentVO.getMemberNo());
			pstmt.setInt(2, phyCourseCommentVO.getCourseNo());
			pstmt.setString(3, phyCourseCommentVO.getCommentContent());
			
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
	public void update(phyCourseCommentVO phyCourseCommentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, phyCourseCommentVO.getMemberNo());
			pstmt.setInt(2, phyCourseCommentVO.getCourseNo());
			pstmt.setString(3, phyCourseCommentVO.getCommentContent());
			pstmt.setInt(4, phyCourseCommentVO.getCommentNo());

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
	public void updateStatus(phyCourseCommentVO phyCourseCommentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);

			
			pstmt.setInt(1, phyCourseCommentVO.getCommentStatus());
			pstmt.setInt(2, phyCourseCommentVO.getCommentNo());

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
	public phyCourseCommentVO findByPrimaryKey(Integer commentNo) {

		phyCourseCommentVO phyCourseCommentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, commentNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				phyCourseCommentVO = new phyCourseCommentVO();
				phyCourseCommentVO.setCommentNo(rs.getInt("comment_no"));
				phyCourseCommentVO.setMemberNo(rs.getInt("member_no"));
				phyCourseCommentVO.setCourseNo(rs.getInt("course_no"));
				phyCourseCommentVO.setCommentContent(rs.getString("comment_content"));
				phyCourseCommentVO.setCommentScore(rs.getInt("comment_score"));
				phyCourseCommentVO.setCommentStatus(rs.getInt("comment_status"));
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
		return phyCourseCommentVO;
	}

	@Override
	public List<phyCourseCommentVO> getAll() {
		List<phyCourseCommentVO> list = new ArrayList<phyCourseCommentVO>();
		phyCourseCommentVO phyCourseCommentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				phyCourseCommentVO = new phyCourseCommentVO();
				phyCourseCommentVO.setCommentNo(rs.getInt("comment_no"));
				phyCourseCommentVO.setMemberNo(rs.getInt("member_no"));
				phyCourseCommentVO.setCourseNo(rs.getInt("course_no"));
				phyCourseCommentVO.setCourseName(rs.getString("course_name"));
				phyCourseCommentVO.setCommentContent(rs.getString("comment_content"));
				phyCourseCommentVO.setCommentStatus(rs.getInt("comment_status"));
				list.add(phyCourseCommentVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (Exception se) {
			se.printStackTrace();
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
	public List<phyCourseCommentVO> getPhyCommentsByCourseNo(Integer courseNo) {
		List<phyCourseCommentVO> list = new ArrayList<>();
		phyCourseCommentVO phyCourseCommentVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PhyComments_ByCourseNo_STMT);
			pstmt.setInt(1, courseNo);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				phyCourseCommentVO = new phyCourseCommentVO();
				phyCourseCommentVO.setCommentNo(rs.getInt("comment_no"));
				phyCourseCommentVO.setMemberNo(rs.getInt("member_no"));
				phyCourseCommentVO.setCourseNo(rs.getInt("course_no"));
				phyCourseCommentVO.setCommentContent(rs.getString("comment_content"));
				phyCourseCommentVO.setCommentScore(rs.getInt("comment_score"));
				phyCourseCommentVO.setCommentStatus(rs.getInt("comment_status"));
				list.add(phyCourseCommentVO); // Store the row in the vector
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
	
	public List<phyCourseCommentVO> getPhyCommentsByMemberNo(Integer memberNo){
		List<phyCourseCommentVO> list = new ArrayList<>();
		phyCourseCommentVO phyCourseCommentVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PhyComments_ByMemberNo_STMT);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				phyCourseCommentVO = new phyCourseCommentVO();
				phyCourseCommentVO.setCommentNo(rs.getInt("comment_no"));
				phyCourseCommentVO.setMemberNo(rs.getInt("member_no"));
				phyCourseCommentVO.setCourseNo(rs.getInt("course_no"));
				phyCourseCommentVO.setCommentContent(rs.getString("comment_content"));
				phyCourseCommentVO.setCommentStatus(rs.getInt("comment_status"));
				list.add(phyCourseCommentVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (Exception se) {
			se.printStackTrace();
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