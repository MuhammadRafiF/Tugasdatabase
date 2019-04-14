package com.example.mahasiswapc.myapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "Sekolah_db")
public class DataSekolah {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "namasekolah")
    private String namasekolah;

    @ColumnInfo(name = "alamat")
    private String alamat;

    @ColumnInfo(name = "jumlahsiswa")
    private String jumlahsiswa;

    @ColumnInfo(name = "jumlahguru")
    private String jumlahguru;

    @NonNull
    public int getId() {return id;}
    public void setId(@NonNull int id) {this.id=id;}

    public String getNamasekolah() { return namasekolah; }
    public void setNamasekolah(String namasekolah) { this.namasekolah = namasekolah; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getJumlahsiswa() { return jumlahsiswa; }
    public void setJumlahsiswa(String jumlahsiswa) { this.jumlahsiswa = jumlahsiswa; }

    public String getJumlahguru() { return jumlahguru; }
    public void setJumlahguru(String jumlahguru) { this.jumlahguru = jumlahguru; }
}
