package com.app.budgetmanagementapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.budgetmanagementapp.model.ExpenseModel;
import com.app.budgetmanagementapp.network.ApiClient;
import com.app.budgetmanagementapp.network.ExpenseApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpenseViewModel extends ViewModel {
    private MutableLiveData<List<ExpenseModel>> expensesLiveData;
    private ExpenseApiService expenseApiService;

    public ExpenseViewModel() {
        expensesLiveData = new MutableLiveData<>();
        expenseApiService = ApiClient.getRetrofitInstance().create(ExpenseApiService.class);
    }

    public LiveData<List<ExpenseModel>> getExpensesLiveData() {
        return expensesLiveData;
    }

    public void getExpenses() {
        Call<List<ExpenseModel>> call = expenseApiService.getExpenses();
        call.enqueue(new Callback<List<ExpenseModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<ExpenseModel>> call, @NonNull Response<List<ExpenseModel>> response) {
                if (response.isSuccessful()) {
                    expensesLiveData.setValue(response.body());
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ExpenseModel>> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void addExpense(ExpenseModel expense) {
        Call<ExpenseModel> call = expenseApiService.addExpense(expense);
        call.enqueue(new Callback<ExpenseModel>() {
            @Override
            public void onResponse(@NonNull Call<ExpenseModel> call, @NonNull Response<ExpenseModel> response) {
                if (response.isSuccessful()) {
                    getExpenses(); // Masrafları güncellemek için getExpenses() metodunu yeniden çağırabilirsiniz.
                }
            }

            @Override
            public void onFailure(@NonNull Call<ExpenseModel> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void deleteExpense(int id) {
        Call<Void> call = expenseApiService.deleteExpense(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    getExpenses(); // Masrafları güncellemek için getExpenses() metodunu yeniden çağırabilirsiniz.
                }

            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
