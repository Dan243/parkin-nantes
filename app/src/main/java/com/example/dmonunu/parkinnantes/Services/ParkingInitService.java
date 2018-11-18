package com.example.dmonunu.parkinnantes.Services;

import com.activeandroid.ActiveAndroid;
import com.example.dmonunu.parkinnantes.Models.ParkingAvailability;
import com.example.dmonunu.parkinnantes.Models.ParkingCoordinates;
import com.example.dmonunu.parkinnantes.Models.ParkingProperties;
import com.example.dmonunu.parkinnantes.Models.ParkingSchedules;

public final class ParkingInitService {

    public static void setParkingProperties(final ParkingProperties local, final ParkingProperties api){
        // Début de l'enregistrement dans la base de donnée
        ActiveAndroid.beginTransaction();

        local.setAdresse(api.getAdresse());
        local.setCode_postale(api.getCode_postale());
        local.setCommune(api.getCommune());
        local.setAcces_transport_commun(api.getAcces_transport_commun());
        local.setAvailablePlaces(api.getAvailablePlaces());
        local.setCategorie(api.getCategorie());
        local.setAccess_pmr(api.isAccess_pmr());
        local.setMoyen_paiement(api.getMoyen_paiement());
        local.setNom_Parking(api.getNom_Parking());
        local.setTelephone(api.getTelephone());
        local.save();

        ActiveAndroid.setTransactionSuccessful();
        ActiveAndroid.endTransaction();

    }

    public static void setParkingCoordinates(final ParkingCoordinates local, final ParkingCoordinates api){

    }

    public static void setParkingSchedules(final ParkingSchedules local, final ParkingSchedules api){

    }

    public static void setParkingAvailability(final ParkingAvailability local, final ParkingAvailability api){

    }

    public static void onSearchParking(final ParkingProperties api) {
        final String id = String.valueOf(api.getIdobj());
        final ParkingProperties parkingBD = ParkingDatabaseService.searchParkingFromMainId(id);
        ParkingProperties local = parkingBD;

        if (local == null) {
            local = api;
            final String Idobj = String.valueOf(api.getIdobj());
            api.setId_Parking(Idobj);

        }
        ParkingInitService.setParkingProperties(local, api);
    }
}
