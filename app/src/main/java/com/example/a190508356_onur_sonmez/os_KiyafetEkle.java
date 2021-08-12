package com.example.a190508356_onur_sonmez;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class os_KiyafetEkle extends AppCompatActivity {
    Spinner os_sp_desen;
    EditText os_et_fiyat;
    int request_imageopen=0;
    CheckBox os_cby,os_cbs,os_cbm,os_cbk;
    RadioButton os_rb_1,os_rb_2,os_rb_3,os_rb_4,os_rb_5;
    String os_renk,os_desen,os_puan,os_fiyat;
    Bitmap bitmap;
    ImageView os_userphoto;
    Button os_bt_pchoice,os_bt_kaydet,os_bt_geridon;
    private veritabani v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os__kiyafet_ekle);
        callInputs();
        SpinnerSettings();
        v1 = new veritabani(this);

        os_bt_pchoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, request_imageopen);
            }
        });
        os_bt_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpinner();
                cb_getText();
                rb_getText();
                SQLiteDatabase db = v1.getWritableDatabase();
                ContentValues data = new ContentValues();
                byte[] photo = BytetoArray();
                data.put("renk",os_renk);
                data.put("desen",os_desen);
                data.put("fiyat",os_fiyat);
                data.put("puan",os_puan);
                data.put("foto",photo);
                db.insertOrThrow("Kiyafet",null,data);
                kiyafet q = new kiyafet(os_renk,os_desen,os_puan,os_fiyat,bitmap);
                kiyafet.Kiyafet_test.add(q);
                Toast.makeText(getApplicationContext(),"Kıyafet eklendi!", Toast.LENGTH_SHORT).show();
            }
        });
        os_bt_geridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(os_KiyafetEkle.this, os_MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void callInputs(){
        os_sp_desen = findViewById(R.id.os_sp_desen);
        os_et_fiyat = findViewById(R.id.os_et_fiyat);
        os_bt_pchoice = findViewById(R.id.os_bt_pchoice);
        os_userphoto = findViewById(R.id.os_u_photo);
        os_bt_kaydet = findViewById(R.id.os_bt_kaydet);
        os_cby = findViewById(R.id.os_cby);
        os_cbk = findViewById(R.id.os_cbk) ;
        os_cbm = findViewById(R.id.os_cbm);
        os_cbs = findViewById(R.id.os_cbs);
        os_rb_1 = findViewById(R.id.os_rb_1);
        os_rb_2 = findViewById(R.id.os_rb_2);
        os_rb_3 = findViewById(R.id.os_rb_3);
        os_rb_4 = findViewById(R.id.os_rb_4);
        os_rb_5 = findViewById(R.id.os_rb_5);
        os_bt_geridon = findViewById(R.id.os_bt_geridon_k);
    }
    public void cb_getText(){
        os_fiyat = os_et_fiyat.getText().toString();
        if(os_cbs.isChecked()){
            os_renk = os_cbs.getText().toString();
        }else if(os_cbm.isChecked()){
            os_renk = os_cbm.getText().toString();
        }else if(os_cbk.isChecked()){
            os_renk = os_cbk.getText().toString();
        }else{
            os_renk = os_cby.getText().toString();
        }

    }
    public void rb_getText(){
        if(os_rb_1.isChecked()){
            os_puan = os_rb_1.getText().toString();
        }else if(os_rb_2.isChecked()){
            os_puan = os_rb_2.getText().toString();
        }else if(os_rb_3.isChecked()){
            os_puan = os_rb_3.getText().toString();
        }else if(os_rb_4.isChecked()){
            os_puan = os_rb_4.getText().toString();
        }else{
            os_puan = os_rb_5.getText().toString();
        }
    }
    public void SpinnerSettings(){
        String[] desen = new String[]{"Kareli", "Düz", "Çizgili"};
        ArrayAdapter<String> adapter_desen = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,desen); os_sp_desen.setAdapter(adapter_desen);
    }
    public void getSpinner(){
        os_desen = os_sp_desen.getSelectedItem().toString();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == request_imageopen && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try{
                if(Build.VERSION.SDK_INT >= 28){
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),uri);
                    bitmap = ImageDecoder.decodeBitmap(source);
                    os_userphoto.setImageBitmap(bitmap);
                }else{
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                    os_userphoto.setImageBitmap(bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public byte[] BytetoArray(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }
}