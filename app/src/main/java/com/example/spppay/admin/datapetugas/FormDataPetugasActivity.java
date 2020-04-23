package com.example.spppay.admin.datapetugas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spppay.R;
import com.example.spppay.admin.datakelas.DataKelasActivity;
import com.example.spppay.admin.datakelas.FormDataKelasActivity;
import com.example.spppay.admin.dataspp.FormDataSppActivity;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Petugas;
import com.example.spppay.model.Spp;

import es.dmoral.toasty.Toasty;

public class FormDataPetugasActivity extends AppCompatActivity {

    private AppDatabase db;
    EditText edtUsername, edtNamaPetugas, edtPassword;
    Spinner spinnerLevel;
    Button btnSimpanPetugas;
    String username, namaPetugas, password, level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_data_petugas);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "petugasdb").build();

        edtUsername         = findViewById(R.id.edt_username_petugas);
        edtNamaPetugas      = findViewById(R.id.edt_nama_petugas);
        edtPassword         = findViewById(R.id.edt_kata_sandi_petugas);
        spinnerLevel        = findViewById(R.id.spin_level_petugas);
        btnSimpanPetugas    = findViewById(R.id.simpan_data_petugas);

        final Petugas petugas = (Petugas) getIntent().getSerializableExtra("datapetugas");

        if (petugas!=null){
            edtUsername.setText(petugas.getUsername());
            edtNamaPetugas.setText(petugas.getNama_petugas());
            edtPassword.setText(petugas.getPassword());
//            spinnerLevel.setSelection(Integer.parseInt(petugas.getLevel()));
            btnSimpanPetugas.setText("UBAH");
            btnSimpanPetugas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    afterClickUpdateButton();
                }
            });
        }else {
            btnSimpanPetugas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    afterClickInsertButton();
                }
            });
        }
    }

    private void insertPetugas(final Petugas petugas){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.petugasDAO().insertPetugas(petugas);
                return status;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                Toasty.success(FormDataPetugasActivity.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT, true).show();
                Intent intent = new Intent(FormDataPetugasActivity.this, DataPetugasActivity.class);
                startActivity(intent);
            }
        }.execute();
    }

    private void UpdatePetugas(final Petugas petugas){
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.petugasDAO().updatePetugas(petugas);
                return status;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                Toasty.success(FormDataPetugasActivity.this, "Data Berhasil Diubah", Toast.LENGTH_SHORT, true).show();
                Intent intent = new Intent(FormDataPetugasActivity.this, DataPetugasActivity.class);
                startActivity(intent);
            }
        }.execute();
    }

    private void afterClickInsertButton(){
        namaPetugas = edtNamaPetugas.getText().toString();
        username    = edtUsername.getText().toString();
        level       = spinnerLevel.getSelectedItem().toString();
        password    = edtPassword.getText().toString();

        Petugas petugas = new Petugas();
        petugas.setNama_petugas(namaPetugas);
        petugas.setUsername(username);
        petugas.setLevel(level);
        petugas.setPassword(password);

        if (username.isEmpty()){
            Toasty.warning(FormDataPetugasActivity.this, "Username Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (namaPetugas.isEmpty()){
            Toasty.warning(FormDataPetugasActivity.this, "Nama Petugas Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (password.isEmpty()){
            Toasty.warning(FormDataPetugasActivity.this, "Password Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else{
            insertPetugas(petugas);
        }
    }

    private void afterClickUpdateButton(){
        final Petugas petugas = (Petugas) getIntent().getSerializableExtra("datapetugas");

        namaPetugas = edtNamaPetugas.getText().toString();
        username    = edtUsername.getText().toString();
        level       = spinnerLevel.getSelectedItem().toString();
        password    = edtPassword.getText().toString();

        petugas.setNama_petugas(namaPetugas);
        petugas.setUsername(username);
        petugas.setPassword(password);
        petugas.setLevel(level);

        if(username.isEmpty()){
            Toasty.warning(FormDataPetugasActivity.this, "Username Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (namaPetugas.isEmpty()){
            Toasty.warning(FormDataPetugasActivity.this, "Nama Petugas Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (password.isEmpty()){
            Toasty.warning(FormDataPetugasActivity.this, "Password Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else{
            UpdatePetugas(petugas);
        }
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, FormDataPetugasActivity.class);
    }
}
