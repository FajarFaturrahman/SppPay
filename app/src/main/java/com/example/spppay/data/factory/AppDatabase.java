package com.example.spppay.data.factory;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.spppay.data.KelasDAO;
import com.example.spppay.model.Kelas;

import okhttp3.internal.Version;

@Database(entities = {Kelas.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract KelasDAO kelasDAO();
}
