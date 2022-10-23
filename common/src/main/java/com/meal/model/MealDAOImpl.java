package com.meal.model;

import static com.common.DBConstants.PASSWORD;
import static com.common.DBConstants.URL;
import static com.common.DBConstants.USER;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealDAOImpl implements MealDAO {


    private static final String GET_ALL_SQL = "select * from meal_product";
    public static final String INSERT_SQL = "insert into meal_product (meal_name, meal_content, meal_cal, meal_allergen, meal_price, meal_photo, meal_recipe, launch) values (?,?,?,?,?,?,?,?);";
    public static final String UPDATE_SQL = "update meal_product set meal_name=?, meal_content=?, meal_cal=?, meal_allergen=?, meal_price=?, meal_photo=?, meal_recipe=?, launch=? where meal_no=? ;";
    public static final String LAUNCH_SQL = "update meal_product set launch = ? where meal_no = ? ;";
    public static final String FINDBYLAST_SQL="select * from meal_product where meal_no=(select max(meal_no) from meal_product);";
    public static final String FINDBY_MEALNO = "select * from meal_product where meal_no=? ;";
    @Override
    public void insert(MealVO meal) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps= conn.prepareStatement(INSERT_SQL);) {
            ps.setString(1,meal.getMealName());
            ps.setString(2, meal.getMealContent());
            ps.setInt(3, meal.getMealCal());
            ps.setString(4, meal.getMealAllergen());
            ps.setInt(5, meal.getMealPrice());
            ps.setBinaryStream(6, meal.getMealPhoto());
            ps.setString(7,meal.getMealRecipe());
            ps.setInt(8,meal.getLaunch());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MealVO findByLastUpdate() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps= conn.prepareStatement(FINDBYLAST_SQL);) {
            ResultSet rs = ps.executeQuery();
            MealVO meal =null;
            if (rs.next()) {
                meal=new MealVO
                        (rs.getInt(1), rs.getString(2),rs.getString(3),
                                rs.getInt(4),rs.getString(5),rs.getInt(6),
                                rs.getBinaryStream(7),rs.getInt(8),rs.getString(9),
                                rs.getInt(10),rs.getInt(11),rs.getInt(12),
                                rs.getDate(13));
                return meal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MealVO findByMealNo(Integer mealNo) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps= conn.prepareStatement(FINDBY_MEALNO);) {
            ps.setInt(1,mealNo);
            ResultSet rs = ps.executeQuery();
            MealVO meal =null;
            if (rs.next()) {
                meal=new MealVO
                        (rs.getInt(1), rs.getString(2),rs.getString(3),
                                rs.getInt(4),rs.getString(5),rs.getInt(6),
                                rs.getBinaryStream(7),rs.getInt(8),rs.getString(9),
                                rs.getInt(10),rs.getInt(11),rs.getInt(12),
                                rs.getDate(13));
                return meal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(MealVO meal) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps= conn.prepareStatement(UPDATE_SQL);) {
            ps.setString(1,meal.getMealName());
            ps.setString(2, meal.getMealContent());
            ps.setInt(3, meal.getMealCal());
            ps.setString(4, meal.getMealAllergen());
            ps.setInt(5, meal.getMealPrice());
            ps.setBinaryStream(6, meal.getMealPhoto());
            ps.setString(7,meal.getMealRecipe());
            ps.setInt(8,meal.getLaunch());
            ps.setInt(9,meal.getMealNo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer launchOn(Integer mealNo) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

             PreparedStatement ps= conn.prepareStatement(LAUNCH_SQL);) {
            ps.setInt(1, 1);
            ps.setInt(2,mealNo);
            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public Integer launchOff(Integer mealNo){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

             PreparedStatement ps= conn.prepareStatement(LAUNCH_SQL);) {
            ps.setInt(1, 0);
            ps.setInt(2,mealNo);
            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
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
        List<MealVO> meal = new ArrayList<>();

        return null;
    }
}
