package com.example.dmonunu.parkinnantes.event;

import com.example.dmonunu.parkinnantes.models.LightParking;

import java.util.List;

/**
 * Created by Zheyu XIE.
 */
public class SearchResultEvent {
    private List<LightParking> parkings;

    public SearchResultEvent(List<LightParking> parkings) {
        this.parkings = parkings;
    }

    public List<LightParking> getParkings() {
        return parkings;
    }
}
