package com.example.spppay.petugas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spppay.R;
import com.example.spppay.admin.PaymentSuccess;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Pembayaran;

import es.dmoral.toasty.Toasty;

public class PembayaranPetugas extends AppCompatActivity {

    private AppDatabase db;
    EditText edtPetugas, edtNisn, edtTglBayar, edtThnBayar, edtIdSpp, edtJmlBayar, edtSiswa;
    Spinner bulanBayar;
    Button btnBayar;
    String namaPetugas, tglBayar, blnBayar, namaSiswa;
    Integer nisn, thnBayar, idSpp, jmlBayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_petugas);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "pembayarandb").build();

        edtPetugas = findViewById(R.id.edt_bayar_petugas);
        edtNisn    = findViewById(R.id.edt_nisn_bayar);
        edtSiswa   = findViewById(R.id.edt_siswa_bayar);
        edtTglBayar= findViewById(R.id.tgl_bayar);
        bulanBayar = findViewById(R.id.spin_bulan_bayar);
        edtThnBayar= findViewById(R.id.edt_tahun_bayar);
        edtIdSpp   = findViewById(R.id.edt_id_spp_bayar);
        edtJmlBayar= findViewById(R.id.edt_jml_bayar);
        btnBayar   = findViewById(R.id.btn_bayar_admin);

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afterClickPayButton();
            }
        });
    }

    private void insertPembayaran(final Pembayaran pembayaran){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.pembayaranDAO().insertPembayaran(pembayaran);
                return status;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                Intent intent = new Intent(PembayaranPetugas.this, PaymentSuccess.class);
                startActivity(intent);
            }
        }.execute();
    }

    private void afterClickPayButton(){
        namaPetugas = edtPetugas.getText().toString();
        nisn        = Integer.parseInt(edtNisn.getText().toString());
        namaSiswa = edtSiswa.getText().toString();
        tglBayar    = edtTglBayar.getText().toString();
        blnBayar    = bulanBayar.getSelectedItem().toString();
        thnBayar    = Integer.parseInt(edtThnBayar.getText().toString());
        idSpp       = Integer.parseInt(edtIdSpp.getText().toString());
        jmlBayar    = Integer.parseInt(edtJmlBayar.getText().toString());

        Pembayaran pembayaran = new Pembayaran();
        pembayaran.setNama_petugas(namaPetugas);
        pembayaran.setNisn(nisn);
        pembayaran.setNama_siswa(namaSiswa);
        pembayaran.setTgl_bayar(tglBayar);
        pembayaran.setBulan_bayar(blnBayar);
        pembayaran.setTahun_bayar(thnBayar);
        pembayaran.setId_spp(idSpp);
        pembayaran.setJml_bayar(jmlBayar);

        if (namaPetugas.isEmpty()){
            Toasty.warning(PembayaranPetugas.this, "Nama Petugas Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (nisn.equals(null)){
            Toasty.warning(PembayaranPetugas.this, "NISN Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (namaSiswa.isEmpty()){
            Toasty.warning(PembayaranPetugas.this, "Nama Siswa Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (tglBayar.isEmpty()){
            Toasty.warning(PembayaranPetugas.this, "Tanggal Bayar Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (thnBayar.equals(null)){
            Toasty.warning(PembayaranPetugas.this, "Tahun Bayar Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (idSpp.equals(null)){
            Toasty.warning(PembayaranPetugas.this, "ID SPP Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (jmlBayar.equals(null)){
            Toasty.warning(PembayaranPetugas.this, "Jumlah Bayar Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else{
            insertPembayaran(pembayaran);
        }
    }
}
