package com.example.dmonunu.parkinnantes.Activities;


import android.os.Bundle;

import com.example.dmonunu.parkinnantes.Utilities.DrawerUtil;
import com.example.dmonunu.parkinnantes.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    public Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        toolBar.setTitle(getResources().getString(R.string.tournament));

        DrawerUtil.getDrawer(this,toolBar);
    }
}
