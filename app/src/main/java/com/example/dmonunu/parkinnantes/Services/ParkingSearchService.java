package com.example.dmonunu.parkinnantes.Services;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.example.dmonunu.parkinnantes.Models.Parking;
import com.example.dmonunu.parkinnantes.Models.ParkingProperties;
import com.example.dmonunu.parkinnantes.Models.ParkingSearchResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
    Author : dmonunu
    Date : 09/11/2018
 */
public class ParkingSearchService {

    public static ParkingSearchService INSTANCE = new ParkingSearchService();
    private final ParkingSearchRESTService apiREST;
    private final static String apiURL = "https://data.nantesmetropole.fr/api/";

    public ParkingSearchService(){
        // Create GSON Converter that will be used to convert JSON from JAVA
        Gson gsonConverter = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls()
                .excludeFieldsWithoutExposeAnnotation().create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(apiURL)
                .addConverterFactory(GsonConverterFactory.create(gsonConverter))
                .build();

        apiREST = retrofit.create(ParkingSearchRESTService.class);
    }

    public void recupTousLesParkings(){
        apiREST.recupTousLesParkings().enqueue(new Callback<ParkingSearchResult>
                () {
            @Override
            public void onResponse(Call<ParkingSearchResult> call, Response<ParkingSearchResult>
                    response) {
                final ParkingSearchResult result = response.body();
                if(result != null && result.records != null){
                    ActiveAndroid.beginTransaction();
                    for(Parking parking: result.records){
                    /*
                        parking.id_parking = parking.properties.id_Parking;
                        parking.geometry.id_parking = parking.properties.id_Parking;
                        parking.availability.id_parking = parking.properties.id_Parking;
                        parking.schedule.id_parking = parking.properties.id_Parking;
                     */
                    }
                } else {
                    // Null result
                    // We may want to display a warning to user
                }
            }

            @Override
            public void onFailure(Call<ParkingSearchResult> call, Throwable t) {

            }
        });
    }

    public void recupToutesLesDispos(){
        apiREST.recupToutesLesDispos().enqueue(new Callback<ParkingSearchResult>
                () {
            @Override
            public void onResponse(Call<ParkingSearchResult> call, Response<ParkingSearchResult>
                    response) {
                final ParkingSearchResult parkingList = response.body();

            }

            @Override
            public void onFailure(Call<ParkingSearchResult> call, Throwable t) {

            }
        });
    }

    public void recupTousLesHoraires(){
        apiREST.recupTousLesHoraires().enqueue(new Callback<ParkingSearchResult>() {
            @Override
            public void onResponse(Call<ParkingSearchResult> call, Response<ParkingSearchResult> response) {
                final ParkingSearchResult parkingList = response.body();


            }

            @Override
            public void onFailure(Call<ParkingSearchResult> call, Throwable t) {

            }
        });
    }
}
