package com.example.dmonunu.parkinnantes.services;

import android.content.Context;
import android.os.AsyncTask;

import com.example.dmonunu.parkinnantes.activities.ListParkingActivity;
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
            String name = lists[0].get(0);
            String address = lists[0].get(1);
            return ParkingDataBase.getAppDatabase(context).lightParkingDao().findParkingsByNameAndAddress(ifLastCharSpace(name), ifLastCharSpace(address), lists[0].get(2), lists[0].get(3), lists[0].get(4), Integer.parseInt(lists[0].get(5)), Integer.parseInt(lists[0].get(6)));
        }

        @Override
        protected void onPostExecute(List<LightParking> lightParkings) {
            super.onPostExecute(lightParkings);
            if (lightParkings != null){
                EventBusManager.BUS.post(new SearchResultEvent(lightParkings));
            }
        }
    }

    @Override
    public void searchParkingByNameOrAddress(String s) {
        new RoomByNameOrAddressAsyncTask().execute(s);
    }

    private class RoomByNameOrAddressAsyncTask extends AsyncTask<String, Void, List<LightParking>> {
        @Override
        protected List<LightParking> doInBackground(String... strings) {
            return ParkingDataBase.getAppDatabase(context).lightParkingDao().findParkingsByNameOrAddress(ifLastCharSpace(strings[0]));
        }

        @Override
        protected void onPostExecute(List<LightParking> lightParkings) {
            super.onPostExecute(lightParkings);
            if (lightParkings != null){
                EventBusManager.BUS.post(new SearchResultEvent(lightParkings));
            }
        }
    }

    private String ifLastCharSpace(String mString) {
        if (mString.length()>1) {
            if (mString.substring(mString.length() - 1).equals(" ")) {
                return mString.substring(0, mString.length()-2);
            }
            return mString;
        }
        return mString;
    }
}
