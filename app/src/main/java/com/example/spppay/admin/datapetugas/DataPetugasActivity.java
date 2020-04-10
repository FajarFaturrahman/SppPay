package com.example.spppay.admin.datapetugas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.example.spppay.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DataPetugasActivity extends AppCompatActivity {

    FloatingActionButton FABAddPetugas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_petugas);

        FABAddPetugas = findViewById(R.id.add_data_petugas);


        FABAddPetugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddPetugas = new Intent(DataPetugasActivity.this, FormDataPetugasActivity.class);
                startActivity(intentAddPetugas);
            }
        });
    }
}
