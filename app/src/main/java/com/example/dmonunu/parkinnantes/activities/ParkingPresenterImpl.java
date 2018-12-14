package com.example.dmonunu.parkinnantes.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dmonunu.parkinnantes.models.BaseResponse;
import com.example.dmonunu.parkinnantes.models.DispoModel;
import com.example.dmonunu.parkinnantes.models.HoraireModel;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.models.ParkingDataBase;
import com.example.dmonunu.parkinnantes.models.ParkingMapper;
import com.example.dmonunu.parkinnantes.models.ParkingModel;
import com.example.dmonunu.parkinnantes.models.Record;
import com.example.dmonunu.parkinnantes.services.BaseService;
import com.example.dmonunu.parkinnantes.services.ParkingSearchRESTService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParkingPresenterImpl implements ParkingPresenter {
    private static final String DATABASE_NAME = "parking_db";

    private ParkingSearchRESTService parkingService;
    private ParkingView view;
    private Context context;

    public ParkingPresenterImpl(ParkingView view, Context context) {
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
        this.parkingService.recupTousLesParkings("244400404_parkings-publics-nantes", 100).enqueue(new Callback<BaseResponse<ParkingModel>>() {
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

                parkingService.recupToutesLesDispos("244400404_parkings-publics-nantes-disponibilites", 100).enqueue(new Callback<BaseResponse<DispoModel>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<DispoModel>> call, Response<BaseResponse<DispoModel>> response) {
                        List<Record<DispoModel>> records = response.body().getRecords();
                        final List<DispoModel> dispoModels = new ArrayList<>();
                        for(final Record<DispoModel> record : records ){
                            dispoModels.add(record.getFields());
                        }

                        parkingService.recupTousLesHoraires("244400404_parkings-publics-nantes-horaires", 100).enqueue(new Callback<BaseResponse<HoraireModel>>() {
                            @Override
                            public void onResponse(Call<BaseResponse<HoraireModel>> call, Response<BaseResponse<HoraireModel>> response) {
                                List<Record<HoraireModel>> records = response.body().getRecords();
                                List<HoraireModel> horaireModels = new ArrayList<>();
                                for (final Record<HoraireModel> record : records ){
                                    horaireModels.add(record.getFields());
                                }

                                List<LightParking> lightParkings = ParkingMapper.createLightParkings(dispoModels, horaireModels, parkingModels);
                                view.init(lightParkings);
                            }

                            @Override
                            public void onFailure(Call<BaseResponse<HoraireModel>> call, Throwable t) {
                                Log.d("", "");
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<DispoModel>> call, Throwable t) {
                        Log.d("", "");
                    }
                });
            }

            @Override
            public void onFailure(Call<BaseResponse<ParkingModel>> call, Throwable t) {
                Log.d("", "");
            }
        });
    }

    private void insertParkignsInRoom(final List<LightParking> parkingModelList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ParkingDataBase.getAppDatabase(context).lightParkingDao().createParkings(parkingModelList);
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

    private class RoomAsyncTask extends AsyncTask<String, Integer, List<LightParking>> {
        protected List<LightParking> doInBackground(String... urls) {
            return ParkingDataBase.getAppDatabase(context).lightParkingDao().getParkings();
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(List<LightParking> result) {
            if (view != null) {
                view.init(result);
            }
        }
    }
}
