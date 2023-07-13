package com.app.budgetmanagementapp.model;

import android.text.TextUtils;
import android.util.Patterns;

public class UserModel {

    String username;
    String password;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserModel() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username =username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(getUsername()) && Patterns.EMAIL_ADDRESS.matcher(getUsername()).matches() && getPassword().length() > 5;
    }


}