package com.datj.mobile.data.repository;

import android.util.Log;

import com.datj.mobile.data.remote.RetrofitClient;
import com.datj.mobile.data.remote.api.AccountApiService;
import com.datj.mobile.data.remote.model.Login;
import com.datj.mobile.data.remote.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final AccountApiService accountApiService;

    public AuthRepository() {
        accountApiService = RetrofitClient.getAccountApiService();
    }

    public void login(String email, String password, AuthCallback callback) {
        Login loginAccount = new Login(email, password);
        Call<LoginResponse> call = accountApiService.login(loginAccount);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    String token = loginResponse.getToken();
                    callback.onSuccess(token);
                    if (token != null) {
                        if (callback != null) {
                            callback.onSuccess(token);
                        } else {
                            // Log lỗi nếu callback null
                            Log.e("AuthRepository", "Callback is null in onSuccess");
                        }
                    } else {
                        if (callback != null) {
                            callback.onError("Token is empty or invalid");
                        } else {
                            Log.e("AuthRepository", "Callback is null in onError");
                        }
                    }
                } else {
                    String errorMessage = "Email not found or password incorrect";
                    if (response.errorBody() != null) {
                        try {
                            errorMessage = response.errorBody().string();
                        } catch (Exception e) {
                            errorMessage = "Login failed!";
                        }
                    }
                    if (callback != null) {
                        callback.onError(errorMessage);
                    } else {
                        Log.e("AuthRepository", "Callback is null in onError");
                    }
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onError("Connection error: " + t.getMessage());
            }
        });
    }

    public interface AuthCallback {
        void onSuccess(String token);
        void onError(String errorMessage);
    }
}