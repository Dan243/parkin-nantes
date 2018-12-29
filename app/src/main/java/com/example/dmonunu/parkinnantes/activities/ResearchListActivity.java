package com.example.dmonunu.parkinnantes.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.event.EventBusManager;
import com.example.dmonunu.parkinnantes.event.SaveEvent;
import com.example.dmonunu.parkinnantes.event.SearchResultEvent;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.services.ResearchService;
import com.example.dmonunu.parkinnantes.services.ResearchServiceImpl;
import com.example.dmonunu.parkinnantes.ui.ParkingAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResearchListActivity extends AppCompatActivity {

    @BindView(R.id.my_list_view)
    ListView myListView;

    private ArrayAdapter parkingAdapter;

    private ResearchService researchService;

    private ListParkingPresenter listParkingPresenter;

    private List<String> research;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_list);
        ButterKnife.bind(this);
        research = new ArrayList<String>();
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");
        research.add(name);
        research.add(address);
        researchService = new ResearchServiceImpl(getApplicationContext());
        researchService.findParkingsFromRoom(research);
        listParkingPresenter = new ListParkingPresenterImpl(getApplicationContext());
        if (listParkingPresenter.isNetworkOnline()) {
            listParkingPresenter.getParkingsFromApi();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBusManager.BUS.register(this);
    }

    @Override
    protected void onPause() {
        EventBusManager.BUS.unregister(this);
        super.onPause();
    }

    @Subscribe
    public void searchResult(SearchResultEvent event) {
        List<LightParking> parkingList = event.getParkings();
        parkingAdapter = new ParkingAdapter(this, parkingList);
        myListView.setAdapter(parkingAdapter);
    }

    @Subscribe
    public void saveSuccess(SaveEvent event) {
        researchService.findParkingsFromRoom(research);
    }
}
