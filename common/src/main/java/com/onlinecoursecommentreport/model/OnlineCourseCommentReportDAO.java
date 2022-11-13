package com.onlinecoursecommentreport.model;

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


public class OnlineCourseCommentReportDAO implements OnlineCourseCommentReportDAO_interface{
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
		"INSERT INTO Online_course_comment_report (member_no, comment_no, report_reason, report_status) "
		+ "VALUES (?, ?, ?, ?)";
	
	private static final String GET_ALL_STMT = 
		"SELECT report_no, member_no, comment_no, report_reason, report_status "
		+ "FROM Online_course_comment_report order by report_no";
	private static final String GET_ONE_STMT = 
		"SELECT report_no, member_no, comment_no, report_reason, report_status "
		+ "FROM Online_course_comment_report where report_no = ?";
	private static final String GET_OnlineCommentReports_ByMemberNo_STMT = 
		"SELECT report_no, member_no, comment_no, report_reason, report_status "
		+ "FROM Online_course_comment_report where member_no = ? order by report_no";
	private static final String GET_OnlineCommentReports_ByReportStatus_STMT = 
			"SELECT report_no, member_no, comment_no, report_reason, report_status "
			+ "FROM Online_course_comment_report where report_status = ? order by report_no";
	
	
	private static final String DELETE = 
		"DELETE FROM Online_course_comment_report where report_no = ?";
	
	private static final String UPDATE = 
		"UPDATE Online_course_comment_report "
		+ "Set member_no=?, comment_no=?, report_reason=?, report_status=? "
		+ "where report_no = ?";
	private static final String UPDATESTATUS = "UPDATE Online_course_comment_report Set report_status = ? where report_no = ?";
	
	@Override
	public void insert(OnlineCourseCommentReportVO onlinecoursecommentreportVO){

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, onlinecoursecommentreportVO.getMemberNo());
			pstmt.setInt(2, onlinecoursecommentreportVO.getCommentNo());
			pstmt.setString(3, onlinecoursecommentreportVO.getReportReason());
			pstmt.setInt(4, onlinecoursecommentreportVO.getReportStatus());
			
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
	public void updateStatus(OnlineCourseCommentReportVO onlinecoursecommentreportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);

			pstmt.setInt(1, onlinecoursecommentreportVO.getReportStatus());
			pstmt.setInt(2, onlinecoursecommentreportVO.getReportNo());

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
	public OnlineCourseCommentReportVO findByPrimaryKey(Integer reportNo) {
		OnlineCourseCommentReportVO onlinecoursecommentreportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reportNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				onlinecoursecommentreportVO = new OnlineCourseCommentReportVO();
				onlinecoursecommentreportVO.setReportNo(rs.getInt("report_no"));
				onlinecoursecommentreportVO.setMemberNo(rs.getInt("member_no"));
				onlinecoursecommentreportVO.setCommentNo(rs.getInt("comment_no"));
				onlinecoursecommentreportVO.setReportReason(rs.getString("report_reason"));
				onlinecoursecommentreportVO.setReportStatus(rs.getInt("report_status"));
				
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
		return onlinecoursecommentreportVO;
	}

	@Override
	public List<OnlineCourseCommentReportVO> getAll() {
		List<OnlineCourseCommentReportVO> list = new ArrayList<>();
		OnlineCourseCommentReportVO onlinecoursecommentreportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				onlinecoursecommentreportVO = new OnlineCourseCommentReportVO();
				onlinecoursecommentreportVO.setReportNo(rs.getInt("report_no"));
				onlinecoursecommentreportVO.setMemberNo(rs.getInt("member_no"));
				onlinecoursecommentreportVO.setCommentNo(rs.getInt("comment_no"));
				onlinecoursecommentreportVO.setReportReason(rs.getString("report_reason"));
				onlinecoursecommentreportVO.setReportStatus(rs.getInt("report_status"));
				list.add(onlinecoursecommentreportVO); // Store the row in the list
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
	public List<OnlineCourseCommentReportVO> getOnlineCommentReportsByMemberNo(Integer memberNo) {
		List<OnlineCourseCommentReportVO> list = new ArrayList<>();
		OnlineCourseCommentReportVO onlinecoursecommentreportVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_OnlineCommentReports_ByMemberNo_STMT);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				onlinecoursecommentreportVO = new OnlineCourseCommentReportVO();
				onlinecoursecommentreportVO.setReportNo(rs.getInt("report_no"));
				onlinecoursecommentreportVO.setMemberNo(rs.getInt("member_no"));
				onlinecoursecommentreportVO.setCommentNo(rs.getInt("comment_no"));
				onlinecoursecommentreportVO.setReportReason(rs.getString("report_reason"));
				onlinecoursecommentreportVO.setReportStatus(rs.getInt("report_status"));
				list.add(onlinecoursecommentreportVO); // Store the row in the vector
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
	
	public Set<OnlineCourseCommentReportVO> getOnlinecommentReportsByReportstatus(Integer reportstatus){
		Set<OnlineCourseCommentReportVO> set = new LinkedHashSet<>();
		OnlineCourseCommentReportVO onlinecoursecommentreportVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_OnlineCommentReports_ByReportStatus_STMT);
			pstmt.setInt(1, reportstatus);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				onlinecoursecommentreportVO = new OnlineCourseCommentReportVO();
				onlinecoursecommentreportVO.setReportNo(rs.getInt("report_no"));
				onlinecoursecommentreportVO.setMemberNo(rs.getInt("member_no"));
				onlinecoursecommentreportVO.setCommentNo(rs.getInt("comment_no"));
				onlinecoursecommentreportVO.setReportReason(rs.getString("report_reason"));
				onlinecoursecommentreportVO.setReportStatus(rs.getInt("report_status"));
				set.add(onlinecoursecommentreportVO); // Store the row in the vector
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
	public List<OnlineCourseCommentReportVO> getOnlinecommentReportsByReportStatus(Integer reportStatus){
		List<OnlineCourseCommentReportVO> list = new ArrayList<>();
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_OnlineCommentReports_ByReportStatus_STMT);
			pstmt.setInt(1, reportStatus);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				OnlineCourseCommentReportVO onlinecoursecommentreportVO= new OnlineCourseCommentReportVO();
				onlinecoursecommentreportVO.setReportNo(rs.getInt("report_no"));
				onlinecoursecommentreportVO.setMemberNo(rs.getInt("member_no"));
				onlinecoursecommentreportVO.setCommentNo(rs.getInt("comment_no"));
				onlinecoursecommentreportVO.setReportReason(rs.getString("report_reason"));
				onlinecoursecommentreportVO.setReportStatus(rs.getInt("report_status"));
				list.add(onlinecoursecommentreportVO); // Store the row in the vector
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