package com.app.budgetmanagementapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.budgetmanagementapp.MainActivity;
import com.app.budgetmanagementapp.R;
import com.app.budgetmanagementapp.helpers.CryptoHelper;
import com.app.budgetmanagementapp.viewmodel.AuthViewModel;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView textViewSign = findViewById(R.id.textViewSignUp);
        textViewSign.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        Button loginButton = findViewById(R.id.loginButton);
        EditText emailEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        CheckBox checkBox = findViewById(R.id.checkBox);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        authViewModel.getUserLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                if (!checkBox.isChecked()) {
                    CryptoHelper.saveCredentials(LoginActivity.this, emailEditText.getText().toString(), passwordEditText.getText().toString());
                }
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "Lütfen eposta ve şifrenizi doğru girinz.", Toast.LENGTH_SHORT).show();

            }
        });
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (email.length() > 0 && password.length() > 0) {
                authViewModel.login(email, password);
            } else {
                Toast.makeText(LoginActivity.this, "Lütfen eposta ve şifrenizi girinz.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}