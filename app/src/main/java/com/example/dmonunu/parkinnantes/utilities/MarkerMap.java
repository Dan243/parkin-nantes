package com.example.dmonunu.parkinnantes.utilities;

import android.content.Context;


import com.example.dmonunu.parkinnantes.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class MarkerMap extends DefaultClusterRenderer<ClusterItemImpl> {


    public MarkerMap(Context context, GoogleMap map, ClusterManager<ClusterItemImpl> clusterManager) {
        super(context, map, clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(ClusterItemImpl item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
        if (item.getNbPlaces() == 0) {
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.full_icon));
        } else {
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.dispo_icon));
        }
    }


}


