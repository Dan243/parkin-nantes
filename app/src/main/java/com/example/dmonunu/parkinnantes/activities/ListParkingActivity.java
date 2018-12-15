package com.example.dmonunu.parkinnantes.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.event.EventBusManager;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.ui.ParkingAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

public class ListParkingActivity extends AppCompatActivity implements ParkingView {

    @BindView(R.id.my_list_view)
    ListView myListView;

    @BindView(R.id.search_bar)
    MaterialSearchBar searchBar;

    private ParkingAdapter parkingAdapter;

    private ParkingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_parking);

        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        presenter = new ParkingPresenterImpl(this, getApplicationContext());
        presenter.getParkings();

    }

    @Override
    public void init(List<LightParking> parkings){
        parkingAdapter = new ParkingAdapter(this, parkings);
        myListView.setAdapter(parkingAdapter);
       // myListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent parkingIntent = new Intent(ListParkingActivity.this, ParkingDetailsActivity.class);
                LightParking lightParking = (LightParking) parkingAdapter.getItem(position);
                parkingIntent.putExtra("SelectedParking", lightParking);
                startActivity(parkingIntent);
            }
        });

    }
    @Override
    protected void onResume() {
        // Do NOT forget to call super.onResume()
        super.onResume();

        // Register to Event bus : now each time an event is posted, the activity will receive it if it is @Subscribed to this event
        EventBusManager.BUS.register(this);


    }

    @Override
    protected void onPause() {
        // Unregister from Event bus : if event are posted now, the activity will not receive it
        EventBusManager.BUS.unregister(this);

        // Do NOT forget to call super.onPause()
        super.onPause();
    }
}
