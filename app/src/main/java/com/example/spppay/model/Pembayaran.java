package com.example.spppay.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "pembayaran")
public class Pembayaran implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_pembayaran")
    public int id_pembayaran;

    @ColumnInfo(name = "nama_petugas")
    public String nama_petugas;

    @ColumnInfo(name = "nisn")
    public int nisn;

    @ColumnInfo(name = "nama_siswa")
    public String nama_siswa;

    @ColumnInfo(name = "tgl_bayar")
    public String tgl_bayar;

    @ColumnInfo(name = "bulan_bayar")
    public String bulan_bayar;

    @ColumnInfo(name = "tahun_bayar")
    public int tahun_bayar;

    @ColumnInfo(name = "id_spp")
    public int id_spp;

    @ColumnInfo(name = "jml_bayar")
    public int jml_bayar;

    public int getId_pembayaran() {
        return id_pembayaran;
    }

    public void setId_pembayaran(int id_pembayaran) {
        this.id_pembayaran = id_pembayaran;
    }

    public String getNama_petugas() {
        return nama_petugas;
    }

    public void setNama_petugas(String nama_petugas) {
        this.nama_petugas = nama_petugas;
    }

    public int getNisn() {
        return nisn;
    }

    public void setNisn(int nisn) {
        this.nisn = nisn;
    }

    public String getTgl_bayar() {
        return tgl_bayar;
    }

    public void setTgl_bayar(String tgl_bayar) {
        this.tgl_bayar = tgl_bayar;
    }

    public String getBulan_bayar() {
        return bulan_bayar;
    }

    public void setBulan_bayar(String bulan_bayar) {
        this.bulan_bayar = bulan_bayar;
    }

    public int getTahun_bayar() {
        return tahun_bayar;
    }

    public void setTahun_bayar(int tahun_bayar) {
        this.tahun_bayar = tahun_bayar;
    }

    public int getId_spp() {
        return id_spp;
    }

    public void setId_spp(int id_spp) {
        this.id_spp = id_spp;
    }

    public int getJml_bayar() {
        return jml_bayar;
    }

    public void setJml_bayar(int jml_bayar) {
        this.jml_bayar = jml_bayar;
    }

    public String getNama_siswa() {
        return nama_siswa;
    }

    public void setNama_siswa(String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }
}
