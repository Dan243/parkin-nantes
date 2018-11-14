package com.example.dmonunu.parkinnantes.Models;

import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;

public class ParkingAvailability {

    @Expose
    @Column(name = "id_parking", index = true, unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public String id_parking;

    @Column(name = "nom")
    public String groupe_nom;

    @Expose
    @Column(name = "groupe_disponible")
    public String groupe_disponible;

    @Column(name = "groupe_exploitation")
    public String groupe_exploitation;
}
