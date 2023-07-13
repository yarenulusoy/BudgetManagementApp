package com.app.budgetmanagementapp.model;


public class Stock {
    private String symbol;
    private String name;
    private double price;
    private String change;
    private String percentChange;
    private String timestamp;

    public Stock(String symbol, String name, double price, String change, String percentChange, String timestamp) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.change = change;
        this.percentChange = percentChange;
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getChange() {
        return change;
    }

    public String getPercentChange() {
        return percentChange;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
