package com.example.dmonunu.parkinnantes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "DispoModel")
public class DispoModel implements Serializable {

    @SerializedName("nom")
    @Expose
    private String nom;

    @SerializedName("type_horaire")
    @Expose
    private String type_horaire;

    @SerializedName("jour")
    @Expose
    private String jour;

    @SerializedName("nom_periode")
    @Expose
    private String nom_periode;

    @SerializedName("heure_fin")
    @Expose
    private String heure_fin;

    @SerializedName("heure_debut")
    @Expose
    private String heure_debut;

    @SerializedName("date_fin")
    @Expose
    private String date_fin;

    @SerializedName("idobj")
    @Expose
    @PrimaryKey
    @NonNull
    private String idobj;

    @SerializedName("location")
    @Expose
    @Ignore
    private List<Double> location;

    @SerializedName("date_debut")
    @Expose
    private String date_debut;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType_horaire() {
        return type_horaire;
    }

    public void setType_horaire(String type_horaire) {
        this.type_horaire = type_horaire;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getNom_periode() {
        return nom_periode;
    }

    public void setNom_periode(String nom_periode) {
        this.nom_periode = nom_periode;
    }

    public String getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(String heure_fin) {
        this.heure_fin = heure_fin;
    }

    public String getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(String heure_debut) {
        this.heure_debut = heure_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    @NonNull
    public String getIdobj() {
        return idobj;
    }

    public void setIdobj(@NonNull String idobj) {
        this.idobj = idobj;
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
}
