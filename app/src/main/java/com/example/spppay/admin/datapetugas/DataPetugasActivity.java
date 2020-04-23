package com.example.spppay.admin.datapetugas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.example.spppay.R;
import com.example.spppay.adapter.AdapterPetugasRecyclerView;
import com.example.spppay.adapter.AdapterSppRecyclerView;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Petugas;
import com.example.spppay.model.Spp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class DataPetugasActivity extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView rvPetugas;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Petugas> daftarPetugas;
    FloatingActionButton FABAddPetugas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_petugas);

        FABAddPetugas = findViewById(R.id.add_data_petugas);

        daftarPetugas = new ArrayList<>();

        //initialize database
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "petugasdb").allowMainThreadQueries().build();

        //initialize recyclerview dan layoutManager
        rvPetugas = findViewById(R.id.rv_petugas);
        rvPetugas.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        rvPetugas.setLayoutManager(layoutManager);

        //add data ke array list
        daftarPetugas.addAll(Arrays.asList(db.petugasDAO().selectAllPetugas()));

        //set semua data ke adapter dan di tampilkan
        adapter = new AdapterPetugasRecyclerView(daftarPetugas, this);
        rvPetugas.setAdapter(adapter);

        //pindah ke halaman FormDataPetugas
        FABAddPetugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddPetugas = new Intent(DataPetugasActivity.this, FormDataPetugasActivity.class);
                startActivity(intentAddPetugas);
            }
        });
    }
}
