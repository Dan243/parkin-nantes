package com.example.dmonunu.parkinnantes.activities;

import com.example.dmonunu.parkinnantes.models.LightParking;

import java.util.List;

public interface ParkingPresenter {

    void getParkings();

    void getParkingsFromApi();

    boolean isNetworkOnline();

    void getParkingsFromRoom();

    void getFavoriteParkings();

    LightParking updateParkingFavori(LightParking parking, List<LightParking> parkings);
}
