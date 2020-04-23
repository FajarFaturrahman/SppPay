package com.example.spppay.adapter;

import android.app.Activity;
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
import com.example.spppay.admin.datapetugas.DetailDataPetugasActivity;
import com.example.spppay.admin.datasiswa.DetailDataSiswaActivity;
import com.example.spppay.admin.datasiswa.FormDataSiswaActivity;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Petugas;
import com.example.spppay.model.Siswa;

import java.util.ArrayList;

public class AdapterSiswaRecyclerView extends RecyclerView.Adapter<AdapterSiswaRecyclerView.ViewHolder> {

    private ArrayList<Siswa> daftarSiswa;
    private Context context;
    private AppDatabase db;
    String nis,namaSiswa,kelas;

    public AdapterSiswaRecyclerView(ArrayList<Siswa> daftarSiswa, Context context) {
        this.daftarSiswa = daftarSiswa;
        this.context = context;

        db = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "siswadb").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_siswa_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        namaSiswa   = daftarSiswa.get(position).getNama();
        nis         = daftarSiswa.get(position).getNis();
        kelas       = daftarSiswa.get(position).getNama_kelas_siswa();

        holder.tvNamaSiswa.setText(namaSiswa);
        holder.tvNis.setText(nis);
        holder.tvKelas.setText(kelas);

        holder.cvSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Siswa siswa = db.siswaDAO().selectSiswaDetail(daftarSiswa.get(position).getNisn());
                context.startActivity(DetailDataSiswaActivity.getActIntent((Activity) context).putExtra("datasiswa", siswa));
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateSiswa(position);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteSiswa(position);
            }
        });
    }

    private void onUpdateSiswa(int position){
        context.startActivity(FormDataSiswaActivity.getActIntent((Activity) context).putExtra("datasiswa", daftarSiswa.get(position)));
    }

    private void onDeleteSiswa(int position){
        db.siswaDAO().deleteSiswa(daftarSiswa.get(position));
        daftarSiswa.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, daftarSiswa.size());
    }

    @Override
    public int getItemCount() {
        return daftarSiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cvSiswa;
        TextView tvNamaSiswa, tvNis, tvKelas;
        ImageButton btnDelete, btnEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cvSiswa         = itemView.findViewById(R.id.cvDataSiswa);
            tvNamaSiswa     = itemView.findViewById(R.id.tv_nama_siswa);
            tvNis           = itemView.findViewById(R.id.tv_nis);
            tvKelas         = itemView.findViewById(R.id.tv_kelas);
            btnDelete       = itemView.findViewById(R.id.btn_delete_siswa);
            btnEdit         = itemView.findViewById(R.id.btn_edit_siswa);
        }
    }
}
