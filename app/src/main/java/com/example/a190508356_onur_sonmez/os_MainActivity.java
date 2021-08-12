package com.example.a190508356_onur_sonmez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class os_MainActivity extends AppCompatActivity {

    Button os_kiyafetekle,os_kiyafetlerim;
    private veritabani v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        os_kiyafetekle = findViewById(R.id.os_bt_kiyafetekle);
        os_kiyafetlerim = findViewById(R.id.os_bt_kiyafetlerim);
        v1 = new veritabani(this);
        /*SQLiteDatabase db = v1.getReadableDatabase();
        v1.onUpgrade(db,1,1);*/
        os_kiyafetekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(os_MainActivity.this, os_KiyafetEkle.class);
                startActivity(intent);
                finish();
            }
        });
        os_kiyafetlerim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(os_MainActivity.this, os_kiyafetlerim.class);
                startActivity(intent);
                finish();
            }
        });
    }
}