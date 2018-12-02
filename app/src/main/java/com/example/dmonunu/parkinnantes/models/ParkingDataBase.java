package com.example.dmonunu.parkinnantes.models;

import com.example.dmonunu.parkinnantes.services.DispoDao;
import com.example.dmonunu.parkinnantes.services.HoraireDao;
import com.example.dmonunu.parkinnantes.services.ParkingDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ParkingModel.class, DispoModel.class, HoraireModel.class}, version = 2, exportSchema = false)
public abstract class ParkingDataBase extends RoomDatabase {

    public abstract ParkingDao parkingDao();

    public abstract DispoDao dispoDao();

    public abstract HoraireDao horaireDao();

}
