package com.example.day02_homework1.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.example.day02_homework1.Beans.FuliBean;
import com.example.day02_homework1.R;

import java.util.ArrayList;

public class FuliAdaper extends RecyclerView.Adapter<FuliAdaper.ViewHolder>{
    public ArrayList<FuliBean.ResultsBean> list;
    private Context context;

    public FuliAdaper(ArrayList<FuliBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.fuli_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getUrl()).into(holder.myimg);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v){
                a.setonLongClick(v,position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView myimg;
        public ViewHolder(View itemView) {
            super(itemView);
            myimg=itemView.findViewById(R.id.myimg);
        }
    }
    public A a;
    public void setA(A a) {
        this.a = a;
    }
    public interface A{
        void setonLongClick(View v, int position);
    }
}
