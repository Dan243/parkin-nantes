package com.example.dmonunu.parkinnantes.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService {

    private static final int TIMEOUT_DELAY = 30;

    private static final String URL = "https://data.nantesmetropole.fr/";

    protected static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null ){
            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .readTimeout(TIMEOUT_DELAY, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT_DELAY, TimeUnit.SECONDS).build();

            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
