package com.example.spppay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.spppay.R;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Kelas;

import java.util.ArrayList;

public class AdapterKelasRecyclerView extends RecyclerView.Adapter<AdapterKelasRecyclerView.ViewHolder> {

    private ArrayList<Kelas> daftarKelas;
    private Context context;
    private AppDatabase db;
    String namaKelas, kompetensi;

    public AdapterKelasRecyclerView(ArrayList<Kelas> daftarKelas, Context context) {
        this.daftarKelas    = daftarKelas;
        this.context        = context;

        db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "kelasdb").allowMainThreadQueries().build();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_kelas_item, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKelasRecyclerView.ViewHolder holder, final int position) {

        namaKelas  = daftarKelas.get(position).getNama_kelas();
        kompetensi = daftarKelas.get(position).getKompetensi_keahlian();

        holder.tvNamaKelas.setText(namaKelas);
        holder.tvKompetensi.setText(kompetensi);
    }

    @Override
    public int getItemCount() {
        return daftarKelas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaKelas, tvKompetensi;
        ImageButton btnDelete, btnEdit;
        CardView cvKelas;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaKelas     = itemView.findViewById(R.id.tv_nama_kelas);
            tvKompetensi    = itemView.findViewById(R.id.tv_nama_kompetensi);
            btnDelete       = itemView.findViewById(R.id.btn_delete);
            btnEdit         = itemView.findViewById(R.id.btn_edit);
            cvKelas         = itemView.findViewById(R.id.cvDataKelas);
        }
    }
}
