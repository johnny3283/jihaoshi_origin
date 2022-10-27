package com.meal.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CodeMapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        Map<Integer, String> launchStatus = Map.of(0, "未上架", 1, "已上架");
        servletContext.setAttribute("launchStatus", launchStatus);
    }
}
