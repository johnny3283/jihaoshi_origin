package com.employee.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;

@WebServlet("/manager/ManagerServlet")
public class EmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("getOne_For_Display".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();

            req.setAttribute("errorMsgs", errorMsgs);

            //*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String str = req.getParameter("managerNo");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入管理員編號");
            }

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/manager/listAllManager.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            Integer managerNo = null;
            try {
                managerNo = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("管理員編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/manager/listAllManager.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            //*************************** 2.開始查詢資料 *****************************************/
            EmployeeService empSvc = new EmployeeService();
            EmployeeVO empVO = empSvc.getOneMember(managerNo);
            if (empVO == null) {
                errorMsgs.add("查無資料");

            }

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/manager/listAllManager.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }
            List<Integer> authorityNo = empSvc.getAuthority(empVO.getManagerNo());
            empVO.setAuthorityNo(authorityNo);
            //*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("EmployeeVO", empVO);
            String url = "/manager/listOneManager.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("getOne_For_Update".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();

            req.setAttribute("errorMsgs", errorMsgs);

            //*************************** 1.接收請求參數 ****************************************/
            Integer managerNo = Integer.valueOf(req.getParameter("managerNo"));
            System.out.println("接收成功");
            //*************************** 2.開始查詢資料 ****************************************/
            EmployeeService empSvc = new EmployeeService();
            EmployeeVO empVO = empSvc.getOneMember(managerNo);
            //*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            req.setAttribute("empVO", empVO);
            String url = "/manager/update_mgr_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("update".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();

            req.setAttribute("errorMsgs", errorMsgs);

            //*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            Integer managerno = Integer.valueOf(req.getParameter("managerNo").trim());
            String manageracc = req.getParameter("managerAccount");
            String managerpwd = req.getParameter("managerPassword");
            String managername = req.getParameter("managerName");
            Integer managerstate = Integer.valueOf(req.getParameter("managerStatus").trim());

            if (manageracc == null || manageracc.trim().length() == 0) {
                errorMsgs.add("員工帳號: 請勿空白");
            }
            if (managerpwd == null || managerpwd.trim().length() == 0) {
                errorMsgs.add("員工密碼: 請勿空白");
            }
            if (managername == null || managername.trim().length() == 0) {
                errorMsgs.add("員工姓名: 請勿空白");
            }
            if (managerstate == null) {
                errorMsgs.add("員工狀態請勿空白");
            } else if (managerstate >= 2) {
                errorMsgs.add("員工狀態只能是0或1");
            }

            EmployeeVO empVO = new EmployeeVO();
            empVO.setManagerNo(managerno);
            empVO.setManagerAccount(manageracc);
            empVO.setManagerPassword(managerpwd);
            empVO.setManagerName(managername);
            empVO.setManagerStatus(managerstate);

            if (!errorMsgs.isEmpty()) {
                req.setAttribute("mgrVO", empVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/manager/update_mgr_input.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            //*************************** 2.開始修改資料 *****************************************/
            EmployeeService empSvc = new EmployeeService();
            empVO = empSvc.updateMember(managerno, manageracc, managerpwd, managername, managerstate);

            //*************************** 3.修改完成,準備轉交(Send the Success view) *************/
            req.setAttribute("empVO", empVO);
            String url = "/manager/listAllManager.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("insert".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();

            req.setAttribute("errorMsgs", errorMsgs);

            //*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

            String manageracc = req.getParameter("managerAccount");
            String managerpas = req.getParameter("managerPassword");
            String managername = req.getParameter("managerName");

            if (manageracc == null || manageracc.trim().length() == 0) {
                errorMsgs.add("會員帳號請勿空白");
            }
            if (managerpas == null || managerpas.trim().length() == 0) {
                errorMsgs.add("會員密碼請勿空白");
            }
            if (managername == null || managername.trim().length() == 0) {
                errorMsgs.add("會員姓名: 請勿空白");
            }

            EmployeeVO empVO = new EmployeeVO();
            empVO.setManagerAccount(manageracc);
            empVO.setManagerPassword(managerpas);
            empVO.setManagerName(managername);


            if (!errorMsgs.isEmpty()) {
                req.setAttribute("employeeVO", empVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/manager/addManager.jsp");
                failureView.forward(req, res);
                return;
            }

            EmployeeService memSvc = new EmployeeService();
            empVO = memSvc.addMember(manageracc, managerpas, managername);


            req.setAttribute("employeeVO", empVO);
            String url = "/manager/listAllManager.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);

        }
        if ("Login".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            final HttpSession session = req.getSession();

            String manageracc = req.getParameter("managerAccount");
            String managerpas = req.getParameter("managerPassword");

            if (manageracc == null || manageracc.trim().length() == 0) {
                errorMsgs.add("請輸入員工帳號");
            }
            if (managerpas == null || managerpas.trim().length() == 0) {
                errorMsgs.add("請輸入員工密碼");
            }

            EmployeeVO empVO = new EmployeeVO();
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("ManagerVO", empVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/manager/login.jsp");
                failureView.forward(req, res);
                return;
            }

            EmployeeService mgrSvc = new EmployeeService();
            empVO = mgrSvc.Login(manageracc, managerpas);
            if (empVO == null) {
                errorMsgs.add("帳號或密碼錯誤");
            }
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("employeeVO", empVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/manager/login.jsp");
                failureView.forward(req, res);
                return;
            }
            if (empVO.getManagerStatus() != 1) {
                errorMsgs.add("帳號鎖定請洽系統管理員");
            }
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("memberVO", empVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/manager/login.jsp");
                failureView.forward(req, res);
                return;
            }
            List<Integer> authorityNo = mgrSvc.getAuthority(empVO.getManagerNo());
            empVO.setAuthorityNo(authorityNo);
            session.setAttribute("manager", empVO);
            res.sendRedirect(req.getContextPath() + "/index.jsp");

        }

        // 登出
        if ("Logout".equals(action)) {
            final HttpSession session = req.getSession();
            session.removeAttribute("manager");
            res.sendRedirect(req.getContextPath() + "/manager/login.jsp");
        }
    }
}
