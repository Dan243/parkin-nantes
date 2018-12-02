package com.example.dmonunu.parkinnantes.services;

import com.example.dmonunu.parkinnantes.models.BaseResponse;
import com.example.dmonunu.parkinnantes.models.DispoModel;
import com.example.dmonunu.parkinnantes.models.HoraireModel;
import com.example.dmonunu.parkinnantes.models.ParkingModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ParkingSearchRESTService {

    @GET("api/records/1.0/search/")
    Call<BaseResponse<ParkingModel>> recupTousLesParkings(@Query("dataset") String datasetId);

    @GET("api/records/1.0/search/")
    Call<BaseResponse<DispoModel>> recupToutesLesDispos(@Query("dataset") String datasetId);

    @GET("api/records/1.0/search/")
    Call<BaseResponse<HoraireModel>> recupTousLesHoraires(@Query("dataset") String datasetId);
}
