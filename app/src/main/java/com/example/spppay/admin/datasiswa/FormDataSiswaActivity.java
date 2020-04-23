package com.example.spppay.admin.datasiswa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spppay.R;
import com.example.spppay.admin.datakelas.FormDataKelasActivity;
import com.example.spppay.admin.datapetugas.FormDataPetugasActivity;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Kelas;
import com.example.spppay.model.Siswa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class FormDataSiswaActivity extends AppCompatActivity {

    private AppDatabase db;
    EditText edtNisn, edtNis, edtNamaSiswa, edtAlamat, edtNotelp, edtIdSpp, edtKelas;
    Button btnSimpanSiswa;
    Integer nisn,idspp;
    String nama,alamat,telp,nis, namaKelas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_data_siswa);

        edtNisn         = findViewById(R.id.edt_nisn);
        edtNis          = findViewById(R.id.edt_nis);
        edtNamaSiswa    = findViewById(R.id.edt_nama_siswa);
        edtAlamat       = findViewById(R.id.edt_alamat_siswa);
        edtNotelp       = findViewById(R.id.edt_telp_siswa);
        edtIdSpp        = findViewById(R.id.edt_id_spp_siswa);
        edtKelas        = findViewById(R.id.edt_kelas_siswa);
        btnSimpanSiswa  = findViewById(R.id.simpan_data_siswa);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "siswadb").build();

        final Siswa siswa = (Siswa) getIntent().getSerializableExtra("datasiswa");

        if (siswa!=null){
            edtNisn.setText(Integer.toString(siswa.getNisn()));
            edtNis.setText(siswa.getNis());
            edtNamaSiswa.setText(siswa.getNama());
            edtAlamat.setText(siswa.getAlamat());
            edtKelas.setText(siswa.getNama_kelas_siswa());
            edtNotelp.setText(siswa.getNo_telp());
            edtIdSpp.setText(Integer.toString(siswa.getUid_spp()));
            btnSimpanSiswa.setText("UBAH");

            btnSimpanSiswa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    afterClickUpdate();
                }
            });

        }else {
            btnSimpanSiswa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    afterClickInsert();
                }
            });
        }
    }

    private void insertSiswa(final Siswa siswa){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                db.siswaDAO().insertSiswa(siswa);
                return null;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                Toasty.success(FormDataSiswaActivity.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT, true).show();
                Intent intent = new Intent(FormDataSiswaActivity.this, DataSiswaActivity.class);
                startActivity(intent);
            }
        }.execute();
    }

    private void updateSiswa(final Siswa siswa){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.siswaDAO().updateSiswa(siswa);
                return status;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                Toasty.success(FormDataSiswaActivity.this, "Data Berhasil Diubah", Toast.LENGTH_SHORT, true).show();
                Intent intent = new Intent(FormDataSiswaActivity.this, DataSiswaActivity.class);
                startActivity(intent);
            }
        }.execute();
    }

    private void afterClickInsert(){
        nisn    = Integer.parseInt(edtNisn.getText().toString());
        nis     = edtNis.getText().toString();
        nama    = edtNamaSiswa.getText().toString();
        alamat  = edtAlamat.getText().toString();
        namaKelas   = edtKelas.getText().toString();
        telp    = edtNotelp.getText().toString();
        idspp   = Integer.parseInt(edtIdSpp.getText().toString());

        Siswa siswa = new Siswa();
        siswa.setNisn(nisn);
        siswa.setNis(nis);
        siswa.setNama(nama);
        siswa.setAlamat(alamat);
        siswa.setNama_kelas_siswa(namaKelas);
        siswa.setNo_telp(telp);
        siswa.setUid_spp(idspp);

        if (nisn.equals(null)){
            Toasty.warning(FormDataSiswaActivity.this, "NISN Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (nis.isEmpty()){
            Toasty.warning(FormDataSiswaActivity.this, "NIS Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (nama.isEmpty()){
            Toasty.warning(FormDataSiswaActivity.this, "Nama Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (alamat.isEmpty()){
            Toasty.warning(FormDataSiswaActivity.this, "Alamat Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (namaKelas.isEmpty()){
            Toasty.warning(FormDataSiswaActivity.this, "Kelas Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (telp.isEmpty()){
            Toasty.warning(FormDataSiswaActivity.this, "Nomor Telepon Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (idspp.equals(null)){
            Toasty.warning(FormDataSiswaActivity.this, "ID SPP Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else{
            insertSiswa(siswa);
        }
    }

    private void afterClickUpdate(){
        final Siswa siswa = (Siswa) getIntent().getSerializableExtra("datasiswa");

        nisn    = Integer.parseInt(edtNisn.getText().toString());
        nis     = edtNis.getText().toString();
        nama    = edtNamaSiswa.getText().toString();
        alamat  = edtAlamat.getText().toString();
        namaKelas   = edtKelas.getText().toString();
        telp    = edtNotelp.getText().toString();
        idspp   = Integer.parseInt(edtIdSpp.getText().toString());

        siswa.setNisn(nisn);
        siswa.setNis(nis);
        siswa.setNama(nama);
        siswa.setAlamat(alamat);
        siswa.setNama_kelas_siswa(namaKelas);
        siswa.setNo_telp(telp);
        siswa.setUid_spp(idspp);

        if (nisn.equals(null)){
            Toasty.warning(FormDataSiswaActivity.this, "NISN Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (nis.isEmpty()){
            Toasty.warning(FormDataSiswaActivity.this, "NIS Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (nama.isEmpty()){
            Toasty.warning(FormDataSiswaActivity.this, "Nama Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (alamat.isEmpty()){
            Toasty.warning(FormDataSiswaActivity.this, "Alamat Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (namaKelas.isEmpty()){
            Toasty.warning(FormDataSiswaActivity.this, "Kelas Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (telp.isEmpty()){
            Toasty.warning(FormDataSiswaActivity.this, "Nomor Telepon Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (idspp.equals(null)){
            Toasty.warning(FormDataSiswaActivity.this, "ID SPP Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else{
            updateSiswa(siswa);
        }
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, FormDataSiswaActivity.class);
    }
}
