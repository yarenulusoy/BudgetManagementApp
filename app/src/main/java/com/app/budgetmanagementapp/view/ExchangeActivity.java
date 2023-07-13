package com.app.budgetmanagementapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.app.budgetmanagementapp.R;
import com.app.budgetmanagementapp.model.ExchangeRate;
import com.app.budgetmanagementapp.model.GoldPrice;
import com.app.budgetmanagementapp.model.Stock;
import com.app.budgetmanagementapp.viewmodel.ExchangeRateViewModel;

import java.util.List;

public class ExchangeActivity extends AppCompatActivity {
    private TextView goldPricesTextView;
    private TextView stocksTextView;
    private ExchangeRateViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        TextView exchangeRatesTextView1 = findViewById(R.id.exchangeRatesTextView);
        goldPricesTextView = findViewById(R.id.goldPricesTextView);
        stocksTextView = findViewById(R.id.stocksTextView);

        viewModel = new ViewModelProvider(this).get(ExchangeRateViewModel.class);

        TextView exchangeRatesTextView = findViewById(R.id.exchangeRatesTextView);

        viewModel.getExchangeRateLiveData(getApplicationContext()).observe(this, exchangeRates -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (ExchangeRate exchangeRate : exchangeRates) {
                stringBuilder.append(exchangeRate.getCurrencyPair()).append(" ")
                        .append(exchangeRate.getRate())
                        .append("\n");
            }
            exchangeRatesTextView.setText(stringBuilder.toString());
        });
        viewModel.getGoldPriceLiveData(getApplicationContext()).observe(this, goldPrices -> {

            StringBuilder stringBuilder = new StringBuilder();
            for (GoldPrice goldPrice : goldPrices) {
                stringBuilder.append(goldPrice.getType()).append(" ")
                        .append(goldPrice.getPrice())
                        .append("\n");
            }
            goldPricesTextView.setText(stringBuilder.toString());
        });

        viewModel.getStockLiveData(getApplicationContext()).observe(this, stocks -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (Stock stock : stocks) {
                stringBuilder.append(stock.getSymbol()).append(" ")
                        .append(stock.getName()).append(" ")
                        .append(stock.getPrice()).append(" ")
                        .append(stock.getChange()).append(" ")
                        .append(stock.getPercentChange())
                        .append("\n");
            }
            stocksTextView.setText(stringBuilder.toString());
        });
    }
}