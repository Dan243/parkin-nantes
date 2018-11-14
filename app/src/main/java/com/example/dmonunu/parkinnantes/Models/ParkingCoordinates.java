package com.example.dmonunu.parkinnantes.Models;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

@Table(name = "ParkingCoordinates")
public class ParkingCoordinates {

    @Expose
    @Column(name = "id_parking", index = true, unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public String id_parking;

    @Expose
    public List<Double> coordinates = new ArrayList<>();

    @Column(name = "latitude")
    public double latitude = 0;

    @Column(name = "longitude")
    public double longitude = 0;
}
