package com.app.budgetmanagementapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.budgetmanagementapp.helpers.ExchangeService;
import com.app.budgetmanagementapp.model.ExchangeRate;
import com.app.budgetmanagementapp.model.GoldPrice;
import com.app.budgetmanagementapp.model.Stock;

import java.util.List;

public class ExchangeRateViewModel extends ViewModel {
    private final MutableLiveData<List<ExchangeRate>> exchangeRatesLiveData;
    private final MutableLiveData<List<GoldPrice>> goldPricesLiveData;
    private final MutableLiveData<List<Stock>> stocksLiveData;
    private final ExchangeService mockService;

    public ExchangeRateViewModel() {
        exchangeRatesLiveData = new MutableLiveData<>();
        goldPricesLiveData = new MutableLiveData<>();
        stocksLiveData = new MutableLiveData<>();
        mockService = new ExchangeService() {
        };
    }

    public LiveData<List<ExchangeRate>> getExchangeRates() {
        return exchangeRatesLiveData;
    }

    public LiveData<List<GoldPrice>> getGoldPrices() {
        return goldPricesLiveData;
    }

    public LiveData<List<Stock>> getStocks() {
        return stocksLiveData;
    }




}
