package com.example.dmonunu.parkinnantes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Parkings")
public class ParkingModel implements Serializable {

    @SerializedName("capacite_moto")
    @Expose
    private String motoCapacity;

    @SerializedName("telephone")
    @Expose
    private String telephone;

    @SerializedName("stationnement_velo_securise")
    @Expose
    private String safeBikeParking;

    @SerializedName("nom_complet")
    @Expose
    private String fullname;

    @SerializedName("capacite_vehicule_electrique")
    @Expose
    private String electricVehicleCapacity;

    @SerializedName("stationnement_velo")
    @Expose
    private String bikeParking;

    @SerializedName("service_velo")
    @Expose
    private String bikeService;

    @SerializedName("capacite_velo")
    @Expose
    private int bikeCapacity;

    @SerializedName("location")
    @Expose
    @Ignore
    private List<Double> location;

    private double latitude;

    private double longitude;

    @SerializedName("libcategorie")
    @Expose
    private String libCategory;

    @SerializedName("presentation")
    @Expose
    private String presentation;

    @SerializedName("acces_pmr")
    @Expose
    private String pmrAccess;

    @SerializedName("commune")
    @Expose
    private String city;

    @SerializedName("infos_complementaires")
    @Expose
    private String moreInfos;

    @SerializedName("moyen_paiement")
    @Expose
    private String paymentWays;

    @SerializedName("adresse")
    @Expose
    private String address;

    @SerializedName("capacite_pmr")
    @Expose
    private String pmrCapacity;

    @SerializedName("code_postal")
    @Expose
    private String zipCode;

    @SerializedName("acces_transports_communs")
    @Expose
    private String publicTransportAccess;


    @PrimaryKey
    @NonNull
    @SerializedName("idobj")
    @Expose
    private String idobj;

    @SerializedName("capacite_voiture")
    @Expose
    private int carCapacity;


    @SerializedName("libtype")
    @Expose
    private String libtype;

    public ParkingModel() {}

    public String getMotoCapacity() {
        return motoCapacity;
    }

    public void setMotoCapacity(String motoCapacity) {
        this.motoCapacity = motoCapacity;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSafeBikeParking() {
        return safeBikeParking;
    }

    public void setSafeBikeParking(String safeBikeParking) {
        this.safeBikeParking = safeBikeParking;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getElectricVehicleCapacity() {
        return electricVehicleCapacity;
    }

    public void setElectricVehicleCapacity(String electricVehicleCapacity) {
        this.electricVehicleCapacity = electricVehicleCapacity;
    }

    public String getBikeParking() {
        return bikeParking;
    }

    public void setBikeParking(String bikeParking) {
        this.bikeParking = bikeParking;
    }

    public String getBikeService() {
        return bikeService;
    }

    public void setBikeService(String bikeService) {
        this.bikeService = bikeService;
    }

    public int getBikeCapacity() {
        return bikeCapacity;
    }

    public void setBikeCapacity(int bikeCapacity) {
        this.bikeCapacity = bikeCapacity;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public String getLibCategory() {
        return libCategory;
    }

    public void setLibCategory(String libCategory) {
        this.libCategory = libCategory;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getPmrAccess() {
        return pmrAccess;
    }

    public void setPmrAccess(String pmrAccess) {
        this.pmrAccess = pmrAccess;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMoreInfos() {
        return moreInfos;
    }

    public void setMoreInfos(String moreInfos) {
        this.moreInfos = moreInfos;
    }

    public String getPaymentWays() {
        return paymentWays;
    }

    public void setPaymentWays(String paymentWays) {
        this.paymentWays = paymentWays;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPmrCapacity() {
        return pmrCapacity;
    }

    public void setPmrCapacity(String pmrCapacity) {
        this.pmrCapacity = pmrCapacity;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPublicTransportAccess() {
        return publicTransportAccess;
    }

    public void setPublicTransportAccess(String publicTransportAccess) {
        this.publicTransportAccess = publicTransportAccess;
    }

    public String getIdobj() {
        return idobj;
    }

    public void setIdobj(String idobj) {
        this.idobj = idobj;
    }

    public int getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(int carCapacity) {
        this.carCapacity = carCapacity;
    }

    public String getLibtype() {
        return libtype;
    }

    public void setLibtype(String libtype) {
        this.libtype = libtype;
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
}