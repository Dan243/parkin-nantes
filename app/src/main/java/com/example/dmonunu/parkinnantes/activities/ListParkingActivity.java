package com.example.dmonunu.parkinnantes.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.event.EventBusManager;

import com.example.dmonunu.parkinnantes.event.SearchResultEvent;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.services.ResearchService;
import com.example.dmonunu.parkinnantes.ui.MyAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.otto.Subscribe;

import java.util.List;

public class ListParkingActivity extends AppCompatActivity {

import com.example.dmonunu.parkinnantes.event.SearchResultEvent;
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.services.ResearchService;
import com.example.dmonunu.parkinnantes.ui.ParkingAdapter;
import com.squareup.otto.Subscribe;

import java.util.List;

public class ListParkingActivity extends AppCompatActivity {

    @BindView(R.id.my_list_view)
    RecyclerView myListView;

    @BindView(R.id.search_bar)
    MaterialSearchBar searchBar;

    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager layoutManager;

    private ParkingPresenter presenter;

    private ResearchService researchService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_parking);

        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        presenter = new ParkingPresenterImpl(getApplicationContext());
        presenter.getParkings();
        layoutManager = new LinearLayoutManager(this);
        myListView.setLayoutManager(layoutManager);
    }


    @Subscribe
    public void searchResult(SearchResultEvent event) {
        myListView.setAdapter(new MyAdapter(event.getParkings(), this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(myListView.getContext(), layoutManager.getOrientation() );
        myListView.addItemDecoration(dividerItemDecoration);
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
