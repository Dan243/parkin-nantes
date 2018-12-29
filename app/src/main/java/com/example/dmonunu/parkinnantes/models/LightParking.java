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

    private boolean isFavorite = false;

    // La capacité totale du stationnement vélo
    private int stationnement_velo;

    //La capacité des PMR
    private int capacite_pmr;

    // La capacité moto
    private int capacite_moto;

    // La présentation globale de la station
    private String presentation;

    private String acces_transports_communs;

    public boolean isCreditCardAvailable = false;

    public boolean isCashAvailable = false;

    public boolean isTotalGRCardAvailable = false;

    public boolean isChequeAvailable = false;

    public boolean isLigneOneNear = false;

    public boolean isLigneTwoNear = false;

    public boolean isLigneThreeNear = false;

    public boolean isLigneFourNear = false;

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
        this.nbPlaceDispo = builder.nbPlaceDispo;
        this.isFavorite = builder.isFavorite;
        this.stationnement_velo = builder.stationnement_velo;
        this.capacite_moto = builder.capacite_moto;
        this.capacite_pmr = builder.capacite_pmr;
        this.presentation = builder.presentation;
        this.isCashAvailable = builder.isCashAvailable;
        this.isChequeAvailable = builder.isChequeAvailable;
        this.isCreditCardAvailable = builder.isCreditCardAvailable;
        this.isLigneOneNear = builder.isLigneOneNear;
        this.isTotalGRCardAvailable = builder.isTotalGRCardAvailable;
        this.isLigneTwoNear = builder.isLigneTwoNear;
        this.isLigneThreeNear = builder.isLigneThreeNear;
        this.isLigneFourNear = builder.isLigneFourNear;
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getAcces_transports_communs() {
        return acces_transports_communs;
    }

    public void setAcces_transports_communs(String acces_transports_communs) {
        this.acces_transports_communs = acces_transports_communs;
    }

    public int getStationnement_velo() {
        return stationnement_velo;
    }

    public void setStationnement_velo(int stationnement_velo) {
        this.stationnement_velo = stationnement_velo;
    }

    public int getCapacite_pmr() {
        return capacite_pmr;
    }

    public void setCapacite_pmr(int capacite_pmr) {
        this.capacite_pmr = capacite_pmr;
    }

    public int getCapacite_moto() {
        return capacite_moto;
    }

    public void setCapacite_moto(int capacite_moto) {
        this.capacite_moto = capacite_moto;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
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

    public boolean isLigneOneNear() {
        return isLigneOneNear;
    }

    public void setLigneOneNear(boolean ligneOneNear) {
        isLigneOneNear = ligneOneNear;
    }

    public boolean isLigneTwoNear() {
        return isLigneTwoNear;
    }

    public void setLigneTwoNear(boolean ligneTwoNear) {
        isLigneTwoNear = ligneTwoNear;
    }

    public boolean isLigneThreeNear() {
        return isLigneThreeNear;
    }

    public void setLigneThreeNear(boolean ligneThreeNear) {
        isLigneThreeNear = ligneThreeNear;
    }

    public boolean isLigneFourNear() {
        return isLigneFourNear;
    }

    public void setLigneFourNear(boolean ligneFourNear) {
        isLigneFourNear = ligneFourNear;
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

        private boolean isFavorite = false;

        // La capacité totale du stationnement vélo
        private int stationnement_velo;

        //La capacité des PMR
        private int capacite_pmr;

        // La capacité moto
        private int capacite_moto;

        // La présentation globale de la station
        private String presentation;

        private String acces_transports_communs;

        public boolean isCreditCardAvailable = false;

        public boolean isCashAvailable = false;

        public boolean isTotalGRCardAvailable = false;

        public boolean isChequeAvailable = false;

        public boolean isLigneOneNear = false;

        public boolean isLigneTwoNear = false;

        public boolean isLigneThreeNear = false;

        public boolean isLigneFourNear = false;

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

        public LightParkingBuilder isFavorite(Boolean favorite){
            this.isFavorite = favorite;
            return this;
        }

        public LightParkingBuilder stationnement_velo(int nb){
            this.stationnement_velo = nb;
            return this;
        }

        public LightParkingBuilder capacite_pmr(int nb){
            this.capacite_pmr = nb;
            return this;
        }

        public LightParkingBuilder capacite_moto(int nb){
            this.capacite_moto = nb;
            return this;
        }

        public LightParkingBuilder presentation(String presentation){
            this.presentation = presentation;
            return this;
        }

        public LightParkingBuilder isCreditCardAvailable(boolean isCreditCardAvailable){
            this.isCreditCardAvailable = isCreditCardAvailable;
            return this;
        }

        public LightParkingBuilder isCashAvailable(boolean isCashAvailable){
            this.isCashAvailable = isCashAvailable;
            return this;
        }

        public LightParkingBuilder isTotalGRCardAvailable(boolean isTotalGRCardAvailable){
            this.isTotalGRCardAvailable = isTotalGRCardAvailable;
            return this;
        }

        public LightParkingBuilder isChequeAvailable(boolean isChequeAvailable){
            this.isChequeAvailable = isChequeAvailable;
            return this;
        }

        public LightParkingBuilder isLigneOneNear(boolean isLigneOneNear){
            this.isLigneOneNear = isLigneOneNear;
            return this;
        }

        public LightParkingBuilder isLigneTwoNear(boolean isLigneTwoNear){
            this.isLigneTwoNear = isLigneTwoNear;
            return this;
        }

        public LightParkingBuilder isLigneThreeNear(boolean isLigneThreeNear){
            this.isLigneThreeNear = isLigneThreeNear;
            return this;
        }

        public LightParkingBuilder isLigneFourNear(boolean isLigneFourNear){
            this.isLigneFourNear = isLigneFourNear;
            return this;
        }

        public LightParkingBuilder acces_transports_communs(String acces_transports_communs){
            this.acces_transports_communs = acces_transports_communs;
            return this;
        }

        public LightParking build() {
            return new LightParking(this);
        }
    }
}
