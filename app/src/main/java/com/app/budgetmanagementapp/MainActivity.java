package com.app.budgetmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.app.budgetmanagementapp.helpers.CryptoHelper;
import com.app.budgetmanagementapp.view.HomeActivity;
import com.app.budgetmanagementapp.view.LoginActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (CryptoHelper.isLoggedIn(this)) {
            navigateToHomePage();
        } else {
            navigateToLogin();
        }


    }
    private void navigateToHomePage() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}