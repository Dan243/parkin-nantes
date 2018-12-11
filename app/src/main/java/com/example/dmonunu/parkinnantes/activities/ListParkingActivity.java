package com.example.dmonunu.parkinnantes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.ui.ParkingAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListParkingActivity extends AppCompatActivity implements ListParkingView{

    @BindView(R.id.my_list_view)
    ListView myListView;



    private ParkingAdapter parkingAdapter;

    private ListParkingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_parking);

        ButterKnife.bind(this);

        presenter = new ListParkingPresenterImpl(this, getApplicationContext());
        presenter.getParkings();

    }

    @Override
    public void createList(List<LightParking> parkings){
        parkingAdapter = new ParkingAdapter(this, parkings);
        myListView.setAdapter(parkingAdapter);
        myListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
    }
}
