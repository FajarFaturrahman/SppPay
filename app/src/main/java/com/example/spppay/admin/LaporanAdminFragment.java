package com.example.spppay.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spppay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaporanAdminFragment extends Fragment {

    public LaporanAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_laporan_admin, container, false);
    }
}
