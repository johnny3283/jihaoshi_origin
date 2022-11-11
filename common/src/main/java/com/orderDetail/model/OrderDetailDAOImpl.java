package com.orderDetail.model;

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
import com.meal.model.MealDAO;
import com.meal.model.MealDAOImpl;

public class OrderDetailDAOImpl implements OrderDetailDAO{
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
    public int insert(String orderNo, CartProdVO prod, Connection conn) {
        String INSERT =
                "INSERT INTO MEAL_ORDER_DETAIL (ORDER_NO, MEAL_NO, QUANTITY, AMOUNT, PRICE) " +
                "values (?,?,?,?,?);";

        try {
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, orderNo);
            ps.setInt(2,prod.getMeal().getMealNo());
            ps.setDouble(3,prod.getQuantity());
            ps.setInt(4,prod.getAmount());
            ps.setInt(5,prod.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
    @Override
    public List<OrderDetailVO> listOrderDetails(String orderNo) {
        Connection conn = null;
        String LISTALL = "SELECT * FROM MEAL_ORDER_DETAIL WHERE ORDER_NO=? ORDER BY MEAL_NO ASC";

        try {
            conn=ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(LISTALL);
            ps.setString(1,orderNo);
            ResultSet rs = ps.executeQuery();
            MealDAO dao=new MealDAOImpl();
            List<OrderDetailVO> orderDetails = new ArrayList<>();
            while (rs.next()) {
                OrderDetailVO orderDetail = new OrderDetailVO();
                orderDetail.setMeal(dao.findByMealNo(rs.getInt(2),conn));
                orderDetail.setQuantity(rs.getDouble(3));
                orderDetail.setAmount(rs.getInt(4));
                orderDetail.setPrice(rs.getInt(5));
                orderDetails.add(orderDetail);
            }
            rs.close();
            ps.close();
            conn.close();
            return orderDetails;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
