package com.example.spppay.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "spp")
public class Spp implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_spp")
    public int id_spp;

    @ColumnInfo(name = "tahun")
    public int tahun;

    @ColumnInfo(name = "nominal")
    public int nominal;

    public int getId_spp() {
        return id_spp;
    }

    public void setId_spp(int id_spp) {
        this.id_spp = id_spp;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

}
