package com.app.budgetmanagementapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.budgetmanagementapp.MainActivity;
import com.app.budgetmanagementapp.R;
import com.app.budgetmanagementapp.adapter.ExpenseAdapter;
import com.app.budgetmanagementapp.model.ExpenseModel;
import com.app.budgetmanagementapp.viewmodel.ExpenseViewModel;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    private ExpenseViewModel expenseViewModel;
    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button buttonIncome = findViewById(R.id.buttonAddIncome);
        Button buttonExpense = findViewById(R.id.buttonAddExpense);
        Button buttonExchange = findViewById(R.id.buttonExchange);
        ImageButton buttonLogout = findViewById(R.id.logOut);
        TextView totalAmountTextView = findViewById(R.id.textViewAmount);
        TextView totalIncomeTextView = findViewById(R.id.amountTextView);
        TextView totalExpenseTextView = findViewById(R.id.expenseTextView );

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        buttonIncome.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AddIncome.class);
            startActivity(intent);
        });

        buttonExpense.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AddExpense.class);
            startActivity(intent);
        });

        buttonExchange.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ExchangeActivity.class);
            startActivity(intent);
        });

        buttonLogout.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        recyclerView= findViewById(R.id.recyclerView);
        adapter = new ExpenseAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        expenseViewModel.getExpenses();
        expenseViewModel.getTotalAmountLiveData();
        expenseViewModel.getExpensesLiveData().observe(this, expenseModels -> adapter.setDataList(expenseModels));
        expenseViewModel.getTotalAmountLiveData().observe(this, totalAmount -> {
            String formattedAmount = String.format(Locale.getDefault(), "%.2f TL", totalAmount);
            totalAmountTextView.setText(formattedAmount);
        });

        expenseViewModel.getIncomeTotalLiveData().observe(this, incomeTotal -> {
            String formattedAmount = String.format(Locale.getDefault(), "%.2f TL", incomeTotal);
            totalIncomeTextView.setText(formattedAmount);
        });

        expenseViewModel.getExpenseTotalLiveData().observe(this, expenseTotal -> {
            String formattedAmount = String.format(Locale.getDefault(), "%.2f TL", expenseTotal);
            totalExpenseTextView.setText(formattedAmount);
        });







    }

}