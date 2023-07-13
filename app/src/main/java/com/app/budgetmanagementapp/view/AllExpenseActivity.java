package com.app.budgetmanagementapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.app.budgetmanagementapp.R;
import com.app.budgetmanagementapp.adapter.ExpenseAdapter;
import com.app.budgetmanagementapp.viewmodel.ExpenseViewModel;

public class AllExpenseActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private ExpenseViewModel expenseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expense);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ExpenseAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
        expenseViewModel.getExpenses();
        expenseViewModel.getExpensesLiveData().observe(this, expenseModels -> adapter.setDataList(expenseModels));
    }
}