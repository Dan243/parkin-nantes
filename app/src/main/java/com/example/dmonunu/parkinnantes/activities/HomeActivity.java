package com.example.dmonunu.parkinnantes.activities;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.utilities.DrawerUtil;
import com.example.dmonunu.parkinnantes.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends FragmentActivity implements MapView,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, GoogleMap.OnMarkerClickListener{


    private static final String TAG = HomeActivity.class.getName();
    private GoogleMap mainMap;
    private static final int MY_LOCATION_REQUEST_CODE = 9401;

    private MapPresenter presenter;

    @BindView(R.id.toolbar)
    public Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        toolBar.setTitle(getResources().getString(R.string.tournament));

        DrawerUtil.getDrawer(this,toolBar);
        this.presenter = new MapPresenterImpl(this, getApplicationContext());
        this.presenter.getParkings();
    }

    @Override
    public void initMap(final List<LightParking> parkingModels) {
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mainMap = googleMap;
                if (!checkForLocationPermission()) {
                    ActivityCompat.requestPermissions(HomeActivity.this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION_REQUEST_CODE);
                }

                // Add some markers to the map, and add a data object to each marker.

                for (LightParking parkingModel : parkingModels) {
                    if (parkingModel != null) {
                        LatLng latLng = new LatLng(parkingModel.getLatitude(), parkingModel.getLongitude());
                        Marker marker = googleMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title(parkingModel.getNomParking()));

                        marker.setTag(parkingModel);
                    }
                }

                // Set a listener for marker click.
                googleMap.setOnMarkerClickListener(HomeActivity.this);
            }
        });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    private boolean checkForLocationPermission() {
        if (mainMap != null &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED){
            Log.d("PERMISSIONS", "PERMISSIONS ACCESS FINE LOCATION OK");

            mainMap.setMyLocationEnabled(true);
            mainMap.setOnMyLocationButtonClickListener(this);
            mainMap.setOnMyLocationClickListener(this);
            return true;
        }

        Log.d("PERMISSIONS", "PERMISSIONS ACCESS FINE LOCATION REFUSE");
        return false;
    }



    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_LOCATION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkForLocationPermission();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION_REQUEST_CODE);
                }
                return;
            }
        }
    }
}
