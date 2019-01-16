package com.example.dmonunu.parkinnantes.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.models.ParkingDataBase;
import com.example.dmonunu.parkinnantes.ui.MyAdapter;
import com.example.dmonunu.parkinnantes.utilities.DrawerUtil;
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

    @BindView(R.id.detailtoolbar)
    androidx.appcompat.widget.Toolbar toolbar;

    @BindView(R.id.parking_details_bike_places)
    TextView parkingCapVelo;

    @BindView(R.id.parkingpres)
    TextView presentation;

    @BindView(R.id.parking_details_moto_places)
    TextView parkingmoto;

    @BindView(R.id.parking_details_accessible_places)
    TextView parkingaccessible;

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

    @BindView(R.id.parking_details_favorites_button)
    ImageView favorite;

    @BindView(R.id.parking_telephone)
    TextView telephone;

    @BindView(R.id.parking_website)
    TextView website;

    LightParking selectedParking;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_details);
        ButterKnife.bind(this);

        toolbar.setTitle("Détail du parking");
        DrawerUtil.getDrawer(this,toolbar);
        this.context = this.getApplicationContext();
        final StreetViewPanoramaFragment streetViewPanoramaFragment =
                (StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id
                        .parking_details_mapView);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

        this.selectedParking = (LightParking) getIntent().getSerializableExtra("SelectedParking");
        this.setPublicsTransports();
        this.setPaymentOptions();
        this.setTextViewFields();


        if (!selectedParking.isFavorite()){
            favorite.setBackgroundResource(R.drawable.star_grey);
            favorite.setTag(R.drawable.star_grey);
        } else {
            favorite.setBackgroundResource(R.drawable.star_yellow);
            favorite.setTag(R.drawable.star_yellow);
        }
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer fTag = (Integer) favorite.getTag();
                fTag = fTag == null ? R.drawable.star_grey : fTag;
                switch(fTag) {
                    case R.drawable.star_grey:
                        selectedParking.setFavorite(true);
                        favorite.setBackgroundResource(R.drawable.star_yellow);
                        favorite.setTag(R.drawable.star_yellow);
                        new ParkingDetailsActivity.RoomFavoriTrueAsyncTask().execute(selectedParking);
                        break;
                    default:
                        selectedParking.setFavorite(false);
                        favorite.setBackgroundResource(R.drawable.star_grey);
                        favorite.setTag(R.drawable.star_grey);
                        new ParkingDetailsActivity.RoomFavoriFalseAsyncTask().execute(selectedParking);
                        break;
                }
            }
        });

    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
        final double latitude = this.selectedParking.getLatitude();
        final double longitude = this.selectedParking.getLongitude();
        panorama.setPosition(new LatLng(latitude, longitude));
    }

    private void setTextViewFields() {
        this.parkingNom.setText(this.selectedParking.getNomParking());
        this.parkingAdresse.setText(this.selectedParking.getAdresse());
        this.parkingCapacite.setText(this.selectedParking.getNbPlaceDispo() + "");
        this.parkingCapVelo.setText(this.selectedParking.getStationnement_velo()  + "");
        this.presentation.setText(this.selectedParking.getPresentation());
        this.parkingmoto.setText(this.selectedParking.getCapacite_moto() + "");
        this.parkingaccessible.setText(this.selectedParking.getCapacite_pmr() + "");
        this.telephone.setText(this.selectedParking.getTelephone());
        this.website.setText("http://www.parkings-nantes.fr");
    }

    private void setPaymentOptions() {
        if (!this.selectedParking.isCreditCardAvailable()) {
            this.creditCardImageView.setVisibility(ImageView.GONE);
        }
        if (!this.selectedParking.isCashAvailable()) {
            this.cashImageView.setVisibility(ImageView.GONE);
        }
        if (!this.selectedParking.isTotalGRCardAvailable()) {
            this.totalGrImageView.setVisibility(ImageView.GONE);
        }
        if (!this.selectedParking.isChequeAvailable()) {
            this.chequeImageView.setVisibility(ImageView.GONE);
        }
    }

    private void setPublicsTransports() {
        if (!this.selectedParking.isLigneOneNear()) {
            this.ligne1Img.setVisibility(ImageView.GONE);
        }
        if (!this.selectedParking.isLigneTwoNear()) {
            this.ligne2Img.setVisibility(ImageView.GONE);
        }
        if (!this.selectedParking.isLigneThreeNear()) {
            this.ligne3Img.setVisibility(ImageView.GONE);
        }
        if (!this.selectedParking.isLigneFourNear()) {
            this.ligne4Img.setVisibility(ImageView.GONE);
        }
    }

    private class RoomFavoriTrueAsyncTask extends AsyncTask<LightParking, Void, Void> {
        @Override
        protected Void doInBackground(LightParking... lightParkings) {
            ParkingDataBase.getAppDatabase(context).lightParkingDao().setFavorite(lightParkings[0].getIdobj(), true);
            return null;
        }
    }

    private class RoomFavoriFalseAsyncTask extends AsyncTask<LightParking, Void, Void> {
        @Override
        protected Void doInBackground(LightParking... lightParkings) {
            ParkingDataBase.getAppDatabase(context).lightParkingDao().setFavorite(lightParkings[0].getIdobj(), false);
            return null;
        }
    }

}
