package com.order.model;

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

import com.cart.model.CartProdVO;
import com.orderDetail.model.OrderDetailDAO;
import com.orderDetail.model.OrderDetailDAOImpl;

public class OrderDAOImpl implements OrderDAO {
    public static DataSource ds = null;

    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jihaoshi");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    String LISTALL = "SELECT * FROM MEAL_ORDER WHERE MEMBER_NO=? ORDER BY ORDER_TIME DESC";
    String INSERT = "INSERT INTO MEAL_ORDER(ORDER_NO, MEMBER_NO,ORDER_PRICE,TRADE_NO) " +
            "VALUES (?,?,?,?)";
    @Override
    public void insert(OrderVO order, List<CartProdVO> cartProds) {

        Connection conn = null;
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, order.getOrderNo());
            ps.setInt(2, order.getMemberNo());
            ps.setInt(3, order.getPrice());
            ps.setString(4, order.getTradeNo());
            ps.executeUpdate();
            OrderDetailDAO dao = new OrderDetailDAOImpl();
            for (CartProdVO prod : cartProds) {
                dao.insert(order.getOrderNo(), prod, conn);
            }
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public List<OrderVO> listOrsers(Integer memberNo) {

        Connection conn = null;
        try {
            conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(LISTALL);
            ps.setInt(1,memberNo);
            ResultSet rs = ps.executeQuery();
            List<OrderVO> orders=new ArrayList<>();
            while (rs.next()) {
                OrderVO order=new OrderVO();
                order.setOrderNo(rs.getString(1));
                order.setOrderTime(rs.getTimestamp(3));
                order.setPrice(rs.getInt(4));
                order.setStatus(rs.getInt(5));
                orders.add(order);
            }
            conn.close();
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
