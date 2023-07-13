package com.app.budgetmanagementapp.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.budgetmanagementapp.model.ExchangeRate;
import com.app.budgetmanagementapp.model.GoldPrice;
import com.app.budgetmanagementapp.model.Stock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class ExchangeRateViewModel extends ViewModel {

    private MutableLiveData<List<ExchangeRate>> exchangeRateLiveData;
    private MutableLiveData<List<GoldPrice>> goldPriceLiveData;
    private MutableLiveData<List<Stock>> stockLiveData;

    public LiveData<List<ExchangeRate>> getExchangeRateLiveData(Context context) {
        if (exchangeRateLiveData == null) {
            exchangeRateLiveData = new MutableLiveData<>();
            loadExchangeRates(context);
        }
        return exchangeRateLiveData;
    }

    public LiveData<List<GoldPrice>> getGoldPriceLiveData(Context context) {
        if (goldPriceLiveData == null) {
            goldPriceLiveData = new MutableLiveData<>();
            loadGoldPrices(context);
        }
        return goldPriceLiveData;
    }

    public LiveData<List<Stock>> getStockLiveData(Context context) {
        if (stockLiveData == null) {
            stockLiveData = new MutableLiveData<>();
            loadStocks(context);
        }
        return stockLiveData;
    }

    private void loadExchangeRates(Context context) {
        String jsonString = loadJSONFromAsset(context, "investment.json");

        try {
            JSONObject jsonObject = new JSONObject(jsonString);


            JSONArray exchangeRatesArray = jsonObject.getJSONArray("exchangeRates");


            List<ExchangeRate> exchangeRates = new ArrayList<>();

            for (int i = 0; i < exchangeRatesArray.length(); i++) {
                JSONObject exchangeRateObject = exchangeRatesArray.getJSONObject(i);

                String currencyPair = exchangeRateObject.getString("currencyPair");
                double rate = exchangeRateObject.getDouble("rate");
                String timestamp = exchangeRateObject.getString("timestamp");

                ExchangeRate exchangeRate = new ExchangeRate(currencyPair, rate, timestamp);
                exchangeRates.add(exchangeRate);
            }

            exchangeRateLiveData.setValue(exchangeRates);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadGoldPrices(Context context) {
        String jsonString = loadJSONFromAsset(context, "investment.json");

        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            JSONArray goldPricesArray = jsonObject.getJSONArray("goldPrices");

            List<GoldPrice> goldPrices = new ArrayList<>();

            for (int i = 0; i < goldPricesArray.length(); i++) {
                JSONObject goldPriceObject = goldPricesArray.getJSONObject(i);

                String type = goldPriceObject.getString("type");
                double price = goldPriceObject.getDouble("price");
                String timestamp = goldPriceObject.getString("timestamp");

                GoldPrice goldPrice = new GoldPrice(type, price, timestamp);
                goldPrices.add(goldPrice);
            }

            goldPriceLiveData.setValue(goldPrices);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadStocks(Context context) {
        // JSON dosyasını oku
        String jsonString = loadJSONFromAsset(context, "investment.json");

        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            JSONArray stocksArray = jsonObject.getJSONArray("stocks");

            List<Stock> stocks = new ArrayList<>();

            for (int i = 0; i < stocksArray.length(); i++) {
                JSONObject stockObject = stocksArray.getJSONObject(i);

                String symbol = stockObject.getString("symbol");
                String name = stockObject.getString("name");
                double price = stockObject.getDouble("price");
                String change = stockObject.getString("change");
                String percentChange = stockObject.getString("percentChange");
                String timestamp = stockObject.getString("timestamp");

                Stock stock = new Stock(symbol, name, price, change, percentChange, timestamp);
                stocks.add(stock);
            }

            stockLiveData.setValue(stocks);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private String loadJSONFromAsset(Context context, String fileName) {
        String json;
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}