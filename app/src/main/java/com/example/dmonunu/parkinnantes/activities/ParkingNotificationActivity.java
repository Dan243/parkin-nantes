package com.example.dmonunu.parkinnantes.activities;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.event.EventBusManager;
import com.example.dmonunu.parkinnantes.event.StartNotifSuccessEvent;
import com.example.dmonunu.parkinnantes.services.ParkingNotificationService;
import com.example.dmonunu.parkinnantes.utilities.DrawerUtil;
import com.squareup.otto.Subscribe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ParkingNotificationActivity extends AppCompatActivity {

    @BindView(R.id.startButton)
    Button mStartButton;

    @BindView(R.id.stopButton)
    Button mStopButton;

    @BindView(R.id.notiftoolbar)
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_notification);
        ButterKnife.bind(this);
        toolbar.setTitle(" Gérer les notifications");
        DrawerUtil.getDrawer(this,toolbar);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {

        }
        if (isMyServiceRunning(ParkingNotificationService.class)) {
            mStartButton.setEnabled(false);
            mStopButton.setEnabled(true);
        }
        else {
            mStartButton.setEnabled(true);
            mStopButton.setEnabled(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBusManager.BUS.register(this);
    }

    @Override
    protected void onPause() {
        EventBusManager.BUS.unregister(this);
        super.onPause();
    }

    public void startService(View v) {
        mStartButton.setEnabled(false);
        Intent serviceIntent = new Intent(this, ParkingNotificationService.class);
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService(View v) {
        if (isMyServiceRunning(ParkingNotificationService.class)) {
            mStopButton.setEnabled(false);
            Intent serviceIntent = new Intent(this, ParkingNotificationService.class);
            stopService(serviceIntent);
            Toast.makeText(this, "Notifications désactivées", Toast.LENGTH_SHORT).show();
            mStartButton.setEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mStartButton.setEnabled(true);
                mStopButton.setEnabled(true);
            }
            else {

            }
        }
        else {
            mStartButton.setEnabled(false);
            mStopButton.setEnabled(false);
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Subscribe
    public void startNotifSuccess(StartNotifSuccessEvent event) {
        Toast.makeText(this, "Notifications activées", Toast.LENGTH_SHORT).show();
        mStopButton.setEnabled(true);
    }
}
