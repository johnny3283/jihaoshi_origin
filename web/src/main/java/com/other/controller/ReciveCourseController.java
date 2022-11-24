package com.other.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemHolder;
import com.mem.model.MemRedisHolder;

@WebServlet("/recive/reciveCourseController")
public class ReciveCourseController extends HttpServlet {
    private final MemHolder memHolder;

    public ReciveCourseController() {
        this.memHolder = new MemRedisHolder();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String action = req.getParameter("action");
        if ("ecpay".equals(action)) {
            String tradeDesc = req.getParameter("CustomField1");

            session.setAttribute("member", memHolder.get(tradeDesc));
            memHolder.remove(tradeDesc);
            RequestDispatcher forwardCheckout = req.getRequestDispatcher("/checkout/checkoutCourseController?action=callBack");
            forwardCheckout.forward(req, res);

        }
    }
}
