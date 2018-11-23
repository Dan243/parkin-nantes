package com.example.dmonunu.parkinnantes.Models;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

@Table(name ="Parking")
public class Parking {

    @Expose
    @Column(name = "id_parking", index = true, unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public String id_parking;

    @Expose
    public ParkingProperties fields;

    @Expose
    public ParkingCoordinates geometry;



}
