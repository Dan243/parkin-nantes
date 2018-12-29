package com.example.dmonunu.parkinnantes.services;

import android.content.Context;
import android.os.AsyncTask;

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
    public void findParkingsFromRoom(List<String> research) {
        new RoomAsyncTask().execute(research);
    }

    private class RoomAsyncTask extends AsyncTask<List<String>, Void, List<LightParking>> {
        @Override
        protected List<LightParking> doInBackground(List<String>... lists) {
            return ParkingDataBase.getAppDatabase(context).lightParkingDao().findParkingsByNameAndAddress(lists[0].get(0), lists[0].get(1));
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
