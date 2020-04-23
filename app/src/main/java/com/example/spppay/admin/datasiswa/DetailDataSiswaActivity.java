package com.example.spppay.admin.datasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.spppay.R;
import com.example.spppay.model.Siswa;


public class DetailDataSiswaActivity extends AppCompatActivity {

    TextView namaSiswa, nis, nisn, alamat, telp, kelas,idSpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_siswa);

        namaSiswa   = findViewById(R.id.nama_siswa_detail);
        nis         = findViewById(R.id.nis_siswa_detail);
        nisn        = findViewById(R.id.nisn_detail);
        alamat      = findViewById(R.id.alamat_detail);
        telp        = findViewById(R.id.telp_detail);
        kelas       = findViewById(R.id.kelas_detail);
        idSpp       = findViewById(R.id.id_spp_detail);

        Siswa siswa = (Siswa) getIntent().getSerializableExtra("datasiswa");

        namaSiswa.setText(siswa.getNama());
        nis.setText(siswa.getNis());
        nisn.setText(Integer.toString(siswa.getNisn()));
        alamat.setText(siswa.getAlamat());
        telp.setText(siswa.getNo_telp());
        kelas.setText(siswa.getNama_kelas_siswa());
        idSpp.setText(Integer.toString(siswa.getUid_spp()));
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, DetailDataSiswaActivity.class);
    }
}
