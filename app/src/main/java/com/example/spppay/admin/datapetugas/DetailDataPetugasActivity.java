package com.example.spppay.admin.datapetugas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.spppay.R;
import com.example.spppay.admin.datakelas.FormDataKelasActivity;
import com.example.spppay.model.Petugas;

public class DetailDataPetugasActivity extends AppCompatActivity {

    TextView namaPetugas,idPetugas,usernamePetugas,levelPetugas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_petugas);

        namaPetugas         = findViewById(R.id.nama_data_petugas);
        idPetugas           = findViewById(R.id.id_data_petugas);
        usernamePetugas     = findViewById(R.id.username_data_petugas);
        levelPetugas        = findViewById(R.id.level_data_petugas);

        Petugas petugas = (Petugas) getIntent().getSerializableExtra("datapetugas");
        namaPetugas.setText(petugas.getNama_petugas());
        idPetugas.setText(Integer.toString(petugas.getId_petugas()));
        usernamePetugas.setText(petugas.getUsername());
        levelPetugas.setText(petugas.getLevel());
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, DetailDataPetugasActivity.class);
    }
}
