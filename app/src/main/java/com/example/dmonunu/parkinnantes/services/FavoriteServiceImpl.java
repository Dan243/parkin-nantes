package com.example.dmonunu.parkinnantes.services;

import android.content.Context;
import android.os.AsyncTask;

import com.example.dmonunu.parkinnantes.event.EventBusManager;
import com.example.dmonunu.parkinnantes.event.SearchResultEvent;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.models.ParkingDataBase;

import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {

    private Context context;

    public FavoriteServiceImpl(Context context){
        this.context = context;
    }

    @Override
    public void setFavoriteInRoom(String fav) {
        new RoomAsyncTask().execute(fav);
    }

    @Override
    public void unSetFavoriteInRoom(String fav) {
        new RoomAsyncTask2().execute();
    }

    @Override
    public void getListOfFavoriteInRoom(List<String> fav) {
        new RoomAsyncTask3().execute();
    }

    private class RoomAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(final String... params) {
             ParkingDataBase.getAppDatabase(context).lightParkingDao().setFavorite(params[0]);
             return null;
        }
    }

    private class RoomAsyncTask2 extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(final String... params) {
            ParkingDataBase.getAppDatabase(context).lightParkingDao().removeFavorite(params[0]);
            return null;
        }
    }

    private class RoomAsyncTask3 extends AsyncTask<Void, Void, List<LightParking>> {
        @Override
        protected List<LightParking> doInBackground(Void... voids) {
            return ParkingDataBase.getAppDatabase(context).lightParkingDao().getFavoriteParkings();
        }

        @Override
        protected void onPostExecute(List<LightParking> lightParkings) {
            super.onPostExecute(lightParkings);
            if (lightParkings != null) {
            }
        }
    }
}
