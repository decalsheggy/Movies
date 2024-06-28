package com.example.movies;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {//здесь получаем реализацию интерфейса ApiService
    private static final String BASE_URL = "https://api.kinopoisk.dev/v1.4/";

    //получаем ссылку на retrofit:
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();//из реализации получаем экземпляр apiService
    public static final ApiService apiService = retrofit.create(ApiService.class);//(реализация синглтона?)
}
