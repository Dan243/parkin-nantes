package com.example.dmonunu.parkinnantes.services;

import com.example.dmonunu.parkinnantes.models.ParkingModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ParkingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createParking(ParkingModel parkingModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createParkings(List<ParkingModel> parkingModels);

    @Query("SELECT * FROM Parkings")
    List<ParkingModel> getParkings();
}
