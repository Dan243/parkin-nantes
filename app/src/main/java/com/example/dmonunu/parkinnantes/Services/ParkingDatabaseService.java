package com.example.dmonunu.parkinnantes.Services;

import com.activeandroid.query.Select;
import com.example.dmonunu.parkinnantes.Models.ParkingProperties;

public class ParkingDatabaseService {

    public static ParkingProperties searchParkingFromMainId(final String id) {
        return new Select().
                from(ParkingProperties.class).where("IdObj LIKE '" + id + "'").executeSingle();
    }
}
