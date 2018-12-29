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
import com.example.dmonunu.parkinnantes.models.LightParking;
import com.example.dmonunu.parkinnantes.ui.MyAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

public class ListParkingActivity extends AppCompatActivity implements ParkingView {

    @BindView(R.id.my_list_view)
    RecyclerView myListView;

    @BindView(R.id.search_bar)
    MaterialSearchBar searchBar;

    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager layoutManager;

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
        layoutManager = new LinearLayoutManager(this);
        myListView.setLayoutManager(layoutManager);

    }

    @Override
    public void init(List<LightParking> parkings){
        myListView.setAdapter(new MyAdapter(parkings, this));
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
