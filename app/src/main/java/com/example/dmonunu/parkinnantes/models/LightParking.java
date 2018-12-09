package com.example.dmonunu.parkinnantes.models;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "LightParking")
public class LightParking implements Serializable {

    @PrimaryKey
    @NonNull
    private String idobj;

    private String nomParking;

    private int nbPlaceDispo;

    private int capaciteTotale;

    private String telephone;

    private String moyenPaiement;

    private String heureDebut;

    private String heureFin;

    private double latitude;

    private double longitude;

    private String dateDebut;

    private String dateFin;

    private String adresse;

    public LightParking() {}

    public LightParking(LightParkingBuilder builder){
        this.idobj = builder.idobj;
        this.adresse = builder.adresse;
        this.capaciteTotale = builder.capaciteTotale;
        this.dateDebut = builder.dateDebut;
        this.dateFin = builder.dateFin;
        this.heureDebut = builder.heureDebut;
        this.heureFin = builder.heureFin;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.moyenPaiement = builder.moyenPaiement;
        this.nomParking = builder.nomParking;
        this.telephone = builder.telephone;
    }

    @NonNull
    public String getIdobj() {
        return idobj;
    }

    public void setIdobj(@NonNull String idobj) {
        this.idobj = idobj;
    }

    public String getNomParking() {
        return nomParking;
    }

    public void setNomParking(String nomParking) {
        this.nomParking = nomParking;
    }

    public int getNbPlaceDispo() {
        return nbPlaceDispo;
    }

    public void setNbPlaceDispo(int nbPlaceDispo) {
        this.nbPlaceDispo = nbPlaceDispo;
    }

    public int getCapaciteTotale() {
        return capaciteTotale;
    }

    public void setCapaciteTotale(int capaciteTotale) {
        this.capaciteTotale = capaciteTotale;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(String moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public static class LightParkingBuilder {

        private String idobj;

        private String nomParking;

        private int nbPlaceDispo;

        private int capaciteTotale;

        private String telephone;

        private String moyenPaiement;

        private String heureDebut;

        private String heureFin;

        private double latitude;

        private double longitude;

        private String dateDebut;

        private String dateFin;

        private String adresse;

        public LightParkingBuilder() {}

        public LightParkingBuilder idObj(String idObj) {
            this.idobj = idObj;
            return this;
        }

        public LightParkingBuilder nomParking(String nomParking) {
            this.nomParking = nomParking;
            return this;
        }

        public LightParkingBuilder nbPlaceDispo(int nbPlaceDispo) {
            this.nbPlaceDispo = nbPlaceDispo;
            return this;
        }

        public LightParkingBuilder capaciteTotale(int capaciteTotale) {
            this.capaciteTotale = capaciteTotale;
            return this;
        }

        public LightParkingBuilder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public LightParkingBuilder moyenPaiement(String moyenPaiement) {
            this.moyenPaiement = moyenPaiement;
            return this;
        }

        public LightParkingBuilder heureDebut(String heureDebut) {
            this.heureDebut = heureDebut;
            return this;
        }

        public LightParkingBuilder heureFin(String heureFin) {
            this.heureFin = heureFin;
            return this;
        }

        public LightParkingBuilder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public LightParkingBuilder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public LightParkingBuilder dateDebut(String dateDebut) {
            this.dateDebut = dateDebut;
            return this;
        }

        public LightParkingBuilder dateFin(String dateFin) {
            this.dateFin = dateFin;
            return this;
        }

        public LightParkingBuilder adresse(String adresse) {
            this.adresse = adresse;
            return this;
        }

        public LightParking build() {
            return new LightParking(this);
        }
    }
}
