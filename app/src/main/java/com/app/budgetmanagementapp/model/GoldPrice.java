package com.app.budgetmanagementapp.model;


public class GoldPrice {
    private String type;
    private double price;
    private String timestamp;

    public GoldPrice(String type, double price, String timestamp) {
        this.type = type;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
