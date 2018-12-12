package com.example.dmonunu.parkinnantes.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

public class ParkingDetailsActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {

    @BindView(R.id.parking_details_name)
    TextView parkingNom;

    @BindView(R.id.parking_details_adress)
    TextView parkingAdresse;

    @BindView(R.id.parking_details_away_places)
    TextView parkingCapacite;

    @BindView(R.id.parking_details_favorites_button)
    ImageButton parkingFavButton;

    @BindView(R.id.credit_card_imageview)
    ImageView creditCardImageView;

    @BindView(R.id.money_imageview)
    ImageView cashImageView;

    @BindView(R.id.total_gr_imageview)
    ImageView totalGrImageView;

    @BindView(R.id.cheque_imageview)
    ImageView chequeImageView;

    @BindView(R.id.parking_details_ligne1_imageview)
    ImageView ligne1Img;

    @BindView(R.id.parking_details_ligne2_imageview)
    ImageView ligne2Img;

    @BindView(R.id.parking_details_ligne3_imageview)
    ImageView ligne3Img;

    @BindView(R.id.parking_details_ligne4_imageview)
    ImageView ligne4Img;

    LightParking selectedParking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_details);
        ButterKnife.bind(this);

        final StreetViewPanoramaFragment streetViewPanoramaFragment =
                (StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id
                        .parking_details_mapView);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
        this.selectedParking = (LightParking) getIntent().getSerializableExtra("SelectedParking");
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
        final double latitude = this.selectedParking.getLatitude();
        final double longitude = this.selectedParking.getLongitude();
        panorama.setPosition(new LatLng(latitude, longitude));
    }
}
