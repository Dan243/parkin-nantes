package com.example.dmonunu.parkinnantes.activities;

import com.example.dmonunu.parkinnantes.models.ParkingModel;
import com.example.dmonunu.parkinnantes.services.ParkingDao;

import java.util.List;

public interface MapView {

    void initMap(List<ParkingModel> parkingModels);
}
