package com.example.retrofitbasic.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static String BASE_URL = "http://demo.lazday.com/rest-api-sample/";
    private static Retrofit retrofit;
    public static ApiEndPoint endPoint(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiEndPoint.class);
    }
}
