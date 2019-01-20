package com.example.dmonunu.parkinnantes.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dmonunu.parkinnantes.event.EventBusManager;
import com.example.dmonunu.parkinnantes.event.SaveEvent;
import com.example.dmonunu.parkinnantes.event.SearchResultEvent;
import com.example.dmonunu.parkinnantes.models.BaseResponse;
import com.example.dmonunu.parkinnantes.models.DispoModel;
import com.example.dmonunu.parkinnantes.models.HoraireModel;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.models.ParkingDataBase;
import com.example.dmonunu.parkinnantes.models.ParkingMapper;
import com.example.dmonunu.parkinnantes.models.ParkingModel;
import com.example.dmonunu.parkinnantes.models.Record;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParkingPresenterImpl implements ParkingPresenter {
    private static final String DATABASE_NAME = "parking_db";

    private ParkingSearchRESTService parkingService;

    private Context context;

    public ParkingPresenterImpl(Context context) {
        this.context = context;
        this.parkingService = BaseService.getRetrofitInstance().create(ParkingSearchRESTService.class);
    }

    @Override
    public void getParkings() {
        getParkingsFromRoom();
        if (isNetworkOnline()) {
            getParkingsFromApi();
        }
    }

    @Override
    public void getParkingsFromApi() {
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

                        parkingService.recupTousLesHoraires("244400404_parkings-publics-nantes-horaires", 300).enqueue(new Callback<BaseResponse<HoraireModel>>() {
                            @Override
                            public void onResponse(Call<BaseResponse<HoraireModel>> call, Response<BaseResponse<HoraireModel>> response) {
                                List<Record<HoraireModel>> records = response.body().getRecords();
                                List<HoraireModel> horaireModels = new ArrayList<>();
                                for (final Record<HoraireModel> record : records ){
                                    horaireModels.add(record.getFields());
                                }

                                List<LightParking> lightParkings = ParkingMapper.createLightParkings(dispoModels, horaireModels, parkingModels);
                                List<LightParking> lightParkingsInitials = ParkingDataBase.getAppDatabase(context).lightParkingDao().getParkings();
                                for(LightParking parking : lightParkings) {
                                    ParkingDataBase.getAppDatabase(context).lightParkingDao().createParking(updateParkingFavori(parking, lightParkingsInitials));
                                }
                                EventBusManager.BUS.post(new SaveEvent());
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

    @Override
    public boolean isNetworkOnline() {
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

    @Override
    public void getParkingsFromRoom() {
        new RoomAsyncTask().execute();
    }

    @Override
    public void getFavoriteParkings() {
        new RoomFavoriAsyncTask().execute();
    }

    private class RoomAsyncTask extends AsyncTask<Void, Void, List<LightParking>> {
        @Override
        protected List<LightParking> doInBackground(Void... voids) {
            return ParkingDataBase.getAppDatabase(context).lightParkingDao().getParkings();
        }

        @Override
        protected void onPostExecute(List<LightParking> lightParkings) {
            super.onPostExecute(lightParkings);
            if (lightParkings != null) {
                EventBusManager.BUS.post(new SearchResultEvent(lightParkings));
            }
        }
    }

    private class RoomFavoriAsyncTask extends AsyncTask<Void, Void, List<LightParking>> {
        @Override
        protected List<LightParking> doInBackground(Void... voids) {
            return ParkingDataBase.getAppDatabase(context).lightParkingDao().getFavoriteParkings();
        }

        @Override
        protected void onPostExecute(List<LightParking> lightParkings) {
            super.onPostExecute(lightParkings);
            if (lightParkings != null) {
                EventBusManager.BUS.post(new SearchResultEvent(lightParkings));
            }
        }
    }

    @Override
    public LightParking updateParkingFavori(LightParking parking, List<LightParking> parkings) {
        for (LightParking item: parkings) {
            if (parking.getIdobj().equals(item.getIdobj())) {
                parking.setFavorite(item.isFavorite());
            }
        }
        return parking;
    }
}
