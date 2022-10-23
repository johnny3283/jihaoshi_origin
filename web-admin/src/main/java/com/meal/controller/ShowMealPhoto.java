package com.meal.controller;
import static com.common.DBConstants.PASSWORD;
import static com.common.DBConstants.URL;
import static com.common.DBConstants.USER;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/ShowMealPhoto")
public class ShowMealPhoto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("image/*");
        ServletOutputStream out = res.getOutputStream();

        String GET_POHOTO_BY_MEALNO="select meal_photo from meal_product where meal_no= ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps= conn.prepareStatement(GET_POHOTO_BY_MEALNO);) {
            ps.setInt(1,Integer.valueOf(req.getParameter("mealNo")));
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("meal_photo"));
                byte[] buf = new byte[4 * 1024];
                int len;
                while ((len = in.read(buf)) != -1) {
                    out.write(buf,0,len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
