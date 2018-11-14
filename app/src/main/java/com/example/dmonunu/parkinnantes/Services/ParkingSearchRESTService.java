package com.example.dmonunu.parkinnantes.Services;

import com.example.dmonunu.parkinnantes.Models.ParkingSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ParkingSearchRESTService {

    @GET("records/1.0/search/?dataset=244400404_parkings-publics-nantes&facet=libcategorie&facet=libtype&facet=acces_pmr&facet=service_velo&facet=stationnement_velo&facet=stationnement_velo_securise&facet=moyen_paiement")
    Call<ParkingSearchResult> recupTousLesParkings();

    @GET("records/1.0/search/?dataset=244400404_parkings-publics-nantes-disponibilites&facet=grp_nom&facet=grp_statut")
    Call<ParkingSearchResult> recupToutesLesDispos();

    @GET("records/1.0/search/?dataset=244400404_parkings-publics-nantes-horaires&facet=nom_periode&facet=jour&facet=type_horaire")
    Call<ParkingSearchResult> recupTousLesHoraires();
}
