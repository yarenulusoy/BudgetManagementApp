package com.app.budgetmanagementapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.budgetmanagementapp.model.ExpenseModel;
import com.app.budgetmanagementapp.network.ApiClient;
import com.app.budgetmanagementapp.network.ExpenseApiService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionViewModel {
    private final MutableLiveData<List<ExpenseModel>> expensesLiveData;
    private final MutableLiveData<Double> totalAmountLiveData;
    private final MutableLiveData<Double> incomeTotalLiveData;
    private final MutableLiveData<Double> expenseTotalLiveData;
    private final ExpenseApiService expenseApiService;

    public TransactionViewModel() {
        expensesLiveData = new MutableLiveData<>();
        totalAmountLiveData = new MutableLiveData<>();
        incomeTotalLiveData = new MutableLiveData<>();
        expenseTotalLiveData = new MutableLiveData<>();
        expenseApiService = ApiClient.getRetrofitInstance().create(ExpenseApiService.class);
    }

    public LiveData<List<ExpenseModel>> getExpensesLiveData() {
        return expensesLiveData;
    }
    public LiveData<Double> getTotalAmountLiveData() {
        return totalAmountLiveData;
    }
    public LiveData<Double> getIncomeTotalLiveData() {
        return incomeTotalLiveData;
    }

    public LiveData<Double> getExpenseTotalLiveData() {
        return expenseTotalLiveData;
    }

    public void getExpenses() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        String userId= user.getUid();
        Call<Map<String, ExpenseModel>> call = expenseApiService.getExpenses();
        call.enqueue(new Callback<Map<String,ExpenseModel>>() {
            @Override
            public void onResponse(@NonNull Call<Map<String, ExpenseModel>> call, @NonNull Response<Map<String, ExpenseModel>> response) {
                if (response.isSuccessful()) {
                    Map<String, ExpenseModel> dataMap = response.body();
                    if (dataMap != null) {
                        List<ExpenseModel> userExpenses = new ArrayList<>();

                        for (Map.Entry<String, ExpenseModel> entry : dataMap.entrySet()) {
                            String expenseId = entry.getKey();
                            ExpenseModel expense = entry.getValue();
                            if (expense.getUserID().equals(userId)) {
                                userExpenses.add(expense);
                            }
                        }

                        expensesLiveData.setValue(userExpenses);
                    }

                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Map<String, ExpenseModel>> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
