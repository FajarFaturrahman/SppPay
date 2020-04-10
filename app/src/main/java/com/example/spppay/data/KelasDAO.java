package com.example.spppay.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.spppay.model.Kelas;

import java.util.List;

@Dao
public interface KelasDAO {

    @Query("SELECT * FROM kelas")
    Kelas[] selectAllKelas();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertKelas(Kelas kelas);

    @Delete
    public void deleteKelas(Kelas kelas);
}
