package com.course.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/sign/DBGifReader")
public class DBGifReader extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String course_no = req.getParameter("course_no").trim();
			System.out.println("==================================");
			System.out.println("course_no: " + course_no);
			ResultSet rs = stmt.executeQuery(
				"SELECT PIC FROM PHYSICAL_COURSE WHERE COURSE_NO ="+course_no);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("pic"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
//				InputStream in = getServletContext().getResourceAsStream("/images/none1.png");
//				byte[] b = in.readAllBytes();
//				out.write(b);
//				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void init() throws ServletException {			
		
	    try {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jihaoshi?serverTimezone=Asia/Taipei", "root", "password");
//	    	Context ctx = new javax.naming.InitialContext();
//	    	DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jihaoshi");	 
//	    	con = ds.getConnection();
//	    	
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//	    }
		 catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}