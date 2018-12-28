package com.example.dmonunu.parkinnantes.services;

import android.content.Context;
import android.os.AsyncTask;

import com.example.dmonunu.parkinnantes.event.EventBusManager;
import com.example.dmonunu.parkinnantes.event.SearchResultEvent;
import com.example.dmonunu.parkinnantes.models.ParkingDataBase;

/**
 * Created by Zheyu XIE.
 */
public class ResearchService {

    private Context context;
    private String search;

    public ResearchService(Context context, String search) {
        this.context = context;
        this.search = search;
    }

    public void findParkingsFromRoom() {
        new ResearchService.RoomAsyncTask().execute(search);
    }

    public class RoomAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            EventBusManager.BUS.post(new SearchResultEvent(ParkingDataBase.getAppDatabase(context).lightParkingDao().findParkingsByName(strings[0])));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
