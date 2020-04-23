package com.example.spppay.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.spppay.model.Pembayaran;
import com.example.spppay.model.Petugas;
import com.example.spppay.model.Spp;

@Dao
public interface PembayaranDAO {

    @Query("SELECT * FROM pembayaran")
    Pembayaran[] selectAllpembayaran();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertPembayaran(Pembayaran pembayaran);

}
