package com.online_course_order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OnlineCourseOrderDetailJDBCDAO implements OnlineCourseOrderDetailDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Jihaoshi?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	@Override
	public void insert(OnlineCourseOrderDetailVO onlineCourseOrderDetailVO) {
		String sql = "INSERT INTO Online_course_order_detail (order_no ,course_no ,course_price ,order_photo) VALUES(?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd);

				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, onlineCourseOrderDetailVO.getOrderNo());
			pstmt.setInt(2, onlineCourseOrderDetailVO.getCourseNo());
			pstmt.setInt(3, onlineCourseOrderDetailVO.getCoursePrice());
			pstmt.setBytes(4, onlineCourseOrderDetailVO.getOrderPhoto());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(OnlineCourseOrderDetailVO onlineCourseOrderDetailVO) {
		String sql = "update Online_course_order_detail set course_price = ? where order_no = ? and course_no = ?";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd);

				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, onlineCourseOrderDetailVO.getCoursePrice());
			pstmt.setInt(2, onlineCourseOrderDetailVO.getOrderNo());
			pstmt.setInt(3, onlineCourseOrderDetailVO.getCourseNo());
			pstmt.setBytes(4, onlineCourseOrderDetailVO.getOrderPhoto());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(OnlineCourseOrderDetailVO onlineCourseOrderDetailVO) {
		String sql = "delete from Online_course_order_detail where order_no = ? and course_no = ?";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd);

				PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setInt(1, onlineCourseOrderDetailVO.getOrderNo());
			pstmt.setInt(2, onlineCourseOrderDetailVO.getCourseNo());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public OnlineCourseOrderDetailVO findByPrimaryKey(Integer orderNo, Integer courseNo) {
		String sql = "select * from Online_course_order_detail where order_no = ? and course_no = ?";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd);

				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, courseNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					OnlineCourseOrderDetailVO vo = new OnlineCourseOrderDetailVO();
					vo.setOrderNo(rs.getInt("order_no"));
					vo.setCourseNo(rs.getInt("course_no"));
					vo.setCoursePrice(rs.getInt("course_price"));
					vo.setOrderPhoto(rs.getBytes("order_photo"));

					return vo;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OnlineCourseOrderDetailVO> getAll() {
		String sql = "select * from Online_course_order_detail";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd);

				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			List<OnlineCourseOrderDetailVO> list = new ArrayList<>();
			while (rs.next()) {
				OnlineCourseOrderDetailVO vo = new OnlineCourseOrderDetailVO();
				vo.setOrderNo(rs.getInt("order_no"));
				vo.setCourseNo(rs.getInt("course_no"));
				vo.setCoursePrice(rs.getInt("course_price"));
				vo.setOrderPhoto(rs.getBytes("order_photo"));

				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}