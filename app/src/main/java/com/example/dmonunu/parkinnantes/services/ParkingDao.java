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

    @Query("SELECT * FROM ParkingModel")
    List<ParkingModel> getParkings();
}
