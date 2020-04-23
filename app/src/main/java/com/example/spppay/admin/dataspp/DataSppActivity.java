package com.example.spppay.admin.dataspp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.spppay.R;
import com.example.spppay.adapter.AdapterKelasRecyclerView;
import com.example.spppay.adapter.AdapterSppRecyclerView;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Spp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class DataSppActivity extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Spp> daftarSpp;
    FloatingActionButton FABAddSpp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_spp);

        FABAddSpp = findViewById(R.id.add_data_spp);

        daftarSpp = new ArrayList<>();

        //initialize database
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "sppdb").allowMainThreadQueries().build();

        //initialize recyclerview dan layoutManager
        rv = findViewById(R.id.rv_spp);
        rv.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);

        //add data ke array list
        daftarSpp.addAll(Arrays.asList(db.sppDAO().selectAllSpp()));

        //set semua data ke adapter dan di tampilkan
        adapter = new AdapterSppRecyclerView(daftarSpp, this);
        rv.setAdapter(adapter);

        //pindah ke halaman formDataSpp
        FABAddSpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddSpp = new Intent(DataSppActivity.this, FormDataSppActivity.class);
                startActivity(intentAddSpp);
            }
        });
    }
}
