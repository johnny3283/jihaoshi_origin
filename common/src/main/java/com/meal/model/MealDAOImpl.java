package com.meal.model;

import static com.common.DBConstants.PASSWORD;
import static com.common.DBConstants.URL;
import static com.common.DBConstants.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MealDAOImpl implements MealDAO {
//    private static DataSource ds = null;
//    static {
//        try {
//            Context ctx = new InitialContext();
//            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jihaoshi");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//    }

    private static final String GET_ALL_SQL = "select * from meal_product";
    public static final String INSERT_SQL = "insert into meal_product (meal_name, meal_content, meal_cal, meal_allergen, meal_price, meal_photo, meal_recipe) values (?,?,?,?,?,?,?);";
    @Override
    public void insert(MealVO meal) {
//        Connection conn=null;
//        PreparedStatement ps=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

             PreparedStatement ps= conn.prepareStatement(INSERT_SQL);) {
//            conn= ds.getConnection();

            ps.setString(1,meal.getMealName());
            ps.setString(2, meal.getMealContent());
            ps.setInt(3, meal.getMealCal());
            ps.setString(4, meal.getMealAllergen());
            ps.setInt(5, meal.getMealPrice());
            ps.setBinaryStream(6, meal.getMealPhoto());
            ps.setString(7,meal.getMealRecipe());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer update(MealVO meal) {
        int rowCount=0;

        return null;
    }

    @Override
    public Integer launch(Boolean launch) {
        return null;
    }

    @Override
    public MealVO findBymealName(String mealName) {
        return null;
    }

    @Override
    public MealVO findWithoutAllergen(String mealAllergen) {
        return null;
    }

    @Override
    public List<MealVO> getAll() {
        return null;
    }
}
