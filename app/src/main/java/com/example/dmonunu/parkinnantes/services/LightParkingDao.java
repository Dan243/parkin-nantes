package com.example.dmonunu.parkinnantes.services;

import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.models.ParkingModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface LightParkingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createParkings(List<LightParking> parkingModels);

    @Query("SELECT * FROM LightParking ")
    List<LightParking> getParkings();



}