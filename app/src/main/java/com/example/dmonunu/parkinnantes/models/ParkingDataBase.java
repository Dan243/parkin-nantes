package com.example.dmonunu.parkinnantes.models;

import android.content.Context;

import com.example.dmonunu.parkinnantes.services.ParkingDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ParkingModel.class}, version = 1, exportSchema = false)
public abstract class ParkingDataBase extends RoomDatabase {

    private static ParkingDataBase INSTANCE;
    public abstract ParkingDao parkingDao();

    public static ParkingDataBase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), ParkingDataBase.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }
}
