package com.example.dmonunu.parkinnantes.activities;

import android.os.Bundle;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.event.EventBusManager;
import com.example.dmonunu.parkinnantes.event.SaveEvent;
import com.example.dmonunu.parkinnantes.event.SearchResultEvent;
import com.example.dmonunu.parkinnantes.services.ResearchService;
import com.example.dmonunu.parkinnantes.services.ResearchServiceImpl;
import com.example.dmonunu.parkinnantes.ui.MyAdapter;
import com.example.dmonunu.parkinnantes.utilities.DrawerUtil;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResearchListActivity extends AppCompatActivity {

    @BindView(R.id.my_list_view)
    RecyclerView myListView;

    @BindView(R.id.listsearchtoolbar)
    Toolbar toolbar;

    private ResearchService researchService;

    private LinearLayoutManager layoutManager;

    private List<String> research;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_list);
        ButterKnife.bind(this);
        toolbar.setTitle("La liste des parkings trouvés");
        DrawerUtil.getDrawer(this, toolbar);
        layoutManager = new LinearLayoutManager(this);
        myListView.setLayoutManager(layoutManager);
        research = new ArrayList<>();
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");
        String cash = getIntent().getStringExtra("cash");
        String total_gr = getIntent().getStringExtra("total_gr");
        String cb = getIntent().getStringExtra("cb");
        research.add(name);
        research.add(address);
        research.add(cash);
        research.add(total_gr);
        research.add(cb);
        research.add(String.valueOf(getIntent().getIntExtra("left", 0)));
        research.add(String.valueOf(getIntent().getIntExtra("right", 700)));
        researchService = new ResearchServiceImpl(getApplicationContext());
        researchService.findParkingsFromRoom(research);
        ParkingPresenter parkingPresenter = new ParkingPresenterImpl(getApplicationContext());
        if (parkingPresenter.isNetworkOnline()) {
            parkingPresenter.getParkingsFromApi();
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
        myListView.setAdapter(new MyAdapter(event.getParkings(), this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(myListView.getContext(), layoutManager.getOrientation() );
        myListView.addItemDecoration(dividerItemDecoration);
    }

    @Subscribe
    public void saveSuccess(SaveEvent event) {
        researchService.findParkingsFromRoom(research);
    }
}
