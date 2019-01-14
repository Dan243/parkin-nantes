package com.example.dmonunu.parkinnantes.services;

import android.Manifest;
import android.app.Notification;
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
import android.util.Log;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.activities.ParkingNotificationActivity;
import com.google.android.gms.maps.model.LatLng;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.ServiceCompat;

/**
 * Created by Zheyu XIE.
 */
public class ParkingNotificationService extends Service {

    public static final String CHANNEL_ID = "parkingServiceChannel";
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double currentLatitude = location.getLatitude();
                double currentLongitude = location.getLongitude();
                Location currentLocation = new Location("Current Location");
                currentLocation.setLatitude(currentLatitude);
                currentLocation.setLongitude(currentLongitude);
                Location parkingTourDeBretagne = new Location("Parking Tour Bretagne");
                parkingTourDeBretagne.setLatitude(47.2178314095);
                parkingTourDeBretagne.setLongitude(-1.55823146051);
                float dist = currentLocation.distanceTo(parkingTourDeBretagne);
                Intent notificationIntent = new Intent(ParkingNotificationService.this, ParkingNotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(ParkingNotificationService.this, 0, notificationIntent, 0);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ParkingNotificationService.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.star_yellow)
                        .setContentTitle("Parking Notification Service")
                        .setContentText("Vous êtes de " + dist + "m à Parking Tour Bretagne")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent);
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
}
