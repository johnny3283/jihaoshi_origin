package com.online_course.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.onlinecoursecomment.model.OnlineCourseCommentVO;


public class OnlineCourseJDBCDAO implements OnlineCourseDAO_interface {

    private static final String GET_OnlineCourseComments_ByOnlineCourseNo_STMT =
            "SELECT * FROM Online_course_comment where course_no = ? order by comment_no";

    private static DataSource ds = null;

    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jihaoshi");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(OnlineCourseVO onlineCourseVO) {
        String sql = "INSERT INTO ONLINE_COURSE(COURSE_NAME ,COURSE_HR ,COURSE_TEACHER ,COURSE_INFO , COURSE_PRICE ,COURSE_STATUS,COURSE_PHOTO,COURSE_VIDEO) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            pstmt.setString(1, onlineCourseVO.getCourseName());
            pstmt.setString(2, onlineCourseVO.getCourseHr());
            pstmt.setString(3, onlineCourseVO.getCourseTeacher());
            pstmt.setString(4, onlineCourseVO.getCourseInfo());
            pstmt.setInt(5, onlineCourseVO.getCoursePrice());
            pstmt.setInt(6, onlineCourseVO.getCourseStatus());
            pstmt.setBytes(7, onlineCourseVO.getOnlineCoursePhoto());
            pstmt.setString(8, onlineCourseVO.getCourseVideo());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    onlineCourseVO.setCourseNo(rs.getInt(1));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(OnlineCourseVO onlineCourseVO) {
        String sql = "UPDATE ONLINE_COURSE SET COURSE_NAME = ?, COURSE_HR = ?, COURSE_TEACHER = ? ,COURSE_INFO = ?,COURSE_PRICE = ?,COURSE_STATUS = ?,COMMENT_PEOPLE = ?, COMMENT_SCORE = ? , COURSE_PHOTO = ? , COURSE_VIDEO = ? WHERE COURSE_NO = ?";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, onlineCourseVO.getCourseName());
            pstmt.setString(2, onlineCourseVO.getCourseHr());
            pstmt.setString(3, onlineCourseVO.getCourseTeacher());
            pstmt.setString(4, onlineCourseVO.getCourseInfo());
            pstmt.setInt(5, onlineCourseVO.getCoursePrice());
            pstmt.setInt(6, onlineCourseVO.getCourseStatus());
            pstmt.setInt(7, onlineCourseVO.getCommentPeople());
            pstmt.setInt(8, onlineCourseVO.getCommentScore());
            pstmt.setBytes(9, onlineCourseVO.getOnlineCoursePhoto());
            pstmt.setString(10, onlineCourseVO.getCourseVideo());
            pstmt.setInt(11, onlineCourseVO.getCourseNo());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(OnlineCourseVO onlineCourseVO) {
        String sql = "delete from Online_course where course_no = ?";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, onlineCourseVO.getCourseNo());
            int rowCount = pstmt.executeUpdate();
            System.out.println(rowCount + " row(s) deleted!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public OnlineCourseVO findByPrimaryKey(Integer courseNo) {
        String sql = "select * from Online_course where course_no = ?";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, courseNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    OnlineCourseVO vo = new OnlineCourseVO();
                    vo.setCourseNo(rs.getInt("course_no"));
                    vo.setCourseName(rs.getString("course_name"));
                    vo.setCourseHr(rs.getString("course_hr"));
                    vo.setCourseTeacher(rs.getString("course_teacher"));
                    vo.setCourseInfo(rs.getString("course_info"));
                    vo.setCoursePrice(rs.getInt("course_price"));
                    vo.setCourseStatus(rs.getInt("course_status"));
                    vo.setUpdateDate(rs.getTimestamp("update_date"));
                    vo.setCommentPeople(rs.getInt("comment_people"));
                    vo.setCommentScore(rs.getInt("comment_score"));
                    vo.setOnlineCoursePhoto(rs.getBytes("course_photo"));
                    vo.setCourseVideo(rs.getString("course_video"));
                    return vo;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OnlineCourseVO> getAll() {
        String sql = "select * from Online_course";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            List<OnlineCourseVO> list = new ArrayList<>();
            while (rs.next()) {
                OnlineCourseVO vo = new OnlineCourseVO();
                vo.setCourseNo(rs.getInt("course_no"));
                vo.setCourseName(rs.getString("course_name"));
                vo.setCourseHr(rs.getString("course_hr"));
                vo.setCourseTeacher(rs.getString("course_teacher"));
                vo.setCourseInfo(rs.getString("course_info"));
                vo.setCoursePrice(rs.getInt("course_price"));
                vo.setCourseStatus(rs.getInt("course_status"));
                vo.setUpdateDate(rs.getTimestamp("update_date"));
                vo.setCommentPeople(rs.getInt("comment_people"));
                vo.setCommentScore(rs.getInt("comment_score"));
                vo.setCommentScore(rs.getInt("comment_score"));
                vo.setOnlineCoursePhoto(rs.getBytes("course_photo"));
                list.add(vo);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OnlineCourseVO> selectByCourseName(String courseName) {
        String sql = "select * from Online_course where course_name like ? or course_no=?";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, "%" + courseName + "%");
            pstmt.setString(2, courseName);
            try (ResultSet rs = pstmt.executeQuery()) {
                List<OnlineCourseVO> list = new ArrayList<>();
                while (rs.next()) {
                    OnlineCourseVO vo = new OnlineCourseVO();
                    vo.setCourseNo(rs.getInt("course_no"));
                    vo.setCourseName(rs.getString("course_name"));
                    vo.setCourseHr(rs.getString("course_hr"));
                    vo.setCourseTeacher(rs.getString("course_teacher"));
                    vo.setCourseInfo(rs.getString("course_info"));
                    vo.setCoursePrice(rs.getInt("course_price"));
                    vo.setCourseStatus(rs.getInt("course_status"));
                    vo.setUpdateDate(rs.getTimestamp("update_date"));
                    vo.setCommentPeople(rs.getInt("comment_people"));
                    vo.setCommentScore(rs.getInt("comment_score"));
                    vo.setOnlineCoursePhoto(rs.getBytes("course_photo"));
                    list.add(vo);
                }
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean courseSwitch(Integer courseNo, Integer courseStatus) {
        String sql = "update Online_course set course_status = ? where course_no = ? ";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, courseStatus);
            pstmt.setInt(2, courseNo);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public List<OnlineCourseVO> getStatusOnAll() {
        String sql = "select * from Online_course where course_status = 0";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            List<OnlineCourseVO> list = new ArrayList<>();
            while (rs.next()) {
                OnlineCourseVO vo = new OnlineCourseVO();
                vo.setCourseNo(rs.getInt("course_no"));
                vo.setCourseName(rs.getString("course_name"));
                vo.setCourseHr(rs.getString("course_hr"));
                vo.setCourseTeacher(rs.getString("course_teacher"));
                vo.setCourseInfo(rs.getString("course_info"));
                vo.setCoursePrice(rs.getInt("course_price"));
                vo.setCourseStatus(rs.getInt("course_status"));
                vo.setUpdateDate(rs.getTimestamp("update_date"));
                vo.setCommentPeople(rs.getInt("comment_people"));
                vo.setCommentScore(rs.getInt("comment_score"));
                vo.setCommentScore(rs.getInt("comment_score"));
                vo.setOnlineCoursePhoto(rs.getBytes("course_photo"));
                list.add(vo);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OnlineCourseCommentVO> getOnlineCourseCommentsByOnlineCourseNo(Integer courseNo) {
        List<OnlineCourseCommentVO> list = new ArrayList<>();
        OnlineCourseCommentVO onlineCourseCommentVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_OnlineCourseComments_ByOnlineCourseNo_STMT);
            pstmt.setInt(1, courseNo);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                onlineCourseCommentVO = new OnlineCourseCommentVO();
                onlineCourseCommentVO.setCommentNo(rs.getInt(1));
                onlineCourseCommentVO.setMemberNo(rs.getInt(2));
                onlineCourseCommentVO.setCourseNo(rs.getInt(3));
                onlineCourseCommentVO.setCommentContent(rs.getString(4));
                onlineCourseCommentVO.setCommentScore(rs.getInt(5));
                onlineCourseCommentVO.setCommentStatus(rs.getInt(6));
                list.add(onlineCourseCommentVO); // Store the row in the vector
            }
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }

            }
        }
        return list;
    }
    
    @Override
    public List<OnlineCourseVO> selectByMemId(Integer memId) {
        String sql = "select c.* "
        		+ "from"
        		+ "	ONLINE_COURSE c"
        		+ "    join ONLINE_COURSE_ORDER_DETAIL od"
        		+ "		on c.COURSE_NO = od.COURSE_NO "
        		+ "	join ONLINE_COURSE_ORDER o"
        		+ "		on od.ORDER_NO = o.ORDER_NO "
        		+ "where o.MEMBER_NO = ?";
        try (
        	Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, memId);
            try (ResultSet rs = pstmt.executeQuery()) {
                List<OnlineCourseVO> list = new ArrayList<>();
                while (rs.next()) {
                    OnlineCourseVO vo = new OnlineCourseVO();
                    vo.setCourseNo(rs.getInt("course_no"));
                    vo.setCourseName(rs.getString("course_name"));
                    vo.setCourseHr(rs.getString("course_hr"));
                    vo.setCourseTeacher(rs.getString("course_teacher"));
                    vo.setCourseInfo(rs.getString("course_info"));
                    vo.setCoursePrice(rs.getInt("course_price"));
                    vo.setCourseStatus(rs.getInt("course_status"));
                    vo.setUpdateDate(rs.getTimestamp("update_date"));
                    vo.setCommentPeople(rs.getInt("comment_people"));
                    vo.setCommentScore(rs.getInt("comment_score"));
                    vo.setOnlineCoursePhoto(rs.getBytes("course_photo"));
                    list.add(vo);
                }
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<OnlineCourseVO> getFree() {
        String sql = "select * "
        		+ "from"
        		+ "	ONLINE_COURSE "
        		+ "where COURSE_PRICE = 0";
        try (
        	Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            try (ResultSet rs = pstmt.executeQuery()) {
                List<OnlineCourseVO> list = new ArrayList<>();
                while (rs.next()) {
                    OnlineCourseVO vo = new OnlineCourseVO();
                    vo.setCourseNo(rs.getInt("course_no"));
                    System.out.print(vo.getCourseNo());
                    vo.setCourseName(rs.getString("course_name"));
                    vo.setCourseHr(rs.getString("course_hr"));
                    vo.setCourseTeacher(rs.getString("course_teacher"));
                    vo.setCourseInfo(rs.getString("course_info"));
                    vo.setCoursePrice(rs.getInt("course_price"));
                    vo.setCourseStatus(rs.getInt("course_status"));
                    vo.setUpdateDate(rs.getTimestamp("update_date"));
                    vo.setCommentPeople(rs.getInt("comment_people"));
                    vo.setCommentScore(rs.getInt("comment_score"));
                    vo.setOnlineCoursePhoto(rs.getBytes("course_photo"));
                    list.add(vo);
                }
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<OnlineCourseVO> selectByCourseNameAndMemId(String courseName, Integer memId) {
        String sql = "select c.* "
        		+ "from"
        		+ "	ONLINE_COURSE c"
        		+ "    join ONLINE_COURSE_ORDER_DETAIL od"
        		+ "		on c.COURSE_NO = od.COURSE_NO "
        		+ "	join ONLINE_COURSE_ORDER o"
        		+ "		on od.ORDER_NO = o.ORDER_NO "
        		+ "where (c.COURSE_NAME like ? or c.COURSE_NO = ?) and o.MEMBER_NO = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, "%" + courseName + "%");
            pstmt.setString(2, courseName);
            pstmt.setInt(3, memId);
            try (ResultSet rs = pstmt.executeQuery()) {
                List<OnlineCourseVO> list = new ArrayList<>();
                while (rs.next()) {
                    OnlineCourseVO vo = new OnlineCourseVO();
                    vo.setCourseNo(rs.getInt("course_no"));
                    vo.setCourseName(rs.getString("course_name"));
                    vo.setCourseHr(rs.getString("course_hr"));
                    vo.setCourseTeacher(rs.getString("course_teacher"));
                    vo.setCourseInfo(rs.getString("course_info"));
                    vo.setCoursePrice(rs.getInt("course_price"));
                    vo.setCourseStatus(rs.getInt("course_status"));
                    vo.setUpdateDate(rs.getTimestamp("update_date"));
                    vo.setCommentPeople(rs.getInt("comment_people"));
                    vo.setCommentScore(rs.getInt("comment_score"));
                    vo.setOnlineCoursePhoto(rs.getBytes("course_photo"));
                    list.add(vo);
                }
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
