package com.example.mahasiswapc.myapplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DataSiswaDAO {
    @Insert
    Long insertData(DataSekolah datasekolah);

    @Query("Select * from sekolah_db")
    List<DataSekolah> getData();

    @Update
    int updateData(DataSekolah item);

    @Delete
    void deleteData(DataSekolah item);
}