package com.example.spppay.petugas;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spppay.R;
import com.example.spppay.data.PembayaranDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePetugasFragment extends Fragment {

    CardView btnLaporan, btnBayar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home_petugas, container, false);

        btnBayar   = view.findViewById(R.id.btn_bayar_petugas);
        btnLaporan = view.findViewById(R.id.btn_laporan);

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PembayaranPetugas.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
