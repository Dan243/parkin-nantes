package com.example.dmonunu.parkinnantes.activities;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dmonunu.parkinnantes.Utilities.DrawerUtil;
import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.models.BaseResponse;
import com.example.dmonunu.parkinnantes.models.DispoModel;
import com.example.dmonunu.parkinnantes.models.HoraireModel;
import com.example.dmonunu.parkinnantes.models.ParkingModel;
import com.example.dmonunu.parkinnantes.models.ParkingDataBase;
import com.example.dmonunu.parkinnantes.models.Record;
import com.example.dmonunu.parkinnantes.services.BaseService;
import com.example.dmonunu.parkinnantes.services.HoraireDao;
import com.example.dmonunu.parkinnantes.services.ParkingSearchRESTService;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.room.Room;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends FragmentActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback {

    private static final String DATABASE_NAME = "parking_db";
    private ParkingSearchRESTService parkingService ;
    private ParkingDataBase parkingDataBase;
    private static final String TAG = HomeActivity.class.getName();
    private GoogleMap mainMap;
    private static final int MY_LOCATION_REQUEST_CODE = 9401;

    @BindView(R.id.toolbar)
    public Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        parkingDataBase = Room.databaseBuilder(getApplicationContext(), ParkingDataBase.class, DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build();
        toolBar.setTitle(getResources().getString(R.string.tournament));

        DrawerUtil.getDrawer(this,toolBar);

        this.parkingService = BaseService.getRetrofitInstance().create(ParkingSearchRESTService.class);
        this.parkingService.recupTousLesParkings("244400404_parkings-publics-nantes").enqueue(new Callback<BaseResponse<ParkingModel>>() {
            @Override
            public void onResponse(Call<BaseResponse<ParkingModel>> call, Response<BaseResponse<ParkingModel>> response) {

                List<Record<ParkingModel>> records = response.body().getRecords();
                List<ParkingModel> parkingModelList = new ArrayList<>();
                for(final Record<ParkingModel> record : records ){
                    parkingModelList.add(record.getFields());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            parkingDataBase.parkingDao().createParking(record.getFields());
                        }
                    }).start();
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<ParkingModel> parkingModels = parkingDataBase.parkingDao().getParkings();
                        Log.d(TAG, "retrieved from database ok");
                    }
                }).start();

                Log.d(TAG, "response parking ok");
            }

            @Override
            public void onFailure(Call<BaseResponse<ParkingModel>> call, Throwable t) {
                
            }
        });

        this.parkingService.recupToutesLesDispos("244400404_parkings-publics-nantes-disponibilites").enqueue(new Callback<BaseResponse<DispoModel>>() {
            @Override
            public void onResponse(Call<BaseResponse<DispoModel>> call, Response<BaseResponse<DispoModel>> response) {
                List<Record<DispoModel>> records = response.body().getRecords();
                List<DispoModel> parkingModelList = new ArrayList<>();
                for(final Record<DispoModel> record : records ){
                    parkingModelList.add(record.getFields());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            parkingDataBase.dispoDao().createDispoDao(record.getFields());
                        }
                    }).start();
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<DispoModel> parkingModels = parkingDataBase.dispoDao().getDisponibilite();
                        Log.d(TAG, "retrieved from database ok");
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<BaseResponse<DispoModel>> call, Throwable t) {

            }
        });

        this.parkingService.recupTousLesHoraires("244400404_parkings-publics-nantes-disponibilites").enqueue(new Callback<BaseResponse<HoraireModel>>() {
            @Override
            public void onResponse(Call<BaseResponse<HoraireModel>> call, Response<BaseResponse<HoraireModel>> response) {
                List<Record<HoraireModel>> records = response.body().getRecords();
                List<HoraireModel> parkingModelList = new ArrayList<>();
                for(final Record<HoraireModel> record : records ){
                    parkingModelList.add(record.getFields());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            parkingDataBase.horaireDao().createHoraire(record.getFields());
                        }
                    }).start();
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<HoraireModel> parkingModels = parkingDataBase.horaireDao().getHoraire();
                        Log.d(TAG, "retrieved from database ok");
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<BaseResponse<HoraireModel>> call, Throwable t) {

            }
        });
        
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap map) {
        mainMap = map;
        if (!checkForLocationPermission()) {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION_REQUEST_CODE);
        }
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
