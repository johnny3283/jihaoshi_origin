package com.checkout.controller;

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

import com.cart.model.CartCourseHolder;
import com.cart.model.CartCourseMapHolder;
import com.cart.model.CartCourseService;
import com.cart.model.CartCourseVO;
import com.mem.model.MemHolder;
import com.mem.model.MemRedisHolder;
import com.mem.model.MemberVO;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@WebServlet("/checkout/checkoutCourseController")
public class CheckoutCourseController extends HttpServlet {
	
	private final CartCourseHolder cartCourseHolder;

    private final MemHolder memHolder;
    CartCourseService cartCourseSV=new CartCourseService();

    public CheckoutCourseController() {

        this.cartCourseHolder=new CartCourseMapHolder();
        this.memHolder=new MemRedisHolder();
	}
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<CartCourseVO> cartCourses = (ArrayList<CartCourseVO>) session.getAttribute("cartCourses");
        String action = req.getParameter("action");
        MemberVO member =(MemberVO) session.getAttribute("member");
        
        if ("checkout".equals(action)) {
            Integer totalPrice = cartCourseSV.calculateTotalPrice(cartCourses);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String tradeDate = sdf.format(new Date(System.currentTimeMillis()));
            
            AllInOne allInOne = new AllInOne("");
            AioCheckOutALL aioCheckOutALL = new AioCheckOutALL();
            
            StringBuilder itemName=new StringBuilder("");
            for (CartCourseVO course : cartCourses) {
                itemName.append("課程名稱："+course.getCourse().getCourseName()+"#");
            }
            if (itemName.length()>=200) {
                itemName = new StringBuilder("Jihaoshi課程一批");
            }
            String tradeDesc="checkout00"+member.getMemberNo();

            String ranAlphabet = RandomStringUtils.randomAlphabetic(2).toUpperCase();
            int ranNum = (int) (Math.random() * 8999+ 1000);
            String merchantTradeNo=ranAlphabet+tradeDate.replace("/", "").replace(":", "").replace(" ", "")+ranNum;
            
            cartCourseHolder.put(merchantTradeNo, cartCourses);
            memHolder.put(tradeDesc,member);
            
//            System.out.println("cartCourseHolder" + cartCourseHolder);
//            System.out.println("memHolder"+ memHolder);
            
            aioCheckOutALL.setMerchantTradeNo(merchantTradeNo);
            aioCheckOutALL.setMerchantTradeDate(tradeDate);
            aioCheckOutALL.setTotalAmount(String.valueOf(totalPrice));
            aioCheckOutALL.setTradeDesc("付款測試");
            aioCheckOutALL.setItemName(String.valueOf(itemName));
            aioCheckOutALL.setCustomField1(tradeDesc);
            aioCheckOutALL.setReturnURL(req.getRequestURL()+"?action=serverCallBack");
            aioCheckOutALL.setOrderResultURL("http://localhost:8081/web/recive/reciveCourseController?action=ecpay");
            aioCheckOutALL.setClientBackURL("http://localhost:8081/web");
            aioCheckOutALL.setNeedExtraPaidInfo("N");
           
            String checkoutPage=allInOne.aioCheckOut(aioCheckOutALL,null);
            req.setAttribute("checkoutCoursePage",checkoutPage);
            
            RequestDispatcher goCheckout = req
                    .getRequestDispatcher("/checkout/CheckoutCoursePage.jsp");
            goCheckout.forward(req, res);
        }
        if ("serverCallBack".equals(action)) {
            res.getWriter().println("1|OK");
            return;
        }
        if ("callBack".equals(action)) {

            Integer rtnCode = Integer.valueOf(req.getParameter("RtnCode")); // rtnCode==1 交易成功

            if (rtnCode.equals(1)) {
                RequestDispatcher orderInsert =req.getRequestDispatcher("/order/orderCourseController?action=orderInsert");
                orderInsert.forward(req,res);
            }else {
                res.sendRedirect("");
            }
        }
    }
}
