package com.online_course_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OnlineCourseOrderJDBCDAO implements OnlineCourseOrderDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Jihaoshi?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	@Override
	public void insert(OnlineCourseOrderVO onlineCourseOrderVO) {
		String sql = "INSERT INTO Online_course_order(course_no,course_name,member_no,order_price,order_status) VALUES(?, ?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, onlineCourseOrderVO.getCourseNo());
			pstmt.setString(2, onlineCourseOrderVO.getCourseName());
			pstmt.setInt(3, onlineCourseOrderVO.getMemberNo());
			pstmt.setInt(4, onlineCourseOrderVO.getOrderPrice());
			pstmt.setInt(5, onlineCourseOrderVO.getOrderStatus());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(OnlineCourseOrderVO onlineCourseOrderVO) {
		String sql = "update Online_course_order set course_no = ?, course_name = ? , order_price = ? , order_status = ? where order_no = ?";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd);

				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, onlineCourseOrderVO.getCourseNo());
			pstmt.setString(2, onlineCourseOrderVO.getCourseName());
			pstmt.setInt(3, onlineCourseOrderVO.getOrderPrice());
			pstmt.setInt(4, onlineCourseOrderVO.getOrderStatus());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(OnlineCourseOrderVO onlineCourseOrderVO) {
		String sql = "delete from Online_course_order where order_no = ?";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd);

				PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setInt(1, onlineCourseOrderVO.getOrderNo());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public OnlineCourseOrderVO findByPrimaryKey(Integer orderNo) {
		String sql = "select * from Online_course_order where order_no = ?";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, orderNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					OnlineCourseOrderVO vo = new OnlineCourseOrderVO();
					vo.setOrderNo(rs.getInt("order_no"));
					vo.setCourseNo(rs.getInt("course_no"));
					vo.setCourseName(rs.getString("course_name"));
					vo.setMemberNo(rs.getInt("member_no"));
					vo.setOrderTime(rs.getTimestamp("order_time"));
					vo.setOrderPrice(rs.getInt("order_price"));
					vo.setOrderStatus(rs.getInt("order_status"));
					return vo;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public OnlineCourseOrderVO findByMemNo(Integer memberNo) {
		String sql = "select * from Online_course_order where order_no = ?";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, memberNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					OnlineCourseOrderVO vo = new OnlineCourseOrderVO();
					vo.setOrderNo(rs.getInt("order_no"));
					vo.setCourseNo(rs.getInt("course_no"));
					vo.setCourseName(rs.getString("course_name"));
					vo.setMemberNo(rs.getInt("member_no"));
					vo.setOrderTime(rs.getTimestamp("order_time"));
					vo.setOrderPrice(rs.getInt("order_price"));
					vo.setOrderStatus(rs.getInt("order_status"));
					return vo;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public List<OnlineCourseOrderVO> getAll() {
		String sql = "select * from Online_course_order";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			List<OnlineCourseOrderVO> list = new ArrayList<>();
			while (rs.next()) {
				OnlineCourseOrderVO vo = new OnlineCourseOrderVO();
				vo.setOrderNo(rs.getInt("order_no"));
				vo.setCourseNo(rs.getInt("course_no"));
				vo.setCourseName(rs.getString("course_name"));
				vo.setMemberNo(rs.getInt("member_no"));
				vo.setOrderTime(rs.getTimestamp("order_time"));
				vo.setOrderPrice(rs.getInt("order_price"));
				vo.setOrderStatus(rs.getInt("order_status"));
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}