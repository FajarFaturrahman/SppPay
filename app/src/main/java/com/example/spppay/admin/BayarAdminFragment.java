package com.example.spppay.admin;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spppay.R;
import com.example.spppay.admin.datapetugas.DataPetugasActivity;
import com.example.spppay.admin.datapetugas.FormDataPetugasActivity;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Pembayaran;
import com.example.spppay.model.Petugas;

import es.dmoral.toasty.Toasty;


public class BayarAdminFragment extends Fragment {

    private AppDatabase db;
    EditText edtPetugas, edtNisn, edtTglBayar, edtThnBayar, edtIdSpp, edtJmlBayar, edtSiswa;
    Spinner bulanBayar;
    Button btnBayar;
    String namaPetugas, tglBayar, blnBayar, namaSiswa;
    Integer nisn, thnBayar, idSpp, jmlBayar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View view = inflater.inflate(R.layout.fragment_bayar_admin, container, false);

         db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "bayardb").build();

         edtPetugas = view.findViewById(R.id.edt_bayar_petugas);
         edtNisn    = view.findViewById(R.id.edt_nisn_bayar);
         edtSiswa   = view.findViewById(R.id.edt_siswa_bayar);
         edtTglBayar= view.findViewById(R.id.tgl_bayar);
         bulanBayar = view.findViewById(R.id.spin_bulan_bayar);
         edtThnBayar= view.findViewById(R.id.edt_tahun_bayar);
         edtIdSpp   = view.findViewById(R.id.edt_id_spp_bayar);
         edtJmlBayar= view.findViewById(R.id.edt_jml_bayar);
         btnBayar   = view.findViewById(R.id.btn_bayar_admin);

         btnBayar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 afterClickPayButton();
             }
         });

        return view;
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
                Intent intent = new Intent(getActivity(), PaymentSuccess.class);
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
            Toasty.warning(getActivity(), "Nama Petugas Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (nisn.equals(null)){
            Toasty.warning(getActivity(), "NISN Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (namaSiswa.isEmpty()){
            Toasty.warning(getActivity(), "Nama Siswa Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (tglBayar.isEmpty()){
            Toasty.warning(getActivity(), "Tanggal Bayar Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (thnBayar.equals(null)){
            Toasty.warning(getActivity(), "Tahun Bayar Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (idSpp.equals(null)){
            Toasty.warning(getActivity(), "ID SPP Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else if (jmlBayar.equals(null)){
            Toasty.warning(getActivity(), "Jumlah Bayar Harus Diisi", Toast.LENGTH_SHORT, true).show();
        }else{
            insertPembayaran(pembayaran);
        }
    }
}
