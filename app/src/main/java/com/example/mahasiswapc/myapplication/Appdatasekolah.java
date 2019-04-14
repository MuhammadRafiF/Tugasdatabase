package com.example.mahasiswapc.myapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.mahasiswapc.myapplication.DataSekolah;

/**
 * Angarsa Labs...!
 * Created by Angga on 13/08/2018.
 */
@Database(entities = {DataSekolah.class} , version = 1)
public abstract class Appdatasekolah extends RoomDatabase {
    public abstract DataSiswaDAO dao();
    private static Appdatasekolah appDatabase;

    public static Appdatasekolah iniDb(Context context){
        if(appDatabase == null)
            appDatabase = Room.databaseBuilder(context, Appdatasekolah.class,
                    "dbUser").allowMainThreadQueries().build();

        return appDatabase;
    }

    public static void destroyInstance() {
        appDatabase = null;
    }
}
