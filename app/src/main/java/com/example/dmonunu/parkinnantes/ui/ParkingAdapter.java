package com.example.dmonunu.parkinnantes.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.activities.ParkingPresenter;
import com.example.dmonunu.parkinnantes.activities.ParkingPresenterImpl;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.models.ParkingDataBase;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParkingAdapter extends ArrayAdapter<LightParking> {

    @BindView(R.id.parking_adapter_name)
    TextView parkingName;

    @BindView(R.id.parking_adapter_address)
    TextView parkingAdress;


    @BindView(R.id.parking_adapter_nb_max_places)
    TextView parkingNbMaxPlaces;

    @BindView(R.id.parking_adapter_progressBar)
    ProgressBar parkingPlacesProgBar;

    @BindView(R.id.parking_adapter_credit_card_imageview)
    ImageView credCardImg;

    @BindView(R.id.parking_adapter_cash_imageview)
    ImageView cashImg;

    @BindView(R.id.parking_adapter_total_imageview)
    ImageView totalImg;

    @BindView(R.id.parking_adapter_cheque_imageview)
    ImageView chequeImg;

    @BindView(R.id.heure_debut)
    TextView heureDebut;

    @BindView(R.id.heure_fin)
    TextView heureFin;

    @BindView(R.id.favorite)
    ToggleButton favorite;

    private Context context;


    public ParkingAdapter(Context context, List<LightParking> parkings){
        super(context, -1, parkings);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View actualView = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            actualView = inflater.inflate(R.layout.parking_adapter, parent, false);
        }
        ButterKnife.bind(this, actualView);

        LightParking lightParking = getItem(position);

        if (lightParking != null) {
            this.parkingName.setText(lightParking.getNomParking());
            final String address = lightParking.getAdresse();
            if (address != null) {
                this.parkingAdress.setText(address);
            }
            int placeDisponible = lightParking.getNbPlaceDispo();
            int placesMax = lightParking.getCapaciteTotale();
            //this.parkingNbAvPlaces.setText(String.valueOf(placeDisponible));
            this.parkingNbMaxPlaces.setText(String.valueOf(placesMax));
            this.parkingPlacesProgBar.setMax(100);
            if (placesMax != 0) {
                double percentage = getPercentage(placeDisponible, placesMax);
                this.parkingPlacesProgBar.setProgress((int) percentage);
                this.parkingNbMaxPlaces.setText(String.valueOf(Math.round(percentage) + "%"));

            }

            favorite.setChecked(false);
            favorite.setText(null);
            favorite.setTextOn(null);
            favorite.setTextOff(null);
            favorite.setBackgroundDrawable(context.getDrawable(R.drawable.ic_40dp_star));
            favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        favorite.setBackgroundDrawable(context.getDrawable(R.drawable.ic_40dp_yellow_star));
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ParkingDataBase.getAppDatabase(context).lightParkingDao().setFavorite(true);
                            }
                        }).start();
                    } else {
                        favorite.setBackgroundDrawable(context.getDrawable(R.drawable.ic_40dp_star));
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ParkingDataBase.getAppDatabase(context).lightParkingDao().removeFavorite(false);
                            }
                        }).start();
                    }
                }
            });

            heureDebut.setText(lightParking.getHeureDebut());
            heureFin.setText(" à " + lightParking.getHeureFin());
        }




        return actualView;
    }

    private double getPercentage(int currentValue, int refValue) {
        int value = refValue - currentValue;
        double div = ((double) value / (double) refValue);
        return (div * 100);
    }
    /*
    private void setPaymentOptions(final LightParking parking) {
        this.setImageVisibility(parking.isCreditCardAvailable(), this.credCardImg);
        this.setImageVisibility(parking.isCashAvailable(), this.cashImg);
        this.setImageVisibility(parking.isTotalGRCardAvailable(), this.totalImg);
        this.setImageVisibility(parking.isChequeAvailable(), this.chequeImg);
    }
    */
    private static void setImageVisibility(final boolean isServiceAvailable, final ImageView
            imageView) {
        int result = ImageView.GONE;
        if (isServiceAvailable) {
            result = ImageView.VISIBLE;
        }
        imageView.setVisibility(result);
    }

}
