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
    private boolean favorite;

    public FavoriteServiceImpl(Context context){
        this.context = context;
        this.favorite = false;
    }

    @Override
    public void setFavoriteInRoom(String fav) {
        new RoomAsyncTask().execute(fav);
    }

    @Override
    public void removeFavoriteInRoom(String fav) {
        new RoomAsyncTask1().execute(fav);
    }

    @Override
    public boolean isFavorite(String fav) {
        new isFavorite().execute(fav);
        return this.favorite;
    }

    private class RoomAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(final String... params) {
             ParkingDataBase.getAppDatabase(context).lightParkingDao().setFavorite(params[0]);
             return null;
        }

    }

    private class RoomAsyncTask1 extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(final String... params) {
            ParkingDataBase.getAppDatabase(context).lightParkingDao().removeFavorite(params[0]);
            return null;
        }

    }

    public class isFavorite extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(final String... params) {

            return ParkingDataBase.getAppDatabase(context).lightParkingDao().isFavorite(params[0]) ;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            FavoriteServiceImpl.this.favorite = aBoolean;
        }
    }

}
