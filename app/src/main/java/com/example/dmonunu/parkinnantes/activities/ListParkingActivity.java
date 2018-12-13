package com.example.dmonunu.parkinnantes.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.ui.ParkingAdapter;

import java.util.List;

public class ListParkingActivity extends AppCompatActivity implements ParkingView {

    @BindView(R.id.my_list_view)
    ListView myListView;

    private ParkingAdapter parkingAdapter;

    private ParkingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_parking);

        ButterKnife.bind(this);

        presenter = new ParkingPresenterImpl(this, getApplicationContext());
        presenter.getParkings();

    }

    @Override
    public void init(List<LightParking> parkings){
        parkingAdapter = new ParkingAdapter(this, parkings);
        myListView.setAdapter(parkingAdapter);
        myListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent parkingDetail = new Intent(ListParkingActivity.this, ParkingDetailsActivity.class);
                LightParking lightParking = (LightParking) parent.getItemAtPosition(position);
                parkingDetail.putExtra("SelectedParking", lightParking);
                startActivity(parkingDetail);
            }
        });
    }

}
