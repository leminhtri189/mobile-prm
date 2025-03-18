package com.datj.mobile.data.remote.api;

import com.datj.mobile.data.remote.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OrderApiService {
    @GET("api/Orders") // Giả định endpoint, thay bằng endpoint thực tế
    Call<List<Order>> getOrders();
}
