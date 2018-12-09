package com.example.dmonunu.parkinnantes.models;

import android.content.Context;

import com.example.dmonunu.parkinnantes.services.LightParkingDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LightParking.class}, version = 3, exportSchema = false)
public abstract class ParkingDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "parking_db";
    private static ParkingDataBase INSTANCE;

    public static ParkingDataBase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), ParkingDataBase.class, DATABASE_NAME)
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public abstract LightParkingDao lightParkingDao();

}
