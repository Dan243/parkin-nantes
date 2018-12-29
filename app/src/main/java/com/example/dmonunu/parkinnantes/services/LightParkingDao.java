package com.example.dmonunu.parkinnantes.services;

import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.models.ParkingModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface LightParkingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createParkings(List<LightParking> parkingModels);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createParking(LightParking parking);

    @Query("SELECT * FROM LightParking ")
    List<LightParking> getParkings();


    @Query("SELECT * FROM LightParking WHERE isFavorite = 'true'")
    List<LightParking> getFavoriteParkings();

    @Query("SELECT * FROM LightParking WHERE adresse LIKE '%' || :search || '%'")
    List<LightParking> findParkingsByAddress(String search);

    @Query("SELECT * FROM LightParking WHERE nomParking LIKE '%' || :search || '%'")
    List<LightParking> findParkingsByName(String search);

    @Query("SELECT * FROM LightParking WHERE nbPlaceDispo >= :search")
    List<LightParking> findParkingsByDispo(int search);

    @Query("UPDATE LightParking SET isFavorite = 'true'")
    void setFavorite();

    @Query("UPDATE LightParking SET isFavorite = 'false'")
    void removeFavorite();

    @Query("SELECT * FROM LightParking WHERE nomParking LIKE '%' || :name || '%' AND adresse LIKE '%' || :address || '%'")
    List<LightParking> findParkingsByNameAndAddress(String name, String address);

}
