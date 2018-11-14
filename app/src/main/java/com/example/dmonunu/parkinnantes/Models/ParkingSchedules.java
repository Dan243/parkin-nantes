package com.example.dmonunu.parkinnantes.Models;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

import java.util.Date;

@Table(name = "ParkingSchedules")
public class ParkingSchedules {

    @Expose
    @Column(name = "id_parking&", index = true, unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public String id_parking;

    @Column(name = "nom")
    public String nom;

    @Column(name = "type_horaire")
    public String type_horaire;

    @Column(name = "jour")
    public String jour;

    @Column(name = "nom_periode")
    public String nom_periode;

    @Column(name = "heure_fin")
    public String heure_fin;

    @Column(name = "heure_debut")
    public String heure_debut;

    @Column(name = "date_fin")
    public Date date_fin;

    @Column(name = "date_debut")
    public Date date_debut;


}
