package com.example.dmonunu.parkinnantes.services;

import android.content.Context;
import android.os.AsyncTask;

import com.example.dmonunu.parkinnantes.activities.ListParkingPresenter;
import com.example.dmonunu.parkinnantes.activities.ListParkingPresenterImpl;
import com.example.dmonunu.parkinnantes.event.EventBusManager;
import com.example.dmonunu.parkinnantes.event.SearchResultEvent;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.models.ParkingDataBase;

import java.util.List;

/**
 * Created by Zheyu XIE.
 */
public class ResearchServiceImpl implements ResearchService {

    private Context context;

    public ResearchServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public void findParkingsFromRoom(String search) {
        new RoomAsyncTask().execute(search);
    }

    private class RoomAsyncTask extends AsyncTask<String, Void, List<LightParking>> {
        @Override
        protected List<LightParking> doInBackground(String... strings) {
            return ParkingDataBase.getAppDatabase(context).lightParkingDao().findParkingsByName(strings[0]);
        }

        @Override
        protected void onPostExecute(List<LightParking> lightParkings) {
            super.onPostExecute(lightParkings);
            if (lightParkings != null){
                EventBusManager.BUS.post(new SearchResultEvent(lightParkings));
            }
        }
    }
}
