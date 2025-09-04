package com.example.hospitalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FindHospitalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_hospital);

        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//        String username = sharedPreferences.getString("username","").toString();
//        Toast.makeText(getApplicationContext(),"Welcome"+username,Toast.LENGTH_SHORT).show();

        CardView exit = findViewById(R.id.cardFHBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(FindHospitalActivity.this,HomeActivity.class));
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        CardView hospital1 = findViewById(R.id.cardFH1);
        hospital1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindHospitalActivity.this,FindDoctorActivity.class));
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        CardView hospital2 = findViewById(R.id.cardFH2);
        hospital2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindHospitalActivity.this,FindDoctorActivity.class));
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        CardView hospital3 = findViewById(R.id.cardFH3);
        hospital3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindHospitalActivity.this,FindDoctorActivity.class));
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        CardView hospital4 = findViewById(R.id.cardFH4);
        hospital4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindHospitalActivity.this, FindDoctorActivity.class));
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        CardView hospital5 = findViewById(R.id.cardFH5);
        hospital5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindHospitalActivity.this, FindDoctorActivity.class));
            }
        });
    }
}