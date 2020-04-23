package com.example.spppay.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "petugas")
public class Petugas implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_petugas")
    public int id_petugas;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "nama_petugas")
    public String nama_petugas;

    @ColumnInfo(name = "level")
    public String level;

    public int getId_petugas() {
        return id_petugas;
    }

    public void setId_petugas(int id_petugas) {
        this.id_petugas = id_petugas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama_petugas() {
        return nama_petugas;
    }

    public void setNama_petugas(String nama_petugas) {
        this.nama_petugas = nama_petugas;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
