package com.example.spppay.admin.datakelas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spppay.R;
import com.example.spppay.admin.dataspp.FormDataSppActivity;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Kelas;

public class FormDataKelasActivity extends AppCompatActivity {

    private AppDatabase db;
    EditText edtNamaKelas, edtKompetensiKeahlian;
    Button btnSimpanKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_data_kelas);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "kelasdb").build();

        edtNamaKelas            = findViewById(R.id.edt_nama_kelas);
        edtKompetensiKeahlian   = findViewById(R.id.edt_kompetensi_keahlian);
        btnSimpanKelas          = findViewById(R.id.btn_simpan_kelas);

        btnSimpanKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kelas kelas = new Kelas();
                kelas.setNama_kelas(edtNamaKelas.getText().toString());
                kelas.setKompetensi_keahlian(edtKompetensiKeahlian.getText().toString());
                insertKelas(kelas);
            }
        });
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
                Toast.makeText(FormDataKelasActivity.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FormDataKelasActivity.this, DataKelasActivity.class);
                startActivity(intent);
            }
        }.execute();
    }
}
