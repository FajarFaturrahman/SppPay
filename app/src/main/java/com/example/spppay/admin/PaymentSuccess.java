package com.example.spppay.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spppay.R;

public class PaymentSuccess extends AppCompatActivity {

    Button btnBeranda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_succes);

        btnBeranda = findViewById(R.id.btn_to_beranda);

        btnBeranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentSuccess.this, MainActivityAdmin.class);
                startActivity(intent);
            }
        });
    }
}
