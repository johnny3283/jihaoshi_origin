package com.pCouCheckout.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import com.cart.model.CartProdVO;
import com.course.model.PhyCouService;
import com.course.model.PhyCouVO;
import com.mem.model.MemberVO;
import com.signupcourse.model.PhyCouSignUpService;
import com.signupcourse.model.PhyCouSignUpVO;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@WebServlet("/pCouCheckout/PCoucheckoutController")
public class PCouCheckoutController extends HttpServlet {
	 PhyCouVO phyCouVO = null;
	 Integer course_no = null;
	 Integer signUpNum = null;
	 Integer member_no = null;
	 Integer totalPrice = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        phyCouVO = (PhyCouVO)session.getAttribute("phyCouVO");
        MemberVO member = (MemberVO) session.getAttribute("member");
        if (member != null ) {
            member_no = (Integer)member.getMemberNo();
        }
        String action = req.getParameter("action");
        
        if ("checkout".equals(action)) {
        	
  		  	
  		  	course_no = phyCouVO.getCourse_no();
  		    signUpNum = phyCouVO.getCurrent_sign_up_people();	
  		    totalPrice = phyCouVO.getCourse_price();
  		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String tradeDate = sdf.format(new Date(System.currentTimeMillis()));
            AllInOne allInOne = new AllInOne("");
            AioCheckOutALL aioCheckOutALL = new AioCheckOutALL();
            String itemName= phyCouVO.getCourse_name();

            String ranAlphabet = RandomStringUtils.randomAlphabetic(2).toUpperCase();
            int ranNum = (int) (Math.random() * 8999+ 1000);
            String merchantTradeNo=ranAlphabet+tradeDate.replace("/", "").replace(":", "").replace(" ", "")+ranNum;
            aioCheckOutALL.setMerchantTradeNo(merchantTradeNo);
            aioCheckOutALL.setMerchantTradeDate(tradeDate);
            aioCheckOutALL.setTotalAmount(String.valueOf(totalPrice));
            aioCheckOutALL.setTradeDesc("付款測試");
            aioCheckOutALL.setReturnURL(req.getRequestURL()+"?action=serverCallBack");
            aioCheckOutALL.setOrderResultURL(req.getRequestURL()+"?action=callBack");
            aioCheckOutALL.setClientBackURL("http://localhost:8081/web");
            aioCheckOutALL.setNeedExtraPaidInfo("N");
            aioCheckOutALL.setItemName(String.valueOf(itemName));
            String checkoutPage=allInOne.aioCheckOut(aioCheckOutALL,null);
            req.setAttribute("checkoutPage",checkoutPage);
            RequestDispatcher goCheckout = req
                    .getRequestDispatcher("/checkout/CheckoutPage.jsp");
            goCheckout.forward(req, res);
        }
        
        if ("serverCallBack".equals(action)) {
            try {
                int rtnCode = Integer.parseInt(req.getParameter("RtnCode")); // rtnCode==1 交易成功
                if (rtnCode == 1) {
                    res.getWriter().println("1|OK");
                    return;
                }
            } catch (NumberFormatException e) {

                System.out.println("綠界有回傳，但非數字");
                e.printStackTrace();

            }
        }
            
        if ("callBack".equals(action)) {

            Integer rtnCode = Integer.valueOf(req.getParameter("RtnCode")); // rtnCode==1 交易成功

            if (rtnCode.equals(1)) {
       	        System.out.println("pass");
       	     /***************************2.開始新增資料****************************************/
//       	       member = (MemberVO) session.getAttribute("member");
               String merchantTradeNo = req.getParameter("MerchantTradeNo"); // 店內之交易編號
               String tradeNo = req.getParameter("TradeNo"); // 綠界之交易編號

               Integer totalPrice = Integer.valueOf(req.getParameter("TradeAmt"));
               
				PhyCouSignUpService phyCouSignUpSvc = new PhyCouSignUpService();
				PhyCouSignUpVO phyCouSignUpVO = phyCouSignUpSvc.signup(member_no, course_no, totalPrice, signUpNum);
			    
				/***************************3.新增完成,準備轉交(Send the Success view)************/
				req.setAttribute("phyCouSignUpVO", phyCouSignUpVO);      
				String url = "/course/confirmOk.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
            } else {
                res.sendRedirect("");
            }
        }
    }
}
