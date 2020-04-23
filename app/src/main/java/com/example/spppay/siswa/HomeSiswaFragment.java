package com.example.spppay.siswa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.spppay.R;
import com.example.spppay.adapter.AdapterRiwayatRecyclerView;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Pembayaran;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeSiswaFragment extends Fragment {

    private AppDatabase db;
    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Pembayaran> daftarRiwayat;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_siswa, container, false);

        daftarRiwayat = new ArrayList<>();

        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "pembayarandb").allowMainThreadQueries().build();

        //initialize recyclerview dan layoutManager
        rv = view.findViewById(R.id.rv_riwayat_siswa);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);

        //add data ke array list
        daftarRiwayat.addAll(Arrays.asList(db.pembayaranDAO().selectAllpembayaran()));

        //set semua data ke adapter dan di tampilkan
        adapter = new AdapterRiwayatRecyclerView(daftarRiwayat, getActivity());
        rv.setAdapter(adapter);
        return view;
    }
}
