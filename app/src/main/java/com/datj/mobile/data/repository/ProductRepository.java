package com.datj.mobile.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.datj.mobile.data.remote.api.ProductApiService;
import com.datj.mobile.data.remote.model.Product;
import com.datj.mobile.data.remote.response.ProductResponse;
import com.datj.mobile.data.network.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private ProductApiService apiService;

    public ProductRepository() {
        apiService = ApiClient.getClient().create(ProductApiService.class);
    }

    public LiveData<Product> getProductById(int id) {
        MutableLiveData<Product> data = new MutableLiveData<>();
        apiService.getProductById(id).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body().getProduct());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
