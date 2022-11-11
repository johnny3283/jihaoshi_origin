package com.cart.model;

import java.io.Serializable;

import com.meal.model.MealVO;

public class CartProdVO implements Serializable {
    private Integer memberNo;
    private MealVO meal;
    private Double quantity;
    private Integer amount;
    private Integer price;
    private Integer cal;
    public Integer getCal() {
        return cal;
    }

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
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
