package com.example.vishal.whether_app_for_android;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vishal.whether_app_for_android.whether_data.Channel;

import java.util.ArrayList;
import java.util.LinkedList;

public class Drawer_recycler_delet_Adapter extends RecyclerView.Adapter<Drawer_recycler_delet_Adapter.Myholder> {

    LinkedList<Channel> arrayList;
    ArrayList<Integer> image;
    private Callback callback;

    public Drawer_recycler_delet_Adapter(LinkedList<Channel> arrayList,ArrayList<Integer> image) {
        this.arrayList = arrayList;
        this.image = image;
    }

    @NonNull
    @Override
    public Drawer_recycler_delet_Adapter.Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myinflater = LayoutInflater.from(parent.getContext());
        View myview = myinflater.inflate(R.layout.raw_delet_drawable,parent,false);
        return new Myholder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull Drawer_recycler_delet_Adapter.Myholder holder, final int position) {
        holder.t1.setText(String.valueOf(arrayList.get(position).getItem().getCondition().getTemprature())+"\u00B0");
        holder.t2.setText(arrayList.get(position).getLocation().getCity());
        holder.i1.setImageResource(image.get(position));
        holder.i2.setImageResource(R.drawable.minus);
        holder.i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback != null){
                    callback.onClicklistner(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void onClicklistner(int position);
    }

    public class Myholder extends RecyclerView.ViewHolder {
        TextView t1,t2;
        ImageView i1,i2;

        public Myholder(View itemView) {
            super(itemView);

            t1 = (TextView)itemView.findViewById(R.id.temp_drawer);
            t2 = (TextView)itemView.findViewById(R.id.city_drawer);
            i1 = (ImageView)itemView.findViewById(R.id.icon_drawer);
            i2 = (ImageView)itemView.findViewById(R.id.minus_icon);
        }
    }
}
