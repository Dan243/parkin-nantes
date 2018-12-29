package com.example.dmonunu.parkinnantes.activities;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.utilities.DrawerUtil;
import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.utilities.MarkerMap;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
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
    private LocationManager mLocationManager;
    private ClusterManager<MyItem> mClusterManager;

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            double currentLatitude = location.getLatitude();
            double currentLongitude = location.getLongitude();

            LatLng latLng = new LatLng(currentLatitude, currentLongitude);
            Log.i("XXXXXXXXXXX","XXXXXXXXXXXXXXXXXXxx");
            float zoomLevel = 16.0f; //This goes up to 21
            mainMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mainMap.animateCamera( CameraUpdateFactory.zoomTo(zoomLevel) );
            Log.i("XXXXXXXXXXX","XXXXXXXXXXXXXXXXXXxx");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

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
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

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
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 300,
                        300, mLocationListener);
                for (LightParking parkingModel : parkingModels) {
                    if (parkingModel != null) {
                        MarkerMap markerMap = new MarkerMap(parkingModel, googleMap);
                        Marker marker = markerMap.createMarker();
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
