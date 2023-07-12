package com.app.budgetmanagementapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.app.budgetmanagementapp.repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends AndroidViewModel {
    private AuthRepository authAppRepository;
    private MutableLiveData<FirebaseUser> userLiveData;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        authAppRepository = new AuthRepository(application);
        userLiveData = authAppRepository.getUserLiveData();
    }

    public void login(String email, String password) {
        authAppRepository.login(email, password);
    }

    public void register(String email, String password) {
        authAppRepository.register(email, password);
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }
}
