package com.example.spppay.petugas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spppay.R;
import com.example.spppay.admin.MainActivityAdmin;
import com.example.spppay.admin.PaymentSuccess;

public class PaymentSuccessPetugas extends AppCompatActivity {

    Button btnBeranda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success_petugas);

        btnBeranda = findViewById(R.id.btn_to_beranda_petugas);

        btnBeranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentSuccessPetugas.this, MainActivityPetugas.class);
                startActivity(intent);
            }
        });
    }
}
