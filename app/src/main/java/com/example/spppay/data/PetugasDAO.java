package com.example.spppay.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.spppay.model.Petugas;

@Dao
public interface PetugasDAO {

    @Query("SELECT * FROM petugas")
    Petugas[] selectAllPetugas();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertPetugas(Petugas petugas);

    @Update
    int updatePetugas(Petugas petugas);

    @Delete
    void deletePetugas(Petugas petugas);

    @Query("SELECT * FROM petugas WHERE id_petugas = :id LIMIT 1")
    Petugas selectPetugasDetail(int id);
}
