package com.example.dmonunu.parkinnantes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.appyvet.materialrangebar.RangeBar;
import com.example.dmonunu.parkinnantes.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResearchParkingActivity extends AppCompatActivity {

    @BindView(R.id.name)
    EditText mNameEditText;

    @BindView(R.id.address)
    EditText mAddressEditText;

    @BindView(R.id.cash)
    CheckBox mCashCheckBox;

    @BindView(R.id.total_gr)
    CheckBox mTotalGRCheckBox;

    @BindView(R.id.cb)
    CheckBox mCBCheckBox;

    @BindView(R.id.nb_avai)
    RangeBar mNbAvailRangeBar;

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
                if (mCashCheckBox.isChecked()) {
                    intent.putExtra("cash", "Espèces");
                }
                else {
                    intent.putExtra("cash", "");
                }
                if (mTotalGRCheckBox.isChecked()) {
                    intent.putExtra("total_gr", "Total");
                }
                else {
                    intent.putExtra("total_gr", "");
                }
                if (mCBCheckBox.isChecked()) {
                    intent.putExtra("cb", "CB");
                }
                else {
                    intent.putExtra("cb", "");
                }
                intent.putExtra("left", mNbAvailRangeBar.getLeftIndex());
                intent.putExtra("right", mNbAvailRangeBar.getRightIndex());
                startActivity(intent);
            }
        });
    }
}
