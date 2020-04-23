package com.example.spppay.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "kelas")
public class Kelas implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_kelas")
    public int id_kelas;

    @ColumnInfo(name = "nama_kelas")
    public String nama_kelas;

    @ColumnInfo(name = "komptensi_keahlian")
    public String kompetensi_keahlian;

    public int getId_kelas() {
        return id_kelas;
    }

    public void setId_kelas(int id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getKompetensi_keahlian() {
        return kompetensi_keahlian;
    }

    public void setKompetensi_keahlian(String kompetensi_keahlian) {
        this.kompetensi_keahlian = kompetensi_keahlian;
    }
}
