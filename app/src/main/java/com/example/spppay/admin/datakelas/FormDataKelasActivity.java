package com.example.spppay.admin.datakelas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spppay.R;
import com.example.spppay.admin.datakelas.FormDataKelasActivity;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Kelas;

import es.dmoral.toasty.Toasty;

public class FormDataKelasActivity extends AppCompatActivity {

    private AppDatabase db;
    EditText edtNamaKelas, edtKompetensiKeahlian;
    Button btnSimpanKelas;
    String namaKelas, kompetensi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_data_kelas);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "kelasdb").build();

        edtNamaKelas            = findViewById(R.id.edt_nama_kelas);
        edtKompetensiKeahlian   = findViewById(R.id.edt_kompetensi_keahlian);
        btnSimpanKelas          = findViewById(R.id.btn_simpan_kelas);

        final Kelas kelas = (Kelas) getIntent().getSerializableExtra("data");

        if(kelas!=null){
            edtNamaKelas.setText(kelas.getNama_kelas());
            edtKompetensiKeahlian.setText(kelas.getKompetensi_keahlian());
            btnSimpanKelas.setText("UBAH");
            btnSimpanKelas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    afterClickUpdateButton();
                }
            });
        }else {
            btnSimpanKelas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    afterClickInsertButton();
                }
            });
        }
    }

    private void insertKelas(final Kelas kelas){

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.kelasDAO().insertKelas(kelas);
                return status;
            }
            @Override
            protected void onPostExecute(Long status) {
                Toasty.success(FormDataKelasActivity.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT, true).show();
                Intent intent = new Intent(FormDataKelasActivity.this, DataKelasActivity.class);
                startActivity(intent);
            }
        }.execute();
    }

    private void updateKelas(final Kelas kelas){

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.kelasDAO().updateKelas(kelas);
                return status;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                Toasty.success(FormDataKelasActivity.this, "Data Berhasil Diubah", Toast.LENGTH_SHORT, true).show();
                Intent intent = new Intent(FormDataKelasActivity.this, DataKelasActivity.class);
                startActivity(intent);
            }
        }.execute();
    }

    private void afterClickInsertButton(){
        namaKelas = edtNamaKelas.getText().toString();
        kompetensi = edtKompetensiKeahlian.getText().toString();

        Kelas kelas = new Kelas();
        kelas.setNama_kelas(namaKelas);
        kelas.setKompetensi_keahlian(kompetensi);

        if (namaKelas.isEmpty()){
            Toasty.warning(FormDataKelasActivity.this, "Nama Kelas Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (kompetensi.isEmpty()){
            Toasty.warning(FormDataKelasActivity.this, "Kompetensi Keahlian Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else{
            insertKelas(kelas);
        }
    }


    private void afterClickUpdateButton(){
        final Kelas kelas = (Kelas) getIntent().getSerializableExtra("data");

        namaKelas = edtNamaKelas.getText().toString();
        kompetensi = edtKompetensiKeahlian.getText().toString();

        kelas.setNama_kelas(namaKelas);
        kelas.setKompetensi_keahlian(kompetensi);

        if(namaKelas.isEmpty()){
            Toasty.warning(FormDataKelasActivity.this, "Nama Kelas Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (kompetensi.isEmpty()){
            Toasty.warning(FormDataKelasActivity.this, "Kompetensi Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else{
            updateKelas(kelas);
        }
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, FormDataKelasActivity.class);
    }
}
