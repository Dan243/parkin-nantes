package com.example.dmonunu.parkinnantes.services;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.activities.ParkingNotificationActivity;
import com.example.dmonunu.parkinnantes.activities.ParkingPresenter;
import com.example.dmonunu.parkinnantes.activities.ParkingPresenterImpl;
import com.example.dmonunu.parkinnantes.event.EventBusManager;
import com.example.dmonunu.parkinnantes.event.SaveEvent;
import com.example.dmonunu.parkinnantes.event.SearchResultEvent;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.models.ParkingDataBase;
import com.squareup.otto.Subscribe;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/**
 * Created by Zheyu XIE.
 */
public class ParkingNotificationService extends Service {

    public static final String CHANNEL_ID = "parkingServiceChannel";
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private ParkingPresenter presenter;
    private List<LightParking> parkings;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        presenter = new ParkingPresenterImpl(getApplicationContext());
        parkings = ParkingDataBase.getAppDatabase(getApplicationContext()).lightParkingDao().getParkings();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        EventBusManager.BUS.register(this);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                presenter.getParkings();
                double currentLatitude = location.getLatitude();
                double currentLongitude = location.getLongitude();
                Location currentLocation = new Location("Current Location");
                currentLocation.setLatitude(currentLatitude);
                currentLocation.setLongitude(currentLongitude);
                LightParking parking = findNearestParkingAvailable(currentLocation);
                Intent notificationIntent = new Intent(ParkingNotificationService.this, ParkingNotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(ParkingNotificationService.this, 0, notificationIntent, 0);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ParkingNotificationService.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.star_yellow)
                        .setContentTitle("Parking Notification Service")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent);
                if(parking != null) {
                    String text = "Vous êtes " + distanceParking(currentLocation, parking) + " m de " + parking.getNomParking() + " dont " + parking.getNbPlaceDispo() + " places disponibles";
                    mBuilder.setContentText(text)
                            .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText(text));
                } else {
                    mBuilder.setContentText("Pas de parking proche et disponible");
                }
                startForeground(1, mBuilder.build());
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
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 150, mLocationListener);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        EventBusManager.BUS.unregister(this);
        super.onDestroy();
        mLocationManager.removeUpdates(mLocationListener);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Parking Service Channel";
            String description = "a parking service channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Subscribe
    public void searchResult(SearchResultEvent event) {
        parkings = event.getParkings();
    }

    @Subscribe
    public void saveSuccess(SaveEvent event) {
        presenter.getParkingsFromRoom();
    }

    public LightParking findNearestParkingAvailable(Location location) {
        LightParking resultParking = null;
        float minDist = 500;
        for (LightParking parking: parkings) {
            float dist = distanceParking(location, parking);
            if (dist < 500 && parking.getNbPlaceDispo() > 0 && dist < minDist) {
                minDist = dist;
                resultParking = parking;
            }
        }
        return resultParking;
    }

    public float distanceParking(Location location, LightParking parking) {
        Location parkingLocation = new Location(parking.getNomParking());
        parkingLocation.setLatitude(parking.getLatitude());
        parkingLocation.setLongitude(parking.getLongitude());
        float dist = location.distanceTo(parkingLocation);
        return dist;
    }
}
