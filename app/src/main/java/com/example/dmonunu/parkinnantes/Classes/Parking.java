package com.example.dmonunu.parkinnantes.Classes;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

import java.io.Serializable;

@Table( name="Parking")
public class Parking extends Model implements Serializable {

    @Expose
    @Column(name = "id_Parking", index = true, unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public String id_Parking;

    @Expose
    @Column(name = "nom_Parking")
    public String nom_Parking;

    @Expose
    @Column(name = "categorie")
    public String categorie;

    @Expose
    @Column(name = "commune")
    public String commune;

    @Expose
    @Column(name = "adresse")
    public String adresse;

    @Expose
    @Column(name = "telephone")
    public String telephone;

    @Expose
    @Column(name = "code_postale")
    public String code_postale;

    @Expose
    @Column(name = "capacite")
    public String capacite;

    @Column(name = "access_pmr")
    public boolean access_pmr = false ;

    @Expose
    @Column(name = "moyen_paiement")
    public String moyen_paiement;

    @Expose
    @Column(name = "acces_transport_commun")
    public String acces_transport_commun;

    @Expose
    @Column(name = "PlaceDisponible")
    public int availablePlaces;

    @Column(name = "Latitude")
    public double latitude;

    @Column(name = "Longitude")
    public double longitude;

    @Column(name = "Favorite")
    public boolean isFavorite = false;

    @Column(name="CarteBancaire")
    public boolean isCreditCardAvailable = false;

    @Column(name="Espece")
    public boolean isCashAvailable = false;

    @Column(name="TotalGR")
    public boolean isTotalGRCardAvailable = false;

    @Column(name="Cheque")
    public boolean isChequeAvailable = false;

    public String getId_Parking() {
        return id_Parking;
    }

    public void setId_Parking(String id_Parking) {
        this.id_Parking = id_Parking;
    }

    public String getNom_Parking() {
        return nom_Parking;
    }

    public void setNom_Parking(String nom_Parking) {
        this.nom_Parking = nom_Parking;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCode_postale() {
        return code_postale;
    }

    public void setCode_postale(String code_postale) {
        this.code_postale = code_postale;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public boolean isAccess_pmr() {
        return access_pmr;
    }

    public void setAccess_pmr(boolean access_pmr) {
        this.access_pmr = access_pmr;
    }

    public String getMoyen_paiement() {
        return moyen_paiement;
    }

    public void setMoyen_paiement(String moyen_paiement) {
        this.moyen_paiement = moyen_paiement;
    }

    public String getAcces_transport_commun() {
        return acces_transport_commun;
    }

    public void setAcces_transport_commun(String acces_transport_commun) {
        this.acces_transport_commun = acces_transport_commun;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isCreditCardAvailable() {
        return isCreditCardAvailable;
    }

    public void setCreditCardAvailable(boolean creditCardAvailable) {
        isCreditCardAvailable = creditCardAvailable;
    }

    public boolean isCashAvailable() {
        return isCashAvailable;
    }

    public void setCashAvailable(boolean cashAvailable) {
        isCashAvailable = cashAvailable;
    }

    public boolean isTotalGRCardAvailable() {
        return isTotalGRCardAvailable;
    }

    public void setTotalGRCardAvailable(boolean totalGRCardAvailable) {
        isTotalGRCardAvailable = totalGRCardAvailable;
    }

    public boolean isChequeAvailable() {
        return isChequeAvailable;
    }

    public void setChequeAvailable(boolean chequeAvailable) {
        isChequeAvailable = chequeAvailable;
    }

}
