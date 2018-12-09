package com.example.dmonunu.parkinnantes.models;

<<<<<<< HEAD
import android.content.Context;

=======
import com.example.dmonunu.parkinnantes.services.DispoDao;
import com.example.dmonunu.parkinnantes.services.HoraireDao;
import com.example.dmonunu.parkinnantes.services.LightParkingDao;
>>>>>>> 3d7cb8c9b3e3545c7dd739d83310130d622a216b
import com.example.dmonunu.parkinnantes.services.ParkingDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ParkingModel.class, DispoModel.class, HoraireModel.class, LightParking.class}, version = 3, exportSchema = false)
public abstract class ParkingDataBase extends RoomDatabase {

    private static ParkingDataBase INSTANCE;
    public abstract ParkingDao parkingDao();

<<<<<<< HEAD
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
=======
    public abstract DispoDao dispoDao();

    public abstract HoraireDao horaireDao();

    public abstract LightParkingDao lightParkingDao();

>>>>>>> 3d7cb8c9b3e3545c7dd739d83310130d622a216b
}
