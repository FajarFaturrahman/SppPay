package com.example.spppay.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "siswa")
public class Siswa implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "nisn")
    public int nisn;

    @ColumnInfo(name = "nis")
    public String nis;

    @ColumnInfo(name = "nama")
    public String nama;

    @ColumnInfo(name = "nama_kelas_siswa")
    public String nama_kelas_siswa;

    @ColumnInfo(name = "alamat")
    public String alamat;

    @ColumnInfo(name = "no_telp")
    public String no_telp;

    @ColumnInfo(name = "Uid_spp")
    public int Uid_spp;

    public int getNisn() {
        return nisn;
    }

    public void setNisn(int nisn) {
        this.nisn = nisn;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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


    public void setNama_kelas_siswa(String nama_kelas_siswa) {
        this.nama_kelas_siswa = nama_kelas_siswa;
    }

    public void setUid_spp(int uid_spp) {
        Uid_spp = uid_spp;
    }

    public String getNama_kelas_siswa() {
        return nama_kelas_siswa;
    }

    public int getUid_spp() {
        return Uid_spp;
    }

}

