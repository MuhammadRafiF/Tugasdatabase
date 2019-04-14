package com.example.mahasiswapc.myapplication;

import android.view.View;
import android.os.Build;
import android.support.annotation.RequiresApi;
import com.example.mahasiswapc.myapplication.Appdatasekolah;
import com.example.mahasiswapc.myapplication.DataSekolah;
import java.util.List;

public interface MainContract {

    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataSekolah> list);
        void editData(DataSekolah item);
        void deleteData(DataSekolah item);
    }
    interface presenter {
        void insertData(String nama, String alamat, String siswa, String guru, Appdatasekolah database);
        void readData(Appdatasekolah database);
        void editData(String nama, String alamat, String siswa, String guru, int id, Appdatasekolah database);
        void deleteData(DataSekolah dataDiri, Appdatasekolah database);
    }
}

