package com.onlinecoursecommentreport.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ReportStatusListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        Map<Integer, String> reportStatus = Map.of(0, "未處理", 1, "未通過", 2, "通過");
        servletContext.setAttribute("reportStatus", reportStatus);
    }
}
