package com.example.dmonunu.parkinnantes.utilities;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class ClusterItemImpl implements ClusterItem {
    private LatLng mPosition;
    private String mTitle;
    private String mSnippet;
    private int nbPlaces;

    public ClusterItemImpl(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    public ClusterItemImpl(double lat, double lng, String title, String snippet, int nbPlaces) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
        this.nbPlaces = nbPlaces;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }
}
