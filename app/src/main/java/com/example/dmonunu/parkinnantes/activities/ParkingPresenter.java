package com.example.dmonunu.parkinnantes.activities;

public interface ParkingPresenter {

    void getParkings();

    void getParkingsFromApi();

    boolean isNetworkOnline();

    void getParkingsFromRoom();

    void getFavoriteParkings();
}
