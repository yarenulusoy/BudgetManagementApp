package com.app.budgetmanagementapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.app.budgetmanagementapp.repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoggedViewModel extends AndroidViewModel {
    private AuthRepository authAppRepository;
    private MutableLiveData<FirebaseUser> userLiveData;
    private MutableLiveData<Boolean> loggedOutLiveData;

    public LoggedViewModel(@NonNull Application application) {
        super(application);

        authAppRepository = new AuthRepository(application);
        userLiveData = authAppRepository.getUserLiveData();
        loggedOutLiveData = authAppRepository.getLoggedOutLiveData();
    }

    public void logOut() {
        authAppRepository.logOut();
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutLiveData() {
        return loggedOutLiveData;
    }
}