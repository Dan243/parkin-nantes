package com.example.dmonunu.parkinnantes.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.activities.ParkingDetailsActivity;
import com.example.dmonunu.parkinnantes.models.LightParking;

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
/*
        holder.favorite = new MaterialFavoriteButton.Builder(activity.getApplicationContext())
                .create();
        holder.favorite.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener() {
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                        //
                    }
                });
                */
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



        private LightParking currentParking;

        public MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

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
                    parkingNbMaxPlaces.setText(String.valueOf(Math.round(100 - percentage) + "%"));
                }
                heureDebut.setText(parking.getHeureDebut());
                heureFin.setText(" à " + parking.getHeureFin());


            }
        }

    }

    private double getPercentage(int currentValue, int refValue) {
        int value = refValue - currentValue;
        double div = ((double) value / (double) refValue);
        return (div * 100);
    }

}