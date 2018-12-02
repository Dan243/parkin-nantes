package com.example.dmonunu.parkinnantes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "HoraireModel")
public class HoraireModel implements Serializable {

    @SerializedName("grp_complet")
    @Expose
    private int grp_complet;

    @SerializedName("grp_identifiant")
    @Expose
    private int grp_identifiant;

    @SerializedName("grp_horodatage")
    @Expose
    private String grp_horodatage;

    @SerializedName("grp_disponible")
    @Expose
    private int grp_disponible;

    @SerializedName("idobj")
    @Expose
    @PrimaryKey
    @NonNull
    private int idobj;

    @SerializedName("grp_nom")
    @Expose
    private String grp_nom;

    @SerializedName("grp_statut")
    @Expose
    private int grp_statut;

    @SerializedName("grp_exploitant")
    @Expose
    private int grp_exploitant;

    @SerializedName("grp_pri_auth")
    @Expose
    private int grp_pri_auth;


    public int getGrp_complet() {
        return grp_complet;
    }

    public void setGrp_complet(int grp_complet) {
        this.grp_complet = grp_complet;
    }

    public int getGrp_identifiant() {
        return grp_identifiant;
    }

    public void setGrp_identifiant(int grp_identifiant) {
        this.grp_identifiant = grp_identifiant;
    }

    public String getGrp_horodatage() {
        return grp_horodatage;
    }

    public void setGrp_horodatage(String grp_horodatage) {
        this.grp_horodatage = grp_horodatage;
    }

    public int getGrp_disponible() {
        return grp_disponible;
    }

    public void setGrp_disponible(int grp_disponible) {
        this.grp_disponible = grp_disponible;
    }

    public int getIdobj() {
        return idobj;
    }

    public void setIdobj(int idobj) {
        this.idobj = idobj;
    }

    public String getGrp_nom() {
        return grp_nom;
    }

    public void setGrp_nom(String grp_nom) {
        this.grp_nom = grp_nom;
    }

    public int getGrp_statut() {
        return grp_statut;
    }

    public void setGrp_statut(int grp_statut) {
        this.grp_statut = grp_statut;
    }

    public int getGrp_exploitant() {
        return grp_exploitant;
    }

    public void setGrp_exploitant(int grp_exploitant) {
        this.grp_exploitant = grp_exploitant;
    }

    public int getGrp_pri_auth() {
        return grp_pri_auth;
    }

    public void setGrp_pri_auth(int grp_pri_auth) {
        this.grp_pri_auth = grp_pri_auth;
    }
}
