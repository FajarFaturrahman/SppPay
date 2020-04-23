package com.example.spppay.admin.datasiswa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.spppay.R;
import com.example.spppay.adapter.AdapterSiswaRecyclerView;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Siswa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class DataSiswaActivity extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView rvSiswa;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Siswa> daftarSiswa;
    FloatingActionButton FABAddSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_siswa);

        FABAddSiswa = findViewById(R.id.add_data_siswa);

        daftarSiswa = new ArrayList<>();

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "siswadb").allowMainThreadQueries().build();

        //initialize recyclerview dan layoutManager
        rvSiswa = findViewById(R.id.rv_siswa);
        rvSiswa.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        rvSiswa.setLayoutManager(layoutManager);

        //add data ke array list
        daftarSiswa.addAll(Arrays.asList(db.siswaDAO().selectAllSiswa()));

        //set semua data ke adapter dan di tampilkan
        adapter = new AdapterSiswaRecyclerView(daftarSiswa, this);
        rvSiswa.setAdapter(adapter);

        FABAddSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddSiswa = new Intent(DataSiswaActivity.this, FormDataSiswaActivity.class);
                startActivity(intentAddSiswa);
            }
        });
    }
}
