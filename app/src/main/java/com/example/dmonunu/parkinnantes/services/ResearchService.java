package com.example.dmonunu.parkinnantes.services;

import android.content.Context;
import android.os.AsyncTask;

import com.example.dmonunu.parkinnantes.activities.ListParkingView;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.models.ParkingDataBase;

import java.util.List;

/**
 * Created by Zheyu XIE.
 */
public class ResearchService {

    private ListParkingView view;
    private Context context;
    private String search;

    public ResearchService(ListParkingView view, Context context, String search) {
        this.view = view;
        this.context = context;
        this.search = search;
    }

    public void findParkingsFromRoom() {
        new ResearchService.RoomAsyncTask().execute(search);
    }

    public class RoomAsyncTask extends AsyncTask<String, Void, List<LightParking>> {
        @Override
        protected List<LightParking> doInBackground(String... strings) {
            List<LightParking> result = ParkingDataBase.getAppDatabase(context).lightParkingDao().findParkingsByName(strings[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<LightParking> lightParkings) {
            super.onPostExecute(lightParkings);
            if (view != null) {
                view.createList(lightParkings);
            }
        }
    }
}
