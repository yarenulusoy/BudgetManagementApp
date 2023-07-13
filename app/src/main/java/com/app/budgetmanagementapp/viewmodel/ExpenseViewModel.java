package com.app.budgetmanagementapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.budgetmanagementapp.model.ExpenseModel;
import com.app.budgetmanagementapp.network.ApiClient;
import com.app.budgetmanagementapp.network.ExpenseApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpenseViewModel extends ViewModel {
    private final MutableLiveData<List<ExpenseModel>> expensesLiveData;
    private final MutableLiveData<Double> totalAmountLiveData;
    private final MutableLiveData<Double> incomeTotalLiveData;
    private final MutableLiveData<Double> expenseTotalLiveData;
    private final ExpenseApiService expenseApiService;

    public ExpenseViewModel() {
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
        Call<Map<String, ExpenseModel>> call = expenseApiService.getExpenses();
        call.enqueue(new Callback<Map<String,ExpenseModel>>() {
            @Override
            public void onResponse(@NonNull Call<Map<String, ExpenseModel>> call, @NonNull Response<Map<String, ExpenseModel>> response) {
                if (response.isSuccessful()) {
                    Map<String, ExpenseModel> dataMap = response.body();
                    if (dataMap != null) {
                        List<ExpenseModel> expenseList = new ArrayList<>(dataMap.values());
                        expensesLiveData.setValue(expenseList);
                        calculateTotalAmount(expenseList);
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

    private void calculateTotalAmount(List<ExpenseModel> expenseList) {
        double totalAmount = 0;
        double incomeTotal = 0;
        double expenseTotal = 0;

        for (ExpenseModel expense : expenseList) {
            double amount = Double.parseDouble(expense.getAmount());
            if ("Income".equals(expense.getMoneyType())) {
                incomeTotal += amount;
                totalAmount += amount;
            } else {
                expenseTotal += amount;
                totalAmount -= amount;
            }

        }

        totalAmountLiveData.setValue(totalAmount);
        incomeTotalLiveData.setValue(incomeTotal);
        expenseTotalLiveData.setValue(expenseTotal);
    }
}
