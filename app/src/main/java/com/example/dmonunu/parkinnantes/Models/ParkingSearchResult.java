package com.example.dmonunu.parkinnantes.Models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ParkingSearchResult {

    @Expose
    public List<Parking> parkingList;

    public List<Parking> getParkingPropertiesList() {
        return parkingList;
    }

    public void setParkingPropertiesList(List<Parking> parkingPropertiesList) {
        this.parkingList = parkingPropertiesList;
    }
}
