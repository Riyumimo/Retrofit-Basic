package com.example.retrofitbasic.Retrofit;

import com.example.retrofitbasic.MainModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoint {
    @GET("data.php")
    Call<MainModel> getdata();
}
