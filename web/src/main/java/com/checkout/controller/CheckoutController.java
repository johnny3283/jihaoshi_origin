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

import com.cart.model.CartHolder;
import com.cart.model.CartProdVO;
import com.cart.model.CartRedisHolder;
import com.cart.model.CartService;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@WebServlet("/checkout/checkoutController")
public class CheckoutController extends HttpServlet {

    private final CartHolder cartHolder;

    CartService cartSV = new CartService();
    private final SimpleDateFormat sdf;

    private final String ECPAY_PROD_FORMAT = "品名：%s 份量：%s 數量：%s #";

    // DI style
//    public CheckoutController(CartHolder cartHolder) {
//        this.cartHolder = cartHolder;
//    }
    public CheckoutController() {

        this.cartHolder = new CartRedisHolder();
        sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<CartProdVO> cartProds = (ArrayList<CartProdVO>) session.getAttribute("cartProds");
        String action = req.getParameter("action");
        if ("checkout".equals(action)) {

            
            AllInOne allInOne = new AllInOne("");

            AioCheckOutALL aioCheckOutALL = getAioCheckOutALL(req.getRequestURL().toString(), cartProds);

            String checkoutPage = allInOne.aioCheckOut(aioCheckOutALL, null);

            cartHolder.put(aioCheckOutALL.getMerchantTradeNo(), cartProds);

            req.setAttribute("checkoutPage", checkoutPage);

            RequestDispatcher goCheckout = req.getRequestDispatcher("/checkout/CheckoutPage.jsp");
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
                RequestDispatcher orderInsert = req.getRequestDispatcher("/order/orderController?action=orderInsert");
                orderInsert.forward(req, res);
            } else {
                res.sendRedirect("/checkout/CheckoutFail.jsp");
            }
        }
    }

    private AioCheckOutALL getAioCheckOutALL(String requestURL, List<CartProdVO> cartProds) {
        AioCheckOutALL aioCheckOutALL = new AioCheckOutALL();

        String tradeDate = sdf.format(new Date(System.currentTimeMillis()));


        aioCheckOutALL.setMerchantTradeNo(getTradeNo(tradeDate));
        aioCheckOutALL.setMerchantTradeDate(tradeDate);
        aioCheckOutALL.setTotalAmount(String.valueOf(cartSV.calculateTotalPrice(cartProds)));
        aioCheckOutALL.setTradeDesc("付款測試");
        aioCheckOutALL.setItemName(String.valueOf(getECContent(cartProds)));
        aioCheckOutALL.setReturnURL(requestURL + "?action=serverCallBack");
        aioCheckOutALL.setOrderResultURL(requestURL + "?action=callBack");
        aioCheckOutALL.setClientBackURL("http://localhost:8081/web");
        aioCheckOutALL.setNeedExtraPaidInfo("N");
        return aioCheckOutALL;

    }

    private StringBuilder getECContent(List<CartProdVO> cartProds) {
        StringBuilder itemName = new StringBuilder();

        for (CartProdVO prod : cartProds) {

            itemName.append(String.format(ECPAY_PROD_FORMAT, prod.getMeal().getMealName(), prod.getQuantity(), prod.getAmount()));

        }
        if (itemName.length() >= 200) {
            itemName = new StringBuilder("Jihaoshi商品一批");
        }
        return itemName;
    }

    private String getTradeNo(String tradeDate) {

        String ranAlphabet = RandomStringUtils.randomAlphabetic(2).toUpperCase();
        int ranNum = (int) (Math.random() * 8999 + 1000);
        return ranAlphabet + tradeDate.replace("/", "").replace(":", "").replace(" ", "") + ranNum;

    }
}
