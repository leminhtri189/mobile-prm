package com.datj.mobile.data.remote;

import com.datj.mobile.data.remote.api.AccountApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    public class RetrofitClient {
        private static final String BASE_URL = "http://10.0.2.2:8080/";  // Thay bằng địa chỉ server của bạn
        private static Retrofit retrofit = null;

        private static Retrofit getRetrofitInstance() {
            if (retrofit == null) {
                // Tạo HttpLoggingInterceptor để debug log (tùy chọn)
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                // Tạo OkHttpClient và thêm interceptor
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(logging)
                        .build();

                // Khởi tạo Retrofit với client
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();
            }
            return retrofit;
        }
        // 🔄 Sử dụng lại getRetrofitInstance() để tạo AccountApiService
        public static AccountApiService getAccountApiService() {
            return getRetrofitInstance().create(AccountApiService.class);
        }
    }

