package com.example.dmonunu.parkinnantes.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.Html;
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

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

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
        TextView moreInfo = view.findViewById(R.id.moreInfo);

        name_tv.setText(marker.getTitle());
        details_tv.setText(Html.fromHtml("Situé à " + ligthParking.getAdresse() + "<br>Il y a <b>" + ligthParking.getNbPlaceDispo() + "</b> places disponibles", Html.FROM_HTML_MODE_COMPACT));
        moreInfo.setPaintFlags(moreInfo.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        return view;
    }
}

