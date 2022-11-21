package com.online_course_order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cart.model.CartCourseVO;
import com.online_course_order_detail.model.OnlineCourseOrderDetailDAO_interface;
import com.online_course_order_detail.model.OnlineCourseOrderDetailJDBCDAO;

public class OnlineCourseOrderJDBCDAO implements OnlineCourseOrderDAO_interface {
	public static DataSource ds = null;

    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jihaoshi");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void insert(OnlineCourseOrderVO onlineCourseOrderVO, List<CartCourseVO> cartCourses) {
		String sql = "INSERT INTO Online_course_order(ORDER_NO, MEMBER_NO,ORDER_PRICE,TRADE_NO) VALUES(?, ?, ?, ?)";
	    Connection conn = null;
		
		try {
			conn = ds.getConnection();
	        conn.setAutoCommit(false);
	        PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, onlineCourseOrderVO.getOrderNo());
			pstmt.setInt(2, onlineCourseOrderVO.getMemberNo());
			pstmt.setInt(3, onlineCourseOrderVO.getOrderPrice());
			pstmt.setString(4, onlineCourseOrderVO.getTradeNo());
			pstmt.executeUpdate();
			
			OnlineCourseOrderDetailDAO_interface dao = new OnlineCourseOrderDetailJDBCDAO();
            for (CartCourseVO prod : cartCourses) {
                dao.insert(onlineCourseOrderVO.getOrderNo(), prod, conn);
            }
            
			conn.commit();
            conn.setAutoCommit(true);
            conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(OnlineCourseOrderVO onlineCourseOrderVO) {
		String sql = "update Online_course_order set order_status = ? where order_no = ?";
		Connection conn = null;
		try{
			conn = ds.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, onlineCourseOrderVO.getOrderStatus());
			pstmt.setString(2, onlineCourseOrderVO.getOrderNo());
			pstmt.executeUpdate();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(OnlineCourseOrderVO onlineCourseOrderVO) {
		String sql = "delete from Online_course_order where order_no = ?";
		Connection conn = null;
		
		try{
			conn = ds.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, onlineCourseOrderVO.getOrderNo());
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public OnlineCourseOrderVO findByPrimaryKey(Integer orderNo) {
		String sql = "select * from Online_course_order where order_no = ?";
		Connection conn = null;
		
		try{
			conn = ds.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					OnlineCourseOrderVO vo = new OnlineCourseOrderVO();
					vo.setOrderNo(rs.getString("order_no"));
					vo.setMemberNo(rs.getInt("member_no"));
					vo.setOrderTime(rs.getTimestamp("order_time"));
					vo.setOrderPrice(rs.getInt("order_price"));
					vo.setOrderStatus(rs.getInt("order_status"));
					vo.setTradeNo(rs.getString("trsde_no"));
					conn.close();
					return vo;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<OnlineCourseOrderVO> findByMemNo(Integer memberNo) {
		String sql = "select * from Online_course_order where member_no = ?";
		Connection conn = null;
		
		try{
			conn = ds.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			List<OnlineCourseOrderVO> list=new ArrayList<OnlineCourseOrderVO>();
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					OnlineCourseOrderVO vo = new OnlineCourseOrderVO();
					vo.setOrderNo(rs.getString("order_no"));
					vo.setMemberNo(rs.getInt("member_no"));
					vo.setOrderTime(rs.getTimestamp("order_time"));
					vo.setOrderPrice(rs.getInt("order_price"));
					vo.setOrderStatus(rs.getInt("order_status"));
					vo.setTradeNo(rs.getString("trade_no"));
					list.add(vo);					
				}
				conn.close();
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<OnlineCourseOrderVO> getAll() {
		String sql = "select * from Online_course_order";
		Connection conn=null;
		
		try {
			conn = ds.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			List<OnlineCourseOrderVO> list = new ArrayList<>();
			while (rs.next()) {
				OnlineCourseOrderVO vo = new OnlineCourseOrderVO();
				vo.setOrderNo(rs.getString("order_no"));
				vo.setMemberNo(rs.getInt("member_no"));
				vo.setOrderTime(rs.getTimestamp("order_time"));
				vo.setOrderPrice(rs.getInt("order_price"));
				vo.setOrderStatus(rs.getInt("order_status"));
				vo.setTradeNo(rs.getString("trade_no"));
				list.add(vo);
			}
			conn.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}