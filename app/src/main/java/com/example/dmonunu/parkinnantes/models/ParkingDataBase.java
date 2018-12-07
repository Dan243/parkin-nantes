package com.example.dmonunu.parkinnantes.models;

import com.example.dmonunu.parkinnantes.services.ParkingDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ParkingModel.class}, version = 1, exportSchema = false)
public abstract class ParkingDataBase extends RoomDatabase {

    public abstract ParkingDao parkingDao();
}
