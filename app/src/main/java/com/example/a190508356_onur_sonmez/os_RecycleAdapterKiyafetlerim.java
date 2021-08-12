package com.example.a190508356_onur_sonmez;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class os_RecycleAdapterKiyafetlerim extends RecyclerView.Adapter<os_RecycleAdapterKiyafetlerim.MyViewHolder>{
    Context context;
    private ArrayList<kiyafet> kiyafetler;
    public os_RecycleAdapterKiyafetlerim(ArrayList<kiyafet> kiyafetler, Context context) {
        this.kiyafetler = kiyafetler;
        this.context = context;
    }
    @NonNull
    @Override
    public os_RecycleAdapterKiyafetlerim.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.os_kiyafet_lister,parent,false);
        return new os_RecycleAdapterKiyafetlerim.MyViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull os_RecycleAdapterKiyafetlerim.MyViewHolder holder, int position) {

        holder.os_foto.setImageBitmap(kiyafetler.get(position).getFoto());
        holder.os_bt_bilgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Kıyafet Bilgileri")
                        .setMessage("Renk: "+
                                kiyafetler.get(position).getRenk()+"\nDesen: "+
                                kiyafetler.get(position).getDesen()+"\nPuan: "+
                                kiyafetler.get(position).getPuan()+"\nFiyat: "+
                                kiyafetler.get(position).getFiyat())
                        .setNegativeButton("Geri", null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return kiyafetler.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Button os_bt_bilgi;
        ImageView os_foto;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            os_foto = itemView.findViewById(R.id.os_u_photo_k);
            os_bt_bilgi = itemView.findViewById(R.id.os_bt_bilgi);
        }
    }
}
