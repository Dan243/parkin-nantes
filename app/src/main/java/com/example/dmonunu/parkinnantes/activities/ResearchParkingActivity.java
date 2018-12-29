package com.example.dmonunu.parkinnantes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dmonunu.parkinnantes.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResearchParkingActivity extends AppCompatActivity {

    @BindView(R.id.name)
    EditText mNameEditText;

    @BindView(R.id.address)
    EditText mAddressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_parking);

        ButterKnife.bind(this);

        Button mButton = (Button) findViewById(R.id.validateButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResearchParkingActivity.this, ResearchListActivity.class);
                intent.putExtra("name", mNameEditText.getText().toString());
                intent.putExtra("address", mAddressEditText.getText().toString());
                startActivity(intent);
            }
        });

        Button mListButton = (Button) findViewById(R.id.listActivitybutton);
        mListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResearchParkingActivity.this, ListParkingActivity.class);
                startActivity(intent);
            }
        });
    }
}
