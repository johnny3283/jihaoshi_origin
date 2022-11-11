package com.manager.model;

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

public class ManagerDAO implements ManagerDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jihaoshi");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
	public void delete(Integer managerNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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
		ManagerVO manVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			//manager_no, manager_name, authority_name, manager_account, manager_status
			
			while (rs.next()) {
				
				manVO = new ManagerVO();
				manVO.setManagerNo(rs.getInt("manager_no"));
				manVO.setManagerName(rs.getString("manager_name"));
				manVO.setManagerAccount(rs.getString("manager_account"));
//				managerVO.setManagerPassword(rs.getString("manager_password"));
				manVO.setManagerStatus(rs.getInt("manager_status"));
				manVO.setAuthorityName(rs.getString("authority_name"));
				
				list.add(manVO); 
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
	
}
