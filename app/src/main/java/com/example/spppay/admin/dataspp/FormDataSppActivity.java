package com.example.spppay.admin.dataspp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spppay.R;
import com.example.spppay.admin.datakelas.DataKelasActivity;
import com.example.spppay.admin.datakelas.FormDataKelasActivity;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Spp;

import es.dmoral.toasty.Toasty;

public class FormDataSppActivity extends AppCompatActivity {

    private AppDatabase db;
    EditText edtTahun, edtNominal;
    Button btnSimpanSpp;
    Integer tahun,nominal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_data_spp);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "sppdb").build();

        final Spp spp = (Spp) getIntent().getSerializableExtra("dataspp");
        edtTahun    = findViewById(R.id.tahun_spp);
        edtNominal  = findViewById(R.id.nominal_spp);
        btnSimpanSpp   = findViewById(R.id.simpan_data_spp);

        if (spp!=null){
            edtTahun.setText(Integer.toString(spp.getTahun()));
            edtNominal.setText(Integer.toString(spp.getNominal()));
            btnSimpanSpp.setText("UBAH");
            btnSimpanSpp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    afterClickUpdateButton();
                }
            });
        }else {
            btnSimpanSpp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    afterClickInsertButton();
                }
            });
        }
    }

    private void insertSpp(final Spp spp){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.sppDAO().insertSpp(spp);
                return status;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                Toasty.success(FormDataSppActivity.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT, true).show();
                Intent intent = new Intent(FormDataSppActivity.this, DataSppActivity.class);
                startActivity(intent);
            }
        }.execute();
    }

    private void UpdateSpp(final Spp spp){
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.sppDAO().updateSpp(spp);
                return status;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                Toasty.success(FormDataSppActivity.this, "Data Berhasil Diubah", Toast.LENGTH_SHORT, true).show();
                Intent intent = new Intent(FormDataSppActivity.this, DataSppActivity.class);
                startActivity(intent);
            }
        }.execute();
    }



    private void afterClickInsertButton(){
        tahun = Integer.parseInt(edtTahun.getText().toString());
        nominal = Integer.parseInt(edtNominal.getText().toString());

        Spp spp = new Spp();
        spp.setTahun(tahun);
        spp.setNominal(nominal);

        if (tahun.equals(null)){
            Toasty.warning(FormDataSppActivity.this, "Nama Kelas Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (nominal.equals(null)){
            Toasty.warning(FormDataSppActivity.this, "Kompetensi Keahlian Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else{
            insertSpp(spp);
        }
    }

    private void afterClickUpdateButton(){
        final Spp spp = (Spp) getIntent().getSerializableExtra("dataspp");

        tahun = Integer.parseInt(edtTahun.getText().toString());
        nominal = Integer.parseInt(edtNominal.getText().toString());

        spp.setTahun(tahun);
        spp.setNominal(nominal);

        if(tahun.equals(null)){
            Toasty.warning(FormDataSppActivity.this, "Tahun Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (nominal.equals(null)){
            Toasty.warning(FormDataSppActivity.this, "Nominal Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else{
            UpdateSpp(spp);
        }
    }


    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, FormDataSppActivity.class);
    }
}
