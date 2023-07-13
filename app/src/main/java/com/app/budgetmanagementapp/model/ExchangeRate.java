package com.app.budgetmanagementapp.model;

public class ExchangeRate {
    private final String currencyPair;
    private final double rate;
    private final String timestamp;

    public ExchangeRate(String currencyPair, double rate, String timestamp) {
        this.currencyPair = currencyPair;
        this.rate = rate;
        this.timestamp = timestamp;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public double getRate() {
        return rate;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
