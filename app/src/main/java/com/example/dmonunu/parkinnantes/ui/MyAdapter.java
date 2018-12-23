package com.example.dmonunu.parkinnantes.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;


import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.activities.ParkingDetailsActivity;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.utilities.ParkingParser;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {



    private List<LightParking> listparking;
    private Activity activity;

    public MyAdapter(List<LightParking> parkings, Activity activity){
        this.listparking = parkings;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return listparking.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.parking_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final LightParking lightParking = listparking.get(position);
        holder.bind(lightParking);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent parkingIntent = new Intent(activity, ParkingDetailsActivity.class);
                parkingIntent.putExtra("SelectedParking", lightParking);
                activity.startActivity(parkingIntent);
            }
        });


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.parking_adapter_name)
        TextView parkingName;

        @BindView(R.id.parking_adapter_address)
        TextView parkingAdress;

        @BindView(R.id.parking_adapter_progressBar)
        ProgressBar parkingPlacesProgBar;

        @BindView(R.id.parking_adapter_nb_max_places)
        TextView parkingNbMaxPlaces;

        @BindView(R.id.heure_debut)
        TextView heureDebut;

        @BindView(R.id.heure_fin)
        TextView heureFin;

        @BindView(R.id.parking_adapter_credit_card_imageview)
        ImageView credCardImg;

        @BindView(R.id.parking_adapter_cash_imageview)
        ImageView cashImg;

        @BindView(R.id.parking_adapter_total_imageview)
        ImageView totalImg;

        @BindView(R.id.parking_adapter_cheque_imageview)
        ImageView chequeImg;

        @BindView(R.id.favorite)
        ToggleButton favorite;

        private LightParking currentParking;

        public MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            favorite.setText(null);
            favorite.setTextOn(null);
            favorite.setTextOff(null);
        }

        public void bind(LightParking parking){

            if (parking != null){
                currentParking = parking;
                parkingName.setText(parking.getNomParking());
                parkingAdress.setText(parking.getAdresse());
                int placeDisponible = parking.getNbPlaceDispo();
                int placesMax = parking.getCapaciteTotale();

                parkingPlacesProgBar.setMax(100);
                if (placesMax != 0) {
                    double percentage = getPercentage(placeDisponible, placesMax);
                    parkingPlacesProgBar.setProgress((int) percentage);
                    parkingNbMaxPlaces.setText(String.valueOf( placeDisponible + " / " + placesMax));
                }
                heureDebut.setText(parking.getHeureDebut());
                heureFin.setText(" à " + parking.getHeureFin());
                setPaymentOptions(parking);

            }
        }
        private void setPaymentOptions(final LightParking parking) {
            ParkingParser.setImageVisibility(parking.isCreditCardAvailable(), this.credCardImg);
            ParkingParser.setImageVisibility(parking.isCashAvailable(), this.cashImg);
            ParkingParser.setImageVisibility(parking.isTotalGRCardAvailable(), this.totalImg);
            ParkingParser.setImageVisibility(parking.isChequeAvailable(), this.chequeImg);
        }
    }

    private double getPercentage(int currentValue, int refValue) {
        int value = refValue - currentValue;
        double div = ((double) value / (double) refValue);
        return (div * 100);
    }



}