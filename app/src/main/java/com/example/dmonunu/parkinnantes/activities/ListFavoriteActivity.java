package com.example.dmonunu.parkinnantes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dmonunu.parkinnantes.R;
import com.example.dmonunu.parkinnantes.event.EventBusManager;
import com.example.dmonunu.parkinnantes.event.SearchResultEvent;
import com.example.dmonunu.parkinnantes.services.ResearchService;
import com.example.dmonunu.parkinnantes.services.ResearchServiceImpl;
import com.example.dmonunu.parkinnantes.ui.MyAdapter;
import com.example.dmonunu.parkinnantes.utilities.DrawerUtil;
import com.squareup.otto.Subscribe;

public class ListFavoriteActivity extends AppCompatActivity {

    @BindView(R.id.my_favorite_list)
    RecyclerView myfavoritelist;

    @BindView(R.id.favoritetoolbar)
    androidx.appcompat.widget.Toolbar toolbar;

    @BindView(R.id.search_edittext)
    EditText mSearchEditText;

    @BindView(R.id.tv_list_parking_no_result_found)
    TextView noResultFoundTextView;

    private LinearLayoutManager layoutManager;

    private ParkingPresenter presenter;

    private ResearchService researchService;

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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(myfavoritelist.getContext(), layoutManager.getOrientation() );
        myfavoritelist.addItemDecoration(dividerItemDecoration);
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
                    researchService.searchParkingFavoriByNameOrAddress("");
                }
                else {
                    researchService.searchParkingFavoriByNameOrAddress(editable.toString());
                }
            }
        });
    }

    @Subscribe
    public void searchResult(SearchResultEvent event) {
        if (event != null && event.getParkings() != null) {
            if (event.getParkings().isEmpty()) {
                // todo : show nothing found
                noResultFoundTextView.setVisibility(View.VISIBLE);
                myfavoritelist.setVisibility(View.GONE);
            } else {
                noResultFoundTextView.setVisibility(View.GONE);
                myfavoritelist.setVisibility(View.VISIBLE);
                myfavoritelist.setAdapter(new MyAdapter(event.getParkings(), this));
            }
        } else {
            // todo : show nothing
            noResultFoundTextView.setVisibility(View.VISIBLE);
            myfavoritelist.setVisibility(View.GONE);
        }
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
