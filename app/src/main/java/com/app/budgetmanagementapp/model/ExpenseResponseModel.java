package com.app.budgetmanagementapp.model;

import java.util.Map;

public class ExpenseResponseModel {
    private Map<String, ExpenseModel> expenses;

    public Map<String, ExpenseModel> getExpenses() {
        return expenses;
    }

    public void setExpenses(Map<String, ExpenseModel> expenses) {
        this.expenses = expenses;
    }



}