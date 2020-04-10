package com.example.spppay.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.spppay.R;
import com.example.spppay.admin.datakelas.DataKelasActivity;
import com.example.spppay.admin.datapetugas.DataPetugasActivity;
import com.example.spppay.admin.datasiswa.DataSiswaActivity;
import com.example.spppay.admin.dataspp.DataSppActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeAdminFragment extends Fragment {

    LinearLayout btnDataSiswa, btnDataKelas, btnDataSpp, btnDataPetugas;

    public HomeAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home_admin, container, false);

        btnDataSiswa    = view.findViewById(R.id.btn_siswa);
        btnDataKelas    = view.findViewById(R.id.btn_kelas);
        btnDataSpp      = view.findViewById(R.id.btn_spp);
        btnDataPetugas  = view.findViewById(R.id.btn_petugas);

        MovePage();

        return view;

    }

    public void MovePage(){

        btnDataSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDataSiswa = new Intent(getActivity(), DataSiswaActivity.class);
                startActivity(intentDataSiswa);
            }
        });

        btnDataKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDataKelas = new Intent(getActivity(), DataKelasActivity.class);
                startActivity(intentDataKelas);
            }
        });

        btnDataPetugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDataPetugas = new Intent(getActivity(), DataPetugasActivity.class);
                startActivity(intentDataPetugas);
            }
        });

        btnDataSpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDataSpp = new Intent(getActivity(), DataSppActivity.class);
                startActivity(intentDataSpp);
            }
        });
    }
}
