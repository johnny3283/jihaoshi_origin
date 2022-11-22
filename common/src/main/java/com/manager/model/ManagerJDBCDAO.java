package com.manager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerJDBCDAO implements ManagerDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/jihaoshi?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	// manager_no, manager_name, manager_ip, manager_account, manager_password,manager_status

	private static final String INSERT_STMT = "INSERT INTO backend_manager (manager_name,manager_ip,manager_account,manager_password) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT b.manager_no,b.manager_name,  e.authority_name, \r\n "
			+ "b.manager_account,b.manager_status\r\n "
			+ "FROM authority_detail a \r\n"
			+ "join authority e join backend_manager b\r\n "
			+ "on a.authority_no = e.authority_no \r\n "
			+ "and b.manager_no = a.manager_no\r\n "
			+ "order by a.manager_no ";
	private static final String GET_ONE_STMT = "SELECT manager_no, manager_name, manager_ip, manager_account, manager_password, manager_status FROM backend_manager where manager_no = ?";
	private static final String DELETE = "DELETE FROM backend_manager where manager_no = ?";
	private static final String UPDATE = "UPDATE backend_manager set manager_name=?,manager_ip=?,manager_account=?,manager_password=? where manager_no = ?";
	private static final String Login = "SELECT * FROM backend_manager where manager_account = ? and manager_password = ?";

	@Override
	public void insert(ManagerVO ManagerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ManagerVO.getManagerName());
			pstmt.setString(2, ManagerVO.getManagerIp());
			pstmt.setString(3, ManagerVO.getManagerAccount());
			pstmt.setString(4, ManagerVO.getManagerPassword());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			se.printStackTrace();
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
	public void update(ManagerVO ManagerVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, ManagerVO.getManagerName());
			pstmt.setString(2, ManagerVO.getManagerIp());
			pstmt.setString(3, ManagerVO.getManagerAccount());
			pstmt.setString(4, ManagerVO.getManagerPassword());
			pstmt.setInt(5, ManagerVO.getManagerNo());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
	public ManagerVO findByPrimaryKey(Integer managerNo) {
		ManagerVO ManamgerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, managerNo);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				ManamgerVO = new ManagerVO();
				ManamgerVO.setManagerName(rs.getNString("manager_name"));
				ManamgerVO.setManagerIp(rs.getString("manager_ip"));
				ManamgerVO.setManagerAccount(rs.getString("manager_account"));
				ManamgerVO.setManagerPassword(rs.getString("manager_password"));
				ManamgerVO.setManagerNo(rs.getInt("manager_no"));


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
		return ManamgerVO;
	}

	@Override
	public ManagerVO selectForLogin(String mamberAccount, String mamberPassword) {
		ManagerVO ManamgerVO = null;
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

				ManamgerVO = new ManagerVO();
				ManamgerVO.setManagerAccount(rs.getString("manager_account"));
//				MemberVO.setManagerPassword(rs.getString("manager_password"));
				ManamgerVO.setManagerName(rs.getString("manager_name"));
				ManamgerVO.setManagerNo(rs.getInt("manager_no"));
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
		return ManamgerVO;
	}

	@Override
	public List<Integer> GetAuthority(Integer managerNo) {
		return null;
	}

	@Override
	public void delete(Integer managerNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, managerNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
	public List<ManagerVO> getAll() {
		List<ManagerVO> list = new ArrayList<ManagerVO>();
		ManagerVO managerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			//manager_no, manager_name, authority_name, manager_account, manager_status
			
			while (rs.next()) {
				
				managerVO = new ManagerVO();
				managerVO.setManagerNo(rs.getInt("manager_no"));
				managerVO.setManagerName(rs.getString("manager_name"));
				managerVO.setManagerAccount(rs.getString("manager_account"));
//				managerVO.setManagerPassword(rs.getString("manager_password"));
				managerVO.setManagerStatus(rs.getInt("manager_status"));
				managerVO.setAuthorityName(rs.getString("authority_name"));
				
				list.add(managerVO); 
			}

			
		} catch (SQLException se) {
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
	public static void main(String[] args) {
		ManagerJDBCDAO dao = new ManagerJDBCDAO();
		List<ManagerVO> list = dao.getAll();
		for (ManagerVO amem : list) {
			System.out.print(amem.getManagerNo() + "," + " ");
			System.out.print(amem.getManagerName() + "," + " ");
			System.out.print(amem.getManagerAccount() + "," + " ");
			System.out.print(amem.getManagerStatus() + "," + " ");
			System.out.print(amem.getAuthorityName() + "," + " ");
			System.out.println();
		}
//		ManagerVO manvo = new ManagerVO();
//		manvo.setAuthorityName("test1");
//		manvo.setManagerIp("0000");
//		manvo.setManagerAccount("parker");
//		manvo.setManagerPassword("peter");
//		dao.insert(manvo);
	}
}
