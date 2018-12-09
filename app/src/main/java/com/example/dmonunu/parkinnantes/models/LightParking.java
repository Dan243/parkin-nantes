package com.example.dmonunu.parkinnantes.models;

import java.io.Serializable;
import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "LightParking")
public class LightParking implements Serializable {

    @PrimaryKey
    private String idobj;

    private String nomParking;

    private int nbPlaceDispo;

    private int capaciteTotale;

    private String telephone;

    private String moyenPaiement;

    private String heure_debut;

    private String heure_fin;

    private List<Double> location;

    private String date_debut;

    private String date_fin;

    private String adresse;

    public LightParking(String idobj, String nomParking, int nbPlaceDispo, int capaciteTotale, String telephone, String moyenPaiement, String heure_debut, String heure_fin, List<Double> location, String date_debut, String date_fin, String adress) {
        this.idobj = idobj;
        this.nomParking = nomParking;
        this.nbPlaceDispo = nbPlaceDispo;
        this.capaciteTotale = capaciteTotale;
        this.telephone = telephone;
        this.moyenPaiement = moyenPaiement;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.location = location;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.adresse = adress;
    }

    public String getIdobj() {
        return idobj;
    }

    public void setIdobj(String idobj) {
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

    public String getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(String heure_debut) {
        this.heure_debut = heure_debut;
    }

    public String getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(String heure_fin) {
        this.heure_fin = heure_fin;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
