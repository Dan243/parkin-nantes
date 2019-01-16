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

    @Query("SELECT * FROM LightParking")
    List<LightParking> getParkings();

    @Query("SELECT * FROM LightParking WHERE isFavorite = 1")
    List<LightParking> getFavoriteParkings();

    @Query("SELECT * FROM LightParking WHERE adresse LIKE '%' || :search || '%'")
    List<LightParking> findParkingsByAddress(String search);

    @Query("SELECT * FROM LightParking WHERE nomParking LIKE '%' || :search || '%'")
    List<LightParking> findParkingsByName(String search);

    @Query("SELECT * FROM LightParking WHERE nomParking = :search")
    LightParking findParkingByName(String search);

    @Query("SELECT * FROM LightParking WHERE nomParking LIKE '%' || :search || '%' OR adresse LIKE '%' || :search || '%'")
    List<LightParking> findParkingsByNameOrAddress(String search);

    @Query("SELECT * FROM LightParking WHERE isFavorite = 1 AND (nomParking LIKE '%' || :search || '%' OR adresse LIKE '%' || :search || '%')")
    List<LightParking> findParkingsFavoriByNameOrAddress(String search);

    @Query("SELECT * FROM LightParking WHERE nbPlaceDispo >= :search")
    List<LightParking> findParkingsByDispo(int search);

    @Query("UPDATE LightParking SET isFavorite =:fav WHERE idobj =:search")
    void setFavorite(String search, boolean fav);

    @Query("SELECT * FROM LightParking WHERE nomParking LIKE '%' || :name || '%' AND adresse LIKE '%' || :address || '%' AND moyenPaiement LIKE '%' || :cash || '%' AND moyenPaiement LIKE '%' || :total_gr || '%' AND moyenPaiement LIKE '%' || :cb || '%' AND nbPlaceDispo > :left AND nbPlaceDispo < :right")
    List<LightParking> findParkingsByNameAndAddress(String name, String address, String cash, String total_gr, String cb, int left, int right);
}
