package com.employee.model;

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

public class EmployeeDAO implements EmployeeDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jihaoshi");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO backend_manager (manager_name,manager_account,manager_password) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * from backend_manager ";
	private static final String GET_ONE_STMT = "SELECT manager_no, manager_name, manager_account, manager_password, manager_status FROM backend_manager where manager_no = ?";
	private static final String DELETE = "DELETE FROM backend_manager where manager_no = ?";
	private static final String UPDATE = "UPDATE backend_manager set manager_name=?,manager_account=?,manager_password=?, manager_status=? where manager_no = ?";
	private static final String Login = "SELECT * FROM backend_manager where manager_account = ? and manager_password = ?";
	private static final String GET_AUTHORITY = "SELECT b.manager_no,e.authority_no\r\n"
			+ "	FROM authority_detail a \r\n"
			+ "	join authority e join backend_manager b \r\n"
			+ "	on a.authority_no = e.authority_no \r\n"
			+ "	and b.manager_no = a.manager_no\r\n"
			+ "	where b.manager_no = ?  ";

	@Override
	public void insert(EmployeeVO EmployeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, EmployeeVO.getManagerAccount());
			pstmt.setString(2, EmployeeVO.getManagerPassword());
			pstmt.setString(3, EmployeeVO.getManagerName());

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
	public void update(EmployeeVO EmployeeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, EmployeeVO.getManagerName());
			pstmt.setString(2, EmployeeVO.getManagerAccount());
			pstmt.setString(3, EmployeeVO.getManagerPassword());
			pstmt.setInt(4, EmployeeVO.getManagerStatus());
			pstmt.setInt(5, EmployeeVO.getManagerNo());
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
	public EmployeeVO findByPrimaryKey(Integer managerNo) {
		EmployeeVO EmployeeVO = null;
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
				EmployeeVO = new EmployeeVO();
				EmployeeVO.setManagerName(rs.getString("manager_name"));
				EmployeeVO.setManagerAccount(rs.getString("manager_account"));
				EmployeeVO.setManagerPassword(rs.getString("manager_password"));
				EmployeeVO.setManagerStatus(rs.getInt("manager_status"));;
				EmployeeVO.setManagerNo(rs.getInt("manager_no"));

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
		return EmployeeVO;
	}

	@Override
	public EmployeeVO selectForLogin(String managerAccount, String managerPassword) {
		EmployeeVO EmployeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(Login);

			pstmt.setString(1, managerAccount);
			pstmt.setString(2, managerPassword);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				EmployeeVO = new EmployeeVO();
				EmployeeVO.setManagerAccount(rs.getString("manager_account"));
				EmployeeVO.setManagerName(rs.getString("manager_name"));
				EmployeeVO.setManagerNo(rs.getInt("manager_no"));
				EmployeeVO.setManagerStatus(rs.getInt("manager_status"));
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
		return EmployeeVO;
	}

	@Override
	public List<Integer> GetAuthority(Integer managerNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_AUTHORITY);

			pstmt.setInt(1, managerNo);

			rs = pstmt.executeQuery();
			List<Integer> authorityNo=new ArrayList();
			while (rs.next()) {

				authorityNo.add(rs.getInt("authority_no"));
			}
			return authorityNo;


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
		return null;
	}
	@Override
	public List<EmployeeVO> getAll() {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			// manager_no, manager_name, authority_name, manager_account, manager_status

			while (rs.next()) {

				empVO = new EmployeeVO();
				empVO.setManagerNo(rs.getInt("manager_no"));
				empVO.setManagerName(rs.getString("manager_name"));
				empVO.setManagerAccount(rs.getString("manager_account"));
				empVO.setManagerStatus(rs.getInt("manager_status"));

				list.add(empVO);
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
