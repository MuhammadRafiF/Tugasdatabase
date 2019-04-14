package com.example.mahasiswapc.myapplication;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import com.example.mahasiswapc.myapplication.DataSekolah;
import com.example.mahasiswapc.myapplication.Appdatasekolah;
import java.util.List;


/**
 * Angarsa Labs...!
 * Created by Angga on 13/08/2018.
 */
public class MainPresenter implements MainContract.presenter {
    private MainContract.view view;

    public MainPresenter(MainContract.view view) {
        this.view = view;
    }

    class InsertData extends AsyncTask<Void, Void, Long>{
        private Appdatasekolah database;
        private DataSekolah dataDiri;

        public InsertData(Appdatasekolah database, DataSekolah dataDiri) {
            this.database = database;
            this.dataDiri = dataDiri;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return database.dao().insertData(dataDiri);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            view.successAdd();
        }

    }

    @Override
    public void insertData(String nama, String alamat, String siswa, String guru,
                           final Appdatasekolah database) {
        final DataSekolah dataDiri = new DataSekolah();
        dataDiri.setAlamat(alamat);
        dataDiri.setJumlahsiswa(siswa);
        dataDiri.setNamasekolah(nama);
        dataDiri.setJumlahguru(guru);
        new InsertData(database, dataDiri).execute();
    }

    @Override
    public void readData(Appdatasekolah database) {
        List list;
        list = database.dao().getData();
        view.getData(list);
    }

    class EditData extends AsyncTask<Void, Void, Integer> {
        private Appdatasekolah database;
        private DataSekolah dataDiri;

        public EditData(Appdatasekolah database, DataSekolah dataDiri) {
            this.database = database;
            this.dataDiri = dataDiri;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return database.dao().updateData(dataDiri);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute: " + integer);
            view.successAdd();
        }
    }

    @Override
    public void editData(String nama, String alamat, String siswa, String guru, int id,
                         final Appdatasekolah database) {
        final DataSekolah dataDiri = new DataSekolah();
        dataDiri.setAlamat(alamat);
        dataDiri.setJumlahsiswa(siswa);
        dataDiri.setNamasekolah(nama);
        dataDiri.setJumlahguru(guru);
        dataDiri.setId(id);

        new EditData(database, dataDiri).execute();
    }

    class DeleteData extends AsyncTask<Void, Void, Void>{
        private Appdatasekolah database;
        private DataSekolah dataDiri;

        public DeleteData(Appdatasekolah database, DataSekolah dataDiri) {
            this.database = database;
            this.dataDiri = dataDiri;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.dao().deleteData(dataDiri);
            return  null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            view.successDelete();
        }

    }

    @Override
    public void deleteData(final DataSekolah dataDiri,
                           final Appdatasekolah database) {
        new DeleteData(database, dataDiri).execute();
    }

}
