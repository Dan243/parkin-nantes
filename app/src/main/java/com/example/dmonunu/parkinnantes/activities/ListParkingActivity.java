package com.example.dmonunu.parkinnantes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.ListView;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.ui.ParkingAdapter;

import java.util.ArrayList;

public class ListParkingActivity extends AppCompatActivity {

    @BindView(R.id.my_list_view)
    ListView myListView;

    private ParkingAdapter parkingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_parking);

        ButterKnife.bind(this);

        this.parkingAdapter = new ParkingAdapter(this, new ArrayList<LightParking>());
        this.myListView.setAdapter(this.parkingAdapter);
        this.myListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);


    }
}
