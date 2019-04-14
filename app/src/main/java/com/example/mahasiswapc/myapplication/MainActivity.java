package com.example.mahasiswapc.myapplication;

import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.mahasiswapc.myapplication.R;
import com.example.mahasiswapc.myapplication.DataSekolah;
import com.example.mahasiswapc.myapplication.Appdatasekolah;
import com.example.mahasiswapc.myapplication.MainAdapter;
import android.widget.Toast;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MainContract.view {
    private Appdatasekolah appDatabase;
    private MainPresenter presenter;
    private MainAdapter adapter;

    private Button btnOK;
    private RecyclerView recyclerView;
    private EditText tvNama, tvAlamat, tvSiswa, tvGuru;

    private char gender;
    private boolean edit = false;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = Appdatasekolah.iniDb(getApplicationContext());

        btnOK = findViewById(R.id.btn_submit);
        btnOK.setOnClickListener(this);
        tvNama = findViewById(R.id.et_nama);
        tvAlamat = findViewById(R.id.et_alamat);
        tvSiswa = findViewById(R.id.et_siswa);
        tvGuru = findViewById(R.id.et_guru);
        recyclerView = findViewById(R.id.rc_main);

        /*FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);*/

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        presenter = new MainPresenter(this);

        presenter.readData(appDatabase);
    }

    @Override
    public void successAdd() {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
        presenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this, "Berhasil menghapus data", Toast.LENGTH_SHORT).show();
        presenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        tvNama.setText("");
        tvAlamat.setText("");
        tvSiswa.setText("");
        tvGuru.setText("");
        btnOK.setText("submit");
    }

    @Override
    public void getData(List<DataSekolah> list) {
        adapter = new MainAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void editData(DataSekolah item) {
        tvNama.setText(item.getNamasekolah());
        tvAlamat.setText(item.getAlamat());
        tvSiswa.setText(item.getJumlahsiswa());
        tvGuru.setText(item.getJumlahguru());
        id = item.getId();
        edit = true;
        btnOK.setText("Update");
    }

    @Override
    public void deleteData(final DataSekolah item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        presenter.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onClick(View view) {
        if(view == btnOK){
            if(tvNama.getText().toString().equals("") || tvAlamat.getText().toString().equals("") || tvSiswa.getText().toString().equals("") || tvGuru.getText().toString().equals("")) {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            }

                if(!edit) presenter.insertData(tvNama.getText().toString(), tvAlamat.getText().toString(), tvSiswa.getText().toString(), tvGuru.getText().toString(), appDatabase);
                else{
                    presenter.editData(tvNama.getText().toString(), tvAlamat.getText().toString(), tvSiswa.getText().toString(), tvGuru.getText().toString(), id, appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
}

