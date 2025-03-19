package com.datj.mobile.data.remote.api;

import com.datj.mobile.data.remote.response.ProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductApiService {
    @GET("api/Accessories/{accessoryId}")
    Call<ProductResponse> getProductById(@Path("accessoryId") int accessoryId);
}