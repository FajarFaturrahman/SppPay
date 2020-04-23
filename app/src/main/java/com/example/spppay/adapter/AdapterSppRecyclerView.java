package com.example.spppay.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.example.spppay.admin.dataspp.FormDataSppActivity;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Spp;

import java.util.ArrayList;

public class AdapterSppRecyclerView extends RecyclerView.Adapter<AdapterSppRecyclerView.ViewHolder> {

    private ArrayList<Spp> daftarSpp;
    private Context context;
    private AppDatabase db;
    Integer tahun, nominal;

    public AdapterSppRecyclerView(ArrayList<Spp> daftarSpp, Context context) {
        this.daftarSpp = daftarSpp;
        this.context = context;

        db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "sppdb").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public AdapterSppRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_spp_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSppRecyclerView.ViewHolder holder, final int position) {

        tahun   = daftarSpp.get(position).getTahun();
        nominal = daftarSpp.get(position).getNominal();

        holder.tvTahun.setText(Integer.toString(tahun));
        holder.tvNominal.setText(Integer.toString(nominal));

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateSpp(position);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteSpp(position);
            }
        });
    }

    private void onUpdateSpp(int position){
        context.startActivity(FormDataSppActivity.getActIntent((Activity) context).putExtra("dataspp", daftarSpp.get(position)));
    }

    private void onDeleteSpp(int position){
        db.sppDAO().deleteSpp(daftarSpp.get(position));
        daftarSpp.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, daftarSpp.size());
    }


    @Override
    public int getItemCount() {
        return daftarSpp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTahun, tvNominal;
        ImageButton btnDelete, btnEdit;
        CardView cvSpp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTahun     = itemView.findViewById(R.id.tv_tahun_spp);
            tvNominal     = itemView.findViewById(R.id.tv_nominal);
            btnDelete   = itemView.findViewById(R.id.btn_delete);
            btnEdit     = itemView.findViewById(R.id.btn_edit);
            cvSpp       = itemView.findViewById(R.id.cvDataSpp);
        }
    }
}
