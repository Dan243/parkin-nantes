package com.example.dmonunu.parkinnantes.services;

import java.util.List;

/**
 * Created by Zheyu XIE.
 */
public interface ResearchService {
    void findParkingsFromRoom(List<String> research);

    void searchParkingByNameOrAddress(String s);

    void searchParkingFavoriByNameOrAddress(String s);
}
