package com.example.dmonunu.parkinnantes.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.event.EventBusManager;
import com.example.dmonunu.parkinnantes.event.SaveEvent;
import com.example.dmonunu.parkinnantes.event.SearchResultEvent;
import com.example.dmonunu.parkinnantes.ui.MyAdapter;
import com.example.dmonunu.parkinnantes.utilities.DrawerUtil;
import com.squareup.otto.Subscribe;

public class ListFavoriteActivity extends AppCompatActivity {

    @BindView(R.id.my_favorite_list)
    RecyclerView myfavoritelist;

    @BindView(R.id.favoritetoolbar)
    androidx.appcompat.widget.Toolbar toolbar;


    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager layoutManager;

    private ParkingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_favorite);
        ButterKnife.bind(this);

        toolbar.setTitle("La liste des parkings favoris");
        DrawerUtil.getDrawer(this,toolbar);

        presenter = new ParkingPresenterImpl(getApplicationContext());
        presenter.getFavoriteParkings();
        layoutManager = new LinearLayoutManager(this);
        myfavoritelist.setLayoutManager(layoutManager);
    }

    @Subscribe
    public void searchResult(SearchResultEvent event) {
        myfavoritelist.setAdapter(new MyAdapter(event.getParkings(), this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(myfavoritelist.getContext(), layoutManager.getOrientation() );
        myfavoritelist.addItemDecoration(dividerItemDecoration);
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
