package com.example.dmonunu.parkinnantes.utilities;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.activities.MapView;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MarkerMap {

    private LightParking parking;
    private GoogleMap googleMap;

    public MarkerMap(LightParking parking, GoogleMap googleMap) {
        this.parking = parking;
        this.googleMap = googleMap;
    }

    public LightParking getParking() {
        return parking;
    }

    public void setParking(LightParking parking) {
        this.parking = parking;
    }

    public Marker createMarker() {
        LatLng latLng = new LatLng(parking.getLatitude(), parking.getLongitude());
        if (parking.getNbPlaceDispo() > 0) {
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.dispo_icon))
                    .title(parking.getNomParking()));
            marker.setTag(parking);
            return marker;
        } else {
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.full_icon))
                    .title(parking.getNomParking()));
            marker.setTag(parking);
            return marker;
        }
    }
}
