package com.example.dmonunu.parkinnantes.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
Author : dmonunu
Date : 09/11/2018
 */
public class ParkingService {

    public static ParkingService INSTANCE = new ParkingService();
    private final ParkingRESTService parkingRestService;
    private final static String NANTES_API_URL = "https://data.nantesmetropole.fr/api/";

    public ParkingService(){
        Gson gsonConverter = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls()
                .excludeFieldsWithoutExposeAnnotation().create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(NANTES_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonConverter))
                .build();

        parkingRestService = retrofit.create(ParkingRESTService.class);gsonConverter
    }

    public interface ParkingRESTService{
        
    }
}
