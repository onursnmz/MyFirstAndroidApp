package com.example.a190508356_onur_sonmez;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class kiyafet {
    String renk;
    String desen;
    String puan;
    String fiyat;
    Bitmap foto;
    public static ArrayList<kiyafet> Kiyafet_test = new ArrayList<>();

    public kiyafet(String renk, String desen, String puan, String fiyat, Bitmap foto) {
        this.renk = renk;
        this.desen = desen;
        this.puan = puan;
        this.fiyat = fiyat;
        this.foto = foto;
    }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    public String getDesen() {
        return desen;
    }

    public void setDesen(String desen) {
        this.desen = desen;
    }

    public String getPuan() {
        return puan;
    }

    public void setPuan(String puan) {
        this.puan = puan;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public static ArrayList<kiyafet> getKiyafet_test() {
        return Kiyafet_test;
    }

    public static void setKiyafet_test(ArrayList<kiyafet> kiyafet_test) {
        Kiyafet_test = kiyafet_test;
    }
}
