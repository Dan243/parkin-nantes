package com.example.dmonunu.parkinnantes.activities;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dmonunu.parkinnantes.event.EventBusManager;
import com.example.dmonunu.parkinnantes.event.SaveEvent;
import com.example.dmonunu.parkinnantes.event.SearchResultEvent;
import com.example.dmonunu.parkinnantes.models.LightParking;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.models.ParkingDataBase;
import com.example.dmonunu.parkinnantes.services.ResearchService;
import com.example.dmonunu.parkinnantes.services.ResearchServiceImpl;
import com.example.dmonunu.parkinnantes.utilities.ClusterItemImpl;
import com.example.dmonunu.parkinnantes.utilities.CustomInfoWindowGoogleMap;
import com.example.dmonunu.parkinnantes.utilities.DrawerUtil;
import com.example.dmonunu.parkinnantes.utilities.MarkerMap;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
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
    private Location myLocationInit;
    private ResearchService researchService;

    @BindView(R.id.toolbar)
    Toolbar toolBar;

    @BindView(R.id.search_edittext)
    EditText mSearchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        toolBar.setTitle("Carte des parkings");
        DrawerUtil.getDrawer(this,toolBar);
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
                myLocationInit = location;
                double currentLatitude = location.getLatitude();
                double currentLongitude = location.getLongitude();
                LatLng latLng = new LatLng(currentLatitude, currentLongitude);
                float zoomLevel = 15.0f; //This goes up to 21
                mainMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
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

        researchService = new ResearchServiceImpl(getApplicationContext());
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    researchService.searchParkingByNameOrAddress("");
                }
                else {
                    researchService.searchParkingByNameOrAddress(editable.toString());
                }
            }
        });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, "Localisation actuelle :\n" + marker.getTag() + ", " + marker.getTag(), Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Localisation actuelle :\n" + location.getLatitude() + ", " + location.getLongitude(), Toast.LENGTH_LONG).show();
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

        myClusterManager = new ClusterManager<ClusterItemImpl>(this, mainMap);
        mainMap.setOnCameraIdleListener(myClusterManager);
        mainMap.setOnMarkerClickListener(myClusterManager);
        mainMap.setInfoWindowAdapter(myClusterManager.getMarkerManager());
    }

    private void addItems(List<LightParking> parkingModels) {
        myClusterManager.clearItems();
        for (LightParking parkingModel : parkingModels) {
            if (parkingModel != null) {
                ClusterItemImpl offsetItem = new ClusterItemImpl(parkingModel.getLatitude(), parkingModel.getLongitude(), parkingModel.getNbPlaceDispo(), parkingModel.getNomParking());
                myClusterManager.addItem(offsetItem);
                //MarkerMap markerMap = new MarkerMap(parkingModel, googleMap);
                //Marker marker = markerMap.createMarker();
            }
        }
        myClusterManager.getMarkerCollection().setOnInfoWindowAdapter(
                new CustomInfoWindowGoogleMap(this));
        myClusterManager.setRenderer(new MarkerMap(this.getApplicationContext(), mainMap, myClusterManager));
        animateCluster(parkingModels);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mainMap = googleMap;
        mainMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
            @Override
            public void onInfoWindowClick(Marker arg0) {
                LightParking ligthParking = ParkingDataBase.getAppDatabase(HomeActivity.this).lightParkingDao().findParkingByName(arg0.getTitle());
                final Intent parkingIntent = new Intent(HomeActivity.this, ParkingDetailsActivity.class);
                parkingIntent.putExtra("SelectedParking", ligthParking);
                startActivity(parkingIntent);
            }

        });
        mainMap.getUiSettings().setZoomControlsEnabled(true);
        mainMap.setOnMarkerClickListener(this);
        mainMap.setMyLocationEnabled(true);
        mainMap.setOnMyLocationButtonClickListener(this);
        mainMap.setOnMyLocationClickListener(this);
        setUpClusterer(parkings);

        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.google_dark_theme));

            if (!success) {
                Log.e("WORK", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("WORK", "Can't find style. Error: ", e);
        }

    }

    @Subscribe
    public void saveSuccess(SaveEvent event) {
        presenter.getParkingsFromRoom();
    }

    @Subscribe
    public void searchResult(SearchResultEvent event) {
        parkings = event.getParkings();
        addItems(parkings);
    }


    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }

    private void animateCluster(List<LightParking> parkings) {
        if (parkings.size() > 0) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (LightParking parking : parkings) {
                builder.include(new LatLng(parking.getLatitude(), parking.getLongitude()));
            }
            LatLngBounds bounds = builder.build();

            // padding in pixels
            int padding = 100;
            // use animateCamera if animation is required
            mainMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));

            if (parkings.size() == 1) {
                // you might want to a custom zoom level if there is only 1 item
                mainMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            }
        }
    }
}
