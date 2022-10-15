package com.example.petmed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AdminTransaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaction);
        getSupportActionBar().hide();
    }
}