package com.example.dmonunu.parkinnantes.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.activities.MapPresenterImpl;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.models.ParkingDataBase;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ParkingAdapter extends ArrayAdapter<LightParking> {

    @BindView(R.id.parking_adapter_name)
    TextView parkingName;

    @BindView(R.id.parking_adapter_address)
    TextView parkingAdress;

    @BindView(R.id.parking_adapter_nb_available_places)
    TextView parkingNbAvPlaces;

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

    @BindView(R.id.favorite)
    ImageButton favorite;

    public ParkingAdapter(Context context, List<LightParking> parkings){
        super(context, -1, parkings);
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

        LightParking parkingDetails = getItem(position);

        if (parkingDetails != null) {
            this.parkingName.setText(parkingDetails.getNomParking());
            final String address = parkingDetails.getAdresse();
            if (address != null) {
                this.parkingAdress.setText(address);
            }
            int placeDisponible = parkingDetails.getNbPlaceDispo();
            int placesMax = parkingDetails.getCapaciteTotale();
            this.parkingNbAvPlaces.setText(String.valueOf(placeDisponible));
            this.parkingNbMaxPlaces.setText(String.valueOf(placesMax));
            this.parkingPlacesProgBar.setMax(100);
            if (placesMax != 0) {
                double percentage = getPercentage(placeDisponible, placesMax);
                this.parkingPlacesProgBar.setProgress((int) percentage);
            }
            // setPaymentOptions(parkingDetails);

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
