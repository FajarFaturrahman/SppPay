package com.example.spppay.admin.datasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.spppay.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DataSiswaActivity extends AppCompatActivity {

    FloatingActionButton FABAddSiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_siswa);

        FABAddSiswa = findViewById(R.id.add_data_siswa);


        FABAddSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddSiswa = new Intent(DataSiswaActivity.this, FormDataSiswaActivity.class);
                startActivity(intentAddSiswa);
            }
        });
    }
}
