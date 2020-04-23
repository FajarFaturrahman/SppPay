package com.example.spppay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.spppay.R;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Pembayaran;

import java.util.ArrayList;

public class AdapterRiwayatRecyclerView extends RecyclerView.Adapter<AdapterRiwayatRecyclerView.ViewHolder> {

    private ArrayList<Pembayaran> daftarRiwayat;
    private AppDatabase db;
    private Context context;
    String namaSiswa, namaPetugas, tglBayar;
    Integer jmlBayar, nisn;

    public AdapterRiwayatRecyclerView(ArrayList<Pembayaran> daftarRiwayat, Context context) {
        this.daftarRiwayat = daftarRiwayat;
        this.context = context;

        db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "pembayarandb").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        namaSiswa   = daftarRiwayat.get(position).getNama_siswa();
        namaPetugas = daftarRiwayat.get(position).getNama_petugas();
        tglBayar    = daftarRiwayat.get(position).getTgl_bayar();
        jmlBayar    = daftarRiwayat.get(position).getJml_bayar();
        nisn        = daftarRiwayat.get(position).getNisn();

        holder.tvNamaSiswa.setText(namaSiswa);
        holder.tvNamaPetugas.setText(namaPetugas);
        holder.tvTglBayar.setText(tglBayar);
        holder.tvJmlBayar.setText(Integer.toString(jmlBayar));
        holder.tvNisn.setText(Integer.toString(nisn));
    }

    @Override
    public int getItemCount() {
        return daftarRiwayat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaSiswa, tvNisn, tvJmlBayar, tvNamaPetugas, tvTglBayar;
        CardView cvRiwayat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cvRiwayat       = itemView.findViewById(R.id.cv_riwayat);
            tvNamaSiswa     = itemView.findViewById(R.id.nama_siswa);
            tvNisn          = itemView.findViewById(R.id.nisn);
            tvJmlBayar      = itemView.findViewById(R.id.jumlah_bayar);
            tvNamaPetugas   = itemView.findViewById(R.id.nama_petugas);
            tvTglBayar      = itemView.findViewById(R.id.tanggal_bayar);
        }
    }
}
