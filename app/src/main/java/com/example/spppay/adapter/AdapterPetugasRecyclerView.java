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
import com.example.spppay.admin.datapetugas.FormDataPetugasActivity;
import com.example.spppay.admin.dataspp.FormDataSppActivity;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Petugas;
import com.example.spppay.model.Spp;

import java.util.ArrayList;

public class AdapterPetugasRecyclerView extends RecyclerView.Adapter<AdapterPetugasRecyclerView.ViewHolder> {

    private ArrayList<Petugas> daftarPetugas;
    private Context context;
    private AppDatabase db;
    String nama, username, role;

    public AdapterPetugasRecyclerView(ArrayList<Petugas> daftarPetugas, Context context) {
        this.daftarPetugas = daftarPetugas;
        this.context = context;

        db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "petugasdb").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public AdapterPetugasRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_petugas_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPetugasRecyclerView.ViewHolder holder, final int position) {

        nama        = daftarPetugas.get(position).getNama_petugas();
        username    = daftarPetugas.get(position).getUsername();
        role    = daftarPetugas.get(position).getLevel();

        holder.tvNama.setText(nama);
        holder.tvUsername.setText(username);
        holder.tvRole.setText(role);

        holder.cvPetugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Petugas petugas = db.petugasDAO().selectPetugasDetail(daftarPetugas.get(position).getId_petugas());
                context.startActivity(DetailDataPetugasActivity.getActIntent((Activity) context).putExtra("datapetugas", petugas));
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdatePetugas(position);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeletePetugas(position);
            }
        });
    }

    private void onUpdatePetugas(int position){
        context.startActivity(FormDataPetugasActivity.getActIntent((Activity) context).putExtra("datapetugas", daftarPetugas.get(position)));
    }

    private void onDeletePetugas(int position){
        db.petugasDAO().deletePetugas(daftarPetugas.get(position));
        daftarPetugas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, daftarPetugas.size());
    }


    @Override
    public int getItemCount() {
        return daftarPetugas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama, tvUsername, tvRole;
        ImageButton btnDelete, btnEdit;
        CardView cvPetugas;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama      = itemView.findViewById(R.id.tv_nama_petugas);
            tvUsername  = itemView.findViewById(R.id.tv_username);
            tvRole      = itemView.findViewById(R.id.tv_role);
            btnDelete   = itemView.findViewById(R.id.btn_delete);
            btnEdit     = itemView.findViewById(R.id.btn_edit);
            cvPetugas   = itemView.findViewById(R.id.cvDataPetugas);
        }
    }
}
