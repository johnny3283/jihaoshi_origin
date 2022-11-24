package com.mem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.onlinecoursecomment.model.OnlineCourseCommentVO;
import com.onlinecoursecommentreport.model.OnlineCourseCommentReportVO;
import com.phyCourseComment.model.phyCourseCommentVO;
import com.phyCourseCommentReport.model.phyCourseCommentReportVO;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/jihaoshi?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO member (member_account,member_password,member_name,member_phone,member_nickname,member_address,member_email) VALUES (?, ?, ?, ?, ?,?,?) ";
	private static final String GET_ALL_STMT = "SELECT member_no,member_account,member_password,member_name,member_phone,member_nickname,member_address,member_email FROM member order by member_no ";
	private static final String GET_ONE_STMT = "SELECT member_no,member_account,member_password,member_name,member_phone,member_nickname,member_address,member_email FROM member where member_no = ? ";
	private static final String DELETE = "DELETE FROM member where member_no = ?";
	private static final String UPDATE = "UPDATE member set member_account=?,member_password=?,member_name=?,member_phone=?,member_nickname=?,member_address=?,member_email=? where member_no = ? ";
	private static final String Login = "SELECT * FROM MEMBER where member_account = ? and member_password = ?";

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memberVO.getMemberAccount());
			pstmt.setString(2, memberVO.getMemberPassword());
			pstmt.setString(3, memberVO.getMemberName());
			pstmt.setString(4, memberVO.getMemberPhone());
			pstmt.setString(5, memberVO.getMemberNickname());
			pstmt.setString(6, memberVO.getMemberAddress());
			pstmt.setString(7, memberVO.getMemberEmail());

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
	public void update(MemberVO memberVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberVO.getMemberAccount());
			pstmt.setString(2, memberVO.getMemberPassword());
			pstmt.setString(3, memberVO.getMemberName());
			pstmt.setString(4, memberVO.getMemberPhone());
			pstmt.setString(5, memberVO.getMemberNickname());
			pstmt.setString(6, memberVO.getMemberAddress());
			pstmt.setString(7, memberVO.getMemberEmail());
			pstmt.setInt(8, memberVO.getMemberNo());

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
	public void delete(Integer memberno) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memberno);

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
	public void mngMember(MemberVO memberVO) {

	}

	@Override
	public MemberVO findByPrimaryKey(Integer member_no) {
		MemberVO MemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, member_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				MemberVO = new MemberVO();
				MemberVO.setMemberNo(rs.getInt("member_no"));
				MemberVO.setMemberAccount(rs.getString("member_account"));
				MemberVO.setMemberPassword(rs.getString("member_password"));
				MemberVO.setMemberName(rs.getString("member_name"));
				MemberVO.setMemberPhone(rs.getString("member_phone"));
				MemberVO.setMemberNickname(rs.getString("member_nickname"));
				MemberVO.setMemberAddress(rs.getString("member_address"));
				MemberVO.setMemberEmail(rs.getString("member_email"));
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
		return MemberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO MemberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				MemberVO = new MemberVO();
				MemberVO.setMemberNo(rs.getInt("member_no"));
				MemberVO.setMemberAccount(rs.getString("member_account"));
				;
				MemberVO.setMemberPassword(rs.getString("member_password"));
				MemberVO.setMemberName(rs.getString("member_name"));
				MemberVO.setMemberPhone(rs.getString("member_phone"));
				MemberVO.setMemberNickname(rs.getString("member_nickname"));
				MemberVO.setMemberAddress(rs.getString("member_address"));
				MemberVO.setMemberEmail(rs.getString("member_email"));
				list.add(MemberVO); // Store the row in the list
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
	public MemberVO selectForLogin(String mamberAccount, String mamberPassword) {
		MemberVO MemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(Login);

			pstmt.setString(1, mamberAccount);
			pstmt.setString(2, mamberPassword);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				MemberVO = new MemberVO();
				MemberVO.setMemberAccount(rs.getString("member_account"));
				MemberVO.setMemberPassword(rs.getString("member_password"));
				MemberVO.setMemberName(rs.getString("member_name"));
				MemberVO.setMemberNo(rs.getInt("member_no"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
		return MemberVO;
	}

	@Override
	public MemberVO findByEmail(String memberEmail) {
		return null;
	}

	@Override
	public List<phyCourseCommentVO> getPhyCourseCommentsByMemberNo(Integer memberNo) {
		return null;
	}

	@Override
	public List<phyCourseCommentReportVO> getPhyCourseCommentReportsByMemberNo(Integer memberNo) {
		return null;
	}

	public static void main(String[] args) {
		MemberJDBCDAO dao = new MemberJDBCDAO();

		MemberVO MemberVO1 = new MemberVO();
		MemberVO1.setMemberAccount("text1");
		MemberVO1.setMemberPassword("testpas");
		MemberVO1.setMemberName("testest2");
		MemberVO1.setMemberPhone("0910258693");
		MemberVO1.setMemberNickname("hihi");
		MemberVO1.setMemberAddress("earth");
		MemberVO1.setMemberEmail("qqq@@@@");
		dao.insert(MemberVO1);

		MemberVO MemberVO2 = new MemberVO();
		MemberVO2.setMemberAccount("1021");
		MemberVO2.setMemberPassword("111111");
		MemberVO2.setMemberName("test5");
		MemberVO2.setMemberPhone("7777777177");
		MemberVO2.setMemberNickname("hih111ihi");
		MemberVO2.setMemberAddress("eart111h1");
		MemberVO2.setMemberEmail("qqq@1111@@@");
		MemberVO2.setMemberNo(11);
		dao.update(MemberVO2);

//		dao.delete(8);

		MemberVO MemberVO3 = dao.findByPrimaryKey(7);
		System.out.print(MemberVO3.getMemberNo() + ",");
		System.out.print(MemberVO3.getMemberAccount() + ",");
		System.out.print(MemberVO3.getMemberPassword() + ",");
		System.out.print(MemberVO3.getMemberName() + ",");
		System.out.print(MemberVO3.getMemberPhone() + ",");
		System.out.print(MemberVO3.getMemberNickname() + ",");
		System.out.print(MemberVO3.getMemberAddress() + ",");
		System.out.println(MemberVO3.getMemberEmail());
		System.out.println("---------------------");

		List<MemberVO> list = dao.getAll();
		for (MemberVO amem : list) {
			System.out.print(amem.getMemberNo() + "," + " ");
			System.out.print(amem.getMemberAccount() + "," + " ");
			System.out.print(amem.getMemberPassword() + "," + " ");
			System.out.print(amem.getMemberName() + "," + " ");
			System.out.print(amem.getMemberPhone() + "," + " ");
			System.out.print(amem.getMemberNickname() + "," + " ");
			System.out.print(amem.getMemberAddress() + ",");
			System.out.print(amem.getMemberEmail() + "\t");
			System.out.println();
		}

	}

	@Override
	public MemberVO findByAccount(String memberAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OnlineCourseCommentVO> getOnlineCourseCommentsByMemberNo(Integer memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OnlineCourseCommentReportVO> getOnlineCourseCommentReportsByMemberNo(Integer memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
