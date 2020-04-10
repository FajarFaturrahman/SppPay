package com.example.spppay.admin.dataspp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.spppay.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DataSppActivity extends AppCompatActivity {

    FloatingActionButton FABAddSpp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_spp);

        FABAddSpp = findViewById(R.id.add_data_spp);


        FABAddSpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddSpp = new Intent(DataSppActivity.this, FormDataSppActivity.class);
                startActivity(intentAddSpp);
            }
        });
    }
}
