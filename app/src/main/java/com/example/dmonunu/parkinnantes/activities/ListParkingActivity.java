package com.example.dmonunu.parkinnantes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.event.EventBusManager;


import com.example.dmonunu.parkinnantes.event.SearchResultEvent;
import com.example.dmonunu.parkinnantes.services.ResearchService;
import com.example.dmonunu.parkinnantes.services.ResearchServiceImpl;
import com.example.dmonunu.parkinnantes.ui.MyAdapter;
import com.example.dmonunu.parkinnantes.utilities.DrawerUtil;
import com.squareup.otto.Subscribe;
import com.example.dmonunu.parkinnantes.event.SaveEvent;

public class ListParkingActivity extends AppCompatActivity {

    @BindView(R.id.my_list_view)
    RecyclerView myListView;

    @BindView(R.id.parkingtoolbar)
    Toolbar searchBar;

    @BindView(R.id.search_edittext)
    EditText mSearchEditText;

    private LinearLayoutManager layoutManager;

    private ParkingPresenter presenter;

    private ResearchService researchService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_parking);

        ButterKnife.bind(this);

        searchBar.setTitle("La liste de tous les parkings");
        DrawerUtil.getDrawer(this, searchBar);
        presenter = new ParkingPresenterImpl(getApplicationContext());
        presenter.getParkings();
        layoutManager = new LinearLayoutManager(this);
        myListView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(myListView.getContext(), layoutManager.getOrientation() );
        myListView.addItemDecoration(dividerItemDecoration);
        researchService = new ResearchServiceImpl(getApplicationContext());
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    researchService.searchParkingByNameOrAddress("");
                }
                else {
                    researchService.searchParkingByNameOrAddress(editable.toString());
                }
            }
        });
    }


    @Subscribe
    public void searchResult(SearchResultEvent event) {
        myListView.setAdapter(new MyAdapter(event.getParkings(), this));
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


    @Subscribe
    public void saveSuccess(SaveEvent event) {
        presenter.getParkingsFromRoom();
    }
}
