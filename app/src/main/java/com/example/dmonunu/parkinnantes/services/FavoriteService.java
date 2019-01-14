package com.example.dmonunu.parkinnantes.services;

import java.util.List;

public interface FavoriteService {
    void setFavoriteInRoom(List<String> fav);

    boolean isFavorite(String fav);
}
