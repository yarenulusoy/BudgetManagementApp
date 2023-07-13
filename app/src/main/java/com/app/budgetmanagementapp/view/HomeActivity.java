package com.app.budgetmanagementapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.app.budgetmanagementapp.R;
import com.app.budgetmanagementapp.adapter.ExpenseAdapter;
import com.app.budgetmanagementapp.model.ExpenseModel;
import com.app.budgetmanagementapp.viewmodel.ExpenseViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
        TextView totalAmountTextView = findViewById(R.id.textViewAmount);
        TextView totalIncomeTextView = findViewById(R.id.amountTextView);
        TextView totalExpenseTextView = findViewById(R.id.expenseTextView   );
        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        buttonIncome.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AddIncome.class);
            startActivity(intent);
        });

        buttonExpense.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AddExpense.class);
            startActivity(intent);
        });
        recyclerView= findViewById(R.id.recyclerView);
        adapter = new ExpenseAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        expenseViewModel.getExpenses();
        expenseViewModel.getTotalAmountLiveData();
        expenseViewModel.getExpensesLiveData().observe(this, new Observer<List<ExpenseModel>>() {
            @Override
            public void onChanged(List<ExpenseModel> expenseModels) {
                adapter.setDataList(expenseModels);

            }
        });
        expenseViewModel.getTotalAmountLiveData().observe(this, totalAmount -> {
            String formattedAmount = String.format(Locale.getDefault(), "%.2f", totalAmount);
            totalAmountTextView.setText(formattedAmount);
        });

        expenseViewModel.getIncomeTotalLiveData().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double incomeTotal) {
                String formattedAmount = String.format(Locale.getDefault(), "%.2f", incomeTotal);
                totalIncomeTextView.setText(formattedAmount);
            }
        });

        expenseViewModel.getExpenseTotalLiveData().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double expenseTotal) {
                String formattedAmount = String.format(Locale.getDefault(), "%.2f", expenseTotal);
                totalExpenseTextView.setText(formattedAmount);
            }
        });







    }

}