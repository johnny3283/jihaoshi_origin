package com.order.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class OrderStatusCodeMapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        Map<Integer, String> OrderStatus = Map.of(0, "訂單已完成", 1, "申請退款", 2, "退款完成");
        servletContext.setAttribute("OrderStatus",OrderStatus);

    }
}
