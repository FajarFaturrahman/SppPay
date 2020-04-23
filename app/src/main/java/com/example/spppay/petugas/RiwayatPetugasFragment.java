package com.example.spppay.petugas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spppay.R;
import com.example.spppay.adapter.AdapterRiwayatRecyclerView;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Pembayaran;

import java.util.ArrayList;
import java.util.Arrays;


public class RiwayatPetugasFragment extends Fragment {

    private AppDatabase db;
    private RecyclerView rvHistory;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Pembayaran> daftarRiwayat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_riwayat_petugas, container, false);

        daftarRiwayat = new ArrayList<>();

        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "pembayarandb").allowMainThreadQueries().build();

        //initialize recyclerview dan layoutManager
        rvHistory = view.findViewById(R.id.rv_riwayat_petugas);
        rvHistory.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvHistory.setLayoutManager(layoutManager);

        //add data ke array list
        daftarRiwayat.addAll(Arrays.asList(db.pembayaranDAO().selectAllpembayaran()));

        //set semua data ke adapter dan di tampilkan
        adapter = new AdapterRiwayatRecyclerView(daftarRiwayat, getActivity());
        rvHistory.setAdapter(adapter);

        return view;
    }
}
