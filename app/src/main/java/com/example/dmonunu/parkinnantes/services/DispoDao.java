package com.example.dmonunu.parkinnantes.services;

import com.example.dmonunu.parkinnantes.models.DispoModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface DispoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createDispoDao(DispoModel dispoModel);

    @Query("SELECT * FROM DispoModel")
    List<DispoModel> getDisponibilite();

    @Query("SELECT * FROM DispoModel WHERE idobj = :idobj")
    DispoModel getDisponibilteOfAParking(String idobj);
}
