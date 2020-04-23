package com.example.spppay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.spppay.admin.MainActivityAdmin;
import com.example.spppay.data.factory.AppDatabase;
import com.example.spppay.model.Petugas;
import com.example.spppay.model.Siswa;
import com.example.spppay.petugas.MainActivityPetugas;
import com.example.spppay.siswa.MainActivitySiswa;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView btnRole;
    private EditText edtNameNis;
    private EditText edtPassword;

    private Context context;
    private Petugas petugas;
    private Siswa siswa;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtNameNis  = findViewById(R.id.nis_and_username);
        edtPassword = findViewById(R.id.password);
        btnLogin    = findViewById(R.id.Login);

        initDbsiswa();
        initDbpetugas();
        initStetho();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
            }
        });
    }

    private void getEdt(){

        siswa = db.siswaDAO().siswaLogin(edtNameNis.getText().toString().trim(), edtPassword.getText().toString().trim());

        petugas = db.petugasDAO().petugaslogin(edtNameNis.getText().toString().trim(), edtPassword.getText().toString().trim());

    }

    private void checkData(){
        getEdt();
        if(siswa!= null) {
            Intent intent = new Intent(LoginActivity.this, MainActivitySiswa.class);
            intent.putExtra("profileSiswa", siswa);
            startActivity(intent);
        } else if(petugas!=null){
                Intent intent = new Intent(LoginActivity.this, MainActivityAdmin.class);
                intent.putExtra("profileAdmin", petugas);
                startActivity(intent);
        }else if (edtNameNis.getText().toString().trim().equals("admin") &&
                edtPassword.getText().toString().trim().equals("admin")) {
            Intent intent = new Intent(LoginActivity.this, MainActivityAdmin.class);
            startActivity(intent);
        }else {
            Toasty.error(LoginActivity.this, "Masukkan data dengan benar").show();
        }
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

    }

    private void initDbsiswa() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "siswadb").allowMainThreadQueries().build();
    }

    private void initDbpetugas() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "petugasdb").allowMainThreadQueries().build();
    }
}
