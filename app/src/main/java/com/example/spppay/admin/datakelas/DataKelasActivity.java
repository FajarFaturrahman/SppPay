package com.example.spppay.admin.datakelas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.spppay.R;
import com.example.spppay.adapter.AdapterKelasRecyclerView;
import com.example.spppay.admin.MainActivityAdmin;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Kelas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class DataKelasActivity extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Kelas> daftarKelas;
    FloatingActionButton FABAddKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas);

        FABAddKelas = findViewById(R.id.add_data_kelas);

        daftarKelas = new ArrayList<>();

        //initialize database
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "kelasdb").allowMainThreadQueries().build();

        //initialize recyclerview dan layoutManager
        rv = findViewById(R.id.rv__kelas);
        rv.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);

        //add data ke array list
        daftarKelas.addAll(Arrays.asList(db.kelasDAO().selectAllKelas()));

        //set semua data ke adapter dan di tampilkan
        adapter = new AdapterKelasRecyclerView(daftarKelas, this);
        rv.setAdapter(adapter);

        //pindah ke halaman formDataKelas
        FABAddKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddKelas = new Intent(DataKelasActivity.this, FormDataKelasActivity.class);
                startActivity(intentAddKelas);
            }
        });
    }

}
