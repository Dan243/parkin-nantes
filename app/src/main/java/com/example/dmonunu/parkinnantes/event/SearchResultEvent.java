package com.example.dmonunu.parkinnantes.event;

import com.example.dmonunu.parkinnantes.models.LightParking;

import java.util.List;

public class SearchResultEvent {
    private List<LightParking> parkings;

    public SearchResultEvent(List<LightParking> places) {
        this.parkings = places;
    }

    public List<LightParking> getPlaces() {
        return parkings;
    }
}
