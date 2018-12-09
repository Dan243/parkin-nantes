package com.example.dmonunu.parkinnantes.services;

import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.models.ParkingModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface LightParkingDao {

    @Query("SELECT idobj FROM LightParking ")
    List<LightParking> getIdobjParking();



}
