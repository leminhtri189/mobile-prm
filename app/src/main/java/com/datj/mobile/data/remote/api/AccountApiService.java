package com.datj.mobile.data.remote.api;

import com.datj.mobile.data.remote.model.Login;
import com.datj.mobile.data.remote.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountApiService {
    @POST("api/Authentication/login")
    Call<LoginResponse> login(@Body Login loginAccount);
}
