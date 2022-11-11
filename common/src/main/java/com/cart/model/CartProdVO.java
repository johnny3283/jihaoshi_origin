package com.cart.model;

import java.io.Serializable;

import com.meal.model.MealVO;

public class CartProdVO implements Serializable {
    private MealVO meal;
    private Double quantity;
    private Integer amount;
    private Integer price;
    private Integer cal;
    private static Integer totalPrice;
    public Integer getCal() {
        return cal;
    }

    public static Integer getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(Integer totalPrice) {
        CartProdVO.totalPrice = totalPrice;
    }

    public void setCal(Integer cal) {
        this.cal = cal;
    }

    public MealVO getMeal() {
        return meal;
    }

    public void setMeal(MealVO meal) {
        this.meal = meal;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
