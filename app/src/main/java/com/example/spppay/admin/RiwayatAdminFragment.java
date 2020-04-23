package com.example.spppay.admin;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spppay.R;
import com.example.spppay.adapter.AdapterRiwayatRecyclerView;
import com.example.spppay.adapter.AdapterSppRecyclerView;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Pembayaran;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class RiwayatAdminFragment extends Fragment {

    private AppDatabase db;
    private RecyclerView rvAdmin;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Pembayaran> daftarRiwayat;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View view =  inflater.inflate(R.layout.fragment_riwayat_admin, container, false);

         daftarRiwayat = new ArrayList<>();

         db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "pembayarandb").allowMainThreadQueries().build();

        //initialize recyclerview dan layoutManager
        rvAdmin = view.findViewById(R.id.rv_riwayat_admin);
        rvAdmin.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rvAdmin.setLayoutManager(layoutManager);

        //add data ke array list
        daftarRiwayat.addAll(Arrays.asList(db.pembayaranDAO().selectAllpembayaran()));

        //set semua data ke adapter dan di tampilkan
        adapter = new AdapterRiwayatRecyclerView(daftarRiwayat, getActivity());
        rvAdmin.setAdapter(adapter);

        return view;
    }
}
