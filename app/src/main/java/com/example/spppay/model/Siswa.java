package com.example.spppay.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "siswa")
public class Siswa implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "nisn")
    public char nisn;

    @ColumnInfo(name = "nis")
    public char nis;

    @ColumnInfo(name = "nama")
    public String nama;

    @ColumnInfo(name = "id_kelas")
    public int id_kelas;

    @ColumnInfo(name = "alamat")
    public String alamat;

    @ColumnInfo(name = "no_telp")
    public String no_telp;

    @ColumnInfo(name = "id_spp")
    public int id_spp;

    public char getNisn() {
        return nisn;
    }

    public void setNisn(char nisn) {
        this.nisn = nisn;
    }

    public char getNis() {
        return nis;
    }

    public void setNis(char nis) {
        this.nis = nis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getId_kelas() {
        return id_kelas;
    }

    public void setId_kelas(int id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public int getId_spp() {
        return id_spp;
    }

    public void setId_spp(int id_spp) {
        this.id_spp = id_spp;
    }
}
