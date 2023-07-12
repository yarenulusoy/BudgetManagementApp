package com.app.budgetmanagementapp.network;

import com.app.budgetmanagementapp.model.ExpenseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ExpenseApiService {
    @GET("expenses")
    Call<List<ExpenseModel>> getExpenses();

    @POST("expenses")
    Call<ExpenseModel> addExpense(@Body ExpenseModel expense);

    @DELETE("expenses/{id}")
    Call<Void> deleteExpense(@Path("id") int id);
}