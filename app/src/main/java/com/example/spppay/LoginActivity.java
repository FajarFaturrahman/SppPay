package com.example.spppay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView btnRole;
    private EditText edtNameNis;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtNameNis  = findViewById(R.id.nis_and_username);
        edtPassword = findViewById(R.id.password);
        btnRole     = findViewById(R.id.btn_role);
        btnLogin    = findViewById(R.id.Login);
    }
}
