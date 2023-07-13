package com.app.budgetmanagementapp.model;

import java.util.Date;

public class ExpenseModel {
    private int id;
    private String description;
    private String amount;
    private String category;
    private String moneyType;
    private String userId;
    private String date;


    public ExpenseModel() {
    }

    public ExpenseModel(String description, String amount, String category, String date, String moneyType, String userId) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.moneyType = moneyType;
        this.userId = userId;
    }

    // Getter ve Setter metodları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String value) {
        this.moneyType = value;
    }

    public String getUserID() {
        return userId;
    }

    public void setUserID(String value) {
        this.userId = value;
    }


}
