package com.example.dmonunu.parkinnantes.models;

import android.util.Log;

import java.util.List;

import androidx.room.Room;

public class ParkingMapper {


    public static void createLightParking(DispoModel dispoModel, HoraireModel horaireModel, ParkingModel parkingModel){

        final String DATABASE_NAME = "parking_db";
        final ParkingDataBase parkingDataBase;

        parkingDataBase = Room.databaseBuilder(getApplicationContext(), ParkingDataBase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<LightParking> lightParking = parkingDataBase.lightParkingDao().getIdobjParking();
            }
        }).start();

         String idobj = parkingModel.getIdobj();
         String nomParking = horaireModel.getNom();
         int nbPlaceDispo = dispoModel.getGrp_disponible();
         int capaciteTotale = parkingModel.getCarCapacity();
         String telephone = parkingModel.getTelephone();
         String moyenPaiement = parkingModel.getPaymentWays();
         String heure_debut = horaireModel.getHeure_debut();
         String heure_fin = horaireModel.getHeure_fin();
         List<Double> location = parkingModel.getLocation();
         String date_debut = horaireModel.getDate_debut();
         String date_fin = horaireModel.getDate_fin();
         String adresse = parkingModel.getAddress();

         LightParking lightParking = new LightParking(idobj, nomParking,  nbPlaceDispo,  capaciteTotale,  telephone,moyenPaiement,heure_debut,heure_fin,location,date_debut,date_fin,adresse);
    }
}
