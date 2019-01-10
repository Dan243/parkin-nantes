package com.example.dmonunu.parkinnantes.services;

import java.util.List;

public interface FavoriteService {
    void setFavoriteInRoom(String search);
    void unSetFavoriteInRoom(String fav);
    void getListOfFavoriteInRoom(List<String> fav);
}
