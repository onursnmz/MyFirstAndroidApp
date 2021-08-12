package com.example.a190508356_onur_sonmez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class os_kiyafetlerim extends AppCompatActivity {
    Button os_bt_geridon;
    private RecyclerView Recycler;
    private os_RecycleAdapterKiyafetlerim adapter;
    private veritabani v1;
    String renk,desen,fiyat,puan;
    byte[] f;
    Bitmap bitmap;
    private ArrayList<kiyafet> Kiyafetler = new ArrayList<kiyafet>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os_kiyafetlerim);
        v1 = new veritabani(this);
        getInput();
        adapter.notifyDataSetChanged();
        showdata(readdata());
        os_bt_geridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(os_kiyafetlerim.this, os_MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void getInput(){
        Recycler = (RecyclerView) findViewById(R.id.os_recyclerview);
        adapter = new os_RecycleAdapterKiyafetlerim(Kiyafetler,os_kiyafetlerim.this);
        Recycler.setAdapter(adapter);
        Recycler.setLayoutManager(new LinearLayoutManager(this));
        os_bt_geridon = findViewById(R.id.os_bt_geridon);
    }
    private String[] columns={"renk","desen","puan","fiyat","foto"};
    private Cursor readdata(){
        SQLiteDatabase db = v1.getReadableDatabase();
        Cursor read = db.query("Kiyafet",columns,null,null,null,null,null);
        return read;
    }
    private void showdata(Cursor show){
        while( show.moveToNext()) {
            renk = show.getString(show.getColumnIndex("renk"));
            desen = show.getString(show.getColumnIndex("desen"));
            puan = show.getString(show.getColumnIndex("puan"));
            fiyat = show.getString(show.getColumnIndex("fiyat"));
            f = show.getBlob(show.getColumnIndex("foto"));
            bitmap = BitmapFactory.decodeByteArray(f,0,f.length);
            kiyafet q = new kiyafet(renk,desen,puan,fiyat,bitmap);
            Kiyafetler.add(q);
            kiyafet.Kiyafet_test.add(q);
        }
    }
}