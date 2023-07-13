package com.app.budgetmanagementapp.network;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://budget-management-app-98efe-default-rtdb.firebaseio.com/"; //base url

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS) // Bağlantı zaman aşımı süresi
                    .readTimeout(10, TimeUnit.SECONDS)    // Okuma zaman aşımı süresi
                    .writeTimeout(10, TimeUnit.SECONDS)   // Yazma zaman aşımı süresi
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

}
