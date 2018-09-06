package com.example.vishal.whether_app_for_android;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class Forcast_adapter extends RecyclerView.Adapter<Forcast_adapter.Myholder> {


    LinkedList<String> day,date,cond;
    LinkedList<Integer> up,condlogo,down;

    public Forcast_adapter(LinkedList<String> day, LinkedList<String> date, LinkedList<String> cond, LinkedList<Integer> condlogo, LinkedList<Integer> up, LinkedList<Integer> down) {
        this.day = day;
        this.date = date;
        this.cond = cond;
        this.condlogo = condlogo;
        this.up = up;
        this.down = down;
    }

    @NonNull
    @Override
    public Forcast_adapter.Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myinflater = LayoutInflater.from(parent.getContext());
        View myview = myinflater.inflate(R.layout.row_layout,parent,false);
        return new Myholder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull Forcast_adapter.Myholder holder, int position) {
        holder.t1.setText(day.get(position));
        holder.t2.setText(date.get(position));
        holder.t3.setText(cond.get(position));
        holder.t4.setText(String.valueOf(down.get(position))+"\u00B0");
        holder.t5.setText(String.valueOf(up.get(position))+"\u00B0");

        holder.imageView.setImageResource(condlogo.get(position));
    }

    @Override
    public int getItemCount() {
        return day.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4,t5;
        ImageView imageView;
        public Myholder(View itemView) {
            super(itemView);

            t1 = (TextView)itemView.findViewById(R.id.day);
            t2 = (TextView)itemView.findViewById(R.id.date);
            t3 = (TextView)itemView.findViewById(R.id.cond);
            t4 = (TextView)itemView.findViewById(R.id.down);
            t5 = (TextView)itemView.findViewById(R.id.up);

            imageView = (ImageView)itemView.findViewById(R.id.logo);
        }
    }
}
