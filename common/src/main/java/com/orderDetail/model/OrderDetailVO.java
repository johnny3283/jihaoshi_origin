package com.orderDetail.model;

import com.meal.model.MealVO;

public class OrderDetailVO {
    public MealVO meal;
    private String orderNo;
    private Integer mealNo;
    private Double quantity;
    private Integer amount;
    private Integer price;

    public MealVO getMeal() {
        return meal;
    }

    public void setMeal(MealVO meal) {
        this.meal = meal;
    }
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getMealNo() {
        return mealNo;
    }

    public void setMealNo(Integer mealNo) {
        this.mealNo = mealNo;
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
