package com.example.dmonunu.parkinnantes.activities;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dmonunu.parkinnantes.event.EventBusManager;
import com.example.dmonunu.parkinnantes.event.SaveEvent;
import com.example.dmonunu.parkinnantes.event.SearchResultEvent;
import com.example.dmonunu.parkinnantes.models.LightParking;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.utilities.ClusterItemImpl;
import com.example.dmonunu.parkinnantes.utilities.DrawerUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterManager;
import com.squareup.otto.Subscribe;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends FragmentActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {
    private GoogleMap mainMap;
    private static final int MY_LOCATION_REQUEST_CODE = 9401;
    private ParkingPresenter presenter;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private ClusterManager<ClusterItemImpl> myClusterManager;
    private List<LightParking> parkings;

    @BindView(R.id.toolbar)
    public Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        this.presenter = new ParkingPresenterImpl(getApplicationContext());
        this.presenter.getParkings();
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(HomeActivity.this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION_REQUEST_CODE);
        }
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                double currentLatitude = location.getLatitude();
                double currentLongitude = location.getLongitude();
                LatLng latLng = new LatLng(currentLatitude, currentLongitude);
                float zoomLevel = 16.0f; //This goes up to 21
                mainMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mainMap.animateCamera(CameraUpdateFactory.zoomTo(zoomLevel));
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
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000000,
                10000000, mLocationListener);
        toolBar.setTitle(getResources().getString(R.string.tournament));
        DrawerUtil.getDrawer(this,toolBar);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Localisation actuelle :\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "Localisation boutton cliqué", Toast.LENGTH_SHORT).show();
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


    @Override
    protected void onResume() {
        // Do NOT forget to call super.onResume()
        super.onResume();

        // Register to Event bus : now each time an event is posted, the activity will receive it if it is @Subscribed to this event
        EventBusManager.BUS.register(this);
    }

    @Override
    protected void onPause() {
        // Unregister from Event bus : if event are posted now, the activity will not receive it
        EventBusManager.BUS.unregister(this);

        mLocationManager.removeUpdates(mLocationListener);

        // Do NOT forget to call super.onPause()
        super.onPause();
    }

    private void setUpClusterer(List<LightParking> parkingModels) {
        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        myClusterManager = new ClusterManager<ClusterItemImpl>(this, mainMap);

        // Add cluster items (markers) to the cluster manager.
        addItems(parkingModels);

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mainMap.setOnCameraIdleListener(myClusterManager);
        mainMap.setOnMarkerClickListener(myClusterManager);
    }

    private void addItems(List<LightParking> parkingModels) {
        for (LightParking parkingModel : parkingModels) {
            if (parkingModel != null) {
                ClusterItemImpl offsetItem = new ClusterItemImpl(parkingModel.getLatitude(), parkingModel.getLongitude());
                myClusterManager.addItem(offsetItem);
                //MarkerMap markerMap = new MarkerMap(parkingModel, googleMap);
                //Marker marker = markerMap.createMarker();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mainMap = googleMap;
        mainMap.getUiSettings().setZoomControlsEnabled(true);
        mainMap.setOnMarkerClickListener(this);
        mainMap.setMyLocationEnabled(true);
        mainMap.setOnMyLocationButtonClickListener(this);
        mainMap.setOnMyLocationClickListener(this);
    }

    @Subscribe
    public void saveSuccess(SaveEvent event) {
        presenter.getParkingsFromRoom();
    }

    @Subscribe
    public void searchResult(SearchResultEvent event) {
        parkings = event.getParkings();
        setUpClusterer(parkings);
    }
}
