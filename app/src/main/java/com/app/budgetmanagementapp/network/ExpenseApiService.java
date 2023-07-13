package com.app.budgetmanagementapp.network;

import com.app.budgetmanagementapp.model.ExpenseModel;
import com.app.budgetmanagementapp.model.ExpenseResponseModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ExpenseApiService {
    @GET("api.json")
    Call<Map<String, ExpenseModel>> getExpenses();

    @POST("api.json")
    Call<ExpenseModel> addExpense(@Body ExpenseModel expense);

    @DELETE("api/{id}.json")
    Call<Void> deleteExpense(@Path("id") int id);

    @PUT("api/{id}.json")
    Call<ExpenseModel> updateExpense(@Path("id") String id, @Body ExpenseModel expense);

}