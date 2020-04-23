package com.example.spppay.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.spppay.model.Kelas;
import com.example.spppay.model.Siswa;

import java.util.List;

@Dao
public interface SiswaDAO {

    @Query("SELECT * FROM siswa")
    Siswa[] selectAllSiswa();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSiswa(Siswa siswa);

    @Update
    int updateSiswa(Siswa siswa);

    @Delete
    void deleteSiswa(Siswa siswa);

    @Query("SELECT * FROM siswa WHERE nisn = :nisn LIMIT 1")
    Siswa selectSiswaDetail(int nisn);

}
