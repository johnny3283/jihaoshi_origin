package com.phyCourseCommentReport.model;

import java.sql.Connection;
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


public class phyCourseCommentReportDAO implements phyCourseCommentReportDAO_interface{
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
		"INSERT INTO physical_course_comment_report (member_no, comment_no, phy_com_rep_content) "
		+ "VALUES (?, ?, ?)";
	
	private static final String GET_ALL_STMT = 
		"SELECT * FROM physical_course_comment_report order by report_status";
		 
	private static final String GET_ONE_STMT = 
		"SELECT *FROM physical_course_comment_report where report_no = ?";
		
	private static final String GET_PhyCommentReports_ByMemberNo_STMT = 
		"SELECT *FROM physical_course_comment_report where member_no = ? order by report_no";
		
	private static final String GET_PhyCommentReports_ByReportStatus_STMT = 
			"SELECT *FROM physical_course_comment_report where report_status = ? order by report_no";
			
	private static final String DELETE = 
		"DELETE FROM physical_course_comment_report where report_no = ?";
	
	private static final String UPDATESTATUS = "UPDATE Online_course_comment_report Set report_status = ? where report_no = ?";
	
	@Override
	public void insert(phyCourseCommentReportVO phyCourseCommentReportVO){

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, phyCourseCommentReportVO.getMemberNo());
			pstmt.setInt(2, phyCourseCommentReportVO.getCommentNo());
			pstmt.setString(3, phyCourseCommentReportVO.getReportReason());
			
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
	public void updateStatus(phyCourseCommentReportVO phyCourseCommentReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);

			pstmt.setInt(1, phyCourseCommentReportVO.getReportStatus());
			pstmt.setInt(2, phyCourseCommentReportVO.getReportNo());

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
	public void delete(Integer reportno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, reportno);


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
	public phyCourseCommentReportVO findByPrimaryKey(Integer reportNo) {
		phyCourseCommentReportVO phyCourseCommentReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reportNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				phyCourseCommentReportVO = new phyCourseCommentReportVO();
				phyCourseCommentReportVO.setReportNo(rs.getInt("report_no"));
				phyCourseCommentReportVO.setMemberNo(rs.getInt("member_no"));
				phyCourseCommentReportVO.setCommentNo(rs.getInt("comment_no"));
				phyCourseCommentReportVO.setReportReason(rs.getString("report_reason"));
				phyCourseCommentReportVO.setReportStatus(rs.getInt("report_status"));
				
			}
			//System.out.println(onlinecoursecommentreportVO.tosString());
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
		return phyCourseCommentReportVO;
	}

	@Override
	public List<phyCourseCommentReportVO> getAll() {
		List<phyCourseCommentReportVO> list = new ArrayList<>();
		phyCourseCommentReportVO phyCourseCommentReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				phyCourseCommentReportVO = new phyCourseCommentReportVO();
				phyCourseCommentReportVO.setReportNo(rs.getInt("report_no"));
				phyCourseCommentReportVO.setMemberNo(rs.getInt("member_no"));
				phyCourseCommentReportVO.setCommentNo(rs.getInt("comment_no"));
				phyCourseCommentReportVO.setReportReason(rs.getString("report_reason"));
				phyCourseCommentReportVO.setReportStatus(rs.getInt("report_status"));
				list.add(phyCourseCommentReportVO); // Store the row in the list
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
	public List<phyCourseCommentReportVO> getPhyCommentReportsByMemberNo(Integer memberNo) {
		List<phyCourseCommentReportVO> list = new ArrayList<>();
		phyCourseCommentReportVO phyCourseCommentReportVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PhyCommentReports_ByMemberNo_STMT);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				phyCourseCommentReportVO = new phyCourseCommentReportVO();
				phyCourseCommentReportVO.setReportNo(rs.getInt("report_no"));
				phyCourseCommentReportVO.setMemberNo(rs.getInt("member_no"));
				phyCourseCommentReportVO.setCommentNo(rs.getInt("comment_no"));
				phyCourseCommentReportVO.setReportReason(rs.getString("report_reason"));
				phyCourseCommentReportVO.setReportStatus(rs.getInt("report_status"));
				list.add(phyCourseCommentReportVO); // Store the row in the vector
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
	
	public Set<phyCourseCommentReportVO> getPhycommentReportsByReportstatus(Integer reportstatus){
		Set<phyCourseCommentReportVO> set = new LinkedHashSet<>();
		phyCourseCommentReportVO phyCourseCommentReportVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PhyCommentReports_ByReportStatus_STMT);
			pstmt.setInt(1, reportstatus);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				phyCourseCommentReportVO = new phyCourseCommentReportVO();
				phyCourseCommentReportVO.setReportNo(rs.getInt("report_no"));
				phyCourseCommentReportVO.setMemberNo(rs.getInt("member_no"));
				phyCourseCommentReportVO.setCommentNo(rs.getInt("comment_no"));
				phyCourseCommentReportVO.setReportReason(rs.getString("report_reason"));
				phyCourseCommentReportVO.setReportStatus(rs.getInt("report_status"));
				set.add(phyCourseCommentReportVO); // Store the row in the vector
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
	public List<phyCourseCommentReportVO> getPhycommentReportsByReportStatus(Integer reportStatus){
		List<phyCourseCommentReportVO> list = new ArrayList<>();
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PhyCommentReports_ByReportStatus_STMT);
			pstmt.setInt(1, reportStatus);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				phyCourseCommentReportVO phyCourseCommentReportVO= new phyCourseCommentReportVO();
				phyCourseCommentReportVO.setReportNo(rs.getInt("report_no"));
				phyCourseCommentReportVO.setMemberNo(rs.getInt("member_no"));
				phyCourseCommentReportVO.setCommentNo(rs.getInt("comment_no"));
				phyCourseCommentReportVO.setReportReason(rs.getString("report_reason"));
				phyCourseCommentReportVO.setReportStatus(rs.getInt("report_status"));
				list.add(phyCourseCommentReportVO); // Store the row in the vector
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
//	public static void main(String[] args) {
//		OnlineCourseCommentReportDAO dao = new OnlineCourseCommentReportDAO();
//		List<OnlineCourseCommentReportVO> list = dao.getOnlinecommentReportsByReportstatus(0);
//		for (FAQVO aFAQ : list) {
//			System.out.print(aFAQ.getFaqNo() + ",");
//			System.out.print(aFAQ.getFaqQue() + ",");
//			System.out.print(aFAQ.getFaqAns() + ",");
//			System.out.print(aFAQ.getFaqClass());
//			System.out.println();
//		}
//	}
}