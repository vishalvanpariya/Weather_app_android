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

public class Drawer_recycler_Adapter extends RecyclerView.Adapter<Drawer_recycler_Adapter.Myholder> {

    LinkedList<Channel> arrayList;
    ArrayList<Integer> image;

    public Drawer_recycler_Adapter(LinkedList<Channel> arrayList,ArrayList<Integer> image) {
        this.arrayList = arrayList;
        this.image = image;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myinflater = LayoutInflater.from(parent.getContext());
        View myview = myinflater.inflate(R.layout.row_drawable,parent,false);
        return new Myholder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        holder.t1.setText(String.valueOf(arrayList.get(position).getItem().getCondition().getTemprature())+"\u00B0");
        holder.t2.setText(arrayList.get(position).getLocation().getCity());
        holder.i1.setImageResource(image.get(position));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {

        TextView t1,t2;
        ImageView i1;

        public Myholder(View itemView) {
            super(itemView);

            t1 = (TextView)itemView.findViewById(R.id.temp_drawer);
            t2 = (TextView)itemView.findViewById(R.id.city_drawer);
            i1 = (ImageView)itemView.findViewById(R.id.icon_drawer);
        }
    }
}
