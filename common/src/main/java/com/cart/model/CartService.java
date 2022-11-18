package com.cart.model;

import java.util.ArrayList;
import java.util.List;

import com.meal.model.MealDAO;
import com.meal.model.MealDAOImpl;
import com.meal.model.MealVO;

public class CartService {
    MealDAO dao=new MealDAOImpl();

    public List<CartProdVO> getCartProds(Double quantity, Integer amount, MealVO meal, List<CartProdVO> cartProds) {
        CartProdVO prod=null;
        if (cartProds == null) {
            cartProds=new ArrayList<>();
            prod = new CartProdVO();
            prod.setMeal(meal);
            prod.setQuantity(quantity);
            prod.setAmount(amount);
            prod.setPrice((int) (meal.getMealPrice() * quantity * amount));
            prod.setCal((int) (meal.getMealCal() * quantity));
            cartProds.add(prod);
            return cartProds;
        } else {
            for (int i = 0; i < cartProds.size(); i++) { // 尋找是否有同編號同份量商品
                prod = cartProds.get(i);
                if (prod.getMeal().getMealNo().equals(meal.getMealNo()) && prod.getQuantity().equals(quantity)) {
                    prod.setAmount(prod.getAmount() + amount); // 找到就改變數量跟價格
                    prod.setPrice((int) (meal.getMealPrice() * quantity*prod.getAmount()));
                    prod.setCal((int) (meal.getMealCal() * quantity));
                    return cartProds;
                }
            }
            // 查無同編號同份量商品時
            prod = new CartProdVO();
            prod.setMeal(meal);
            prod.setQuantity(quantity);
            prod.setAmount(amount);
            prod.setPrice((int) (meal.getMealPrice() * quantity * amount));
            prod.setCal((int) (meal.getMealCal() * quantity));
            cartProds.add(prod);
            return cartProds;
        }

    }

    public Integer calculateTotalPrice(List<CartProdVO> cartProds) {
        Integer totalPrice = 0;
        for (CartProdVO CartProd : cartProds) {
            totalPrice += CartProd.getPrice();
        }
        return totalPrice;
    }

}
