package com.example.dmonunu.parkinnantes.services;

import com.example.dmonunu.parkinnantes.models.HoraireModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface HoraireDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createHoraire(HoraireModel horaireModel);

    @Query("SELECT * FROM HoraireModel")
    List<HoraireModel> getHoraire();
}
