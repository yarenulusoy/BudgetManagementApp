package com.app.budgetmanagementapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.budgetmanagementapp.R;
import com.app.budgetmanagementapp.viewmodel.AuthViewModel;

public class SignUpActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button registerButton = findViewById(R.id.registerButton);
        EditText emailEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (email.length() > 0 && password.length() > 0) {
                    authViewModel.register(email, password);
                } else {
                    Toast.makeText(SignUpActivity.this, "Lütfen eposta ve şifrenizi girinz.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}