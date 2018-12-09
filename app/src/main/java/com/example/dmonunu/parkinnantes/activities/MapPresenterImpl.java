package com.example.dmonunu.parkinnantes.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dmonunu.parkinnantes.models.BaseResponse;
import com.example.dmonunu.parkinnantes.models.ParkingDataBase;
import com.example.dmonunu.parkinnantes.models.ParkingModel;
import com.example.dmonunu.parkinnantes.models.Record;
import com.example.dmonunu.parkinnantes.services.BaseService;
import com.example.dmonunu.parkinnantes.services.ParkingSearchRESTService;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapPresenterImpl implements MapPresenter {
    private static final String DATABASE_NAME = "parking_db";

    private ParkingSearchRESTService parkingService;
    private MapView view;
    private Context context;

    public MapPresenterImpl(MapView view, Context context) {
        this.view = view;
        this.context = context;
        this.parkingService = BaseService.getRetrofitInstance().create(ParkingSearchRESTService.class);
    }

    @Override
    public void getParkings() {
        if (isNetworkOnline()) {
            getParkingsFromApi();
        } else {
            getParkingsFromRoom();
        }
    }

    private void getParkingsFromApi() {
        this.parkingService.recupTousLesParkings("244400404_parkings-publics-nantes").enqueue(new Callback<BaseResponse<ParkingModel>>() {
            @Override
            public void onResponse(Call<BaseResponse<ParkingModel>> call, Response<BaseResponse<ParkingModel>> response) {

                List<Record<ParkingModel>> records = response.body().getRecords();
                final List<ParkingModel> parkingModels = new ArrayList<>();
                for(final Record<ParkingModel> record : records ){
                    ParkingModel parkingModel = record.getFields();
                    parkingModel.setLatitude(parkingModel.getLocation().get(0));
                    parkingModel.setLongitude(parkingModel.getLocation().get(1));
                    parkingModels.add(parkingModel);
                }

                insertParkignsInRoom(parkingModels);
                if (view != null) {
                    view.initMap(parkingModels);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ParkingModel>> call, Throwable t) {

            }
        });
    }

    private void insertParkignsInRoom(final List<ParkingModel> parkingModelList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ParkingDataBase.getAppDatabase(context).parkingDao().createParkings(parkingModelList);
            }
        }).start();
    }

    private void getParkingsFromRoom() {
        new RoomAsyncTask().execute("");
    }

    private boolean isNetworkOnline() {
        boolean status = false;
        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                status= true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
                    status= true;
            }
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return status;

    }

    private class RoomAsyncTask extends AsyncTask<String, Integer, List<ParkingModel>> {
        protected List<ParkingModel> doInBackground(String... urls) {
            return ParkingDataBase.getAppDatabase(context).parkingDao().getParkings();
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(List<ParkingModel> result) {
            if (view != null) {
                view.initMap(result);
            }
        }
    }
}
