package com.app.budgetmanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.budgetmanagementapp.view.HomeActivity;
import com.app.budgetmanagementapp.view.SignUpActivity;
import com.app.budgetmanagementapp.viewmodel.AuthViewModel;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textViewSign= findViewById(R.id.textViewSignUp);
        textViewSign.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        Button loginButton = findViewById(R.id.loginButton);
        EditText emailEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        authViewModel.getUserLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                     }
                else{
                    Toast.makeText(MainActivity.this, "Lütfen eposta ve şifrenizi doğru girinz.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (email.length() > 0 && password.length() > 0) {
                    authViewModel.login(email, password);
                } else {
                    Toast.makeText(MainActivity.this, "Lütfen eposta ve şifrenizi girinz.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}