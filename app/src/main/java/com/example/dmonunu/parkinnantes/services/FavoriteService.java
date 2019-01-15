package com.example.dmonunu.parkinnantes.services;

import java.util.List;

public interface FavoriteService {
    void setFavoriteInRoom(String fav);

    void removeFavoriteInRoom(String fav);

    boolean isFavorite(String fav);
}
