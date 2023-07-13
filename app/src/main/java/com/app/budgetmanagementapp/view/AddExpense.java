package com.app.budgetmanagementapp.view;
import com.app.budgetmanagementapp.R;
import com.app.budgetmanagementapp.helpers.JsonHelper;
import com.app.budgetmanagementapp.model.ExpenseModel;
import com.app.budgetmanagementapp.viewmodel.ExpenseViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddExpense extends AppCompatActivity {
    private ExpenseViewModel expenseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
        Button addExpenseButton = findViewById(R.id.buttonAddExpense);
        Spinner categorySpinner = findViewById(R.id.categorySpinner);
        EditText amountEditText = findViewById(R.id.editTextAmount);
        EditText descriptionEditText = findViewById(R.id.editTextDescription);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        List<String> categories = JsonHelper.getCategoriesFromJson(this, "categories.json");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        addExpenseButton.setOnClickListener(v -> {
            String category = categorySpinner.getSelectedItem().toString();
            String amount = amountEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            assert user != null;
            expenseViewModel.addExpense(new ExpenseModel(description,
                    amount,category,today.toString(),"Expense",user.getUid()
            ));
            Toast.makeText(getApplicationContext(), "Veri başarıyla eklendi", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddExpense.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}