package com.example.dmonunu.parkinnantes.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.activities.HomeActivity;
import com.example.dmonunu.parkinnantes.activities.ParkingDetailsActivity;
import com.example.dmonunu.parkinnantes.activities.ParkingDetailsActivity_ViewBinding;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.models.ParkingDataBase;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterItem;

public class CustomInfoWindowGoogleMap  implements GoogleMap.InfoWindowAdapter {

    private Context context;
    private LightParking ligthParking;

    public CustomInfoWindowGoogleMap(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.windows_marker, null);



        ligthParking = ParkingDataBase.getAppDatabase(context).lightParkingDao().findParkingByName(marker.getTitle());
        ClusterItemImpl infoWindowData = (ClusterItemImpl) marker.getTag();

        System.out.println("TROLOLO : "+marker.getTitle());

        TextView name_tv = view.findViewById(R.id.txtTitle);
        TextView details_tv = view.findViewById(R.id.txtSnippet);
        Button goTo = view.findViewById(R.id.goTo);

        name_tv.setText(marker.getTitle());
        goTo.setText("DETAILS");

        return view;
    }
}

