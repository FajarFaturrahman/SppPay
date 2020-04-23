package com.example.spppay.data.factory;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.spppay.data.KelasDAO;
import com.example.spppay.data.PembayaranDAO;
import com.example.spppay.data.PetugasDAO;
//import com.example.spppay.data.SiswaDAO;
import com.example.spppay.data.SiswaDAO;
import com.example.spppay.data.SppDAO;
import com.example.spppay.model.Kelas;
//import com.example.spppay.model.Pembayaran;
import com.example.spppay.model.Pembayaran;
import com.example.spppay.model.Petugas;
import com.example.spppay.model.Siswa;
import com.example.spppay.model.Spp;

import okhttp3.internal.Version;

@Database(entities = {Kelas.class, Spp.class, Siswa.class, Petugas.class, Pembayaran.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract KelasDAO kelasDAO();

    public abstract SppDAO sppDAO();

    public abstract PetugasDAO petugasDAO();

    public abstract SiswaDAO siswaDAO();

    public abstract PembayaranDAO pembayaranDAO();
}
