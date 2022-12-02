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
        Map<Integer,String> courseStatus=Map.of(0,"上架",1,"下架");
        servletContext.setAttribute("courseStatus", courseStatus);
        Map<String,String> actions=Map.of
                ("listAll","${param.get(\"action\")}","nameKeywordSearch","${param.get(\"action\")}${param.get(\"action\")}${(empty param.get(\"nameKeyword\"))?\"\":\"&nameKeyword=\"}${param.get(\"nameKeyword\")}"
                );
        servletContext.setAttribute("actions", actions);
    }
}
