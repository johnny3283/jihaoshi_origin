package com.faq.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class FAQDAO implements FAQDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jihaoshi");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO FAQ (FAQ_que, FAQ_ans, FAQ_class) VALUES (?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT FAQ_no, FAQ_que, FAQ_ans, FAQ_class FROM FAQ order by FAQ_no";
	private static final String GET_ONE_STMT = "SELECT FAQ_no, FAQ_que, FAQ_ans, FAQ_class FROM FAQ where FAQ_no = ?";
	private static final String GET_FAQs_ByFAQClass_STMT = "SELECT FAQ_no, FAQ_que, FAQ_ans, FAQ_class FROM FAQ where FAQ_class = ? order by FAQ_no";

	private static final String DELETE = "DELETE FROM FAQ where FAQ_no = ?";

	private static final String UPDATE = "UPDATE FAQ Set FAQ_que=?, FAQ_ans=?, FAQ_class=? where FAQ_no = ?";
	private static final String GET_ALL= "SELECT * FROM FAQ";
	private  static final String GET_CLASS=" where FAQ_class=? "; 
	@Override
	public void insert(FAQVO faqVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, faqVO.getFaqQue());
			pstmt.setString(2, faqVO.getFaqAns());
			pstmt.setString(3, faqVO.getFaqClass());

			pstmt.executeUpdate();

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
	public void update(FAQVO faqVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, faqVO.getFaqQue());
			pstmt.setString(2, faqVO.getFaqAns());
			pstmt.setString(3, faqVO.getFaqClass());
			pstmt.setInt(4, faqVO.getFaqNo());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer faqNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, faqNo);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public FAQVO findByPrimaryKey(Integer faqNo) {

		FAQVO faqVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, faqNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				faqVO = new FAQVO();
				faqVO.setFaqNo(rs.getInt("faq_no"));
				faqVO.setFaqQue(rs.getString("faq_que"));
				faqVO.setFaqAns(rs.getString("faq_ans"));
				faqVO.setFaqClass(rs.getString("faq_class"));
			}

			// Handle any driver errors
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
		return faqVO;
	}

	@Override
	public List<FAQVO> getAll() {
		List<FAQVO> list = new ArrayList<>();
		FAQVO faqVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				faqVO = new FAQVO();
				faqVO.setFaqNo(rs.getInt("faq_no"));
				faqVO.setFaqQue(rs.getString("faq_que"));
				faqVO.setFaqAns(rs.getString("faq_ans"));
				faqVO.setFaqClass(rs.getString("faq_class"));
				list.add(faqVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public List<FAQVO> getFAQsByFAQClass(String faqClass) {
		List<FAQVO> list = new ArrayList<>();
		FAQVO faqVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FAQs_ByFAQClass_STMT);
			pstmt.setString(1, faqClass);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				faqVO = new FAQVO();
				faqVO.setFaqNo(rs.getInt("faq_no"));
				faqVO.setFaqQue(rs.getString("faq_que"));
				faqVO.setFaqAns(rs.getString("faq_ans"));
				faqVO.setFaqClass(rs.getString("faq_class"));
				list.add(faqVO); // Store the row in the vector
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<FAQVO> selectFAQ(){
		 List<FAQVO> list = new ArrayList<>();
			FAQVO faqVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					faqVO = new FAQVO();
					faqVO.setFaqNo(rs.getInt("faq_no"));
					faqVO.setFaqQue(rs.getString("faq_que"));
					faqVO.setFaqAns(rs.getString("faq_ans"));
					faqVO.setFaqClass(rs.getString("faq_class"));
					list.add(faqVO); // Store the row in the list
				}

				// Handle any driver errors
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
	public List<FAQVO> selectFAQ(String faqClass){
		 List<FAQVO> list = new ArrayList<>();
			FAQVO faqVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL+GET_CLASS);
				pstmt.setString(1, faqClass);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					faqVO = new FAQVO();
					faqVO.setFaqNo(rs.getInt("faq_no"));
					faqVO.setFaqQue(rs.getString("faq_que"));
					faqVO.setFaqAns(rs.getString("faq_ans"));
					faqVO.setFaqClass(rs.getString("faq_class"));
					list.add(faqVO); // Store the row in the list
				}

				// Handle any driver errors
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
		FAQDAO dao = new FAQDAO();
//		FAQVO faqVO10 = new FAQVO();
//		faqVO10.setFaqque("�ʪ��@�w�n�[�J�|����");
//		faqVO10.setFaqans("�Ĥ@���ʪ��Х��[�J�|���A�}�l�ʪ��A�~�i�ɦ��h���|���A��");
//		faqVO10.setFaqclass("�ʪ� Shopping");
//		dao.insert(faqVO10);
		// �d��
		List<FAQVO> list = dao.getAll();
		for (FAQVO aFAQ : list) {
			System.out.print(aFAQ.getFaqNo() + ",");
			System.out.print(aFAQ.getFaqQue() + ",");
			System.out.print(aFAQ.getFaqAns() + ",");
			System.out.print(aFAQ.getFaqClass());
			System.out.println();
		}
	}
}