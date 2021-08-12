package com.example.a190508356_onur_sonmez;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class veritabani extends SQLiteOpenHelper {

    private static final String androidprogramming = "os_final.sqlite3";
    private static final int SURUM = 1;
    public veritabani(Context c){
        super(c, androidprogramming,null,SURUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Kiyafet (kiyafet_id INTEGER PRIMARY KEY AUTOINCREMENT,renk Text, desen Text, puan Text, fiyat Text, foto Blob);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Kiyafet");
        onCreate(db);
    }
}