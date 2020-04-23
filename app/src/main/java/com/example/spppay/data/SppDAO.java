package com.example.spppay.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.spppay.model.Kelas;
import com.example.spppay.model.Spp;

@Dao
public interface SppDAO {

    @Query("SELECT * FROM spp")
    Spp[] selectAllSpp();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertSpp(Spp spp);

    @Update
    int updateSpp(Spp spp);

    @Delete
    void deleteSpp(Spp spp);
}
